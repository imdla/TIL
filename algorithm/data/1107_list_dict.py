gems = [3, 3, 1, 2, 3, 2, 2, 3, 3, 1]

# 1. 리스트 안에 1이 존재하는지 여부
  # 반복문을 이용해서 리스트를 순회하며
for gem in gems:
  # 1 이라는 데이터가 발견되었으면 ?
  if gem == 1:
  # 찾았음을 표시하고 종료
    print('찾았다')
    break

# 인덱스로 접근
  # range(start, end) : start는 생략 가능
  # range(len(gems)) : len()을 통해 배열 길이 조회 가능
for idx in range(len(gems)):
  if gems[idx] == 1:
    print('찾았다')
    break

# 2. 리스트에서 가장 큰 숫자를 찾는 문제
lst = [56, 23, 43, 87, 12, 457, 86]

# 초기값(가장 큰 숫자 후보)을 세팅
ans = -float("INF")
ans = lst[0]

# 반복문을 이용해서 리스트를 선형 탐색
for num in lst:
  # 만약 방금 뽑은 그 값이 후보보다 크다면?
  if num > ans:
  # 숫자를 갱신
    ans = num

print(ans)

# 내가 푼 내용
max = -999

for item in lst:
  if max < item:
    max = item

print(max)

idxMax = -999
for idx in range(len(lst)):
  if idxMax < lst[idx]:
    idxMax = lst[idx]

print(idxMax)

# 3. 집계 알고리즘
gems = [3, 3, 1, 2, 3, 2, 2, 3, 3, 1]

# 딕셔너리를 이용한 집계
# 딕셔너리에 1:0, 2:0, 3:0 이라는 키 값을 만든다.
grades = {1:0, 2:0, 3:0}

# 반복문을 이용해서 리스트를 선형 탐색
for gem in gems:
  # 방금 뽑은 그 등급에 따라서 value값을 갱신한다.
  grades[gem] += 1

print(grades)

# 리스트를 이용한 집계
# 빈 판 만들기
grades = [0] * 4

for gem in gems:
  grades[gem] += 1

print(grades)