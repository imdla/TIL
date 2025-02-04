# import sys
# input = sys.stdin.readline

N, M = map(int, input().split())

nums = list(map(int, input().split()))

acc_num = [0]
for num in nums:
  acc_num.append(acc_num[-1] + num)

for _ in range(M):
  i, j = map(int, input().split())
  print(acc_num[j] - acc_num[i-1])