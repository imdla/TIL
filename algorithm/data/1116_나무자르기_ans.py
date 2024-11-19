# import sys
# input = sys.stdin.readline

N, M = map(int, input().split())
trees = list(map(int, input().split()))

# 초기값 세팅(l, r, c)

l, r = 0, max(trees)
c = (l + r) // 2


# 반복을 하며(l < r)
while l <= r:
  # c를 가지고 가져갈 수 있는 나무 길이를 계산
  length = sum([tree-c for tree in trees if tree > c])
  print(f'l: {l}, r: {r}, c: {c}')

  # length == M
  if length == M:
    break
  # length > M
  elif length > M:
    # l값을 갱신
    l = c + 1
    
  # length < M
  elif length < M:
    # r값을 갱신
    r = c - 1

  # 새로운 c를 계산
  c = (l + r) // 2

print(c)