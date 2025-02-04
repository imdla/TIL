V = int(input())
E = int(input())

# 그래프 구조화

# 1. 빈 판 만들기
adj_matrix = [[0] * (V+1) for _ in range(V+1)]

# 2. 간선 정보 입력받기
for _ in range(E):
  s, e = map(int, input().split())
  adj_matrix[s][e] = 1
  adj_matrix[e][s] = 1
  

# 세팅
stack = [1]       # 방문 예정지(스택)
visited = set()   # 방문 기록지(집합)
trail = []        # 궤적을 담아줄 리스트

# 그래프 탐색
while stack:
  cur = stack.pop()
  if cur not in visited:
    trail.append(cur)
  visited.add(cur)
  
  for nxt in range(V+1):
    if adj_matrix[cur][nxt] and nxt not in visited:
      stack.append(nxt)

print(trail)