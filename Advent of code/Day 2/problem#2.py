def passwordValidator(lines):
    validPasswords = 0
    for line in lines:
        split1 = line.split(": ", 1)
        range = split1[0].split("-", 1)
        lower_bound = range[0]
        upper_bound = range[1][:-2]
        password = split1[1]
        key = split1[0][-1]
        if (password.count(key) >= int(lower_bound) and password.count(key) <= int(upper_bound) ):
            validPasswords += 1
    return validPasswords

def passwordValidatorNew(lines):
    validPasswords = 0
    for line in lines:
        split1 = line.split(": ", 1)
        range = split1[0].split("-", 1)
        lower_bound = range[0]
        upper_bound = range[1][:-2]
        password = split1[1]
        key = split1[0][-1]
        if (password[int(lower_bound)-1] == key and password[int(upper_bound)-1] != key):
            validPasswords += 1
        elif (password[int(lower_bound)-1] != key and password[int(upper_bound)-1] == key):
            validPasswords += 1
    return validPasswords

def main():
    file = open("inputDayTwo.txt", "r")
    lines = file.readlines()
    print(passwordValidator(lines))
    print(passwordValidatorNew(lines))
main()