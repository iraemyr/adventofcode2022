use ahash::AHashSet;
use std::fs;

struct Rope<const N: usize> {
    points: [(i32, i32); N],
    tail_positions: AHashSet<(i32, i32)>,
    prev_tail: (i32, i32),
}

impl<const N: usize> Rope<N> {
    fn visited(&self) -> usize {
        self.tail_positions.len()
    }

    fn new() -> Rope<N> {
        let mut tail_positions = AHashSet::with_capacity(1024);
        tail_positions.insert((0, 0));
        Rope {
            points: [(0, 0); N],
            prev_tail: (0, 0),
            tail_positions,
        }
    }

    fn go(&mut self, instr: &str) {
        let dist = instr[2..].parse::<usize>().unwrap();
        let dir = get_dir(instr.bytes().take(1).last().unwrap());

        for _ in 0..dist {
            self.points[0] = (self.points[0].0 + dir.0, self.points[0].1 + dir.1);

            for i in 1..self.points.len() {
                let diff0 = self.points[i - 1].0 - self.points[i].0;
                let diff1 = self.points[i - 1].1 - self.points[i].1;

                if diff0.abs() <= 1 && diff1.abs() <= 1 {
                    break;
                }

                self.points[i] = (
                    self.points[i].0 + diff0.signum(),
                    self.points[i].1 + diff1.signum(),
                );
            }

            let tail = self.points.len() - 1;
            if self.prev_tail != self.points[tail] {
                self.tail_positions.insert(self.points[tail]);
                self.prev_tail = self.points[tail];
            }
        }
    }
}

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 6384
    println!("{}", part2(contents)); // 2734
}

fn part1(directions: String) -> usize {
    let mut rope = Rope::<2>::new();
    directions.lines().for_each(|line| rope.go(line));
    rope.visited()
}

fn part2(directions: String) -> usize {
    let mut chain = Rope::<10>::new();
    directions.lines().for_each(|line| chain.go(line));
    chain.visited()
}

fn get_dir(d: u8) -> (i32, i32) {
    match d {
        b'U' => (1, 0),
        b'D' => (-1, 0),
        b'R' => (0, 1),
        b'L' => (0, -1),
        _ => {
            println!("Invalid direction");
            (0, 0)
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 6384);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 2734);
    }

    #[test]
    fn test_part1_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part1(contents), 13);
    }

    #[test]
    fn test_part2_sample() {
        let contents = fs::read_to_string("test2").expect("File not found");
        assert_eq!(part2(contents), 36);
    }
}
