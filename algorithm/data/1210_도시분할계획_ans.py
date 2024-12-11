import sys
input = sys.stdin.readline

def find(x):
  if x != parent[x]:
    parent[x] = find(parent[x])
  
  return parent[x]

def union(x, y):
  parent[find(x)] = find(y)

N, M = map(int, input().split())
edges = [list(map(int, input().split())) for _ in range(M)]
edges.sort(key=lambda x: x[2])
parent = [i for i in range(N+1)]
cnt = ans = 0

# 간선을 하나씩 꺼내서
for a, b, w in edges:
  # 연결할 수 없다면(사이클이 생긴다면)?
  if find(a) == find(b):
    # 그냥 넘어간다
    continue
    
  # 연결할 수 있다면(사이클이 생기지 않는다면)?
  # 연결하고
  union(a, b)
  # 간선 하나 세어주기
  cnt += 1
  # 가중치를 더해준다
  ans += w
    
  # 간선을 몇 개 뽑았는지 검토
  if cnt == N-1:
    ans -= w  
    # N-1개 뽑았다면 ? => 종료
    break

print(ans)