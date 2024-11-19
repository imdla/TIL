T = int(input())

# 델타 세팅
for tc in range(1, T+1):
  N, M = map(int, input().split())
  board = [list(map(int, input().split())) for _ in range(N)]
  
  dr = [-1, 1, 0, 0]
  dc = [0, 0, -1, 1]
  ans = 0

  # 순회하면서
  for r in range(N):
    for c in range(M):
      tmp = board[r][c]
      
      for d in range(4):
        nr, nc = r+dr[d], c+dc[d]
        if 0 <= nr < N and 0 <= nc < M:
          tmp += board[nr][nc]
      
      ans = max(tmp, ans)
    # tmp = 0
    # r, c 에서 델타탐색(4방향)

    # ans 갱신
  
  # ans 출력
  print(f'#{tc} {ans}')