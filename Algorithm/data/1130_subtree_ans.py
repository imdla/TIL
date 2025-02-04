T = int(input())

for tc in range(1, T+1):
  E, M = map(int, input().split())

  edges = list(map(int, input().split()))
  L = [0]*(E+2)
  R = [0]*(E+2)

  # 구조화
  for i in range(E):
    p, c = edges[i*2], edges[i*2+1]
    if not L[p]:
      L[p] = c
    else:
      R[p] = c
    
  cnt = 0
  
  # DFS(N)
  def DFS(cur):
    global cnt
    # 방문
    # 카운팅+1
    cnt += 1
    
    # 탐색
    # 갈 수 있는 곳(자식노드)을 찾아서
    # 왼쪽 자식 노드가 있다면?
    if L[cur]:
      # 방문
      DFS(L[cur])
    # 오른쪽 자식 노드가 있다면?
    if R[cur]:
      # 방문
      DFS(R[cur])
  
  DFS(M)
  print(f'#{tc} {cnt}')