import sys
import math
input = sys.stdin.readline

def find(x):
  if x != parent[x]:
    parent[x] = find(parent[x])
  return parent[x]

def union(x, y):
  x_root, y_root = find(x), find(y)
  
  if x_root == y_root:
    return False
  
  if rank[x_root] > rank[y_root]:
    parent[y_root] = x_root
  elif rank[x_root] < rank[y_root]:
    parent[x_root] = y_root
  else:
    parent[x_root] = y_root
    rank[y_root] += 1
  
  return True

N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
parent = list(range(N+1))
rank = [0] * (N + 1)
edges = []
total = counts = 0

# 이미 연결된 통로 병합
for _ in range(M):
  x, y = map(int, input().split())
  union(x, y)

# 정점 사이마다 거리 계산
for i in range(N):
  x1, y1 = board[i]
  
  for j in range(i+1, N):
    x2, y2 = board[j]
  
    a = abs(x1-x2)
    b = abs(y1-y2)
    dist = math.sqrt((a*a) + (b*b))
    edges.append((dist, i+1, j+1))
edges.sort()
# print(edges)

for dist, a, b in edges:
  if union(a, b):
    # print(f'a: {a}, b: {b}')
    total += dist
    counts += 1
    
    if counts == N-1:
      break
    
print(f'{total:.2f}')
# print(board)