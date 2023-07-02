use std::collections::{HashMap, HashSet};
use std::fs;

use nom::bytes::complete::tag;
use nom::character::complete::i32;
use nom::sequence::separated_pair;
use nom::IResult;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone(), false)); // 4907780
    println!("{}", part2(contents, false)); // 13639962836448
}

fn part1(input: String, sample: bool) -> i32 {
    let (map, beacons) = parse(input);
    let line = if sample { 10 } else { 2000000 };
    let mut min = i32::MAX;
    let mut max = i32::MIN;
    for (s, dist) in map.iter() {
        min = min.min(s.0 - dist);
        max = max.max(s.0 + dist);
    }

    (min..=max)
        .map(|i| (i, line))
        .filter(|p| !beacons.contains(p))
        .filter(|p| {
            for (sensor, dist) in map.iter() {
                if manhattan(*sensor, *p) <= *dist {
                    return true;
                }
            }
            false
        })
        .count() as i32
}

fn manhattan(p1: Point, p2: Point) -> i32 {
    (p1.0 - p2.0).abs() + (p1.1 - p2.1).abs()
}

fn part2(input: String, sample: bool) -> u64 {
    let (map, _beacons) = parse(input);
    let max = if sample { 20 } else { 4000000 };
    for row in 0..=max {
        let mut ranges: Vec<Point> = Vec::new();
        for (sensor, dist) in map.iter() {
            let d = (sensor.1 - row).abs();
            if d > *dist {
                continue;
            }
            let r = *dist - d;
            let mut start = sensor.0 - r;
            let mut end = sensor.0 + r;
            if end < 0 || start > max {
                continue;
            }

            start = start.max(0);
            end = end.min(max);

            ranges = push_reduce(start, end, &ranges);
        }

        if ranges.len() == 2 {
            let r1 = ranges[0];
            return row as u64
                + 4000000
                    * if r1.0 == 0 {
                        (r1.1 + 1) as u64
                    } else {
                        (r1.0 - 1) as u64
                    };
        }
    }
    0
}

fn push_reduce(mut start: i32, mut end: i32, v: &Vec<Point>) -> Vec<Point> {
    let mut reduced: Vec<Point> = Vec::with_capacity(v.len() + 1);
    if v.is_empty() {
        reduced.push((start, end));
        return reduced;
    }

    for p in v {
        if start >= p.0 && end <= p.1 {
            return v.clone(); // contained
        }

        if start > p.1 || end < p.0 {
            if start == p.1 + 1 || end == p.0 - 1 {
                // join
                start = start.min(p.0);
                end = end.max(p.1);
            } else {
                // no overlap
                reduced.push(*p);
            }
        } else if start <= p.0 && end >= p.1 { // contains
        } else if (start >= p.0 && start <= p.1) || (end >= p.0 && end <= p.1) {
            // combine
            start = start.min(p.0);
            end = end.max(p.1);
        }
    }
    reduced.push((start, end));
    reduced.sort();
    reduced
}

type Point = (i32, i32);
type HashTuple = (HashMap<Point, i32>, HashSet<Point>);
fn parse(input: String) -> HashTuple {
    let mut map = HashMap::new();
    let mut set = HashSet::new();
    input.lines().for_each(|line| {
        if let Ok((_, (s, b))) = parse_coords(line) {
            set.insert(b);
            map.insert(s, (s.0 - b.0).abs() + (s.1 - b.1).abs());
        }
    });
    (map, set)
}

type PointTuple = ((i32, i32), (i32, i32));
fn parse_coords(input: &str) -> IResult<&str, PointTuple> {
    let (i, _) = tag("Sensor at x=")(input)?;
    let (i, p1) = parse_point(i)?;
    let (i, _) = tag(": closest beacon is at x=")(i)?;
    let (_, p2) = parse_point(i)?;
    Ok(("", (p1, p2)))
}

fn parse_point(input: &str) -> IResult<&str, (i32, i32)> {
    separated_pair(i32, tag(", y="), i32)(input)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part1(contents, true), 26);
    }
    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents, false), 4907780);
    }

    #[test]
    fn test_part2_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part2(contents, true), 56000011);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents, false), 13639962836448);
    }
}
