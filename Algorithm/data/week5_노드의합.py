T = int(input())

# 노드의 개수 N과 리프 노드의 개수 M, 값을 출력할 노드 번호 L
# 왼 -> 오 -> 루트 (후위순회)
# 리프노드 제외 자식의 합

def postorder(idx):
  global node
  if idx > N:
    return
  
  postorder(idx*2)
  postorder(idx*2+1)
  if idx not in leaf:
    if (idx*2+1) <= len(tree)-1:
      node = tree[idx*2] + tree[idx*2+1]
    else:
      node = tree[idx*2]
    tree[idx] = node

for tc in range(1, T+1):
  N, M, L = map(int, input().split())
  tree = [0] * (N+1)
  leaf = [idx for idx in range(N, N-M, -1)]
  node = 1
  
  for _ in range(M):
    leaf_idx, num = map(int, input().split())
    tree[leaf_idx] = num
  
  postorder(1)
  print(f'#{tc} {tree[L]}')