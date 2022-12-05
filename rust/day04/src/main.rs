use std::fs;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 448
    println!("{}", part2(contents)); // 794
}

fn part1(ranges: String) -> u32 {
    ranges
        .lines()
        .map(|line| {
            let mut fields = line.split(&['-', ','][..]);
            (
                fields.next().unwrap().parse::<u32>().unwrap(),
                fields.next().unwrap().parse::<u32>().unwrap(),
                fields.next().unwrap().parse::<u32>().unwrap(),
                fields.next().unwrap().parse::<u32>().unwrap(),
            )
        })
        .filter(|(a, b, x, y)| (a <= x && b >= y) || (x <= a && y >= b))
        .count() as u32
}

fn part2(ranges: String) -> u32 {
    ranges
        .lines()
        .map(|line| {
            let mut fields = line.split(&['-', ','][..]);
            (
                fields.next().unwrap().parse::<u32>().unwrap(),
                fields.next().unwrap().parse::<u32>().unwrap(),
                fields.next().unwrap().parse::<u32>().unwrap(),
                fields.next().unwrap().parse::<u32>().unwrap(),
            )
        })
        .filter(|(a, b, x, y)| (b >= x && y >= a))
        .count() as u32
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 448);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 794);
    }
}
