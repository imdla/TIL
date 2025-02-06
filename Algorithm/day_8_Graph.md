## <mark color="#fbc956">그래프 자료구조</mark>

### 1. 그래프 (Graph)

- 정점(Vertex)와 간선(edge)로 이루어진 비선형 자료구조

- **정점과 간선** : 그래프를 이루는 기초가 되는 점과 그 점들을 연결하는 선
- **가중치** : 간선에 부여되는 값
- **유방향(일방향) 그래프** : 간선에 방향에 있는 그래프
- **무방향(양방향) 그래프** : 간선에 방향이 없는 그래프
- 희소 그래프 : 정점들 간에 간선이 희소하게 있음
- 밀집 그래프 : 정점들 간에 간선이 밀집되어 있음
- **완전 그래프** : 가능한 모든 정점 사이에 간선이 모두 연결되어 있음
  - E = V (V-1)

## <mark color="#fbc956">그래프 구조화</mark>

- **그래프 구조화 순서**
  1. 빈 판 만들기
  2. 간선 정보 입력 받기
     - 정점(V), 간선(E)

### **인접 행렬로 구조화**

- 정점 간 연결 관계를 2차원 배열로 표현
- 장점
  - 정점 간 연결 확인 효율적
  - 유방향 그래프의 경우 손쉽게 모든 방향 전환 가능(전치)

```python
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
```

### **인접 리스트로 구조화**

- 시작 정점의 인덱스에 해당 정점에서 갈 수 있는 정점들의 리스트 저장
- 장점
  - 완전 탐색 시 탐색 속도 빠름
  - 공간 효율적 사용

```python
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
```
