import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

def find(x):
  if parent[x] != x:
    parent[x] = find(parent[x])
  
  return parent[x]

def union(x, y):
  if rank[find(x)] < rank[find(y)]:
    parent[find(x)] = find(y)
  elif rank[find(y)] < rank[find(x)]:
    parent[find(y)] = find(x)
  else:
    parent[find(y)] = find(x)
    rank[find(x)] += 1

n, m = map(int, input().split())
parent = list(range(n+1))
rank = [0] * (n+1)

for _ in range(m):
  oper, a, b = map(int, input().split())
  
  if oper == 0:
    union(a, b)
  elif oper == 1:
    if find(a) == find(b):
      print("yes")
    else:
      print("no")