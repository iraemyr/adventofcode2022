use serde::Deserialize;
use std::cmp::Ordering;
use std::fs;

#[derive(Debug, Deserialize, PartialEq, Eq, Clone)]
#[serde(untagged)]
enum Data {
    List(Vec<Data>),
    Int(u8),
}

impl Ord for Data {
    fn cmp(&self, other: &Self) -> Ordering {
        match (self, other) {
            (Data::Int(a), Data::Int(b)) => a.cmp(b),
            (Data::List(a), Data::List(b)) => a.cmp(b),
            (Data::Int(a), Data::List(b)) => {
                let v = [Data::Int(*a)];
                v.as_slice().cmp(b.as_slice())
            }
            (Data::List(a), Data::Int(b)) => {
                let v = [Data::Int(*b)];
                a.as_slice().cmp(v.as_slice())
            }
        }
    }
}
impl PartialOrd for Data {
    fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
        Some(self.cmp(other))
    }
}

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 5529
    println!("{}", part2(contents)); // 27690
}

fn part1(input: String) -> i32 {
    let data: Vec<Data> = input
        .lines()
        .filter(|line| !line.is_empty())
        .map(|json| serde_json::from_str(json).unwrap())
        .collect();
    data.chunks(2)
        .zip(1..)
        .map(|(data, pair)| if data[0] < data[1] { pair } else { 0 })
        .sum()
}

fn part2(input: String) -> i32 {
    let d1: Data = serde_json::from_str("[[2]]").unwrap();
    let d2: Data = serde_json::from_str("[[6]]").unwrap();
    let mut data: Vec<Data> = input
        .lines()
        .filter(|line| !line.is_empty())
        .map(|json| serde_json::from_str(json).unwrap())
        .collect();
    data.push(d1.clone());
    data.push(d2.clone());
    // let d1 = serde_json::from_str("[[2]]").unwrap();
    // let d2 = serde_json::from_str("[[6]]").unwrap();
    data.sort();
    data.iter()
        .zip(1..)
        .map(|(data, line)| if data == &d1 || data == &d2 { line } else { 1 })
        .product()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part1(contents), 13);
    }

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 5529);
    }

    #[test]
    fn test_part2_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part2(contents), 140);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 27690);
    }
}
