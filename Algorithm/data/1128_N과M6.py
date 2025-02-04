import sys
input = sys.stdin.readline

N, M = map(int, input().split())
nums = list(map(int, input().split()))
nums.sort()
visited = [0]*N
ans = []

def combi(depth, i):
  if depth == M:
    print(*ans)
    return
  
  for idx in range(i, N):
    if not visited[idx]:
      visited[idx] = 1
      
      ans.append(nums[idx])
      combi(depth + 1, idx+1)
      
      ans.pop()
      visited[idx] = 0
  
combi(0, 0)
