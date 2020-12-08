#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <map>
#include <sstream>

struct Bag {
    std::string name;
    std::map<std::string, int> bags; // Sub bags

    void SetName(const std::string& bag_name) { name = bag_name; }

    void Add(const std::string& sub_bag_name, int bag_value) {
        bags.insert(std::make_pair(sub_bag_name, bag_value));
    }

    int GetTotalBags() const {
        int sum = 0;
        for (auto& bag : bags) {
            sum += bag.second;
        }
        return sum;
    }

    void Print() const {
        std::cout << name << std::endl;
        for (auto it = bags.begin(); it != bags.end(); it++) {
            std::cout << it->first << ": " << it->second << ", ";
        }
        std::cout << std::endl;
    }
};

std::vector<Bag> ReadData(const std::string& filename) {
    std::ifstream reader(filename);
    std::vector<Bag> bags;

    if (reader.is_open()) {

        std::string line;
        while (std::getline(reader, line)) {
            const std::string& word = "contain";
            line = line.replace(line.find(word), word.size(), ","); // Take out the word 'contain'
            line.pop_back(); // Take out the dot from the back
            line.erase(std::remove(line.begin(), line.end(), ' '), line.end()); // No whitespaces

            std::stringstream ss(line);
            std::string s;
            
            std::vector<std::string> bag_data;
            while (std::getline(ss, s, ',')) {
                if (s.back() != 's') s.push_back('s');
                bag_data.push_back(s);
            }
            
            Bag b;
            b.SetName(bag_data[0]);
            for (int i = 1; i < (int)bag_data.size(); i++) {
                if (isdigit(bag_data[i][0])) {
                    b.Add(bag_data[i].substr(1), bag_data[i][0] - '0');
                } else {
                    b.Add(bag_data[i], 0);
                }
            }
            bags.push_back(b);
        }
        reader.close();
    }

    return bags;
}

bool is_loop = true;
int n_bags = 0;
void SolveA(const std::string& name, const std::vector<Bag>& bags) {

    if (!is_loop) return;

    for (auto& bag : bags) {
        if (bag.name == name) {
            if (bag.name == "shinygoldbags") {
                n_bags++;
                is_loop = false;
                return;
            }

            for (auto& sub_bag : bag.bags) {
                SolveA(sub_bag.first, bags);
            }
        }
    }
}


int SolveB(const std::string& name, const std::vector<Bag>& bags) {
    int n_bag_2 = 0;

    for (auto& bag : bags) {
        if (bag.name == name) {
            for (auto& sub_bag : bag.bags) {
                n_bag_2 += sub_bag.second;
                n_bag_2 += sub_bag.second * SolveB(sub_bag.first, bags);
            }
        }
    }

    return n_bag_2;
}

int main() {
    
    auto bags = ReadData("input.txt");
    
    for (auto& bag : bags) {
        is_loop = true;
        SolveA(bag.name, bags);
    }
    std::cout << "n_bags(A): " << (n_bags - 1) << std::endl;

    std::cout << "n_bags(B): " << SolveB("shinygoldbags", bags) << std::endl;

    return 0;
}
