# 인접 행렬 구현

# 7 (정점)
# 7 (간선)
# 0 1
# 1 2
# 0 4
# 4 1
# 4 5
# 3 6
# 6 5
# 출발지 -> 도착지 -> 가중치 순서 입력 제공

V = int(input())
E = int(input())
# 1. 빈 판 만들기(V*V)
adj_matrix = [[0]*V for _ in range(V)]

# 2. 간선정보 입력 받기
for _ in range(E):
  s, e = map(int, input().split())
  adj_matrix[s][e] = 1 # (가중치일 때는 1 대신 가중치 할당)
  # 양방향 그래프
  adj_matrix[e][s] = 1

print(adj_matrix)