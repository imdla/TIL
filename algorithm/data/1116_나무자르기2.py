# import sys
# input = sys.stdin.readline

N, M = map(int, input().split())
trees = list(map(int, input().split()))
s, l = 0, max(trees)

def max_height(s, l):
  mid_tree = int((s + l) / 2)
  sort = sum([tree-mid_tree for tree in trees if tree > mid_tree])

  if s < l:
    if sort == M:
      return mid_tree
    
    elif sort < M:
      l = mid_tree + 1
      return max_height(s, l)
      
    elif sort > M:
      s = mid_tree - 1
      return max_height(s, l)

print(max_height(s, l))