# 스택 pop 메서드
  # pop() -> O(1)
  # pop(0) -> O(N)
# 큐 -> deque를 이용해 구현할 것
from collections import deque
# deque : 양방향 연결 리스트
  # 양 끝에서만 데이터 추출 및 삽입 가능

queue = deque([1, 2, 3])
print(queue)

queue.append(5)
print(queue)

num = queue.popleft()
print(num, queue)