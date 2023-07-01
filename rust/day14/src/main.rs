use std::collections::HashMap;
use std::fs;

use nom::multi::many0;
use nom::sequence::{preceded, separated_pair};
use nom::{bytes::complete::tag, character::complete::digit1, combinator::map_res, IResult};

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 638
    println!("{}", part2(contents)); // 31722S
}

fn part1(input: String) -> i32 {
    let (mut map, lowest) = nom_parse(input);
    do_drops(&mut map, lowest)
}

fn part2(input: String) -> i32 {
    let (mut map, lowest) = nom_parse(input);
    do_drops_2(&mut map, lowest)
}

fn do_drops(map: &mut HashMap<(i32, i32), char>, lowest: i32) -> i32 {
    let mut grains = 0;
    loop {
        let grain = drop(map, lowest);
        if grain.1 == lowest {
            break;
        }
        map.insert(grain, 'O');
        grains += 1;
    }
    grains
}

fn do_drops_2(map: &mut HashMap<(i32, i32), char>, lowest: i32) -> i32 {
    let mut grains = 0;
    loop {
        let grain = drop(map, lowest + 1);
        map.insert(grain, 'O');
        grains += 1;
        if grain == (500, 0) {
            break;
        }
    }
    grains
}

fn drop(map: &HashMap<(i32, i32), char>, lowest: i32) -> (i32, i32) {
    let mut pos = (500, 0);
    while pos.1 < lowest {
        if !map.contains_key(&(pos.0, pos.1 + 1)) {
            pos = (pos.0, pos.1 + 1);
        } else if !map.contains_key(&(pos.0 - 1, pos.1 + 1)) {
            pos = (pos.0 - 1, pos.1 + 1);
        } else if !map.contains_key(&(pos.0 + 1, pos.1 + 1)) {
            pos = (pos.0 + 1, pos.1 + 1);
        } else {
            break;
        }
    }
    pos
}

fn nom_parse(input: String) -> (HashMap<(i32, i32), char>, i32) {
    let mut map = HashMap::new();
    let mut lowest = 0;
    input.lines().for_each(|line| {
        if let Ok((i, mut position)) = parse_point(line) {
            //print!("{:?} : ", point);
            lowest = lowest.max(position.1);
            map.insert(position, '#');
            if let Ok((_, points)) = parse_remaining(i) {
                for p in points {
                    lowest = lowest.max(p.1);
                    while position != p {
                        position.0 += (p.0 - position.0).signum();
                        position.1 += (p.1 - position.1).signum();
                        map.insert(position, '#');
                    }
                }
            }
        }
    });
    (map, lowest)
}

fn parse_int(input: &str) -> IResult<&str, i32> {
    map_res(digit1, str::parse::<i32>)(input)
}

fn parse_point(input: &str) -> IResult<&str, (i32, i32)> {
    separated_pair(parse_int, tag(","), parse_int)(input)
}

fn parse_remaining(input: &str) -> IResult<&str, Vec<(i32, i32)>> {
    many0(preceded(tag(" -> "), parse_point))(input)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part1(contents), 24);
    }
    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 638);
    }

    #[test]
    fn test_part2_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part2(contents), 93);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 31722);
    }
}
