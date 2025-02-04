T = int(input())

def find(x):
  if parent[x] != x:
    parent[x] = find(parent[x])
    
  return parent[x]

def union(x, y):
  parent[find(x)] = find(y)

for tc in range(1, T+1):
  N, M = map(int, input().split())
  pairs = list(map(int, input().split()))
  parent = list(range(N+1))
  
  while pairs:
    a, b = pairs.pop(), pairs.pop()
    
    if find(a) == find(b):
      continue
    
    union(a, b)
  
  for idx in range(1, N+1):
    find(idx)
    
  print(f'#{tc} {len(set(parent))-1}')