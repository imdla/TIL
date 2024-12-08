import sys
input = sys.stdin.readline

M = int(input())

def add(x):
  global S
  S.add(x)

def remove(x):
  global S
  S.discard(x)
  
def check(x):
  global S
  if x in S:
    print(1)
  else:
    print(0)
  
def toggle(x):
  global S
  if x in S:
    S.discard(x)
  else:
    S.add(x)
    
def all():
  global S
  S = set(idx for idx in range(1, 21))

def empty():
  global S
  S = set()
  
func = {
  "add": add,
  "remove": remove,
  "check": check,
  "toggle": toggle,
  "all": all,
  "empty": empty,
}

S = set()
for _ in range(M):
  operators = input().split()
  if len(operators) == 2:
    oper, num = operators
    num = int(num)
    
    if oper == 'add':
      if num in S:
        continue
    elif oper == 'remove':
      if num not in S:
        continue
      
    func[oper](num)
  else:
    oper = operators.pop()
    func[oper]()