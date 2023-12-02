def main():
    with open("input.txt") as f:
        lines = f.readlines()

    sum_of_games = 0
    for line in lines:
        game, sets_unsplit = line.split(":")
        game = int(game.replace('Game ', '').strip())
        sets = sets_unsplit.strip().split(";")
        mapping = {"red": 0, "blue": 0, "green": 0}
        for set in sets:
            set = set.strip()
            if set == "":
                continue
            balls = set.split(",")
            for ball in balls:
                num_balls, color = ball.strip().split(" ")
                num_balls = int(num_balls)
                mapping[color] = max(mapping[color], num_balls)

        sum_of_games += mapping["red"] * mapping["blue"] * mapping["green"]


    print(sum_of_games)

if __name__ == "__main__":
    main()
