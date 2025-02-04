import sys
input = sys.stdin.readline

N = int(input())
board = [list(map(int, input().split())) for _ in range(N)]

visited = [0]*N
ans = float("INF")

def perm(depth, cur, cnt):
  global ans
  
  if depth == N:
    if board[cur][0]:
      cnt += board[cur][0]
      ans = min(ans, cnt)
      return
    else:
      return
  
  if cnt > ans:
    return
  
  for nxt in range(N):
    if not visited[nxt] and board[cur][nxt]:
      visited[nxt] = 1
      perm(depth+1, nxt, cnt+board[cur][nxt])
      visited[nxt] = 0
      

visited[0] = 1
perm(1, 0, 0)
print(ans)