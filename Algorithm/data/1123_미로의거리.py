from collections import deque

T = int(input())

for tc in range(1, T+1):
  N = int(input())
  maze = [list(map(int, input())) for _ in range(N)]
  
  ans = 0
  
  # 세팅 (출발지, 예정지, 기록지)
  flag = False
  for r in range(N):
    for c in range(N):
      if maze[r][c] == 2:
        flag = True
        sr, sc = r, c
        break
    if flag:
      break
  
  visited = set([(sr, sc)])
  queue = deque([(sr, sc, -1)])
  
  # 예정지가 빌 때까지
  while queue:
    # 방문 (큐에서 뽑기)
    (r, c, cnt) = queue.popleft()
    # 도착지인지 검토
    if maze[r][c] == 3:
      ans = 1
      break 
    
    # 탐색 - 델타 탐색
    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]
    # 인접한 방향 탐색해서
    for d in range(4):
      nr, nc = r+dr[d], c+dc[d]
      # 유효하고, 방문한적 없고, 벽이 아니라면
      if 0 <= nr < N and 0 <= nc < N and (nr, nc) not in visited and maze[nr][nc] != 1:
        # 방문 체크하고
        visited.add((nr, nc))
        # 방문 예정지에 넣기
        queue.append((nr, nc, cnt+1))
  
  if ans == 1:
    print(f'#{tc} {cnt}')
  else:
    print(f"#{tc} {ans}")