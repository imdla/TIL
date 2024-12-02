import sys
input = sys.stdin.readline

T = int(input())

for _ in range(T):
  tc = list(input().strip('\n'))
  
  ans = 'YES'
  stack = []
  for i in range(len(tc)):
    item = tc.pop()
    
    if item == ')':
      stack.append(item)
    elif item == '(':
      if len(stack) == 0:
        ans = 'NO'
        break
      stack.pop()
  
  if len(stack) != 0:
    ans = 'NO'
  
  print(ans)