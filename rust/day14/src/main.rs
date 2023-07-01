use std::collections::HashMap;
use std::fs;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 638
    println!("{}", part2(contents)); // 31722S
}

fn part1(input: String) -> i32 {
    let (mut map, lowest) = parse_map(input);
    do_drops(&mut map, lowest)
}

fn part2(input: String) -> i32 {
    let (mut map, lowest) = parse_map(input);
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

fn parse_map(input: String) -> (HashMap<(i32, i32), char>, i32) {
    let mut lowest = 0;
    let mut map = HashMap::new();
    input.lines().for_each(|line| {
        let fields = line.split(" -> ");
        let mut position = (0, 0);
        let mut first = true;
        for field in fields {
            let mut nums = field.split(',');
            let pos = (
                nums.next().unwrap().parse::<i32>().unwrap(),
                nums.next().unwrap().parse::<i32>().unwrap(),
            );
            lowest = lowest.max(pos.1);
            if !first {
                while position != pos {
                    position.0 += (pos.0 - position.0).signum();
                    position.1 += (pos.1 - position.1).signum();
                    map.insert(position, '#');
                }
            } else {
                first = false;
                map.insert(pos, '#');
                position = pos;
            }
        }
    });
    (map, lowest)
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
