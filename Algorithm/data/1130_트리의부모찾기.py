import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

N = int(input())

tree = [[0] for _ in range(N+1)]
for _ in range(N-1):
  i, j = map(int, input().split())
  tree[i].append(j)
  tree[j].append(i)
  
parent = [0] * (N+1)
visited = [0] * (N+1)
visited[1] = 1

def DFS(p, cur):
  parent[cur] = p
  
  for nxt in tree[cur]:
    if not visited[nxt]:
      visited[nxt] = 1
      DFS(cur, nxt)
      
DFS(0, 1)
for i in range(2, N+1):
  print(parent[i])