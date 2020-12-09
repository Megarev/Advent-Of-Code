using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

class MainClass {

  private static List<long> input_values;
  private static long value_in_list = 0;
  private static List<long[]> contiguous_ranges;

  private static void SolveA(int position, int size) {
    int length = position + size;
    if (length + 1 > input_values.Count) return;

    bool is_numeral_in_list = false;

    List<long> range = new List<long>();

    for (int i = position; i < position + size; i++) {
      for (int j = position; j < position + size; j++) {
        if (i == j) continue;

        long sum = input_values[i] + input_values[j];
        long numeral = input_values[position + size];

        if (sum == numeral) {
          is_numeral_in_list = true;
          break;
        }
      }
      range.Add(input_values[i]);
    }

    if (!is_numeral_in_list) {
      value_in_list = input_values[position + size];
      return;
    }

    contiguous_ranges.Add(range.ToArray());

    SolveA(position + 1, size);
  }

  private static void SolveB() {
    foreach(var a in contiguous_ranges) {
      int contiguous_sum = 0;
      List<int> range_values = new List<int>();

      foreach(int b in a) {
        contiguous_sum += b;
        range_values.Add(b);

        if (contiguous_sum == value_in_list) {
          int min_value = range_values.Min();
          int max_value = range_values.Max();

          Console.WriteLine("Sum: " + (min_value + max_value));
        }
      }
    }
  }

  public static void Main (string[] args) {
    
    input_values = new List<long>();
    contiguous_ranges = new List<long[]>();

    foreach (string line in File.ReadLines("input.txt")) {
      var value = Convert.ToInt64(line);
      input_values.Add(value);
    }

    SolveA(0, 25);
    Console.WriteLine("Value: " + value_in_list);
    SolveB();

  }
}