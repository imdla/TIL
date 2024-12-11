import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

def find(x):
  if parent[x] != x:
    parent[x] = find(parent[x])
    
  return parent[x]

def union(x, y):
  parent[find(x)] = find(y)
  
n, m = map(int, input().split())
parent = list(range(n))

for cnt in range(1, m+1):
  a, b = map(int, input().split())
  
  if find(a) == find(b):
    print(cnt)
    break
  
  union(a, b)
  
else:
  print(0)