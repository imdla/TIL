import sys
input = sys.stdin.readline

N = int(input())
tree = {}
for _ in range(N):
  p, l, r = input().split()
  tree[p] = [l, r]

# 전위 순회 
pre_ans = []
def preorder(node):
  # 방문
  pre_ans.append(node)
  # 왼쪽 서브트리 탐색
  if tree[node][0] != '.':
    preorder(tree[node][0])
  # 오른쪽 서브트리 탐색
  if tree[node][1] != '.':
    preorder(tree[node][1])

preorder('A')
print(''.join(pre_ans))

# 중위 순회
in_ans = []
def inorder(node):
  # 왼쪽 서브트리 탐색
  if tree[node][0] != '.':
    inorder(tree[node][0])
  # 방문
  in_ans.append(node)
  # 오른쪽 서브트리 탐색
  if tree[node][1] != '.':
    inorder(tree[node][1])

inorder('A')
print(''.join(in_ans))

# 후위 순회
post_ans = []
def postorder(node):
  # 왼쪽 서브트리 탐색
  if tree[node][0] != '.':
    postorder(tree[node][0])
  # 오른쪽 서브트리 탐색
  if tree[node][1] != '.':
    postorder(tree[node][1])
  # 방문
  post_ans.append(node)

postorder('A')
print(''.join(post_ans))