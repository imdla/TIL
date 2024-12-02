import sys
input = sys.stdin.readline

N, M = map(int, input().split())

nums = list(map(int, input().split()))
nums.sort()

visited = [0]*N
ans = []

def combi(depth, idx):
  if depth == M:
    print(*ans)
    return
  
  for i in range(idx, N):
    if not visited[i]:
      visited[i] = 1
      ans.append(nums[i])
      combi(depth+1, i)
      
      ans.pop()
      visited[i] = 0

combi(0, 0)