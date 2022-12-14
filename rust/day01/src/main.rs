use std::fs;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 72017
    println!("{}", part2(contents)); // 212520
}

fn part1(elves: String) -> u32 {
    elves
        .split("\n\n")
        .map(|elf| elf.lines().map(|n| n.parse::<u32>().unwrap()).sum())
        .max()
        .unwrap()
}

fn part2(s: String) -> u32 {
    let mut elves: Vec<u32> = s
        .split("\n\n")
        .map(|elf| elf.lines().map(|n| n.parse::<u32>().unwrap()).sum())
        .collect();
    elves.sort_by(|a, b| b.cmp(a));
    elves.iter().take(3).sum()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 72017);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 212520);
    }
}
