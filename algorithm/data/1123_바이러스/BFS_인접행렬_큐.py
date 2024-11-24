from collections import deque

import sys
input = sys.stdin.readline

V = int(input())
E = int(input())

adj_matrix = [[0]*(V+1) for _ in range(V+1)]

for _ in range(E):
  s, e = map(int, input().split())
  adj_matrix[s][e] = 1
  adj_matrix[e][s] = 1
  
queue = deque([1])
visited = set()

while queue:
  cur = queue.popleft()
  visited.add(cur)
  
  for nxt in range(V+1):
    if adj_matrix[cur][nxt] and nxt not in visited:
      queue.append(nxt)
      
  print(len(visited)-1)