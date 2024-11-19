T = int(input())
# 델타 세팅
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

for tc in range(1, T+1):
  N, M = map(int, input().split())
  # 패딩
  # 1. (N+2)(M+2) 빈 판 제작
  matrix = [[0] * (M+2) for _ in range(N+2)]
  # 2. 입력 받아서 한 줄씩 교체
  for i in range(1, N+1):
    matrix[i][1:M+1] = list(map(int, input().split()))
  print(matrix)

  # matrix = [list(map(int, input().split())) for _ in range(N)]
  
  # 초기 변수 세팅
  ans = 0
  
  # matrix를 순회하면서(r, c)
  for r in range(1, N+1):
    for c in range(1, M+1):
      # tmp = matrix[r][c]
      tmp = matrix[r][c]
      # 그 칸을 기준으로 델타탐색
      for d in range(4):
        # 좌표 찍기
        nr, nc = r+dr[d], c+dc[d]
        # tmp에 더해주기
        tmp += matrix[nr][nc]
    # 정답 갱신 시도
      ans = max(ans, tmp)    
  # ans 출력
  print(f'#{tc} {ans}')
  
  # # matrix를 순회하면서(r, c)
  # for r in range(N):
  #   for c in range(M):
  #     # tmp = matrix[r][c]
  #     tmp = matrix[r][c]
  #     # 그 칸을 기준으로 델타탐색
  #     for d in range(4):
  #       # 좌표 찍기
  #       nr, nc = r+dr[d], c+dc[d]
  #       # 유효성 검사
  #       if 0 <= nr < N and 0 <= nc < M:
  #         # tmp에 더해주기
  #         tmp += matrix[nr][nc]
  #   # 정답 갱신 시도
  #       ans = max(ans, tmp)    
  # # ans 출력
  # print(f'#{tc} {ans}')