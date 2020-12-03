import numpy

count = 0                                       #experiencing with global vars
totalDown = 0
totalRight = 0

originalRight = 0
originalDown = 0

def treeCount(lines):
    global totalDown
    global count
    global totalRight
    for line in lines[totalDown::originalDown]: #goes through lines of the tree map
        line = line[:-1]
        if (totalRight >= len(line)):           #if it passes the horizontal, then double the rest of the treemap with method doubleString
            doubleString(lines)
        elif (line[totalRight] == '#'):
            count += 1
        totalRight += originalRight             #update global vars
        totalDown += originalDown
    return count

def doubleString(lines):
    new_list = []
    for i in lines:
        i = i[:-1]
        double_line = i + i + "\n"               #add a '\n' because lists dont put that on, as i take \n off from the original treeGraph.txt input
        new_list.append(double_line)
    treeCount(new_list)
        
def main():
    global totalRight
    global count
    global totalDown
    global originalDown
    global originalRight
    file = open('Day 3/treeGraph.txt', "r")
    lines = file.readlines()
    
    userInput = True
    final_answer = []
    while(userInput):                            #big user interaction pog
        userInput = input("continue? (y/n)\n")
        if (userInput == 'n'):
            break
        totalRight = int(input("right?\n"))
        totalDown = int(input("down?\n"))

        originalDown = totalDown
        originalRight = totalRight
        x = treeCount(lines)
        count = 0
        print(x)
        final_answer.append(x)

    print(numpy.prod(final_answer))             #thanks numpy :)
    file.close

main()