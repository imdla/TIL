# 상하좌우 혹은 대각선으로 인접한 8개의 칸에 지뢰가 몇 개 있는지 알려주는 0과 8 사이의 숫자
# 지뢰가 있는 칸이 열렸다면 지뢰가 있는 모든 칸이 별표(*)로 표시

import sys
input = sys.stdin.readline

n = int(input())
petard = [list(input().strip('\n')) for _ in range(n)]
strPetard = [list(input().strip('\n')) for _ in range(n)]

dr = [0, 1, -1, 0, 0, -1, 1, 1, -1]
dc = [0, 0, 0, 1, -1, -1, -1, 1, 1]

def count_petard(r, c):
  d = 0
  count = 0
  
  for _ in range(9):
    nr, nc = r+dr[d], c+dc[d]
    
    if 0 <= nr < n and 0 <= nc < n:
      if petard[nr][nc] == '*':
        count += 1
    d += 1
  return count
    
def make_petard(strPetard):
  for x in range(n):
    for y in range(n):
      if petard[x][y] == '*':
        strPetard[x][y] = '*'
  return strPetard

for r in range(n):
  for c in range(n):
    if strPetard[r][c] == 'x':
      strPetard[r][c] = str(count_petard(r, c))
      if petard[r][c] == '*':
        make_petard(strPetard)

      
for item in strPetard:
  joinItem = ''.join(item)
  print(joinItem)



