> 💡 **한 줄 요약**
>
> 무공변이란 타입 S, T가 있을 때 서로 관계가 없다는 것이고, 공변이란 S가 T의 하위 타입일 때 S는 T가 될 수 있다는 것이고, 반공변이란 S가 T의 하위 타입일 때 T는 S가 될 수 있다는 것이다.

### 1. 🤔 왜 사용하는가

- **자바에서 제네릭(Generic)**

  - 기본적으로 무공변(Invariant)
  - S와 T가 서로 상속 관계이면 공변성이 있지만,
    제네릭은 상속 관계가 호환되지 않음
    ⇒ 타입이 정확히 일치하지 않으면 컴파일 에러 발생

  ```java
  public class Animal {
  }

  public class Cat extends Animal {
  }

  List<Animal> animals = new ArrayList<Cat>(); // 컴파일 에러
  List<Cat> cats = new ArrayList<Animal>(); // 컴파일 에러
  ```

### 2. 💡 무엇인지 아는가(특징)

- **무공변(Invariant)**

  - 타입 S, T가 있을 때 서로 관계가 없다는 것
  - 장점 : 타입 안정성 보장
  - 단점 : 타입의 유연성 부족
  - 자바에서 와일드 카드(`?`), `extends`, `super`키워드로 공변과 반공변 지원

- **공변(Covariant)**

  - S가 T의 하위 타입일 때 S는 T가 될 수 있다는 것
  - 제네릭에서 `<? extends T>` 를 사용해 하위 타입을 허용하고 읽기 전용으로 사용 가능
    - 쓰기는 null만 가능

- **반공변(Contravariant)**
  - S가 T의 하위 타입일 때 T는 S가 될 수 있다는 것
  - 제네릭에서는 `<? super ?>` 를 사용해 상위 타입을 허용하고 쓰기 전용으로 사용 가능
    - 읽기는 Object 타입으로만 가능

> **PECS란?**

- **PECS(Producer Extends, Consumer Super)**

  - 제네릭에서 와일드카드의 상위 또는 하위 경계를 설정할 때 사용하는 가이드라인
  - 객체 생산 시 `<? extends T>` 를 사용
  - 객체 소비 시 `<? super T>` 를 사용

  ```java
  public void produce(List<? extends Animal> animals) { // animals가 생산자 역할
  	for (Animal a : animals) {
  		System.out.println(a);
  	}
  }

  public void consume(List<? super Cat> cats) { // cats가 소비자 역할
  	cats.add(new Cat());
  }
  ```

> **`<?>` 와 `<Object>` 의 차이점**

- 모든 타입을 수용하는 것처럼 보이지만 동작 방식에 차이 있음
- **`<?>`**
  - 모든 타입을 메서드 인자로 받을 수 있지만
    null 외에 값을 추가할 수 없음
    ⇒ 읽기 전용으로 사용
- **`<Object>`**
  - `<Object>` 외의 타입을 메서드 인자로 받을 수 없지만
    모든 객체를 추가할 수 있음
    ⇒ 읽기, 쓰기 모두 가능
