# 메모이제이션

# import sys
# input = sys.stdin.readline


n = int(input())
memo = [0, 1]

for _ in range(n-1):
  memo.append(memo[-1] + memo[-2])

print(memo[n])