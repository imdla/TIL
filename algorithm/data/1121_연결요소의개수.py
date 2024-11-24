import sys
input = sys.stdin.readline

def DFS(start):
# 초기 세팅
  stack = [start]

  # 예정지가 빌 때까지
  while stack:
    # 방문
    cur = stack.pop()
    
    # 탐색 (인접행렬)
    for nxt in range(1, V+1):
      # 방문하지 않은 곳이라면?
      if adj_matrix[cur][nxt] and nxt not in visited:
        # 방문 체크하고
        visited.add(nxt)
        # 예정지에 집어넣는다
        stack.append(nxt)

V, E = map(int, (input().split()))

adj_matrix = [[0] * (V+1) for _ in range(V+1)]

for _ in range(E):
  s, e = map(int, input().split())
  adj_matrix[s][e] = 1
  adj_matrix[e][s] = 1

#########################################
visited = set()
cnt = 0

# 정점을 하나씩 돌면서
for node in range(1, V+1):
  # 방문한 적 없는 정점이라면?
  if node not in visited:
    # 방문체크하고
    visited.add(node)
    # DFS
    DFS(node)
    # 1 카운팅
    cnt += 1

print(cnt)