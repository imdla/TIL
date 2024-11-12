import sys
input = sys.stdin.readline

N = int(input())
# 구조화
# 빈 딕셔너리 제작
sales_info = {}

# 반복문을 돌며
for _ in range(N):
  # 책 제목을 입력받고
  book_name = input()

# 1. 조건문을 통한 분기
  # 만약 딕셔너리에 해당 제목이 없다면 ? => 생성
  if book_name not in sales_info:
    sales_info[book_name] = 1
  # 있다면 ? => 1 더해주기
  else:
    sales_info[book_name] += 1

# 2. get 메서드 활용
  sales_info[book_name] = sales_info.get(book_name, 0) + 1

# 3. defaultdict 활용
from collections import defaultdict
sales_info = defaultdict(int)

for _ in range(N):
  book_name = input()
  sales_info[book_name] += 1

# 4. Counter 활용
from collections import Counter
# 리스트 컴프리헨션
sales_info = [input() for _ in range(N)]
# sales_info = []
# for _ in range(N):
#   book_name = input()
#   sales_info.append(book_name)
sales_info = Counter(sales_info)
print(sales_info)


# 연산
max_sales_cnt = 0
max_sales_book = ''

for name in sales_info:
    if sales_info[name] >= max_sales_book and max_sales_book > name:
      max_sales_book = name

# # items() 활용
for name, cnt in sales_info.items():
    if cnt >= max_sales_book and max_sales_book > name:
      max_sales_book = name

# 딕셔너리 정렬
sorted_sales_info = sorted(sales_info.items(), key=lambda x: (-x[1], x[0]))
print(sorted_sales_info[0][0])

# 조건 1 : 가장 많이 팔렸을 것(value값이 가장 클 것)
# 조건 2 : 제목이 사전순으로 가장 앞설 것(key값이 가장 작을 것)
