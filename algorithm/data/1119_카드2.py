from collections import deque

# import sys
# input = sys.stdin.readline


N = int(input())

queue = deque([num for num in range(1, N+1)])

for _ in range(N-1):
  queue.popleft()
  num = queue.popleft()
  queue.append(num)

ans = queue.popleft()
print(ans)