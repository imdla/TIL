import sys
input = sys.stdin.readline

N = int(input())

for _ in range(N):
  results = input()
  score = []

  for idx in range(len(results)):
    value = results[idx]

    if value == 'O':
      if results[idx-1] == 'O' and idx > 0:
        score.append(score[idx-1] + 1)
      else:
        score.append(1)

    else:
      score.append(0)
  print(sum(score))