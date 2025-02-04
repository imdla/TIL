import sys
input = sys.stdin.readline

K = int(input())

stack = []
for _ in range(K):
  n = int(input())
  if not n:
    stack.pop()
  else:
    stack.append(n)

print(sum(stack))