from itertools import combinations

dwarfs = [int(input()) for _ in range(9)]
dwarfs.sort()

for combi in combinations(dwarfs, 7):
  if sum(combi) == 100:
    for dwarf in combi:
      print(dwarf)
    break