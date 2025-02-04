def solution(gems):
  l = r = 0
  gems_cnt = len(set(gems))
  cur = {gems[0] : 1}
  cur_cnt = 1
  answer = []
  
  # 보석을 종류별로 다 카운트
  while cur_cnt < gems_cnt:
    r += 1
    cur[gems[r]] = cur.get(gems[r], 0) + 1
    cur_cnt = len(cur.keys())
  
  while l <= r < len(gems) and (r-l+1) >= gems_cnt:
    # 최소 길이 및 빠른 시작 번호 업데이트
    if not answer:
      answer = [l+1, r+1]
    else:
      cl, cr = answer[0], answer[1]
      if cr-cl > r-l:
        answer = [l+1, r+1]
    
    if cur[gems[l]] > 1:
      cur[gems[l]] -= 1
      l += 1
    elif r < len(gems)-1:
      r += 1
      cur[gems[r]] += 1
    else:
      break
    
  return answer

gems= ["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]
print(solution(gems))