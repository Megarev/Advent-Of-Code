file = io.open('bootcode.txt')
io.input(file)

raw_input = io.read('*all') --Read entire file
instruction = {} --Instruction names
value = {} --Values associated with instruction names

for str in raw_input:gmatch('[^\n]+') do
  --Instruction--
  command = str:sub(0, 3)
  table.insert(instruction, command)

  --Key--
  key = str:sub(5, #str)
  table.insert(value, tonumber(key))
end

function FindValueAtLoop(commands)
  local i = 0
  local accumulator = 0
  local is_done = true

  states = {}

  while (i < #commands) do
    i = i + 1
    -- If instruction repeat, there is a loop
    if (states[i] == true) then is_done = false break end

    if(commands[i] == 'jmp') then
      i = i + value[i] - 1
    else if (commands[i] == 'acc') then
      accumulator = accumulator + value[i]
    end

    -- Set current state as visited
    states[i] = true
    end
  end

  return accumulator, is_done
end

function GetJmpPositions()
  potential_jmp_positions = {}
  for i = 0, #instruction do
    if (instruction[i] == 'jmp' and value[i] < 0) then
      table.insert(potential_jmp_positions, i)
    end
  end
  return potential_jmp_positions
end

function GetNopPositions() 
  potential_nop_positions = {}
  for i = 0, #instruction do
    if (instruction[i] == 'nop' and value[i] < 0) then
      table.insert(potential_nop_positions, i)
    end
  end
  return potential_nop_positions
end

function SolveWithChangingInstructions()

  -- Changing jmp -> nop
  potential_jmp_positions = GetJmpPositions()
  for i = 1, #potential_jmp_positions do
    instruction_cpy = instruction
    instruction_cpy[potential_jmp_positions[i]] = 'nop'

    new_value, is_done = FindValueAtLoop(instruction_cpy)

    if (is_done) then return new_value end
  end

  -- Changing nop -> jmp
  potential_nop_positions = GetNopPositions()
  for i = 1, #potential_nop_positions do
    instruction_cpy = instruction
    instruction_cpy[potential_nop_positions[i]] = 'nop'

    new_value, is_done = FindValueAtLoop(instruction_cpy)

    if (is_done) then return new_value end
  end

  return 1
end

v, _ = FindValueAtLoop(instruction)
print('Accumulator value (AoC 8.1):', v)
print('Accumulator value (AoC 8.2):', SolveWithChangingInstructions())