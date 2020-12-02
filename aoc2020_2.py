import re


# 15-16 f: ffffffffffffffhf

LINE_PATTERN = r'(\d+)\-(\d+)\s(.):\s(.*)$'


def correct_format_v1(lmin, lmax, lchar, lstr):
  char_count = lstr.count(lchar)
  return lmin <= char_count <= lmax

def correct_format_v2(lmin, lmax, lchar, lstr):
  return bool(lchar == lstr[lmin - 1]) ^ bool(lchar == lstr[lmax - 1])


Q1_COUNT = 0
Q2_COUNT = 0

with open("day2.txt") as f:
  for line in f.readlines():
    match = re.search(LINE_PATTERN, line)
    if correct_format_v1(int(match.group(1)), int(match.group(2)), match.group(3), match.group(4)):
      Q1_COUNT += 1
    if correct_format_v2(int(match.group(1)), int(match.group(2)), match.group(3), match.group(4)):
      Q2_COUNT += 1

print(f"Q1: {Q1_COUNT} Q2: {Q2_COUNT}")
