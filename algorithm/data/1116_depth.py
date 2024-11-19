import sys
# 재귀의 한계를 설정
sys.setrecursionlimit(10**9)

def find(depth):
  if depth == 1000:
    print('찾았다')
    return
  
  print(f'탐색하는 중 ... 깊이는 {depth}')

  find(depth+1)

  print(f'올라가는 중 ... 깊이는 {depth}')

find(0)