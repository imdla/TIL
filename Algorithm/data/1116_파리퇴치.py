T = int(input())

for tc in range(1, T+1):
  N, M = map(int, input().split())
  board = [list(map(int, input().split())) for _ in range(N)]
  
  ans = 0
  for r in range(N-M+1):
    for c in range(N-M+1):
      tmp = 0
      for x in range(r, r+M):
        for y in range(c, c+M):
          tmp += board[x][y]
      
      ans = max(ans, tmp)
  
  print(f'#{tc} {ans}')