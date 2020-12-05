import math

# Read from file
file = open('input.txt', 'r')
lines_raw_data = file.readlines()
lines = []
for line in lines_raw_data:
  lines.append(line.strip())

# Convenient Structure
class SeatData:
  def __init__(self, value_range):
    self.min_value = 0
    self.max_value = 0
    self.lanes = []
    self.min_reset_value = value_range[0]
    self.max_reset_value = value_range[1]

  def Reset(self):
    self.min_value = self.min_reset_value
    self.max_value = self.max_reset_value

  def Push(self, new_value):
    self.lanes.append(new_value)

# Check binary arrangement
rows = SeatData([0, 127])
cols = SeatData([0, 7])
for line in lines:
  rows.Reset()
  for i in range(0, len(line) - 3):
    if line[i] == 'B':
      rows.min_value = math.ceil((rows.min_value + rows.max_value) / 2)
      row_value = max(rows.min_value, rows.max_value)
    elif line[i] == 'F':
      rows.max_value = math.floor((rows.min_value + rows.max_value) / 2)
      row_value = min(rows.min_value, rows.max_value)
    
  rows.Push(row_value)

  cols.Reset()
  for i in range(7, len(line)):
    if line[i] == 'L':
      cols.max_value = math.floor((cols.min_value + cols.max_value) / 2)
      col_value = min(cols.min_value, cols.max_value)
    elif line[i] == 'R':
      cols.min_value = math.ceil((cols.min_value + cols.max_value) / 2)
      col_value = max(cols.min_value, cols.max_value)
    
  cols.Push(col_value)

seat_ids = []
for i in range(0, len(rows.lanes)):
  seat_ids.append(rows.lanes[i] * 8 + cols.lanes[i])

print("seat_id: {}".format(max(seat_ids)))
seat_ids.sort()

for i in range(1, len(seat_ids) - 1):
  try:
    seat_ids.index(seat_ids[i] + 1)
    seat_ids.index(seat_ids[i] - 1)
  except ValueError:
    print("SeatID: {}".format(seat_ids[i] - 1 | seat_ids[i] + 1))
    break