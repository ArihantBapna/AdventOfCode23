use std::env;
use std::fs::File;
use std::io::{self, Read}; 
use std::path::Path;

fn read_file_to_string<P: AsRef<Path>>(path: P) -> io::Result<String> {
    let mut file = File::open(path)?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    Ok(contents)
}

fn main() {
    println!("Current directory: {:?}", env::current_dir().unwrap());

    let file_path = "src/input.txt";
    let contents = read_file_to_string(file_path);

    let file_contents = match contents {
        Ok(c) => c,
        Err(e) => {
            println!("Error reading file: {}", e);
            return;
        },
    };

    let mut floor = 0;

    // Go through each character in the file!
    // If it's a (, increment the floor by 1
    // If it's a ), decrement the floor by 1

    let mut i = 0;
    for c in file_contents.chars() {
        match c {
            '(' => floor += 1,
            ')' => floor -= 1,
            _ => (),
        }
        i += 1;
        if floor == -1 {
            println!("Basement at: {}", i);
            break;
        }
    }

    println!("Floor: {}", floor);
}
