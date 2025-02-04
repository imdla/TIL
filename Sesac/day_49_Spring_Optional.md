## <mark color="#fbc956">Optionnal</mark>

### 1. Optional

- 형태 : Optional <T>
- null일 수 있는 객체를 감싸는 래퍼 클래스
- NullPointerException을 방지하기 위해 사용
- null 체크를 명시적으로 하지 않고도 null 가능성이 있는 값을 처리할 수 있게 해줌

### 2. 메서드

- `.orElseThrow(() -> new ErrorName)`

  - 값이 없으면 예외를 던짐
  - 가장 많이 사용되는 메서드

- `.orElse(기본값)`

  - 값이 없으면 기본값을 반환

- `.map()`

  - 값이 존재하면 주어진 함수를 적용하고 Optional로 감싸 반환
  - `.map().orElse()` 의 조합으로 쓰이는 경우가 있음

- `ifPresent(instance -> {})`
  - 값이 존재할 때만 주어진 동작 수행
  - 반환값 없음
