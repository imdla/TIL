T = int(input())

for tc in range(1, T+1):
  N = int(input())
  maze = [list(map(int, input())) for _ in range(N)]
  
  flag = False
  for r in range(N):
    for c in range(N):
      if maze[r][c] == 2:
        sr, sc = r, c
        flag = True
        break
    if flag:
      break
    
  stack = [(sr, sc)]
  visited = set([(sr, sc)])
  ans = 0

  while stack:
    (r, c) = stack.pop()

    if maze[r][c] == 3:
      ans = 1
      break
    
    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]

    for d in range(4):
      nr, nc = r+dr[d], c+dc[d]
      if 0 <= nr < N and 0 <= nc < N and (nr, nc) not in visited and maze[nr][nc] != 1:
        visited.add((nr, nc))
        stack.append((nr, nc))
      
  print(f'#{tc} {ans}')