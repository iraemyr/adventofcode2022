use std::fs;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // TBVFVDZPN
    println!("{}", part2(contents)); // VLCWHTDSZ
}

fn part1(input: String) -> String {
    let (stack_input, moves) = input.split_once("\n\n").unwrap();
    let mut stacks = parse_stacks(stack_input);
    do_moves(&mut stacks, moves, true);
    let mut result = String::with_capacity(stacks.len());
    for mut stack in stacks {
        result.push(stack.pop().unwrap() as char);
    }
    result
}

fn part2(input: String) -> String {
    let (stack_input, moves) = input.split_once("\n\n").unwrap();
    let mut stacks = parse_stacks(stack_input);
    do_moves(&mut stacks, moves, false);
    let mut result = String::with_capacity(stacks.len());
    for mut stack in stacks {
        result.push(stack.pop().unwrap() as char);
    }
    result
}

fn do_moves(stacks: &mut [Vec<u8>], moves: &str, reverse: bool) {
    let move_it = moves.lines().map(|line| {
        let mut fields = line.split(' ');
        fields.next();
        let num = fields.next().unwrap().parse::<usize>().unwrap();
        fields.next();
        let src = fields.next().unwrap().parse::<usize>().unwrap() - 1;
        fields.next();
        let dest = fields.next().unwrap().parse::<usize>().unwrap() - 1;
        (num, src, dest)
    });
    for (num, src, dest) in move_it {
        let source = &mut stacks[src];
        let mut tmp = Vec::<u8>::new();
        for _ in 0..num {
            tmp.push(source.pop().unwrap());
        }
        if reverse {
            tmp.reverse();
        }
        let destination = &mut stacks[dest];
        while !tmp.is_empty() {
            destination.push(tmp.pop().unwrap());
        }
    }
}

fn parse_stacks(input: &str) -> Vec<Vec<u8>> {
    let mut it = input.lines().rev();
    let num_stacks = it.next().unwrap().trim().bytes().last().unwrap() - b'0';
    let mut stacks: Vec<Vec<u8>> = Vec::with_capacity(num_stacks as usize);
    for _ in 0..num_stacks {
        stacks.push(Vec::<u8>::with_capacity(64));
    }
    it.for_each(|line| {
        let chars = line.as_bytes();
        let mut index = 1;
        stacks.iter_mut().for_each(|stack| {
            let c = chars[index];
            if c != b' ' {
                stack.push(c);
            }
            index += 4;
        });
    });
    stacks
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), "TBVFVDZPN");
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), "VLCWHTDSZ");
    }
}
