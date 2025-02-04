import sys
input = sys.stdin.readline
from heapq import heappop, heappush

N, E = map(int, input().split())
adj_lst = [[] for _ in range(N+1)]
for _ in range(E):
  a, b, c = map(int, input().split())
  adj_lst[a].append((c, b))
  adj_lst[b].append((c, a))
  
v1, v2 = map(int, input().split())

def dijkstra(start, end):
  heap = [(0, start)]
  dist = [float("INF")] * (N+1)
  
  while heap:
    cnt, cur = heappop(heap)
    
    if dist[cur] != float("INF"):
      continue
    dist[cur] = cnt
    
    for w, nxt in adj_lst[cur]:
      if dist[nxt] == float("INF"):
        heappush(heap, (cnt+w, nxt))
        
  return dist[end]

dist1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N)
dist2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N)

ans = min(dist1, dist2)
if ans >= float("INF"):
  ans = -1
  
print(ans)