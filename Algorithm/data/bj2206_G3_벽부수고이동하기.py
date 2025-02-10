import sys
input = sys.stdin.readline
from collections import deque

N, M = map(int, input().split())
board = [list(map(int, *input().split())) for _ in range(N)]

dr = [0, 0, 1, -1]
dc = [1, -1, 0, 0]

queue = deque([(0, 0, 1, 0)])
visited = set([(0, 0, 0)])
ans = -1

while queue:
  (r, c, cnt, flag) = queue.popleft()
    
  if r == N-1 and c == M-1:
    ans = cnt
    break
    
  for d in range(4):
    nr, nc = r+dr[d], c+dc[d]
    
    if 0 > nr or nr >= N or 0 > nc or nc >= M or (nr, nc, flag) in visited:
      continue
      
    if board[nr][nc] == 0:
      queue.append((nr, nc, cnt+1, flag))
      visited.add((nr, nc, flag))
        
    elif flag == 0:
      queue.append((nr, nc, cnt+1, 1))
      visited.add((nr, nc, 1))

print(ans)