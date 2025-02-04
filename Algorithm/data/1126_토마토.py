import sys
input = sys.stdin.readline

from collections import deque

M, N = map(int, input().split())

box = [list(map(int, input().split())) for _ in range(N)]

dc = [-1, 1, 0, 0]
dr = [0, 0, -1, 1]

# visited = set()
queue = deque()

day = 0
for r in range(N):
  for c in range(M):
    if box[r][c] == 1:
      queue.append((r, c, day))

while queue:
  (r, c, day) = queue.popleft()
  # visited.add((r, c))
  
  for d in range(4):
    nr, nc = r+dr[d], c+dc[d]
    if 0 <= nr < N and 0 <= nc < M and box[nr][nc] == 0:
      queue.append((nr, nc, day+1))
      box[nr][nc] = 1
      
for r in range(N):
  for c in range(M):
    if box[r][c] == 0:
      day = -1

print(day)         



# 방문하지 않은 곳이 있으면 순회
  # 익은 토마토 1을 기준으로
  # 사방을 돌며
  # 안익은 토마토일 경우(0)
    # 1로 바꿔줌
    # 방문 체크하고
    # 예정지에 넣음
  
