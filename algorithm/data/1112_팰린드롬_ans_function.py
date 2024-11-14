import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
  k = int(input())

# 리스트 컴프리헨션
words = [input().rstrip() for _ in range(k)]

# 3. 함수로 만들기
def find_palindrome():
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
        return word
      
  return 0






