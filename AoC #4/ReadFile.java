import java.io.*;
import java.util.*;

public class ReadFile {
  public ReadFile(String name) { 
    lines = new ArrayList<String>(); 
    filename = name;
  }

  public void ReadByLine() {
    try {
      File data = new File(filename);
      FileReader reader = new FileReader(data);
      BufferedReader buffer_reader = new BufferedReader(reader);
      StringBuffer string = new StringBuffer();

      String line;

      while ((line = buffer_reader.readLine()) != null) {
        if (line.length() == 0) {
          lines.add(string.toString());
          string.setLength(0);
        } else {
          string.append(line + " ");
        }
      }

      // Add bottommost line
      lines.add(string.toString());

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> GetLines() { return lines; }
  private ArrayList<String> lines;
  private String filename;
}