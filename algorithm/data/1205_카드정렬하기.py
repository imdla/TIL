import sys
input = sys.stdin.readline
from heapq import heapify, heappop, heappush

N = int(input())

# 힙 자료구조로 구조화
cards = []
for _ in range(N):
  card = int(input())
  heappush(cards, card)
  
ans = 0
# 카드뭉치가 하나 남을 때까지 반복
while len(cards) > 1:
  # 가장 작은 카드 두 뭉치를 뽑아서
  card1, card2 = heappop(cards), heappop(cards)
  # 더한 후에
  n_card = card1 + card2
  # 더한 값(비교횟수)을 누적
  ans += n_card
  # 다시 카드뭉치 덩어리(힙에 넣기)
  heappush(cards, n_card)
  
print(ans)