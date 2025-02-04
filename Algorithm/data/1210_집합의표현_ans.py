import sys
input = sys.stdin.readline

# 루트 노드 찾기 -> 루트 노드 찾고 돌아오면서 경로 압축(재귀)
def find(x):
  if x != parent[x]:
    parent[x] = find(parent[x])
  
  return parent[x]

def union(x, y):
  if find[x] != find[y]:
    parent[find(x)] = find(y)
    # # 작은 트리가 큰 트리에 붙으면 트리의 깊이가 깊어지지 않음
    # if rank[find[x]] > rank[find[y]]:
    #   parent[find(y)] = find(x)
    # elif rank[find[x]] < rank[find[y]]:
    #   parent[find(x)] = find(y)
    # # 트리의 깊이(rank)가 동일한 트리라면 깊이가 깊어짐
    # else:
    #   parent[find(x)] = find(y)
    #   rank[find(y)] += 1
  

n, m = map(int, input().split())
parent = list(range(n+1))

for _ in range(m):
  c, a, b = map(int, input().split())
  rank = [0] * (n+1)
  
  if c == 0:
    union(a, b)
  
  elif c == 1:
    if find(a) == find(b):
      print("yes")
    else:
      print("no")
    