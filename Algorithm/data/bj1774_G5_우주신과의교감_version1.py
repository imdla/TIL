import sys
import math
input = sys.stdin.readline

N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
connect = [list(map(int, input().split())) for _ in range(M)]
parent = [set() for _ in range(N)]

# 연결 통로 parent에 업데이트
for x, y in connect:
  parent[x-1].add(y)
  parent[y-1].update(parent[x-1])
  parent[y-1].add(x)
  parent[x-1].update(parent[y-1])

# 정점마다 거리 -> lst
lst = []
dist = [0] * (N+1)
for x in range(1, N):
  for y in range(x+1, N+1):
    a = board[x-1][0] - board[y-1][0]
    b = board[x-1][1] - board[y-1][1]
    dist = round(math.sqrt((a*a) + (b*b)), 3)
    
    lst.append((dist, x, y))
lst.sort()

# 정점 연결 -> parent에 업데이트트
def union(x, y):
  parent[x-1].add(y)
  parent[y-1].update(parent[x-1])
  parent[y-1].add(x)
  parent[x-1].update(parent[y-1])
  
print(parent)
# print(lst)
ans = 0
edge_cnt = 0
# 거리가 짧은 곳부터 순회하며
for item in lst:
  c, a, b = item
  
  # a와 b가 연결되어 있으면
  if a in parent[b-1]:
    edge_cnt += 1
    continue
  
  # a와 b가 연결되어 있지 않으면
  union(a, b)
  # print(parent)
  print(edge_cnt, a, b, ans, c)
  edge_cnt += 1
  ans += c

  # 종료 조건
  # 연결 통로 리스트인 parent를 순회하며
  # 빈 집합이 없으면 break
  empty_p = 0
  for p in parent:
    if not p:
      empty_p += 1
  if empty_p == 0:
    break

print(f'{ans:.2f}')