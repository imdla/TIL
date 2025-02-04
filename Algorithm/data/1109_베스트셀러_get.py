N = int(input())

salse_info = {}
for _ in range(N):
  book_name = input()
  salse_info[book_name] = salse_info.get(book_name, 0) + 1

max_book_name = ''
max_book_cnt = 0
for name, cnt in salse_info.items():
  if max_book_cnt < cnt:
    max_book_cnt = cnt
    max_book_name = name

print(max_book_name)