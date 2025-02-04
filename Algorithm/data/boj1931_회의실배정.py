import sys
input = sys.stdin.readline

N = int(input())
time_board = [tuple(map(int, input().split())) for _ in range(N)]
time_board.sort(key=lambda x: (x[1], x[0]))

cnt = 1
cur = 0
for i in range(0, N-1, 1):
  if (time_board[cur][1] <= time_board[i + 1][0]):
    cnt += 1
    cur = i + 1

print(cnt)