T = int(input())

# 1. 공백을 기준으로 자른다.
# 2. 각각을 숫자로 형변환한다.
# 3. 리스트로 감싸 준다.

for tc in range(1, T+1):
  N = int(input())
  nums = list(map(int, input().split()))

  # 초기값 설정
  max_num = -float("INF")
  min_num = float("INF")

  # 반복문을 이용해서 nums 순회하며
  for num in nums:
    # max_num과 방금 뽑은 숫자를 비교 -> 갱신
    if max_num < num:
      max_num = num
    # min_num과 방금 뽑은 숫자를 비교 -> 갱신
    if min_num > num:
      min_num = num
  
  ans = max_num - min_num
  print(f'#{tc} {ans}')

