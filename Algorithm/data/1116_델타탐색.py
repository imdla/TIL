dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

matrix = [[3, 7, 9], 
          [4, 2, 6], 
          [8, 1, 5]]

r, c = 2, 2

for d in range(4):
  nr, nc = r+dr[d], c+dc[d]
  # 유효한 좌표인지
  if 0 <= nr < 3 and 0 <= nc < 3:
    print(matrix[nr][nc])
    # r, c = nr, nc # 이동