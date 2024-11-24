import sys
input = sys.stdin.readline

def DFS(start):
  stack = [start]
  while stack:
    cur = stack.pop()
    for nxt in adj_lst[cur]:
      if nxt not in visited:
        visited.add(nxt)
        stack.append(nxt)
    
N, M = map(int, input().split())
adj_lst = [[] for _ in range(N+1)]

for _ in range(M):
  s, e = map(int, input().split())
  adj_lst[s].append(e)
  adj_lst[e].append(s)
  
visited = set()
cnt = 0

for node in range(1, N+1):
  if node not in visited:
    visited.add(node)
    DFS(node)
    cnt += 1

print(cnt)