# 1. 함수
  # 로직을 담아서 반복 사용
  # def을 이용하여 정의

# 2. LEGB 규칙
  # 1) local 영역에서(함수 내부에서) global 영역 변수를 재할당할 수 없다
      # 재할당하고 싶을 때에는 global {변수} 처리 
  # 2) local 영엑에서 global 영역 변수를 참조할 수는 있다
  # 3) 참조할 때는 LEGB 순서로 참조한다
      # (Local Enclosed Global Built-in)

# 3. 재귀함수
  # 1) 자기 자신으로써 정의되는 함수
    # 깊이(depth) -> 할당되는 변수가 조금씩 달라짐
  # 2) 함수 내 재귀의 종료 조건 설정 후 -> 탐색 로직

  # 피보나치 -> O(2^n) 시간 복잡도
  # 중간 연산 결과를 기록(캐싱) -> 해당하는 연산의 기록 가져와서 사용 : 메모이제이션


# 실습 1
# nums = [-5, 2, 7, -3, 2, 10, 8, 6, 5, -1]
# # ans = -float("INF")

# def my_func(idx, ans):
#   # global ans
#   # 재귀의 종료 조건
#   # idx가 len(nums)일 때 종료
#   if idx == len(nums) - 1 :
#     return ans
  
#   # 탐색 및 갱신 로직
#   # nums[idx]와 ans를 비교하여 큰 값으로 갱신
#   ans = max(nums[idx], ans)

#   return my_func(idx+1, ans)

# print(my_func(0, -float("INF")))

# 실습2
# def find(depth):
#   if depth == 10:
#     print('찾았다')
#     return
  
#   print(f"탐색하는 중... 깊이는 {depth}")
  
#   find(depth + 1)

#   print(f"올라가는 중... 깊이는 {depth}")

# find(0)