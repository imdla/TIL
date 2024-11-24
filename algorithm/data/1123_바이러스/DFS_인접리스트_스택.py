import sys
input = sys.stdin.readline

V = int(input())
E = int(input())

adj_lst = [[] for _ in range(V+1)]

for _ in range(E):
  s, e = map(int, input().split())
  adj_lst[s].append(e)
  adj_lst[e].append(s)
  
stack = [1]
visited = set()

while stack:
  cur = stack.pop()
  visited.add(cur)
  
  for nxt in adj_lst[cur]:
    if nxt not in visited:
      stack.append(nxt)
      
print(len(visited)-1)