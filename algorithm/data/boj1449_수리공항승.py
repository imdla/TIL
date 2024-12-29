import sys
input = sys.stdin.readline

N, L = map(int, input().split())
leak = list(map(int, input().split()))
leak.sort()

cnt = 0
l = 0

# 왼쪽 포인터가 N보다 작을 때까지
while l < N:
    # 현재 테이프가 커버할 수 있는 최대 범위
    coverage = leak[l] + L - 1
    cnt += 1  # 테이프 하나 추가
    
    # 현재 테이프가 커버하는 범위 내에서 오른쪽 포인터 이동
    while l < N and leak[l] <= coverage:
        l += 1

print(cnt)
