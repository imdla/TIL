# import sys
# imput = sys.stdin.readline

N, M = map(int, input().split())
nums = list(map(int, input().split()))

# 세팅
# 두 포인터를 각각의 변수에 할당
i = j = 0
# 정답변수, 임시값
tmp = ans = 0

# 계속 반복(while)
while True:
  # tmp < M
  if tmp < M:
    # j 포인터가 N에 가있으면
    if j == N:
      # 탐색 종료
      break
    # 오른쪽 포인터가 가리키는 값을 tmp에 더하고
    tmp += nums[j]
    # 오른쪽 포인터 이동
    j += 1
  
  # tmp > M
  elif tmp > M:
    # 왼쪽 포인터가 가리키는 값을 tmp에서 빼고
    tmp -= nums[i]
    # 왼쪽 포인터를 이동
    i += 1
  
  # tmp == M
  elif tmp == M:
    # 정답 하나 세어주고
    ans += 1
    # 왼쪽 포인터가 가리키는 값을 tmp에서 빼고
    tmp -= nums[i]
    # 왼쪽 포인터를 이동
    i += 1

print(ans)