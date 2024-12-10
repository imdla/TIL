# import sys
# input = sys.stdin.readline
from heapq import heappop, heappush

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def dijkstra():
  # 세팅(출발지, 예정지, 기록지)
  heap = [(cave[0][0], 0, 0)]
  dist = [[float("INF")] * N for _ in range(N)]
  
  # 예정지가 빌 때까지
  while heap:
    # 방문(cnt, r, c)
    cnt, r, c = heappop(heap)
    if r == N-1 and c == N-1:
      return cnt
    # 방문한적 있다면?
    if dist[r][c] != float("INF"):
      continue
    # dist 배열 갱신
    dist[r][c] = cnt
    
    # 탐색
    # 4방향 탐색을 하며
    for d in range(4):
      # 좌표를 찍고
      nr, nc = r+dr[d], c+dc[d]
      # 범위 조건 + 방문한적 없다면?
      if 0 <= nr < N and 0 <= nc < N and dist[nr][nc] == float("INF"):
        # 예정지에 넣어주기
        heappush(heap, (cnt+cave[nr][nc], nr, nc))
        
tc = 1       
while True:
  N = int(input())
  if not N:
    break

  cave = [list(map(int, input().split())) for _ in range(N)]
  
  print(f'Problem {tc}: {dijkstra()}')
  tc += 1
    