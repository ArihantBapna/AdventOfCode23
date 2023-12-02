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

fn find_first_last_number(input: &str) -> (Option<String>, Option<String>) {
    let number_strings = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"];
    let number_digits = ['1', '2', '3', '4', '5', '6', '7', '8', '9'];


    let word_to_digit: std::collections::HashMap<_, _> = number_strings.iter()
        .zip(number_digits.iter())
        .map(|(&word, &digit)| (word.to_string(), digit.to_string()))
        .collect();

    let mut first_number = None;
    let mut last_number = None;
    for i in 0..input.len() {
        for &number in &number_strings {
            if input[i..].starts_with(number) {
                if first_number.is_none() {
                    first_number = Some(number.to_string());
                }
                last_number = Some(number.to_string());
                break;
            }
        }

        for &digit in &number_digits {
            let digit_str = digit.to_string();
            if input[i..].starts_with(&digit_str) {
                if first_number.is_none() {
                    first_number = Some(digit_str.clone());
                }
                last_number = Some(digit_str);
                break;
            }
        }
    }

    let first_number_digit = first_number
        .map(|num| word_to_digit.get(&num).unwrap_or(&num).clone());

    let last_number_digit = last_number
        .map(|num| word_to_digit.get(&num).unwrap_or(&num).clone());

    (first_number_digit, last_number_digit)
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

    let mut total = 0;
    for line in file_contents.lines() {

        let (first_number, last_number) = find_first_last_number(line);
        match (first_number, last_number) {
            (Some(first), Some(last)) => {
                println!("Line: {}, First: {}, Last: {}", line, first, last);

                let combined = format!("{}{}", first, last);
                if let Ok(combined_int) = combined.parse::<i32>() {
                    total += combined_int;
                } else {
                    println!("Failed to parse combined number: {}", combined);
                }
            }
            _ => println!("No valid numbers found in line: '{}'", line),
        }
    }
    println!("Total: {}", total);
}
