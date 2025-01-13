import sys
input = sys.stdin.readline

m, n = map(int, input().split())

# 소수 찾기
def find_prime(n):
  # 0부터 n까지 소수표시 리스트
  sieve = [True] * (n+1)
  # n의 최대 약수
  sqrt = int(n ** 0.5)
  # n의 최대 약수를 순회하며
  for i in range(2, sqrt+1):
    if sieve[i] == True:           # i가 소수면
      for j in range(i+i, n+1, i): # i의 배수들은 소수가 아님
        sieve[j] = False
  if m == 1:                       # 1은 소수에서 제외
    sieve[1] = False         
  return [i for i in range(m, n+1) if sieve[i] == True]

prime_list = find_prime(n)

# 정답 출력
for prime in prime_list:
    print(prime)
