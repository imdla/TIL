# import sys
# imput = sys.stdin.readline

N, K = map(int, input().split())
tempature = list(map(int, input().split()))

tmp = max_sum = sum(tempature[:K])


for idx in range(N-K):
  tmp += tempature[idx+K] - tempature[idx]
  max_sum = max(max_sum, tmp)

print(max_sum)
