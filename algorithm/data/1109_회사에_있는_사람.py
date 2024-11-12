import sys
input = sys.stdin.readline

n = int(input())

# 데이터셋 제작 (해시, 집합)
company = set()

# n번에 걸쳐서 로그가 입력
for _ in range(n):
  # 입력을 받은 후
  name, status = input().split()
  # 상태가 enter라면 ? => 데이터셋에 추가
  if status == 'enter':
    company.add(name)
  # 상태가 leave라면 ? => 데이터셋에서 삭제
  elif status == 'leave':
    company.discard(name)

# 데이터셋을 역순으로 정렬
# .sort() : 원본 리스트 자체를 변경
# sorted() : 원본은 그대로, 정렬된 새로운 리스트 반환
sorted_company = sorted(company, reverse=True)

# 하나씩 출력
for name in sorted_company:
  print(name)