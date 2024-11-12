from collections import Counter

N = int(input())

sales_info = Counter(input() for _ in range(N))

sales_info = sorted(sales_info.items(), key=lambda x : (-x[1], x[0]))
print(sales_info[0][0])