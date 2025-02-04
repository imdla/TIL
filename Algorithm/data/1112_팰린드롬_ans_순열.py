import sys
input = sys.stdin.readline

# 뽑아서 줄을 세우는 경우의 수까지
from itertools import permutations

T = int(input())
for _ in range(T):
  k = int(input())

  # 리스트 컴프리헨션
  words = [input().rstrip() for _ in range(k)]

  # 2. 순열 모듈 활용해 단어 두 개를 뽑은 후
  for w1, w2 in permutations(words, 2):
    # 둘을 이어 붙이고
    word = w1 + w2
    # 회문 여부 검사
    # 회문이라면?
    if word == word[::-1]:
      # 출력 후 break
      print(word)
      break

  # 찾지 못했다면?
  else:
    # 0 출력
    print(0)