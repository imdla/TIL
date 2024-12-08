from collections import deque

def solution(maps):
  n = len(maps)
  m = len(maps[0])
  # 패딩
  pad_maps = [[0]*(m+2) for _ in range(n+2)]
  for i in range(1, n+1):
    pad_maps[i][1:m+1] = maps[i-1]
  
  # 초기 세팅 (출발지, 예정지, 기록지)
  queue = deque([(0, 0, 1)])
  # queue = deque([(1, 1, 1)])
  visited = set([(0, 0)])
  # visited = set([(1, 1)])
  # pad_maps[1][1] = 0
  
  dr = [-1, 1, 0, 0]
  dc = [0, 0, -1, 1]
  answer = -1
  # 예정지 빌 때까지
  while queue:
    # 방문
    r, c, cnt = queue.popleft()
    if r == n-1 and c == m-1:
    # if r == n and c == m:
      answer = cnt
    
    # 탐색
    for d in range(4):
      # 좌표를 찍고
      nr, nc = r+dr[d], c+dc[d]
      # 갈 수 있다면?
      if 0 <= nr < n and 0 <= nc < m and (nr, nc) not in visited and maps[nr][nc] == 1:
      # if (nr, nc) not in visited and pad_maps[nr][nc] == 1:
        # 방문 체크
        visited.add((nr, nc))
        # pad_maps[nr][nc] = 0
        # 예정지에 넣기
        queue.append((nr, nc, cnt+1))
        
  return answer