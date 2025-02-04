from collections import deque
import sys
input = sys.stdin.readline

V = int(input())
E = int(input())

adj_lst = [[] for _ in range(V+1)]

for _ in range(E):
  s, e = map(int, input().split())
  adj_lst[s].append(e)
  adj_lst[e].append(s)


# DFS => queue를 stack으로만 바꿔주면 됨
# BFS
# 초기 세팅 (출발지, 기록지, 예정지)
queue = deque([1])
# visited = set([1])
visited = [0] * (V+1)
visited[1] = 1

# 예정지가 빌 때까지
while queue:
  # 예정지에서 꺼내서 방문
  cur = queue.popleft()
  
  # 방문한 곳에서 갈 수 있는 곳 탐색 (인접리스트)
  for nxt in adj_lst[cur]:
    # 방문한 적이 없는 곳이라면? (기록지)
    # if nxt not in visited:
    if not visited[nxt]:
      # 방문 체크하고
      # visited.add(nxt)
      visited[nxt] = 1
      # 예정지에 집어넣는다
      queue.append(nxt)

ans = sum(visited) - 1
print(ans)