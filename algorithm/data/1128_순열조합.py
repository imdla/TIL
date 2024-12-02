# DFS(깊이 우선 탐색) : 경로 형태, 궤적
  # 외판원 순회 문제 = DFS(재귀)통한 순열 문제
   # : 최소시간 얼마? -> DFS 경로
   # : N개 중 N개 뽑아 줄을 세움
# 순열: M개 중 N개 뽑아 줄을 세움
from itertools import permutations
cities = ['A', 'B', 'C']
visited = [0, 0, 0]
ans = []

# 몇 번 depth에 있는지 중요
# 다음에 갈 곳을 정해서
# 순열
def perm(depth):
# 조합
# def perm(depth, idx):
  if depth == 3:
    print(ans)
    return
  
  # 다음에 갈 곳을 향해서
  # 순열
  for idx in range(3):
  # 조합 (idx -> i로 바꿔줌)
  # for i in range(idx, 3):
    # 방문한 적이 없다면?
    if not visited[idx]:
      # 방문표시하고;
      visited[idx] = 1
      # 방문
      ans.append(cities[idx])
      perm(depth + 1)
      
      # 방문 표시 해제
      ans.pop()
      visited[idx] = 0
      
perm(0)
print(ans)