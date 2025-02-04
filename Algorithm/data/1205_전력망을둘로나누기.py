from collections import deque

def solution(n, wires):
  in_degree = [0]*(n+1)
  adj_matrix = [[0]*(n+1) for _ in range(n+1)]
  for v1, v2 in wires:
    in_degree[v1] += 1
    in_degree[v2] += 1
    adj_matrix[v1][v2] = 1
    adj_matrix[v2][v1] = 1
    
  queue = deque()
  for idx in range(1, n+1):
    if in_degree[idx] == 1:
      queue.append(idx)
      
  answer = float("INF")
  rank = [1]*(n+1)
  
  while queue:
    cur = queue.popleft()
    answer = min(answer, abs(2*rank[cur]-n))
    
    for nxt in range(1, n+1):
      if adj_matrix[cur][nxt]:
        in_degree[nxt] -= 1
        rank[nxt] += rank[cur]
        if in_degree[nxt] == 1:
          queue.append(nxt)
          
  return answer