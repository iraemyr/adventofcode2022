use std::fs;

struct Monkey {
    true_target: usize,
    false_target: usize,
    op: u8,
    val: Option<u64>,
    div: u64,
    inspected: u64,
}

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 100345
    println!("{}", part2(contents)); // 28537348205
}

fn part1(input: String) -> u64 {
    solve(input, 20, true)
}

fn part2(input: String) -> u64 {
    solve(input, 10000, false)
}

fn solve(input: String, rounds: u32, p1: bool) -> u64 {
    let mut item_queues = Vec::with_capacity(10);
    let (mut monkeys, cm) = parse_monkeys(input, &mut item_queues);
    for _ in 0..rounds {
        if p1 {
            do_round(&mut monkeys, &mut item_queues, |v: u64| v / 3);
        } else {
            do_round(&mut monkeys, &mut item_queues, |v: u64| v % cm);
        }
    }
    let mut max1 = 0;
    let mut max2 = 0;
    for monkey in monkeys {
        let mut v = monkey.inspected;
        if v > max1 {
            std::mem::swap(&mut v, &mut max1);
        }
        if v > max2 {
            max2 = v;
        }
    }
    max1 * max2
}

fn do_round<F>(monkeys: &mut Vec<Monkey>, item_queues: &mut [Vec<u64>], f: F)
where
    F: Fn(u64) -> u64,
{
    for i in 0..monkeys.len() {
        let mut monkey = &mut monkeys[i];
        for j in 0..item_queues[i].len() {
            let item = item_queues[i][j];
            let v = if let Some(x) = monkey.val { x } else { item };
            let mut result = if monkey.op == b'+' {
                item + v
            } else {
                item * v
            };
            result = f(result);
            if result % monkey.div == 0 {
                item_queues[monkey.true_target].push(result);
            } else {
                item_queues[monkey.false_target].push(result);
            }
            monkey.inspected += 1;
        }
        item_queues[i].clear();
    }
}

fn parse_monkeys(input: String, item_queues: &mut Vec<Vec<u64>>) -> (Vec<Monkey>, u64) {
    let mut lines = input.lines().peekable();
    let mut monkeys = Vec::new();
    let mut mul = 1;
    loop {
        lines.next();
        let mut items = Vec::with_capacity(10);
        let mut item_fields = lines.next().unwrap().split(": ").skip(1);
        let item_fields = item_fields.next().unwrap().split(", ");
        item_fields.for_each(|item| items.push(item.parse::<u64>().unwrap()));
        item_queues.push(items);

        let op_fields = lines
            .next()
            .unwrap()
            .split("= old ")
            .skip(1)
            .last()
            .unwrap();
        let op = op_fields.bytes().next().unwrap();
        let val = match op_fields[2..].parse::<u64>() {
            Ok(x) => Some(x),
            _ => None,
        };
        let div = lines
            .next()
            .unwrap()
            .split("by ")
            .last()
            .unwrap()
            .parse::<u64>()
            .unwrap();
        mul *= div;
        let true_target = lines
            .next()
            .unwrap()
            .split("monkey ")
            .last()
            .unwrap()
            .parse::<usize>()
            .unwrap();
        let false_target = lines
            .next()
            .unwrap()
            .split("monkey ")
            .last()
            .unwrap()
            .parse::<usize>()
            .unwrap();
        monkeys.push(Monkey {
            true_target,
            false_target,
            op,
            val,
            div,
            inspected: 0,
        });
        if lines.peek().is_none() {
            return (monkeys, mul);
        }
        lines.next();
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 100345);
    }

    #[test]
    fn test_part1_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part1(contents), 10605);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 28537348205);
    }

    #[test]
    fn test_part2_sample() {
        let contents = fs::read_to_string("test1").expect("File not found");
        assert_eq!(part2(contents), 2713310158);
    }
}
