import sys
input = sys.stdin.readline

T = int(input())

for _ in range(T):
  M, N, K = map(int, input().split())
  
  field = set()
  for _ in range(K):
    c, r = map(int, input().split())
    field.add((r, c))
    # {(2, 4), (0, 4), (2, 1), (2, 0), (2, 3), (2, 2)}
  
  cnt = 0
  
  dr = [-1, 1, 0, 0]
  dc = [0, 0, -1, 1]
  
  while field:
    sr, sc = field.pop()
    stack = [(sr, sc)]
    
    while stack:
      r, c = stack.pop()
      
      for d in range(4):
        nr, nc = r+dr[d], c+dc[d]
        if (nr, nc) in field:
          stack.append((nr, nc))
          field.discard((nr, nc))
          
    cnt += 1
    
  print(cnt)