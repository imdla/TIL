# 인접리스트
# 도착지의 번호를 해당 출발지 행에 저장

V = int(input())
E = int(input())
# 1. 빈 판 만들기(빈 리스트 V개가 들어있는 이차원 리스트)
adj_lst = [ [] for _ in range(V) ]

# 2. 간선정보 입력받기
for _ in range(E):
  s, e = map(int, input().split())
  # 가중치 있을 때
  s, e, w= map(int, input().split())
  adj_lst[s].append(e)
  # 양방향 그래프일 때
  adj_lst[e].append(s)
  # 가중치 있을 때
  adj_lst[e].append((w, s))
  
print(adj_lst)