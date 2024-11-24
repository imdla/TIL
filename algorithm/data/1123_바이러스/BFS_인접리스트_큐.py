from collections import deque

import sys
input = sys.stdin.readline

V = int(input())
E = int(input())

adj_lst = [[] for _ in range(V+1)]

for _ in range(E):
  s, e = map(int, input().split())
  adj_lst[s].append(e)
  adj_lst[e].append(s)
  
queue = deque([1])
visited = set()

while queue:
  cur = queue.popleft()
  visited.add(cur)
  
  for nxt in adj_lst[cur]:
    if nxt not in visited:
      queue.append(nxt)
      
ans = len(visited)-1
print(ans)