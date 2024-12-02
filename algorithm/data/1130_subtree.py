T = int(input())

for tc in range(1, T+1):
  E, N = map(int, input().split())
  pair = list(map(int, input().split()))
  
  L = [0]*(E+2)
  R = [0]*(E+2)
  
  for i in range(E):
    p, c = pair[i*2], pair[i*2+1]
    if not L[p]:
      L[p] = c
    else:
      R[p] = c
      
  cnt = 0
  def DFS(cur):
    global cnt
    cnt += 1
    
    if L[cur]:
      DFS(L[cur])
    if R[cur]:
      DFS(R[cur])
  
  DFS(N)
  print(f'#{tc} {cnt}')