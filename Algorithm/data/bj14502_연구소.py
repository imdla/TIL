import sys
import copy
input = sys.stdin.readline
from collections import deque

N, M = map(int, input().split())
adj_matrix = [list(map(int, input().split())) for _ in range(N)]

queue = deque()
for r in range(N):
  for c in range(M):
    if adj_matrix[r][c] == 2:
      queue.append((r, c))

dc = [-1, 1, 0, 0]
dr = [0, 0, -1, 1]
ans = -float("INF")

# 벽 만들기
def make_wall(depth, idx):
  if depth == 3:
    dfs()
    return
  
  # combination, permutation
  # 중복 조합 ?! 검사하기
  for r in range(idx, N):
    for c in range(M):
      if adj_matrix[r][c] == 0:
        adj_matrix[r][c] = 1
        make_wall(depth+1, r)
        adj_matrix[r][c] = 0
        
# 바이러스 퍼뜨리기기
def dfs():
  global ans
  adj_matrix2 = copy.deepcopy(adj_matrix)
  queue2 = copy.deepcopy(queue)
  
  while queue2:
    r, c = queue2.popleft()
    for d in range(4):
      nr, nc = r+dr[d], c+dc[d]
      if 0 <= nr < N and 0 <= nc < M and adj_matrix2[nr][nc] == 0:
        adj_matrix2[nr][nc] = 2
        queue2.append((nr, nc))
        
         # 가지치기 (최소값 + dfs = 백트래킹)
          
  cnt = 0
  for x in range(N):
    for y in range(M):
      if adj_matrix2[x][y] == 0:
        cnt += 1

  ans = max(ans, cnt)
  
make_wall(0, 0)
print(ans)