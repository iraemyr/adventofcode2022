use std::fs;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 12679
    println!("{}", part2(contents)); // 14470
}

fn part1(s: String) -> u32 {
    s.lines()
        .map(|line| score(line.split_once(' ').unwrap()))
        .sum()
}

fn part2(s: String) -> u32 {
    s.lines()
        .map(|line| score2(line.split_once(' ').unwrap()))
        .sum()
}

fn score(game: (&str, &str)) -> u32 {
    match game.0 {
        "A" => match game.1 {
            "X" => 3 + 1,
            "Y" => 6 + 2,
            "Z" => 3,
            _ => panic!("Invalid move"),
        },
        "B" => match game.1 {
            "X" => 1,
            "Y" => 3 + 2,
            "Z" => 6 + 3,
            _ => panic!("Invalid move"),
        },
        "C" => match game.1 {
            "X" => 6 + 1,
            "Y" => 2,
            "Z" => 3 + 3,
            _ => panic!("Invalid move"),
        },
        _ => panic!("Invalid opponent move"),
    }
}

fn score2(game: (&str, &str)) -> u32 {
    match game.0 {
        "A" => match game.1 {
            "X" => 3,
            "Y" => 3 + 1,
            "Z" => 6 + 2,
            _ => panic!("Invalid move"),
        },
        "B" => match game.1 {
            "X" => 1,
            "Y" => 3 + 2,
            "Z" => 6 + 3,
            _ => panic!("Invalid move"),
        },
        "C" => match game.1 {
            "X" => 2,
            "Y" => 3 + 3,
            "Z" => 6 + 1,
            _ => panic!("Invalid move"),
        },
        _ => panic!("Invalid opponent move"),
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 12679);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 14470);
    }
}
