use std::collections::HashMap;
use std::fs;
use std::path::PathBuf;

fn main() {
    let contents = fs::read_to_string("input1").expect("File not found");
    println!("{}", part1(contents.clone())); // 1427048
    println!("{}", part2(contents)); // 2940614
}

fn part1(input: String) -> u32 {
    let dirs = parse_map(input);
    dirs.values().filter(|v| **v <= 100000).sum()
}

fn part2(input: String) -> u32 {
    let dirs = parse_map(input);
    let used = dirs.get(&"\\".to_string()).unwrap();
    let free = 70_000_000 - used;
    let needed = 30_000_000 - free;
    *dirs.values().filter(|v| **v > needed).min().unwrap()
}

fn parse_map(input: String) -> HashMap<String, u32> {
    let mut lines = input.lines().peekable();
    let mut dir = PathBuf::new();
    let mut dirs = HashMap::new();
    _ = lines.next();
    dir.push("\\");
    dirs.insert(dir.to_str().unwrap().to_string(), 0);
    while lines.peek().is_some() {
        let mut fields = lines.next().unwrap().split(' ').skip(1);
        match fields.next().unwrap() {
            "cd" => {
                let name = fields.next().unwrap();
                if name == ".." {
                    let size = dirs[&dir.to_str().unwrap().to_string()];
                    dir.pop();
                    dirs.entry(dir.to_str().unwrap().to_string())
                        .and_modify(|v| *v += size);
                } else {
                    dir.push(name);
                    dirs.insert(dir.to_str().unwrap().to_string(), 0);
                }
            }
            "ls" => {
                while lines.peek().is_some() && !lines.peek().unwrap().starts_with('$') {
                    let (f1, _) = lines.next().unwrap().split_once(' ').unwrap();
                    if !f1.starts_with('d') {
                        dirs.entry(dir.to_str().unwrap().to_string())
                            .and_modify(|e| *e += f1.parse::<u32>().unwrap());
                    }
                }
            }
            _ => {}
        }
    }

    while dir.to_str().unwrap() != "\\" {
        let size = dirs[&dir.to_str().unwrap().to_string()];
        dir.pop();
        dirs.entry(dir.to_str().unwrap().to_string())
            .and_modify(|v| *v += size);
    }
    dirs
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part1(contents), 1427048);
    }

    #[test]
    fn test_part2() {
        let contents = fs::read_to_string("input1").expect("File not found");
        assert_eq!(part2(contents), 2940614);
    }
}
