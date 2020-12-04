import java.util.*;

public class DataReader {
  public DataReader() { data_array = new ArrayList<Data>(); }

  private void IntrepretData(String[] key_values, Data data) {
    if (key_values.length != 2) return;

    if (key_values[0].equals("byr")) data.byr = Integer.parseInt(key_values[1]);
    if (key_values[0].equals("iyr")) data.iyr = Integer.parseInt(key_values[1]);
    if (key_values[0].equals("eyr")) data.eyr = Integer.parseInt(key_values[1]);
    if (key_values[0].equals("hgt")) data.hgt = key_values[1];
    if (key_values[0].equals("hcl")) data.hcl = key_values[1];
    if (key_values[0].equals("ecl")) data.ecl = key_values[1];
    if (key_values[0].equals("pid")) data.pid = key_values[1];
    if (key_values[0].equals("cid")) data.cid = Integer.parseInt(key_values[1]);
  }

  public void Read(ArrayList<String> raw_data) {
    ArrayList<String[]> key_value = new ArrayList<String[]>();
    for (String s : raw_data) {
      String[] keyvalue_pair = s.split("\\s+");
      key_value.add(keyvalue_pair);
    }

    for (String[] pair : key_value) {
      Data data = new Data();
      for (String s : pair) {
        String[] key_values = s.split("\\:+");
        IntrepretData(key_values, data);
      }
      data_array.add(data);
    }
  }

  public void GetIsHeight() {
    for (Data d : data_array) {
      d.IsHeightInRange();
      d.GetHCLType();
    }
  }

  public void CalculateOkPassports() {
    int n = 0;
    
    for (Data d : data_array) {
      n += d.IsDataPresent();
    }

    System.out.println("n_passports: " + n);
  }

  public void CalculateOkPassports2() {
    int n = 0;

    for (Data d : data_array) {
      n += d.CheckData();
    }

    System.out.println("n_passports2: " + n);
  }

  private ArrayList<Data> data_array;
}