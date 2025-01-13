import sys
input = sys.stdin.readline

M, N = map(int, input().split())
nums = [num for num in range(M, N+1)]
visited = [0 for _ in range(M, N+1)]

# 3 4 5 6 7 8 9 10 11 12 13 14 15 16

# 2의 배수 visited
# 3의 배수 visited
# 6의 배수 양쪽 기준으로 소수 확인
  # 소수 아니면 visited

# 소수인지 확인
def checkPrime(num):
  sqr = round(num ** 0.5)
  for j in range(2, sqr+1):
    if num % j == 0:
      num = 0
      break
  return num

# 2와 3의 배수 확인
for i in range(len(nums)):
  num = nums[i]
  if num == 1:
    visited[i] = 1
  elif num != 2 and num % 2 == 0:
    visited[i] = 1
  elif num != 3 and num % 3 == 0:
    visited[i] = 1

for i in range(len(nums)):
  num = nums[i]    
  if num % 6 == 0:  
    if i-1 > -1 and checkPrime(nums[i-1]) == 0:
      visited[i-1] = 1
    if i+1 < len(nums) and checkPrime(nums[i+1]) == 0:
      visited[i+1] = 1

if len(nums) <= 5:
  for i in range(len(nums)):
    if checkPrime(nums[i]) == 0:
      visited[i] = 1

# print(visited)
# 정답 출력
cnt = 0
for idx in range(len(visited)):
  if visited[idx] == 0:
    cnt += 1
    print(nums[idx])

print(f'{cnt} ea')