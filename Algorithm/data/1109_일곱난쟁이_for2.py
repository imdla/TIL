import sys
input = sys.stdin.readline

mans = [int(input()) for _ in range(9)]
mans.sort()

flag = False
for i in range(8):
  for j in range(i+1, 9):
    if sum(mans) - mans[i] - mans[j] == 100:
      notMans = [i, j]
      flag = True
      break
  if flag:
    break

for idx in range(9):
  if idx in notMans:
    continue
  print(mans[idx])