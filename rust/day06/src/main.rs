use std::fs;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 1480
    println!("{}", part2(contents)); // 2746
}

fn part1(input: String) -> usize {
    let mut i = 0;
    while i < input.len() - 4 {
        let end = i + 4;
        if let Some(x) = find_duplicate_index(&input[i..end]) {
            i += x;
        } else {
            return i + 4;
        }
        i += 1;
    }
    0
}

fn part2(input: String) -> usize {
    let mut i = 0;
    while i < input.len() - 14 {
        let end = i + 14;
        if let Some(x) = find_duplicate_index(&input[i..end]) {
            i += x;
        } else {
            return i + 14;
        }
        i += 1;
    }
    0
}

fn find_duplicate_index(s: &str) -> Option<usize> {
    let bytes = s.as_bytes();
    for x in (1..s.len()).rev() {
        for y in (0..x).rev() {
            if bytes[x] == bytes[y] {
                return Some(y);
            }
        }
    }
    None
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 1480);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 2746);
    }
}
