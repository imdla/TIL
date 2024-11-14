# 이진탐색 -> O(logN) 시간복잡도
# 조건 : 특정 범위, 정렬된 리스트에서 탐색할 때 사용 !
T = int(input())

def binary_search(l, r, target, cnt):
  # 가운데를 찝는다
  c = int((l+r) / 2)

  # 3. c == target
  if c == target:
    # 종료 return 탐색횟수
    return cnt

  # 1. c > target
  elif c > target:
    r = c
    cnt += 1
    # 왼쪽 절반으로 재귀 (r을 갱신)
    return binary_search(l, r, target, cnt)

  # 2. c < target
  elif c < target:
    l = c
    cnt += 1
    # 오른쪽 절반으로 재귀
    return binary_search(l, r, target, cnt)
  

for tc in range(1, T+1):
  p, a, b = map(int, input().split())
  ansA = binary_search(1, p, a, 1)
  ansB = binary_search(1, p, b, 1)

  if ansA < ansB:
    ans = 'A'
  elif ansA > ansB:
    ans = 'B'
  else:
    ans = 0
  
  print(f"#{tc} {ans}")
  