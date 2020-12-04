class Main {
  public static void main(String[] args) {
    ReadFile read_file = new ReadFile("data.txt");
    read_file.ReadByLine();

    DataReader data_reader = new DataReader();
    data_reader.Read(read_file.GetLines());
    
    // AoC 4.1
    data_reader.CalculateOkPassports();
    // AoC 4.2
    data_reader.CalculateOkPassports2();
  }
}