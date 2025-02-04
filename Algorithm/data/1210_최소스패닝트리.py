import sys
input = sys.stdin.readline

def find(x):
  if parent[x] != x:
    parent[x] = find(parent[x])
    
  return parent[x]

def union(x, y):
  parent[find(x)] = find(y)

V, E = map(int, input().split())
edges = [list(map(int, input().split())) for _ in range(E)]
edges.sort(key=lambda x: x[2])
parent = [i for i in range(V+1)]
cnt = edge_cnt = 0

for A, B, C in edges:
  if find(A) == find(B):
    continue
  
  union(A, B)
  cnt += C
  edge_cnt += 1
  
  if edge_cnt == V-1:
    break
  
print(cnt)