use std::fs;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 8493
    println!("{}", part2(contents)); // 2552
}

fn part1(sacks: String) -> u32 {
    sacks.lines().map(find_repeat).sum::<u32>()
}

fn part2(sacks: String) -> u32 {
    sacks
        .lines()
        .collect::<Vec<&str>>()
        .chunks(3)
        .map(|bags| {
            bags.iter()
                .map(|bag| bag.bytes().map(priority).fold(0_u64, insert))
                .reduce(|acc, set| acc & set)
                .unwrap()
                .trailing_zeros() as u32
        })
        .sum()
}

fn find_repeat(sack: &str) -> u32 {
    let (a, b) = sack.split_at(sack.len() / 2);
    let seen = a.bytes().map(priority).fold(0_u64, insert);
    for p in b.bytes().map(priority) {
        if (1 << p) & seen > 0 {
            return p;
        }
    }
    panic!("No repeat");
}

fn priority(b: u8) -> u32 {
    match b {
        b'a'..=b'z' => (b - b'a' + 1) as u32,
        b'A'..=b'Z' => (b - b'A' + 27) as u32,
        _ => panic!("Invalid character"),
    }
}

fn insert(set: u64, priority: u32) -> u64 {
    set | 1 << (priority as u64)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 8493);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 2552);
    }
}
