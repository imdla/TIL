import sys
input = sys.stdin.readline

R, C = map(int, input().split())

field = [list(input().strip('\n')) for _ in range(R)]

v_feild = set()
o_feild = set()

for r in range(R):
  for c in range(C):
    if field[r][c] == 'o':
      o_feild.add((r, c))
    elif field[r][c] == 'v':
      v_feild.add((r, c))
      
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

visited = set()
stack = []
ans = [0, 0]
    
while o_feild or v_feild:
  v_cnt = 0
  o_cnt = 0
  
  if len(o_feild) != 0:
    r, c = o_feild.pop()  
    o_cnt += 1
  else:
    r, c = v_feild.pop()
    v_cnt += 1
  
  visited.add((r, c))
  stack.append((r, c))

  # 첫번째 양의 영역
  while stack:
    (r, c) = stack.pop()
    
    for d in range(4):
      nr, nc = r+dr[d], c+dc[d]
      if 0 <= nr < R and 0 <= nc < C and (nr, nc) not in visited and field[nr][nc] != '#':
        visited.add((nr, nc))
        stack.append((nr, nc))
        if field[nr][nc] == 'o':
          o_feild.discard((nr, nc))
          o_cnt += 1
        elif field[nr][nc] == 'v':
          v_feild.discard((nr, nc))
          v_cnt += 1

  if v_cnt >= o_cnt:
    ans[1] += v_cnt
  else:
    ans[0] += o_cnt

print(f'{ans[0]} {ans[1]}')