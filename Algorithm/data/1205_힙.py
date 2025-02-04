from heapq import heappop, heappush, heapify

nums = [1, 2, 3, 1, 2, 3, 145, 2, 6]

# heapify -> 힙 자료구조로 만들어줌
heapify(nums)
print(nums)

heap = []
heappush(heap, -5)
heappush(heap, -10)
heappush(heap, -1)

print(heap)

print(-heappop(heap))
print(-heappop(heap))
print(-heappop(heap))