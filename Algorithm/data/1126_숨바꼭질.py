import sys
input = sys.stdin.readline

from collections import deque

N, K = map(int, input().split())

queue = deque([(N, 0)])
visited = set([N])

while queue:
  cur, cnt = queue.popleft()
  
  if cur == K:
    break
  
  for nxt in [cur+1, cur-1, cur*2]:
    if 0 <= nxt < 140000 and nxt not in visited:
      visited.add(nxt)
      queue.append((nxt, cnt+1))
      
print(cnt)