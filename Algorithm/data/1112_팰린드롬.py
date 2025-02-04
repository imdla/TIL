# import sys
# input = sys.stdin.readline

T = int(input())

for _ in range(T):
  N = int(input())

  words = []
  flag = False
  ans = False
  for _ in range(N):
    word = input()
    words.append(word)

    for i in range(len(words)):
      for j in range(len(words)):
        if i == j:
          continue
        else:
          new_word = words[i] + words[j]

          if new_word == new_word[::-1]:
            print(new_word)
            ans = True
            flag = True
            break
      if flag:
        break
  if not ans:
    print(0)