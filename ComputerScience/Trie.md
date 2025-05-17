> 💡 **한 줄 요약**
>
> 트라이는 문자열을 저장하고 효율적으로 탐색하기 위한 트리 형태의 자료 구조로, 루트는 항상 비어있고 각 간선이 추가될 문자를 키로 가지고 있으며 각 정점은 이전 정점의 값과 간선의 키를 더한 결과를 값으로 가지는 구조다.

### 1. 🤔 왜 사용하는가

- **자료구조 트라이(Trie)**
  - 문자열을 저장하고 효율적으로 탐색하기 위한 트리 형태의 자료 구조
  - **장점** : 문자열을 탐색할 때 단순히 비교하는 것에 비해 효율적 검색 가능
  - **단점** : 각 정점이 자식에 대한 링크 모두 가지고 있어 저장 공간을 많이 사용
  - ex. 검색어 자동완성, 사전 찾기 기능 구현

### 2. 💡 무엇인지 아는가(특징)

> **트라이 구현**

- 루트는 항상 비어 있음
- 각 간선은 추가될 문자를 키로 가지고 있음
- 각 정점은 이전 정점의 값과 간선의 키를 더한 결과를 값으로 가짐
- 해시 테이블과 연결 리스트 이용해 구현 가능

```java
class TrieTest {

	@Test
	void trieTest() {
		Trie trie = new Trie();
		trie.insert("helloworld");
		assertThat(trie.has("he")).isTrue();
		assertThat(trie.has("hello")).isTrue();
		assertThat(trie.has("helloworld")).isTrue();
		assertThat(trie.has("heo")).isTrue();
	}

	class Trie {

		private final Node root = new Node("");

		public void insert(String str) {
			Node current = root;
			for (String ch : str.split("")) {
				if (!current.children.containsKey(ch)) {
					current.children.put(ch, new Node(current.value + ch));
				}
				current = current.children.get(ch);
			}
		}

		public boolean has(String str) {
			Node current = root;
			for (String ch : str.split("")) {
				if (!current.children.containsKey(ch)) {
					return false;
				}
				current = current.children.get(ch);
			}
			return true;
		}
	}

	class Node {

		public String value;
		public Map<String, Node> children;

		public Node(String value) {
			this.value = value;
			this.children = new HashMap<>();
		}
	}
}
```
