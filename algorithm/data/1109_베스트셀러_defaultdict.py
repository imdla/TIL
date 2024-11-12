import sys
input = sys.stdin.readline

N = int(input())

from collections import defaultdict
sales_info = defaultdict(int)

for _ in range(N):
  book_name = input()
  sales_info[book_name] += 1

sales_info = sorted(sales_info.items(), key=lambda x: (-x[1], x[0]))
print(sales_info[0][0])

