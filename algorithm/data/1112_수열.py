# import sys
# imput = sys.stdin.readline

N, K = map(int, input().split())
tempatures = list(map(int, input().split()))


sum_tem = sum(tempatures[:K])
sum_tem_lst = [sum_tem]
for i in range(K, N):
  sum_tem += tempatures[i] - tempatures[i-K]
  sum_tem_lst.append(sum_tem)

print(max(sum_tem_lst))
