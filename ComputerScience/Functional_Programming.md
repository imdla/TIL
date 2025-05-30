> 💡 **한 줄 요약**
>
> 함수형 프로그래밍은 움직이는 부분을 최소화해 코드 이해를 돕는다. 이의 목적은 함수를 합성해 복잡한 프로그램을 쉽게 만들고, 부수 효과를 공통적인 방법으로 추상화하는 것이다.

### 1. 🤔 왜 사용하는가

- **객체지향 프로그래밍**

  - 움직이는 부분을 캡슐화해 코드의 이해를 도움

- **함수형 프로그래밍(Functional Programming)**
  - 객체지향 패러다임과 마찬가지로 하나의 프로그래밍 패러다임
  - 움직이는 부분을 최소화하여 코드 이해를 도움
  - 목적
    - 함수를 합성해 복잡한 프로그램을 쉽게 만듦
      - 함수 합성은 순수 함수로 이루어짐
    - 부수 효과를 공통적인 방법으로 추상화함
      - 부수 효과를 추상화 및 분리 → 코드 이해하기 쉽게 만듦
  - 프로그래밍 패러다임 중 하나
  - 순수 함수와 불변성을 강조하는 방식
  - 어떻게(how)보다는 무엇(what)에 집중
  - 순수 함수를 조합하는 형태로 코드를 작성

### 2. 💡 무엇인지 아는가(특징)

- **부수 효과(Side Effect)**

  - 값을 반환하는 것 이외에 부수적으로 발생하는 일들
    - ex. 변수 수정, I/O 작업 등
  - 부수 효과가 많은 코드는 이해하고 결과 예측이 어려울 수 있음
  - 함수형 프로그래밍 → 부수 효과를 추상화 및 분리해 코드 이해하기 쉽게 만듦

- **함수 합성(Function Composition)**

  - 특정 함수의 공역이 다른 함수의 정의역과 일치하는 경우, 두 함수를 이어서 새로운 함수를 만드는 연산
    - 프로그래밍에서 공역, 정의역 : 타입에 해당
  - ex. A 함수에서 int 타입 반환, B 함수에서 int 타입을 인자로 받는 상황
    - `B(A())` 와 같은 형태로 호출
  - 함수 합성으로 복잡한 프로그램을 쉽게 만듦
    - 함수는 입력 들어오면 부수 효과의 발생과 함께 결과 반환 가능
    - 부수 효과가 존재하는 함수는 합성하기 까다로움
    ```java
    // 부수 효과가 존재하는 sum 함수는 합성하기 까다로울 수 있음
    // 1. 다른 함수에서 1부터 1000까지 더하는 함수가 필요할 경우?
    // 2. 다른 함수에서 1부터 100까지 곱하는 함수가 필요할 경우?
    int sum () {
    	int sum = 0;
    	for (int i = 1; i <= 100; i ++) {
    		sum += i;
    	}

    	return sum;
    }
    ```

- **순수 함수(Pure Function)**

  - 같은 입력이 들어올 경우, 항상 같은 값을 반환하는 함수
  - 부수 효과를 일으키지 않음
  - 함수형 프로그래밍 → 함수 합성은 순수 함수로 이루어짐

  ```java
  class FunctionCompositionTest {

  	@Test
  	@DisplayName("함수 합성")
  	void fp() {
  		System.out.println(sum());
  		System.out.println(factorial(10));
  	}

  	// 1부터 100까지의 합
  	private int sum() {
  		return loop((a, b) -> a + b, 0, range(1, 100));
  	}

  	// 팩토리얼
  	private int factorial(int n) {
  		return loop((a, b) -> a * b, 1, range(1, n));
  	}

  	private int loop(BiFunction<Integer, Integer, Integer> fn, int sum, Queue<Integer> queue) {
  		if (queue.isEmpty()) {
  			return sum;
  		}

  		return loop(fn, fn.apply(sum, queue.poll()), queue);
  	}

  	private Queue<Integer> range(Integer start, Integer to) {
  		return IntStream.rangeClosed(start, to)
  						.boxed()
  						.collect(Collectors.toCollection(LinkedList::new));
  	}
  }
  ```

  - 동일한 입력에 대해 항상 동일한 결과를 반환
  - 외부 상태를 변경하지 않는 함수
  - 예
    ```jsx
    function add(a, b) {
      return a + b;
    }
    ```
    - 인자 a와 b에 따라 항상 같은 값을 반환
    - 외부 상태를 변경하지 않기에 순수 함수임
    - 만약, 함수 내에서 데이터베이스 접근 및 전역 변수를 변경하는 등 부수 효과를 포함할 경우 순수 함수가 아님

- **불변성**
  - 함수형 프로그래밍에서는 데이터를 직접 변경하는 것을 피하고, 기존 데이터를 기반으로 새로운 데이터를 생성하는 방식 사용
  - ex. 배열의 값을 변경할 때 `push` 대신 `concat` 을 사용
    ```jsx
    const numbers = [1, 2, 3];
    const newNumbers = numbers.concat(4);

    console.log(newNumbers); // [1, 2, 3, 4]
    // 원본 배열이 변경되지 않음
    console.log(numbers); // [1, 2, 3]
    ```
- 고차 함수, 커링, 함수 합성, 재귀 등의 기법 적극 활용함

### 3. ✅ 장점

1. **유지보수성**
   - 함수형 코드는 절차적이지 않고 선언적임
   - 코드가 읽고 수정하기 좋은 형태
2. **테스트 용이**
   - 순수 함수는 외부 의존성이 없어 테스트하기 쉬운 형태
3. **병렬 처리 용이**
   - 불변성 및 부수 효과가 없음
