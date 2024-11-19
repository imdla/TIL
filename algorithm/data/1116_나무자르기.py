# import sys
# input = sys.stdin.readline

N, M = map(int, input().split())
trees = sorted(list(map(int, input().split())))

def max_height(trees):
  s, l = trees[0], trees[-1]
  mid_tree = int((s + l) / 2)
  sum_sort = sum([tree-mid_tree for tree in trees if tree > mid_tree])

  if sum_sort == M:
    return mid_tree
  
  elif sum_sort < M:
    trees.pop()
    new_lst = trees
    return max_height(new_lst)
    
  elif sum_sort > M:
    trees.pop(0)
    new_lst = trees
    return max_height(new_lst)

print(max_height(trees))