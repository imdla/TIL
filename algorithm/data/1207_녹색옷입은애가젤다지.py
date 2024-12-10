import sys
input = sys.stdin.readline
from heapq import heappop, heappush

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def dijkstra():
  heap = [(road[0][0], 0, 0)]
  dist = [[float("INF")]*N for _ in range(N)]
  
  while heap:
    cnt, r, c = heappop(heap)
    if r == N-1 and c == N-1:
      return cnt    
    if dist[r][c] != float("INF"):
      continue
    dist[r][c] = cnt

    for d in range(4):
      nr, nc = r+dr[d], c+dc[d]
      if 0 <= nr < N and 0 <= nc < N and dist[nr][nc] == float("INF"):
        heappush(heap, (cnt+road[nr][nc], nr, nc))
    

tc = 1
while True:
  N = int(input())
  if not N:
    break
  
  road = [list(map(int, input().split())) for _ in range(N)]
  
  print(f'Problem {tc}: {dijkstra()}')
  tc += 1