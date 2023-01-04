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
    r[0..col].iter().all(|h| *h < height)
        || r[(col + 1)..map.len()].iter().all(|h| *h < height)
        || (0..row).map(|r| map[r][col]).all(|h| h < height)
        || ((row + 1)..map.len())
            .map(|r| map[r][col])
            .all(|h| h < height)
}

fn scenic_score(row: usize, col: usize, map: &Vec<Vec<u8>>) -> u32 {
    if row == 0 || col == 0 || row == map.len() - 1 || col == map[row].len() - 1 {
        return 0;
    }
    let r = map.get(row).unwrap();
    let height = *r.get(col).unwrap();

    visible_trees(r[0..col].iter().rev().copied(), height)
        * visible_trees(r[(col + 1)..].iter().copied(), height)
        * visible_trees(map.iter().take(row).rev().map(|v| v[col]), height)
        * visible_trees(map.iter().skip(row + 1).map(|v| v[col]), height)
}

fn visible_trees(it: impl Iterator<Item = u8>, height: u8) -> u32 {
    let mut trees = 0;
    for h in it {
        trees += 1;
        if h >= height {
            break;
        }
    }
    trees
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
