var fs = require('fs')

// Read data line by line from text file
let raw_data = fs.readFileSync('data.txt')
let raw_data_str = raw_data.toString()
raw_data_str += '\n'
let raw_input = raw_data_str.split('\n')

function ReadData(type) {
  let data = [], temp = []

  // Placing questions of same group in an array
  for (let input of raw_input) {
    if (input.length == 0) {
      data.push(temp)
      temp = []
      continue
    } 

    temp.push(input)
  }

  if (type == 1) return data

  // Taking out duplicate elements
  let data_set = []
  for (let i = 0; i < data.length; i++) {
    data[i] = data[i].join().replace(/,/g, '')
    let s = Array.from(new Set(data[i].split(''))).join().replace(/,/g, '')
    data_set.push(s)
  }
  return data_set
}

// AoC 6.1
function SolveA() {
  let data_set = ReadData(0)
  let sum = 0
  for (let data of data_set) {
    sum += data.length
  }
  console.log("SumA: " + sum)
}

// AoC 6.2
function SolveB() {
  let sum = 0
  let data_set = ReadData(1)
  

  // Storing each string as an array of chars
  let data_set_chars = []
  for (let line of data_set) {
    let array = []
    for (let sub_line of line) array.push(sub_line.split(''))

    data_set_chars.push(array)
  }

  for (let line of data_set_chars) {
    // Comparing all arrays of strings of each group to find same questions
    let common_elements = line.reduce((p, c) => p.filter(e => c.includes(e)))
    sum += common_elements.length
  }

  console.log("SumB: " + sum)
}

SolveA()
SolveB()