import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
  k = int(input())

  # 리스트 컴프리헨션
  words = [input().rstrip() for _ in range(k)]

  flag = False
  # 1. 2중 for 반복문 이용해 단어 두 개를 뽑은 후
  for i in range(k):
    for j in range(k):
      # 같은걸 뽑았다면 넘어간다
      if i == j:
        continue
      # 둘을 이어 붙이고
      word = words[i] + words[j]
      # 회문 여부 검사
      # 회문이라면?
      if word == word[::-1]:
        # 출력 후 break
        print(word)

        # 1. break
        flag = True
        break

    # 1. break
    if flag:
      break

  # 찾지 못했다면?
  else:
    # 0 출력
    print(0)