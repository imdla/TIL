# 각 던전마다 탐험을 시작하기 위해 필요한 "최소 필요 피로도"
  # 해당 던전을 탐험하기 위해 가지고 있어야 하는 최소한의 피로도
# 던전 탐험을 마쳤을 때 소모되는 "소모 피로도"
  # 던전을 탐험한 후 소모되는 피로도
  
# 유저의 현재 피로도 k
# 유저가 탐험할수 있는 최대 던전 수
# [최소 필요 피로도, 소모 피로도]

def solution(k, dungeons):
    visited = [0]*len(dungeons)
    answer = -1
    ans = []
    
    def perm(k):
      nonlocal answer
      answer = max(answer, len(ans))

      
      for idx in range(len(dungeons)):
        nec, con = dungeons[idx]
        if not visited[idx] and k >= nec:
          visited[idx] = 1
          
          ans.append(dungeons[idx])
          k -= con
          perm(k)
          
          ans.pop()
          k += con
          visited[idx] = 0
          
    perm(0, k)
    print(answer)
    return answer

k = 80
dungeons = [[80,20],[50,40],[30,10]]

print(solution(k, dungeons))