N = int(input())
adj_matrix = [list(map(int, input().split())) for _ in range(N)]

def perm(depth, cur, cost):
  global ans
  # 방문
  # 종료조건 (N개의 도시를 다 돌았는가?)
  if depth == N:
    # cur에서 0으로 돌아갈 수 있다면
    if adj_matrix[cur][0]:
      # cur-0 비용 더해서 정답 갱신
      cost += adj_matrix[cur][0]
      ans = min(ans, cost)
    # 리턴
    return
  
  # 백트래킹
  if cost >= ans:
    # cost와 ans 비교
    return
  
  
  # 탐색
  # cur에서 갈 수 있는 곳을 찾아서(adj_matrix)
  for nxt in range(N):
    # 갈 수 있다면? (방문한적 없고, 0이 아니라면?)
    if adj_matrix[cur][nxt] and not visited[nxt]:
      # 방문 체크하고
      visited[nxt] = 1
      # 방문 (재귀)
      perm(depth+1, nxt, cost+adj_matrix[cur][nxt])
      # 방문 체크 해제
      visited[nxt] = 0

# 초기세팅(출발지, 기록지, 예정지)
ans = float("INF")
visited = [0]*N
visited[0] = 1
perm(1, 0, 0)

print(ans)