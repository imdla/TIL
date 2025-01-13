> 💡 **한 줄 요약**
>
> equals와 hashCode 메서드는 객체의 동등성 비교와 해시값 생성을 위해 사용 할 수 있지만, 해시값을 사용하는 자료구조(HashSet, HashMap) 사용 시 문제 발생할 수 있다.
>
> 왜냐하면, equals는 동일 연산자를 통해 두 객체가 서로 같은 주소값을 갖는지 비교하는 메서드이고, hashCode는 객체 메모리 주소 값을 이용해 해시 코드를 만들어 반환한다. 해시값을 사용하는 자료구조는 hashCode 메서드의 반환값을 사용해 값이 일치한 후 equals 메서드의 반환값이 참일 때 논리적으로 같은 객체로 판단하기 때문이다.

### 1. 🤔 왜 사용하는가

- **equals(Object obj)**

  - 객체 자신과 obj가 동등한 객체인지 비교하는 메서드
  - 동일 연산자(`==`) 통해 두 객체가 서로 같은 주소값을 갖는지 비교

- **hashCode()**

  - 객체 자신의 해시코드 반환
  - 객체의 메모리 주소 값을 이용해 해시 코드를 만들어 반환

- **equals와 hashCode 메서드**
  - 객체의 동등성 비교와 해시값 생성을 위해 사용
  - 함께 재정의하지 않을 경우 예상치 못한 결과 만들 수 있음

### 2. 💡 무엇인지 아는가(특징)

> Java 컬렉션 프레임워크에서 해시 코드를 사용하는 HashSet, HashMap 같은 자료 구조 사용할 때, equals와 hashCode 함께 재정의하지 않을 경우 문제 발생 가능

```java
class EqualsHashCodeTest {

    @Test
    @DisplayName("equals만 정의하면 HashSet이 제대로 동작하지 않는다.")
    void test() {
        // 아래 2개는 같은 구독자
        Subscribe subscribe1 = new Subscribe("team.maeilmail@gmail.com", "backend");
        Subscribe subscribe2 = new Subscribe("team.maeilmail@gmail.com", "backend");
        HashSet<Subscribe> subscribes = new HashSet<>(List.of(subscribe1, subscribe2));

        // 결과는 1개여야하는데..? 2개가 나온다.
        System.out.println(subscribes.size());
    }

    class Subscribe {

        private final String email;
        private final String category;

        public Subscribe(String email, String category) {
            this.email = email;
            this.category = category;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Subscribe subscribe = (Subscribe) o;
            return Objects.equals(email, subscribe.email) && Objects.equals(category, subscribe.category);
        }
    }
}

```

- 해시 값을 사용하는 자료구조는 hashCode 메서드의 반환값을 사용
- hashCode 메서드의 반환 값이 일치한 후, equals 메서드의 반환값 참일 때만 논리적으로 같은 객체라 판단함
- `Subscribe` 클래스는 `hashCode` 메서드를 재정의하지 않아 `Object` 클래스의 기본 `hashCode` 메서드를 사용
- `Object` 클래스의 기본 `hashCode` 메서드는 객체의 고유한 주소를 사용해 객체마다 다른 값 반환함

⇒ 2개의 `Subscribe` 객체는 다른 객체로 판단, `HashSet` 에서 중복 처리가 되지 않음

- 아래와 같이 재정의 필요
  ```java
  @Override
  public int hashCode() {
      return Objects.hash(email, category);
  }
  ```
