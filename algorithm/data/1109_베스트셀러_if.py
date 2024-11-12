N = int(input())

sales_info = {}

for _ in range(N):
  book_name = input()

  if book_name not in sales_info:
    sales_info[book_name] = 1
  else:
    sales_info[book_name] += 1
  

sales_info = sorted(sales_info.items(), key=lambda x: (-x[1], x[0]))
print(sales_info[0][0])