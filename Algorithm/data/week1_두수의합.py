import sys
input = sys.stdin.readline

N = int(input())
nums = list(map(int, input().split(' ')))
nums.sort()
M = int(input())

count = 0
l = 0
r = len(nums) -1

while l < r:
  tmp = nums[l] + nums[r]
  
  if  tmp == M:
    count += 1
    l += 1
    r -= 1
    continue
  elif tmp > M:
    r -= 1
  else:
    l += 1
  

print(count)