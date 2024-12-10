import sys
input = sys.stdin.readline
from heapq import heappop, heappush

N = int(input())
M = int(input())

road = [[] for _ in range(N+1)]
for _ in range(M):
  s, e, w = map(int, input().split())
  road[s].append((w, e))
  
S, E = map(int, input().split())

def dijkstra(start):
  heap = [(0, start)]
  dist = [float("INF")]*(N+1)
  
  while heap:
    cnt, cur = heappop(heap)
    if dist[cur] != float("INF"):
      continue
    dist[cur] = cnt
    
    for w, nxt in road[cur]:
      if dist[nxt] == float("INF"):
        heappush(heap, (cnt+w, nxt))
        
  return dist[E]

print(dijkstra(S))