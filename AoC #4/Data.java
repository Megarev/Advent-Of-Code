import java.util.regex.*;

public class Data {
  public Data() {
    // Intialization
    byr = -1;
    iyr = -1;
    eyr = -1;
    cid = -1;
    pid = "";
    hcl = "";
    ecl = "";
    hgt = "";
  }

  private boolean GetECLType() {
    String[] colors = { "amb", "blu", "brn", "gry", "grn", "hzl", "oth" };
    for (String s : colors) {
      if (s.equals(ecl)) { return true; }
    }
    return false;
  };

  public boolean IsHeightInRange() {
    if (hgt.length() == 0) return false;
    String unit = hgt.substring(hgt.length() - 2, hgt.length());
    if (!Pattern.matches("\\D*", unit)) return false;

    String height = hgt.substring(0, hgt.length() - 2);
    int h = Integer.parseInt(height);

    if (unit.equals("cm")) {
      return (h >= 150 && h <= 193);
    } else if (unit.equals("in")) {
      return (h >= 59 && h <= 76);
    }

    return false;
  }

  public boolean GetHCLType() {
    if (hcl.charAt(0) != '#' || hcl.length() != 7) return false;
  
    String chars = "0123456789abcdef";

    for (int i = 1; i < hcl.length(); i++) {
      boolean is_char = false;
      for (char c : chars.toCharArray()) {
        if (hcl.charAt(i) == c) {
          is_char = true;
          break;
        }
      }
      if (!is_char) { 
        System.out.println("No color");
        return false;
      }
    }

    return true;
  }

  // AoC 4.1
  public int IsDataPresent() {
    return (
      byr > 0 &&
      iyr > 0 &&
      eyr > 0 &&
      !pid.equals("") &&
      !hcl.equals("") &&
      !ecl.equals("") &&
      !hgt.equals("")
      //cid > 0
    ) ? 1 : 0;
  }

  // AoC 4.2
  public int CheckData() {
    return (
      (byr >= 1920 && byr <= 2002) &&
      (iyr >= 2010 && iyr <= 2020) &&
      (eyr >= 2020 && eyr <= 2030) &&
      (IsHeightInRange()) &&
      (GetHCLType()) &&
      (GetECLType()) &&
      (pid.length() == 9)
      // cid > 0
    ) ? 1 : 0;
  }

  public int byr, iyr, eyr;
  public String pid, hcl, ecl, hgt;
  public int cid;
}