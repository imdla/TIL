T = int(input())

for tc in range(1, T+1):
  N, M = map(int, input().split())
  board = [list(input()) for _ in range(N)]
  
  for y in range(N-M+1):
    for r in range(N):
      r_word = []
      c_word = []
      new_word = []
      
      for c in range(y, y+M):
        r_word.append(board[r][c])
        c_word.append(board[c][r])
      # print(c_word)  
      
      if r_word == r_word[::-1]:
        ans = "".join(r_word)
        break
      
      if c_word == c_word[::-1]:
        ans = "".join(c_word)
        break
    
  
  print(f'#{tc} {ans}')

