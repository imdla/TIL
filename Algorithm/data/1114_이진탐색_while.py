def binary_search(l, r, target, cnt):
  while True:
    c = int((l + r) / 2)

    if c == target:
      return cnt
    
    elif c > target:
      r = c
    
    elif c < target:
      l = c
    
    cnt += 1