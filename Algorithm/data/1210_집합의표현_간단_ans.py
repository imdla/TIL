import sys
input = sys.stdin.readline

# 루트 노드 찾기
def find(x):
  while x != parent[x]:
    x = parent[x]
  return x

def union(x, y):
  if find[x] != find[y]:
    parent[find(x)] = find(y)
  

n, m = map(int, input().split())
for _ in range(m):
  c, a, b = map(int, input().split())
  parent = list(range(n+1))
  
  if c == 0:
    union(a, b)
  
  elif c == 1:
    if find(a) == find(b):
      print("yes")
    else:
      print("no")
    