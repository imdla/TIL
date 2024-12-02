T = int(input())

def perm(depth, cnt):
    global ans
    # 마지막 뎁스에 도달했다면
    if depth == N:
      # 정답을 갱신하고 리턴
      ans = min(ans, cnt)
      return
    
    if cnt >= ans:
      return
    
    # 다음에 갈 곳을 탐색하며,
    for idx in range(N):
      # 방문한 적 없다면
      if not visited[idx]:
        # 방문체크하고
        visited[idx] = 1
        # 방문
        perm(depth+1, cnt+nums[depth][idx])
        # 방문체크해제(중요)
        visited[idx] = 0


for tc in range(1, T+1):
  N = int(input())
  nums = [list(map(int, input().split())) for _ in range(N)]
  
  visited = [0 for _ in range(N)]
  ans = float("INF")
  
  perm(0, 0)
  
  print(f'#{tc} {ans}')