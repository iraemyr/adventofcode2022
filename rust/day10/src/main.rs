use std::fs;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 14860
    println!("{}", part2(contents)); // RGZEHURK
}

fn part1(input: String) -> i32 {
    let mut cycle = 0;
    let mut x = 1;
    let mut result = 0;
    for line in input.lines() {
        if line == "noop" {
            cycle += 1;
            result += do_cycle(x, cycle);
        } else {
            cycle += 1;
            result += do_cycle(x, cycle);
            cycle += 1;
            result += do_cycle(x, cycle);
            x += line[5..].parse::<i32>().unwrap();
        }
    }
    result
}

fn part2(input: String) -> String {
    let mut screen = String::new();
    let mut col = -1;
    let mut x = 1;
    for line in input.lines() {
        if line == "noop" {
            col = update(&mut screen, col, x);
        } else {
            col = update(&mut screen, col, x);
            col = update(&mut screen, col, x);
            x += line[5..].parse::<i32>().unwrap();
        }
    }
    screen
}

fn do_cycle(x: i32, cycle: i32) -> i32 {
    if cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220 {
        return cycle * x;
    }
    0
}

fn update(screen: &mut String, mut col: i32, x: i32) -> i32 {
    col += 1;
    if col == 40 {
        col = 0;
        *screen += "\n";
    }
    if (col - x).abs() <= 1 {
        *screen += "#";
    } else {
        *screen += ".";
    }
    col
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 14860);
    }

    #[test]
    fn test_part1_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part1(contents), 13140);
    }
}
