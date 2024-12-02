import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

V = int(input())

tree = [[0] for _ in range(V+1)]
for _ in range(V-1):
  i, j = map(int, input().split())
  tree[i].append(j)
  tree[j].append(i)

parent = [0] * (V+1)
visited = [0] * (V+1)
visited[1] = 1

def DFS(p, cur):
  # 방문
  # parent 리스트 갱신
  parent[cur] = p
  
  # 탐색
  # 갈 수 있는 곳을 뽑아서 (인접리스트)
  for nxt in tree[cur]:
    # 방문한적 없다면? (부모)
    if not visited[nxt]:
      visited[nxt] = 1
      DFS(cur, nxt)


DFS(0, 1)
for i in range(2, V+1):
  print(parent[i])