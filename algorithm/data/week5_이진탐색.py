import math
T = int(input())

def inorder(idx):
  global node
  if idx > N:
    return
  
  inorder(idx*2)
  tree[idx] = node
  node += 1
  inorder(idx*2+1)
  

for tc in range(1, T+1):
  N = int(input())
  tree = [0] * (N+1)
  node = 1
  
  inorder(1)
  print(f'#{tc} {tree[1]} {tree[math.floor(N/2)]}')