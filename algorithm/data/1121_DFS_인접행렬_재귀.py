V, E = map(int, input().split())

# 그래프 구조화

# 1. 빈 판 만들기
adj_matrix = [[0] * (V+1) for _ in range(V+1)]

# 2. 간선정보 입력받기
for _ in range(E):
  s, e = map(int, input().split())
  adj_matrix[s][e] = 1
  adj_matrix[e][s] = 1
  
# 세팅
visited = set()
trail = []

# 그래프 탐색
def DFS(cur):
  if cur not in visited:
    trail.append(cur)
  visited.add(cur)
  
  for nxt in range(V+1):
    if adj_matrix[cur][nxt] and nxt not in visited:
      DFS(nxt)
      
DFS(1)

print(trail)