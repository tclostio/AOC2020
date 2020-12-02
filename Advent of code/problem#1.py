import math
def expenseFixTwo(lines):
    for line1 in lines:
        line1 = int(line1[:-1])
        for line2 in lines:
            line2 = int(line2[:-1])
            addition = (line1 + line2)
            if (addition == 2020):
                return line1*line2

def expenseFixThree(lines):
    for line1 in lines:
        line1 = int(line1[:-1])
        for line2 in lines:
            line2 = int(line2[:-1])
            for line3 in lines:
                line3 = int(line3[:-1])
                addition = (line1 + line2 + line3)
                if (addition == 2020):
                    return line1*line2*line3

def main():
    file = open("input.txt", "r")
    lines = file.readlines()
    print(expenseFixTwo(lines))
    print(expenseFixThree(lines))

main()