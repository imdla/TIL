def solution(k, dungeons):
  answer = -1
  visited = [0] * len(dungeons)
  
  def perm(depth, k):
    nonlocal answer
    # 방문
    answer = max(depth, answer)
    
    # 탐색 (갈 수 있는 곳을 찾아서)
    for i in range(len(dungeons)):
      # 갈 수 있다면?
      if not visited[i] and k >= dungeons[i][0]:
        # 방문 체크하고
        visited[i] = 1
        # 바로 방문
        perm(depth+1, k-dungeons[i][1])
        # 방문 체크 해제
        visited[i] = 0
        
  perm(0, k)
  return answer