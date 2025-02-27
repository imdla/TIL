from collections import deque

V, E = map(int, input().split())

# 그래프 구조화

# 1. 빈 판 만들기
adj_lst = [[] for _ in range(V+1)]

# 2. 간선정보 입력받기
for _ in range(E):
  s, e = map(int, input().split())
  adj_lst[s].append(e)
  adj_lst[e].append(s)
  
# 세팅
queue = deque([1])
visited = set()
trail = []

# 그래프 탐색
while queue:
  cur = queue.popleft()
  if cur not in visited:
    trail.append(cur)
  visited.add(cur)
  
  for nxt in adj_lst[cur]:
    if nxt not in visited:
      queue.append(nxt)

print(trail)