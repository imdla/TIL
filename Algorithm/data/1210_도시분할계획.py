import sys
input = sys.stdin.readline

def find(x):
  if parent[x] != x:
    parent[x] = find(parent[x])
    
  return parent[x]

def union(x, y):
  parent[find(x)] = find(y)

N, M = map(int, input().split())
edges = [list(map(int, input().split())) for _ in range(M)]
edges.sort(key=lambda x: x[2])
parent = list(range(N+1))
road_cnt = cost = 0

for A, B, C in edges:
  if find(A) == find(B):
    continue
  
  union(A, B)
  road_cnt += 1
  cost += C
  
  if road_cnt == N-1:
    cost -= C
    break

print(cost)