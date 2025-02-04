mans = [int(input() for _ in range(9))]
mans.sort()

from itertools import combinations
for combi in combinations(mans, 7):
  if sum(combi) == 100:
    for man in combi:
      print(man)
    break