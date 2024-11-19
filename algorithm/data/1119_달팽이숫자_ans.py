T = int(input())

for tc in range(1, T+1):
  N = int(input())
  
  # N*N짜리 빈 판 제작
  board = [[0]*N for _ in range(N)]
  print(board)
  
  # 초기값 세팅
  r, c = 0, 0
  d = 0
  dc = [1, 0, -1, 0]
  dn = [0, 1, 0, -1]
  num = 1
  
  # 1~N*N까지 반복하며
  for num in range(1, N**2+1):
    # r, c에 숫자 입력
    board[r][c] = num
    # 새로운 좌표 찍기
    nr, nc = r+dn[d], c+dc[d]
    # 좌표 유효성 검사 (범위, 방문 여부)
    if 0 <= nr < N and 0 <= nc < N and not board[nr][nc]:
      # 유효하다면? -> 이동
      n, c = nr, nc
      continue
      # 유효하지 않다면? -> 방향 전환 -> 새로운 좌표 이동
    else:
      d = (d + 1) % 4