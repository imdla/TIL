> 💡 **한 줄 요약**
>
> 전략 패턴은 객체의 행위를 동적으로 변경하고 싶은 경우, 코드를 직접 수정하지 않고 추상화된 전략의 구현만을 바꾸어서 객체의 행위를 변경하는 디자인 패턴이다.

### 1. 🤔 왜 사용하는가

- **전략 패턴(Strategy Pattern)**
  - 객체의 행위를 동적으로 변경하고 싶은 경우, 코드를 직접 수정하는 것이 아닌 추상화된 전략의 구현만을 바꿔 객체의 행위를 변경하는 디자인 패턴
  - ex. JAVA
    - 객체의 행위를 `interface`로 정의
    - `interface` 의 메서드를 구현하는 구현체들을 주입

```java
class Car {

	private final MoveStrategy strategy;
	private final int position;

	public Car(MoveStrategy strategy, int position) {
		this.strategy = strategy;
		this.position = position;
	}

	public Car move(int input) {
		if (strategy.isMovable(input)) {
			return new Car(strategy, car + 1)
		}

		return this;
	}
}

interface MoveStrategy {
	boolean isMovable(int input);
}

class EvenNumberMoveStrategy implements MoveStrategy {

	@Override
	public boolean isMovable(int input) {
		return (input % 2) == 0;
	}
}

class OddNumberMoveStrategy implements MoveStrategy {
	...
}

class PrimeNumberMoveStrategy implements MoveStrategy {
	...
}
```
