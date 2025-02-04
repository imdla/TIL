T = int(input())

for tc in range(1, T+1):
  N = int(input())
  board = [ list([0] * N) for _ in range(N) ]
  r, c = 0, 0
  dr = [0, 1, 0, -1]
  dc = [1, 0, -1, 0]
  d = 0
  board[0][0] = 1
  num = 2

  while num <= (N*N):
    nr, nc = r+dr[d], c+dc[d]
    
    if 0 <= nr < N and 0 <= nc < N and board[nr][nc] == 0:
      r, c = nr, nc
      board[r][c] = num
      num += 1
    else:
      d = (d + 1) % 4
  
  print(f'#{tc}')
  for num in board:
    print(*num)
