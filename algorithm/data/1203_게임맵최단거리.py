from collections import deque

def solution(maps):
    answer = float("INF")
    queue = deque([(0, 0, 1)])
    visited = set()
    
    n = len(maps)
    m = len(maps[n-1])
    while queue:
      (r, c, cnt) = queue.popleft()
      
      if r == (n-1) and c == (m-1):
        answer = min(answer, cnt)
        break
      
      dr = [-1, 1, 0, 0]
      dc = [0, 0, -1, 1]
      
      for d in range(4):
        nr, nc = r+dr[d], c+dc[d]
        if 0 <= nr < n and 0 <= nc < m and (nr, nc) not in visited and maps[nr][nc] == 1:
          visited.add((nr, nc))
          queue.append((nr, nc, cnt+1))
          
    if answer == float("INF"):
      answer = -1
          
    
    return answer