## <mark color="#fbc956">DOM 조작</mark>

### 1. DOM 요소 생성

- **`document.createElement(tag)` : 새로운 요소 생성**
  ```jsx
  const divTag = document.createElement("div");
  ```
- **`elem.cloneNode(deep)` : 요소 복제**
  - `deep=true` : 모든 자손 요소도 복제
  - `deep=false` : 후손 노드 복사 없이 `elem` 만 복제
  ```jsx
  // 복제할 요소.cloneNode(deep option)
  elem.cloneNode(true);
  ```

### 2. DOM 요소 추가

- **`appendChild()`**
  - 새로운 요소를 부모 요소의 마지막 자식으로 추가
  - 노드만 추가 가능
  ```jsx
  // 부모 요소.appendChild(새로운 요소)
  parentElement.appendChild(childElement);
  ```
- **`insertBefore()`**
  - 부모 요소의 특정 자식 요소 앞에 새로운 요소 삽입
  - 노드만 추가 가능
  ```jsx
  // 부모요소.insertBefore(새로운 요소, 특정 자식 요소)
  parendElement.inserBefore(childElement1, childElement2);
  ```
- **`append()`**
  - 선택한 부모의 마지막 자식으로 요소나 문자열 추가
  - 복수의 요소나 문자열 한 번에 추가 가능
  ```jsx
  // 부모 요소.append(마지막 자식으로 넣을 새로운 요소...)
  parentElement.append(childElement1, childElement2, ...);
  ```
- **`prepend()`**
  - 선택한 부모 요소의 첫번째 자식으로 요소나 문자열 추가
  - 복수의 요소나 문자열 한 번에 추가 가능
  ```jsx
  // 부모 요소.append(첫번째 자식으로 넣을 새로운 요소...)
  parentElement.prepend(childElement1, childElement2, ...);
  ```
- **`node.before()` : node 이전에 노드를 삽입**
  ```jsx
  // 노드.before(노드의 이전에 삽입할 노드나 문자열)
  element.before("string");
  ```
- **`node.after()` : node 다음에 노드를 삽입**
  ```jsx
  // 노드.after(노드의 이후에 삽입할 노드나 문자열)
  element.after("string");
  ```
- **`node.replacerWith()` : node를 대체**
  ```jsx
  // 부모 요소.replceChild(새로운 노드, 교체할 노드)
  parentElement.replaceChild(node, oldChild);
  ```

### 3. DOM 요소 삭제

- **`parentElement.removeChild()` : 부모 요소에서 자식 요소 제거**
  ```jsx
  // 부모 요소.removeChild(삭제할 자식 요소)
  parentElement.removeChild(childElement);
  ```
- **`remove()` : 자기 자신 직접 제거**
  ```jsx
  // 삭제할 요소.remove()
  element.remove();
  ```
