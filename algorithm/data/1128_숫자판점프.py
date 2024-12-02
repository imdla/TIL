import sys
input = sys.stdin.readline

nums = [list(input().split()) for _ in range(5)]
ans = set()

dc = [-1, 1, 0, 0]
dr = [0, 0, -1, 1]

def perm(depth, r, c, num):
  if depth == 5:
    ans.add(num)    
    return
  
  for d in range(4):
    nr, nc = r+dr[d], c+dc[d]
    if 0 <= nr < 5 and 0 <= nc < 5:
      perm(depth+1, nr, nc, num+nums[nr][nc])
      

for r in range(5):
  for c in range(5):
    perm(0, r, c, nums[r][c])

print(len(ans))