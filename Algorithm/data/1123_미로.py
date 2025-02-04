T = int(input())

# 델타 세팅
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def DFS(r, c):
  global ans
  # 방문
  if maze[r][c] == 3:
    ans = 1
    return
  
  # 탐색 - 델타 탐색
  for d in range(4):
    # 새로운 좌표 찍어보기
    nr, nc = r+dr[d], c+dc[d]
    
    # 유효한지 검사(범위 조건, 1이면 안됨, 아직 방문하지 않았을 것)
    if 0 <= nr < N and 0 <= nc < N and (nr, nc) not in visited and maze[nr][nc] != 1:
      # 유효하다면
      # 방문
      visited.add((nr, nc))
      DFS(nr, nc)
  

for tc in range(1, T+1):
  N = int(input())
  maze = [list(map(int, input())) for _ in range(N)]
  
  ans = 0

  # 세팅(출발지, 예정지, 기록지)
  flag = False
  for r in range(N):
    for c in range(N):
      if maze[r][c] == 2:
        sr, sc = r, c
        flag = True
        break
    if flag:
      break
  
  visited = set([(sr, sc)])
  DFS(sr, sc)

  print(f'#{tc} {ans}')