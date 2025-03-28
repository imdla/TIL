> 💡 **한 줄 요약**
>
> 연결 리스트는 리스트 내 요소들을 포인터로 연결해 관리하는 선형 자료구조로, 각 노드는 데이터와 다음 요소에 대한 포인터를 가진다. 이는 메모리가 허용하는 한 요소를 계속 삽입할 수 있다.

### 1. 🤔 왜 사용하는가

- **연결 리스트 (Linked List)**

  - 리스트 내의 요소(노드)들을 포인터로 연결하여 관리하는 선형 자료구조
  - 각 노드는 데이터와 다음 요소에 대한 포인터 가짐
    - 첫 번째 노드 : HEAD
    - 마지막 노드 : TAIL
  - 메모리가 허용하는 한 요소를 계속 삽입 가능
  - 시간 복잡도
    - 탐색 : O(n)
    - 노드 삽입 및 삭제 : O(1)
  - 파생된 자료구조
    - 단일 연결 리스트(Singly Linked List)
    - 이중 연결 리스트(Doubly Linked List, Circular Linked List)

- **배열 연결 리스트의 차이점**
  - **배열** : 순차적인 데이터가 들어가 메모리 영역을 연속적으로 사용
  - **연결 리스트** : 메모리 공간에 흩어져 존재

### 2. 💡 무엇인지 아는가(특징)

> **단일 연결 리스트의 탐색, 추가, 삭제**

```java
class SinglyLinkedList {
	public Node head;
	public Node tail;

	public Node insert(int newValue) {
		Node newNode = new Node(null, newValue);
		if (head == null) {
			head = newNode;
		} else {
			tail.next = newNode;
		}
		tail = newNode;

		return newNode;
	}

	public Node find(int findValue) {
		Node currentNode = head;
		while (currentNode.value != findValue) {
			currentNode = currentNode.next
		}

		return currentNode;
	}

	public void appendNext(Node prevNode, int value) {
		prevNode.next = new Node(prevNode.next, value);
	}

	public void deleteNext(Node prevNode) {
		if (prevNode.next != null) {
			prevNode.next = prevNode.next.next
		}
	}
}
```

- **탐색**
  - 최악의 경우, 시간 복잡도 : O(n)
    - 노드 탐색하기 위해 HEAD의 포인터부터 데이터를 찾을 때까지 다음 요소를 반복적으로 탐색
- **삽입**
  - 삽입될 위치 이전에 존재하는 노드와 신규 노드의 포인터 조작
  - ex. 3번 위치에 신규 노드 삽입할 경우
    1. 2번 위치에 있는 노드 포인터를 신규 노드를 가리키도록함
    2. 신규 노드 포인터는 기존 3번 노드의 위치 가리키도록 함
- **삭제**
  - 삭제할 노드의 이전 노드가 삭제 대상 노드의 다음 노드를 가리키도록 수정
  - 삭제 대상 노드를 메모리에서 지움
- **삽입∙삭제 시간 복잡도** : O(1)
  - 탐색이 필요한 경우 선형 시간 걸림
