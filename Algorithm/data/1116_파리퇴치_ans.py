T = int(input())

for tc in range(1, T+1):
    N, M = map(int, input().split())
    flies = [list(map(int, input().split())) for _ in range(N)]

    # 정답 초기화(0)
    ans = 0

    # 2중 for 문으로 flies를 순회하며
    for r in range(N-M+1):
        for c in range(N-M+1):
        # r, c를 기준으로 잡아(tmp = 0)
            tmp = 0
            # 2중 for 문으로 M*M만큼 순회하며
            for y in range(r, r+M):
                for x in range(c, c+M):
                    tmp += flies[y][x]
                # 파리 개수 세어주기
            # 정답 갱신
            ans = max(ans, tmp)

    # 정답 출력
    print(f'#{tc} {ans}')