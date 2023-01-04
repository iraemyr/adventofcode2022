use std::fs;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 1801
    println!("{}", part2(contents)); // 209880
}

fn part1(input: String) -> u32 {
    let trees = parse_map(input);
    let mut visible = 0;
    for row in 0..trees.len() {
        for col in 0..trees.get(row).unwrap().len() {
            if is_visible(row, col, &trees) {
                visible += 1;
            }
        }
    }
    visible
}

fn part2(input: String) -> u32 {
    let trees = parse_map(input);
    let mut max = 0;
    for row in 1..(trees.len() - 1) {
        for col in 1..(trees[row].len() - 1) {
            max = max.max(scenic_score(row, col, &trees));
        }
    }
    max
}

fn parse_map(input: String) -> Vec<Vec<u8>> {
    input.lines().map(|line| line.bytes().collect()).collect()
}

fn is_visible(row: usize, col: usize, map: &Vec<Vec<u8>>) -> bool {
    if row == 0 || col == 0 || row == map.len() - 1 || col == map[row].len() - 1 {
        return true;
    }
    let r = map.get(row).unwrap();
    let height = *r.get(col).unwrap();
    if r[0..col].iter().all(|h| *h < height) {
        return true;
    }

    if r[(col + 1)..map.len()].iter().all(|h| *h < height) {
        return true;
    }

    let mut blocked = false;
    for r in map.iter().take(row) {
        if r[col] >= height {
            blocked = true;
            break;
        }
    }
    if !blocked {
        return true;
    }

    blocked = false;
    for r in map.iter().skip(row + 1) {
        if r[col] >= height {
            blocked = true;
            break;
        }
    }
    !blocked
}

fn scenic_score(row: usize, col: usize, map: &Vec<Vec<u8>>) -> u32 {
    if row == 0 || col == 0 || row == map.len() - 1 || col == map[row].len() - 1 {
        return 0;
    }
    let r = map.get(row).unwrap();
    let height = *r.get(col).unwrap();

    let mut left = 0;
    for tree in r[0..col].iter().rev() {
        left += 1;
        if *tree >= height {
            break;
        }
    }

    let mut right = 0;
    for tree in r[(col + 1)..].iter() {
        right += 1;
        if *tree >= height {
            break;
        }
    }

    let mut up = 0;
    for r in map.iter().take(row).rev() {
        up += 1;
        if height <= r[col] {
            break;
        }
    }

    let mut down = 0;
    for r in map.iter().skip(row + 1) {
        down += 1;
        if height <= r[col] {
            break;
        }
    }

    up * down * left * right
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 1801);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 209880);
    }

    #[test]
    fn test_part1_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part1(contents), 21);
    }

    #[test]
    fn test_part2_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part2(contents), 8);
    }
}
