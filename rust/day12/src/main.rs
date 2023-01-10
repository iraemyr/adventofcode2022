extern crate pathfinding;

use pathfinding::prelude::bfs;
use std::collections::HashMap;
use std::fs;

#[derive(PartialEq, Eq, Hash, Clone, Copy, Debug)]
struct Pos(i32, i32);

impl Pos {
    fn neighbors(&self, map: &HashMap<Pos, u8>) -> Vec<Pos> {
        let &Pos(row, col) = self;
        let mut v = Vec::with_capacity(4);
        if map[self] + 1 >= *map.get(&Pos(row - 1, col)).unwrap_or(&30) {
            v.push(Pos(row - 1, col));
        }
        if map[self] + 1 >= *map.get(&Pos(row + 1, col)).unwrap_or(&30) {
            v.push(Pos(row + 1, col));
        }
        if map[self] + 1 >= *map.get(&Pos(row, col - 1)).unwrap_or(&30) {
            v.push(Pos(row, col - 1));
        }
        if map[self] + 1 >= *map.get(&Pos(row, col + 1)).unwrap_or(&30) {
            v.push(Pos(row, col + 1));
        }
        v
    }

    fn neighbors2(&self, map: &HashMap<Pos, u8>) -> Vec<Pos> {
        let &Pos(row, col) = self;
        let mut v = Vec::with_capacity(4);
        if map.get(&Pos(row - 1, col)).is_some()
            && *map.get(&Pos(row - 1, col)).unwrap() + 1 >= map[self]
        {
            v.push(Pos(row - 1, col));
        }
        if map.get(&Pos(row + 1, col)).is_some()
            && *map.get(&Pos(row + 1, col)).unwrap() + 1 >= map[self]
        {
            v.push(Pos(row + 1, col));
        }
        if map.get(&Pos(row, col - 1)).is_some()
            && *map.get(&Pos(row, col - 1)).unwrap() + 1 >= map[self]
        {
            v.push(Pos(row, col - 1));
        }
        if map.get(&Pos(row, col + 1)).is_some()
            && *map.get(&Pos(row, col + 1)).unwrap() + 1 >= map[self]
        {
            v.push(Pos(row, col + 1));
        }
        v
    }
}

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 330
    println!("{}", part2(contents)); // 321
}

fn part1(input: String) -> usize {
    let (start, end, map) = parse_input(input);
    bfs(&start, |p| p.neighbors(&map), |p| *p == end)
        .unwrap()
        .len()
        - 1
}

fn part2(input: String) -> usize {
    let (_, start, map) = parse_input(input);
    bfs(&start, |p| p.neighbors2(&map), |p| map[p] == 0)
        .unwrap()
        .len()
        - 1
}

fn parse_input(input: String) -> (Pos, Pos, HashMap<Pos, u8>) {
    let mut start = Pos(0, 0);
    let mut end = Pos(0, 0);
    let mut map = HashMap::new();
    for (row, line) in input.lines().enumerate() {
        for (col, b) in line.bytes().enumerate() {
            match b {
                b'S' => {
                    start = Pos(row as i32, col as i32);
                    map.insert(Pos(row as i32, col as i32), 0);
                }
                b'E' => {
                    end = Pos(row as i32, col as i32);
                    map.insert(Pos(row as i32, col as i32), 25);
                }
                _ => {
                    map.insert(Pos(row as i32, col as i32), b - b'a');
                }
            }
        }
    }

    (start, end, map)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 330);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 321);
    }
}
