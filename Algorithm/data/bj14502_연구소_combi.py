import sys
import copy
input = sys.stdin.readline
from collections import deque
from itertools import combinations

N, M = map(int, input().split())
adj_matrix = [list(map(int, input().split())) for _ in range(N)]
empty_lst = []

queue = deque()
for r in range(N):
  for c in range(M):
    if adj_matrix[r][c] == 2:
      queue.append((r, c))
    elif adj_matrix[r][c] == 0:
      empty_lst.append((r, c))

dc = [-1, 1, 0, 0]
dr = [0, 0, -1, 1]
ans = -float("INF")

def make_wall(combi):
  global ans
  adj_matrix2 = copy.deepcopy(adj_matrix)
  queue2 = copy.deepcopy(queue)
  
  # 벽 세우기기
  for (r, c) in combi:
    adj_matrix2[r][c] = 1
  
  # 바이러스 퍼뜨리기기
  while queue2:
    r, c = queue2.popleft()
    for d in range(4):
      nr, nc = r+dr[d], c+dc[d]
      if 0 <= nr < N and 0 <= nc < M and adj_matrix2[nr][nc] == 0:
        adj_matrix2[nr][nc] = 2
        queue2.append((nr, nc))
          
  cnt = 0
  for x in range(N):
    for y in range(M):
      if adj_matrix2[x][y] == 0:
        cnt += 1

  ans = max(ans, cnt)

# 벽 만들기
combis = list(combinations(empty_lst, 3))
for combi in combis:
  make_wall(combi)
  
print(ans)