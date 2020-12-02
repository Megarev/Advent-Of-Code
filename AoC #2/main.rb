#AoC 2.1

#Read lines from text file
lines = IO.readlines("input.txt")
$index = -1
$n = 0

# Iterate over all lines in text file
while ($index += 1) < lines.length
  # Lines -> Words by whitespace
  words = lines[$index].split()
  # Range -> Values by '-' delimter
  values_range = words[0].split('-')

  # Get the min and max range values for the character under test
  min_char = values_range[0].to_i
  max_char = values_range[1].to_i

  # Test character
  char = words[1][0]
  n_char = 0

  password = words[2]
  # Compare each character in password with test character
  # If character is the test character, add it to n_char
  password.split('').each { |a|
    n_char += char == a ? 1 : 0
  }

  if n_char >= min_char && n_char <= max_char
    $n += 1
  end
end
puts "Correct passwords(Range based): #$n"

#AoC 2.2
$index = -1
$n = 0

while ($index += 1) < lines.length
  words = lines[$index].split()
  pos = words[0].split('-')

  # Set the range values
  a = pos[0].to_i - 1
  b = pos[1].to_i - 1

  # Test character
  char = words[1][0]
  password = words[2]
  
  # if the same character is not present at the two positions
  unless (password[a] == char && password[b] == char)
    is_char = (password[a] == char) | (password[b] == char)
    $n += is_char ? 1 : 0
  end
end
puts "Correct passwords(Position based): #$n"