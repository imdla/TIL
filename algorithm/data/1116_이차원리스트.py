matrix = [[3, 7, 9], [4, 2, 6], [8, 1, 5]]

# 인덱싱
print(matrix[0][0])
print(matrix[0][2])

# 행 우선 순회
for r in range(3):
  for c in range(3):
    print(matrix[r][c])

# 행 우선 순회
for c in range(3):
  for r in range(3):
    print(matrix[r][c])

# 전치
for r in range(3):
  for c in range(3):
    if r > c:
      matrix[r][c], matrix[c][r] = matrix[c][r], matrix[r][c]

for i in range(3):
  print(matrix[i])

# zip 함수를 이용한 전치
print(list(zip(*matrix)))