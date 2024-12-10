import sys
input = sys.stdin.readline
from heapq import heappop, heappush

N = int(input())
M = int(input())

adj_lst = [[] for _ in range(N+1)]
for _ in range(M):
  s, e, w = map(int, input().split())
  adj_lst[s].append((w, e))

S, E = map(int, input().split())

def dijkstra(start):
  # 세팅(출발지, 예정지, 기록지)
  heap = [(0, start)]
  dist = [float("INF")] * (N+1)
  
  # 예정지가 빌 때까지
  while heap:
    # 방문
    cnt, cur = heappop(heap)
    # 이미 방문한 곳이라면?(dist)
    if dist[cur] != float("INF"):
      # continue
      continue
    # dist 갱신(거리 갱신)
    dist[cur] = cnt
    
    # 탐색
    # 지금 위치에서 갈 수 있는 곳을 모두 뽑아서(인접리스트)
    for w, nxt in adj_lst[cur]:
      # 아직 방문하지 않은 곳이라면?(dist)
      if dist[nxt] == float("INF"):
        # 예정지(힙)에 넣기 (넣어줄 때 거리 누적해서 넣기)
        heappush(heap, (cnt+w, nxt))
        
  return dist[E]

print(dijkstra(S))