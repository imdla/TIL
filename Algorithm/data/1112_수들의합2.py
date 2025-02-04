import sys
input = sys.stdin.readline

N, M = list(map(int, input().split(' ')))

nums = list(map(int, input().split(' ')))

count = 0
i = 0
j = 0
while j < len(nums):
  j = i + 1
  tmp = nums[i]
  flag = True

  if tmp == M:
    i += 1
    count += 1
    continue
  else:
    while flag and j < len(nums):
      tmp += nums[j]
      if tmp == M and tmp <= M:
        i += 1
        count += 1
        flag = False
        continue
      elif tmp > M:
        i += 1
        flag = False
        continue
      else:
        j += 1


print(count)