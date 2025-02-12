def solution(queue1, queue2):
    queue = [*queue1, *queue2]
    l, r = 0, len(queue1)
    total = sum(queue) / 2
    q_sum = sum(queue1)
    answer = 0
    
    # 두 큐의 합이 홀수일 때 중단
    if (sum(queue) % 2 == 1):
        return -1
    
    # 포인터를 유지할 때까지
    while l < r:
        # 각 큐의 합이 동일할 때
        if q_sum == total:
            break
        
        # 큐1의 합이 절반 and r이 더 이상 움직일 수 없을 때
        if q_sum < total and r == (2 * len(queue1) - 1):
            break
        
        # 큐1 합이 절반보다 클 때
        if q_sum > total:
            # 큐1에서 l 포인터에 해당하는 값 빼기
            q_sum -= queue[l]
            # l 포인터 1 증가
            l += 1
        # 큐1 합이 절반보다 작을 때
        elif q_sum < total:
            # 큐1에서 r 포인터에 해당하는 값 더하기
            q_sum += queue[r]
            # r 포인터 1 증가
            r += 1
        answer += 1
    
    if q_sum != total:
      answer = -1
      
    return answer  
            

queue1 = [3, 2, 7, 2]
queue2 = [4, 6, 5, 1]

print(solution(queue1, queue2))