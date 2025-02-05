import sys
input = sys.stdin.readline

N, M = map(int, input().split())
# (r, c, d)
# d -> 0: 북쪽, 1: 동쪽, 2: 남쪽, 3: 서쪽
# 0: 상, 1: 우, 2: 하, 3: 좌
robot = tuple(map(int, input().split()))
# 0: 청소되지 않음, 1: 벽
state = [list(map(int, input().split())) for _ in range(N)]

# 0: 상, 1: 우, 2: 하, 3: 좌
# 0 기준 : 상 -> 좌 -> 하 -> 우 (0 -> 3 -> 2 -> 1)
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]
# d = 0
# rotate = d % 4

empty = 0
for r in range(N):
  for c in range(M):
    if state[r][c] == 0:
      empty += 1

cnt = 0
(r, c, d) = robot
flag = True
while empty and flag:
  # 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
  if state[r][c] == 0:
    state[r][c] = 2
    cnt += 1
    empty -= 1
  
  around = 0
  for i in range(4):
    nr = r+dr[i]
    nc = c+dc[i]
  # 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
    if 0 <= nr < N and 0 <= nc < M and state[nr][nc] == 0:
      around += 1
    # 3-1. 반시계 방향으로 90도 회전한다.
      d = (d-1) % 4
    # 3-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
      fr = r+dr[d]
      fc = c+dc[d]
      if 0 <= fr < N and 0 <= fc < M and state[fr][fc] == 0:
        # print(f'r: {r}, c: {c}, d: {d} / fr: {fr}, fc: {fc}')
        r, c = fr, fc
    # 3-3. 1번으로 돌아간다.
        break
      
      
  # 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
  if around == 0:
    bd = (d-2) % 4
    br = r+dr[bd]
    bc = c+dc[bd]
    # 2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
    if 0 <= br < N and 0 <= bc < M and state[br][bc] == 2:
      r, c = br, bc
    # 2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
    else:
      flag = False
    
print(cnt)