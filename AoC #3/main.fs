open System.IO

// Read lines from text file
let lines = File.ReadAllLines("input.txt")
let cols = lines.[0].Length

// Convenient structure
type Vector2 = struct
  val mutable x : int
  val mutable y : int

  new (x1, y1) =
    { x = x1; y = y1; }
end

let IsTree (pos: Vector2) : bool =
  (lines.[pos.y].[pos.x] = '#') // Checks to see if the current position is a tree character

let mutable p = new Vector2(0, 0)
let mutable n_trees = 0UL

// AoC 3.1
while p.y < lines.Length do

  // Applies position, p as parameter to IsTree
  // Then changes the bool from IsTree to an unsigned int
  let is_tree = p |> IsTree |> System.Convert.ToUInt64
  n_trees <- n_trees + is_tree

  p.x <- (p.x + 3) % cols
  p.y <- p.y + 1

printfn "n_trees: %i" n_trees

// AoC 3.2
// Values on the left -> Slope right
// Values on the right -> Slope down
let distance = [| 1; 1; 
                  3; 1; 
                  5; 1;
                  7; 1;
                  1; 2; |]

let mutable n = 0
let mutable product = 1UL

while n < distance.Length / 2 do
  n_trees <- 0UL
  p <- new Vector2(0, 0)
  while p.y < lines.Length do

    let is_tree = p |> IsTree |> System.Convert.ToUInt64
    n_trees <- n_trees + is_tree

    p.x <- (p.x + distance.[2 * n]) % cols
    p.y <- p.y + distance.[2 * n + 1]
  n <- n + 1
  product <- product * n_trees

printfn "Product: %i" product