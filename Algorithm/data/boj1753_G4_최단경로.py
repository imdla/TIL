import sys
input = sys.stdin.readline
from heapq import heappop, heappush

V, E = map(int, input().split())
K = int(input())

edges = [[] for _ in range(V+1)]
for _ in range(E):
  u, v, w = map(int, input().split())
  edges[u].append((w, v))

def dijkstra(start):
  heap = [(0, start)]
  dist = [float("INF")]*(V+1)
  
  while heap:
    cnt, cur = heappop(heap)
    if dist[cur] != float("INF"):
      continue
    dist[cur] = cnt
    
    for w, nxt in edges[cur]:
      if dist[nxt] == float("INF"):
        heappush(heap, (cnt+w, nxt))
        
  for idx in range(1, V+1):
    if (dist[idx] == float("INF")):
      print("INF")
    else:
      print(dist[idx])

dijkstra(K)