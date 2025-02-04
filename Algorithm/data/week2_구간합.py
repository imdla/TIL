T = int(input())

for i in range(T):
  N, M = map(int, input().split())
  nums = list(map(int, input().split()))
  max_num = min_num = sum_num = sum(nums[:M])

  for j in range(M, N):
    sum_num += nums[j] - nums[j-M]
    max_num = max(max_num, sum_num)
    min_num = min(min_num, sum_num)

  ans = max_num - min_num
  print(f"#{i+1} {ans}")

