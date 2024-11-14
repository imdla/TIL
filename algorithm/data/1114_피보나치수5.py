# f(n+2) = f(n+1) + f(n)

# import sys
# input = sys.stdin.readline

n = int(input())

def fibo(num):
  if num == 0 or num == 1:
    return num

  return fibo(num-1) + fibo(num-2)

print(fibo(n))