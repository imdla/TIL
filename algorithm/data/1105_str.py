T = int(input())

for _ in range(T):
  idx, word = input().split()
  idx = int(idx) -1

  # 처음~idx전까지 자르고 // idx+1~끝까지 잘라서 붙이기
  ans = word[:idx] + word[idx+1:]
  print(ans)