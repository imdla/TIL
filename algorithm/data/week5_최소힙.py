import sys
from heapq import heapify, heappop, heappush
input = sys.stdin.readline

N = int(input())

heap = []
heapify(heap)
for _ in range(N):
  x = int(input())
  
  if x > 0:
    heappush(heap, x)
  else:
    if not heap:
      print(0)
    else:
      small = heappop(heap)
      print(small)