use std::fs;

fn main() {

    // Read file as string
    let data = fs::read_to_string("file.txt").expect("Unable to read file");
    // Read values into a vector and parse them to ints
    let mut values: Vec<i32> = data.split_whitespace().filter_map(|s| s.parse().ok()).collect(); 
    // Sorting values for convenience
    values.sort();
    
    let answer = 2020;
    
    // For two values
    for i in 0..values.len() {
        // If the (answer - value) happens to be greater than the original value, then look at all values to the right of it
        if answer - values[i] >= values[i] {
            // As the vector is sorted, the larger values will be to the right, so look for larger values to satisfy the equation
            for j in i..values.len() {
                if values[i] + values[j] == answer {
                   println!("For two values");
                   println!("Values that sum to {} are {} and {}", answer, values[i], values[j]);
                   println!("Answer: {}", values[i] * values[j]);
                }
            }
        }
    }

    // For three values
    for i in 0..values.len() {
        // If the (answer - value) happens to be greater than the original value, then look at all values to the right of it
        if answer - values[i] >= values[i] {
            // For every value at ith position, if (answer - value) > value, then look for larger values to right of ith position
            for j in i..values.len() {
                // For every value at jth position, if (answer - value) > value, then look for larger values to right of jth position
                if answer - values[j] >= values[i] {
                    // For every value at kth position, find a value that potentially satifies the equation for finding the answer
                    for k in j..values.len() {
                        if values[i] + values[j] + values[k] == answer {
                            println!("For three values");
                            println!("Values that sum to {} are {}, {} and {}", answer, values[i], values[j], values[k]);
                            println!("Answer: {}", values[i] * values[j] * values[k]);
                        }
                    }
                }
            }
        }
    }
}