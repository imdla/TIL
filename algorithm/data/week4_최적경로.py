T = int(input())

def perm(depth, cur, dist):
  global ans
  if depth == N:
    dist = ((dist[0]+abs(cur[0]-end[0])), dist[1]+abs(cur[1]-end[1]))
    ans = min(ans, sum(dist))
    return
  
  if sum(dist) >= ans:
    return
  
  for nxt in customer:
    if nxt not in visited:
      visited.add(nxt)
      perm(depth+1, nxt, (dist[0]+abs(nxt[0]-cur[0]), dist[1]+abs(nxt[1]-cur[1])))
      visited.discard(nxt)

for tc in range(1, T+1):
  N = int(input())
  road = list(map(int, input().split()))
  start = (road[0], road[1])
  end = (road[2], road[3])
  customer = []
  for idx in range(4, N*2+4, 2):
    customer.append((road[idx], road[idx+1]))
  
  dist = (0, 0)
  visited = set()
  ans = float("INF")
  perm(0, start, dist)
  print(f'#{tc} {ans}')