V, E = map(int, input().split())

# 그래프 구조화

# 1. 빈 판 만들기
adj_lst = [[] for _ in range(V+1)]

# 2. 간선 정보 입력받기
for _ in range(E):
  s, e = map(int, input().split())
  adj_lst[s].append(e)
  adj_lst[e].append(s)
  
# 세팅
stack = [1]       # 방문예정지(스택)
visited = set()   # 방문기록지(집합)
trail = []        # 궤적을 담아줄 리스트

# 그래프 탐색
while stack:
  cur = stack.pop()
  if cur not in visited:
    trail.append(cur)
  visited.add(cur)
  
  for nxt in adj_lst[cur]:
    if nxt not in visited:
      stack.append(nxt)
      
print(trail)