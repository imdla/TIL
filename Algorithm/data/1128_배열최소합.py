T = int(input())

def perms(depth, cnt):
  global ans
  if depth == N:
    ans = min(ans, cnt)
    return
  
  if cnt >= ans:
    return
  
  for idx in range(N):
    if not visited[idx]:
      visited[idx] = 1
      
      perms(depth+1, cnt+nums[depth][idx])
      
      visited[idx] = 0

for tc in range(1, T+1):
  N = int(input())
  nums = [list(map(int, input().split())) for _ in range(N)]
  visited = [0]*N
  ans = float("INF")
  
  perms(0, 0)
  print(f'#{tc} {ans}')