from itertools import permutations

my_lst = ['A', 'B', 'C']
visited = [0, 0, 0]

# 순열
# 완전 그래프에서 가능한 궤적을 하나씩 다 그리는 것

def DFS(depth):
  if depth == 3:
    return
  
  for nxt in range(3):
    if not visited[nxt]:
      visited[nxt] = 1
      DFS(depth+1)
      visited[nxt] = 0
      
DFS(0)
print(visited)