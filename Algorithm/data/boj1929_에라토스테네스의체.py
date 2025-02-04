def prime_list(n):
  # 에라토스테네스의 체 초기화 : n개 요소에 True 설정 (소수 간주)
  sieve = [True] * n
  ## [True, True, True, True, True]
  
  # n의 최대 약수는 sqrt(n) 이하, i=sqrt(n)까지 검사
  m = int(n ** 0.5)
  ## 
  for i in range(2, m+1):
    ## i = 2 3
    if sieve[i] == True:         # i가 소수인 경우
      ## i = 2
      for j in range(i+i, n, i): # i 이후 i의 배수들을 False 판정
        ## 
        sieve[j] = False
        
  
  # 소수 목록 산출
  return [i for i in range(2, n) if sieve[i] == True]

print(prime_list(5))
