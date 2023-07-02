use std::fs;

use day15::{part1, part2_parallel};

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone(), false)); // 4907780
    println!("{}", part2_parallel(contents, false)); // 13639962836448
}
