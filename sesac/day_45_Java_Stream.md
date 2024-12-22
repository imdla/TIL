## <mark color="#fbc956">Stream</mark>

### 1. 스트림 (Stream)

- 배열 또는 컬렉션을 함수형 프로그래밍 스타일로 다루기 위한 추상화된 흐름
- 원본 배열 또는 컬렉션으로부터 데이터를 읽어와 중간 연산 실행 후 최종 연산으로 결과 도출에 사용
- **장점**
  1. 람다 표현식과 메서드 체이닝을 함께 사용해 선언적이고 직관적인 코드 작성 가능
  2. 필터링, 매핑, 정렬, 집계 등의 공통적인 데이터 처리 로직을 간결히 작성 가능
  3. 원본 데이터의 변경 없이 처리해 데이터 조작에 대한 부수효과 최소화

### 2. 특징

1. **데이터 원본을 변경하지 않음**
   - 스트림으로 처리하는 동안 원본 배열이나 컬렉션의 내용은 변경되지 않음
   - 필터링이나 정렬 등의 연산은 새로운 스트림 반환함, 원본 데이터는 안전하게 유지됨
2. **한 번 사용 후 없어짐, 재사용 불가**
   - 스트림은 일회용 소비 구조를 따름
   - 최종 연산 수행 후 스트림은 소모되어 더 이상 사용할 수 없음
   - 같은 처리를 하려면 원본 배열이나 컬렉션으로부터 새로운 스트림 생성 필요
3. **작업을 내부 반복으로 처리함**
   - 기존 컬렉션의 외부 반복(for, while)과 달리, 내부적으로 요소를 반복해 처리함
   - 개발자는 처리 로직(map, filter 등)만 제공하면 스트림이 알아서 각 요소에 대해 연산 적용함
4. **스트림 파이프라인 : 생성 → 중간 연산 → 최종 연산**
   - 생성 : 원본 배열이나 컬렉션으로부터 스트림을 생성하는 단계
   - 중간 연산 : 필터링, 매핑, 정렬 등의 데이터 변환 진행 단계 (이 과정의 결과 스트림을 반환)
   - 최종 연산 : 중간 연산 이후 최종 결과를 반환하거나 소비하는 단계 (이후 스트림 소멸)
5. **스트림은 지연 연산**
   - 중간 연산은 최종 연산 호출되기 전까지 실행되지 않음
     → 불필요한 연산 줄이고, 성능 최적화 가능

---

## <mark color="#fbc956">생성</mark>

### 1. 배열 스트림 생성

- 두 가지 방법으로 배열로부터 스트림 생성 가능

1. `Stream.of(T[] array)`

   ```java
   String[] array = {"a", "b", "c", "d"};
   Stream<String> stream = Stream.of(array);
   ```

2. `Arrays.stream(T[] array)`

   ```java
   String[] array = {"a", "b", "c", "d"};
   Stream<String> stream = Arrays.stream(array);
   ```

### 2. 컬렉션 스트림 생성

- `인스턴스.stream()` : 컬렉션 인스턴스로부터 스트림 생성
- 컬렉션 프레임워크 최상위 조상인 Collection 인터페이스에 `stream()` 메서드가 정의되어 있음

```java
List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
list.add("c");
list.add("d");

Stream<String> stream = list.stream();
```

### 3. 기본 자료형 스트림 생성

- int, long, double 자료형에 대한 스트림 생성
- 스트림에서 Wrapper Type 대신 기본 자료형 자체를 원소로 가져, 박싱/언박싱에 대한 오버헤드 줄임
- int → `IntStream` , long → `LongStream` , double → `DoubleStream` 사용

1. **`IntStream`**
   - `.of(int... values)`
     - 원하는 원소로 `IntStream` 생성
     ```java
     IntStream intStream = IntStream.of(1, 10, 100);
     ```
   - `.range(int start, int end)`
     - start 이상 end 미만의 연속된 원소로 `IntSteam` 생성
     ```java
     IntStream intStream = IntStream.range(0, 10);
     ```
   - `.rangeClosed(int start, int end)`
     - start 이상 end 이하 연속된 원소로 `IntStream` 생성
     ```java
     IntStream intStream = IntStream.rangeClosed(0, 10);
     ```
2. **`LongStream`**
   - `.of(long... values)`
     - 원하는 원소로 `LongStream` 생성
     ```java
     LongStream longStream = LongStream.of(1L, 10L, 100L);
     ```
   - `.range(long start, long end)`
     - start 이상 end 미만의 연속된 원소로 `LongStream` 생성
     ```java
     LongStream longStream = LongStream.range(0L, 10L);
     ```
   - `.rangeClosed(long start, long end)`
     - start 이상 end 이하의 연속된 원소로 `LongStream` 생성
     ```java
     LongStream longStream = LongStream.rangeClosed(0L, 10L);
     ```
3. **`DoubleStream`**
   - `.of(double... values)`
     - 원하는 원소로 `DoubleStream` 생성
     ```java
     DoubleStream doubleStream = DoubleStream.of(1.2, 2.5, 3.4);
     ```
   - `.range()` 와 `.rangeClosed()` 는 지원하지 않음

---

## <mark color="#fbc956">중간 연산</mark>

### 1. 중간 연산

- 스트림 생성 후, 중간 연산 이용해 필터힝, 정렬, 변환 등의 작업 수행
- 중간 연산에 해당하는 작업들은 연산의 결과를 새로운 스트림으로 반환해 연산을 계속 수행 가능

### 2. 자르기

1. **`.skip(long n)`**

   - 스트림의 앞에서부터 n개의 원소 건너뜀

   ```java
   Stream<String> stream = Stream.of("a", "b", "c", "d", "e");

   Stream<String> skipped = stream.skip(2); // "c", "d", "e" 갖는 스트림
   ```

2. **`.limit(long maxSize)`**

   - 스트림의 원소를 앞에서부터 maxSize 개수만큼 제한

   ```java
   Stream<String> stream = Stream.of("a", "b", "c", "d", "e");

   Stream<String> limited = stream.limit(3); // "a", "b", "c" 갖는 스트림
   ```

### 3. 필터링

1. **`.filter(Predicate<? super T> predicate)`**

   - 스트림에서 주어진 조건(predicate)을 만족하는 원소만을 추출

   ```java
   Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

   Stream<Integer> filtered = stream.filter(i -> i % 2 == 0); // 2, 4 갖는 스트림
   ```

2. **`.distinct()`**

   - 스트림에서 중복된 원소 제거
   - 중복의 여부는 원소의 `.equals()` 메서드 사용해 동등성 판별 및 결정

   ```java
   Stream<String> stream = Stream.of("a", "a", "b", "b", "b", "c");

   Stream<String> distincted = stream.distinct(); // "a", "b", "c" 갖는 스트림
   ```

### 4. 정렬

1. **`.sorted()`**

   - 스트림의 원소를 오름차순으로 정렬
   - 별도의 Comparator 객체 부여해 원하는 기준으로 정렬 가능

   ```java
   Stream<String> stream = Stream.of("d", "b", "a", "c");

   Stream<String> sorted = stream.sorted(); // "a", "b", "c", "d" 로 정렬된 스트림
   ```

   ```java
   Stream<String> stream = Stream.of("d", "b", "a", "c");

   Stream<String> customSorted = stream.sorted(Comparator.reverseOrder()); // "d", "c", "b", "a" 로 정렬된 스트림
   ```

### 5. 변환

1. **`.map(Function<? super T, ? extends R> mapper)`**

   - 스트림의 원소를 주어진 람다 표현식을 통해 계산한 값으로 변환

   ```java
   Stream<String> stream = Stream.of("a", "b", "c");

   Stream<String> upper = stream.map(string -> string.toUpperCase());
   // "A", "B", "C" 갖는 스트임
   ```

2. **`.mapToInt` , `.mapToLong` , `.mapToDouble`**

   - 스트림의 원소를 기본 자료형(int, long, double)으로 변환

   ```java
   Stream<String> stream = Stream.of("1", "2", "3");

   IntStream intStream = stream.mapToInt(Integer::parseInt); // 1, 2, 3 갖는 IntStream
   ```

   ```java
   Stream<String> stream = Stream.of("10", "20", "30");

   LongStream longStream = stream.mapToLong(Long::parseLong); // 10L, 20L, 30L 갖는 LongStream
   ```

   ```java
   Stream<String> stream = Stream.of("1.5", "2.5", "3.5");

   DoubleStream doubleStream = stream.mapToDouble(Double::parseDouble); // 1.5, 2.5, 3.5 갖는 DoubleStream
   ```

3. **`.boxed()`**

   - 기본 자료형 스트림을 Wrapper Type의 참조 자료형으로 변환

   ```java
   IntStream intStream = IntStream.range(0, 5);

   Stream<Integer> boxedStream = intStream.boxed(); // 0, 1, 2, 3, 4 갖는 Integer 타입 스트림
   ```

4. **`.mapToObj(IntFunction<? extends R>mapper)`**

   - 기본 자료형 스트림의 원소를 임의의 참조 자료형으로 변환

   ```java
   IntStream intStream = IntStream.of(1, 2, 3);

   Stream<String> stringStream = intStream.mapToObj(i -> "Number: " + i);
   // "Number: 1", "Number: 2", "Number: 3" 갖는 스트림
   ```

---

## <mark color="#fbc956">최종 연산<mark>

### 1. 최종 연산

- 스트림 중간 연산 이루 최종 연산 이용해 중간 연산들의 결과 반환하는 작업 수행
- 스트림은 지연 연산 방식 사용해, 최종 연산 때 앞선 중간 연산들이 실제 수행됨
- 최종 연산 이후 스트림은 재사용 불가, 추가 작업 필요 시 새로운 스트림 생성 필요

### 2. 반복

1. **`.forEach(Consumer<? super T> action)`**

   - 스트림의 각 원소에 대해 지정된 동작 반복 수행

   ```java
   Stream<String> stream = Stream.of("a", "b", "c");

   stream.forEach(System.out::println);
   // a
   // b
   // c
   ```

   - 컬렉션 프레임워크에서 지원하는 `.forEach()`

     - Stream의 `.forEach()` 와 다름

     ```java
     List<Integer> numbers = new ArrayList<>();
     number.add(1);
     number.add(2);
     number.add(3);

     // Stream의 .forEach()
     numbers.stream()
     	.forEach(System.out::println);
     // 컬렉션의 .forEach()
     numbers.forEach(System.out::println);
     ```

### 3. 조건 검사

1. **`.allMatch(Predicate<? super T> predicate)`**

   - 스트림의 모든 원소가 주어진 조건(predicate)을 만족하는지 검사
   - 모든 만족하면 `true` , 아닐 경우 `false` 반환

   ```java
   Stream<Integer> stream = Stream.of(2, 4, 6, 8);

   boolean allEven = stream.allMatch(i -> i % 2 == 0);
   ```

2. **`.anyMatch(Predicate<? super T> predicate)`**

   - 스트림에서 하나라도 조건을 만족하는 원소가 있는지 검사
   - 하나라도 만족하는 경우 `true` , 아닐 경우 `false` 반환

   ```java
   Stream<Integer> stream = Stream.of(1, 2, 3, 4);

   boolean anyEven = stream.anyMatch(i -> i % 2 == 0);
   ```

### 4. 조회

- 조회와 관련된 최종 연산은 보통 중간 연산 `.filter()` 와 함께 사용
- 스트림에서 특정 조건에 해당하는 원소들만 필터링한 후 결과를 조회하는 방식으로 사용

1. **`.findFirst()`**

   - 스트림의 첫 번째 원소를 Optional로 반환
   - 원소가 없으면 `Optional.empty()` 반환

   ```java
   Stream<String> stream = Stream.of("a", "b", "c");

   Optional<String> first = stream.findFirst(); // Optional["a"] 반환
   ```

2. **`.findAny()`**

   - 스트림 내 임의의 원소를 Optional로 반환
   - 원소가 없으면 `Optional.empty()` 반환
   - 병렬 스트림일 경우 어떤 원소 반환할지 보장되지 않음

   ```java
   Stream<String> stream = Stream.of("a", "b", "c");

   Optional<String> any = stream.findAny(); // Optional["a" 또는 "b" 또는 "c"] 반환
   ```

### 5. 집계

1. **`.count()`**

   - 스트림의 원소 개수 반환

   ```java
   Stream<String> stream = Stream.of("a", "b", "c");

   long count = stream.count(); // 3
   ```

2. **`.sum()` (IntStream, LongStream, DoubleStream 전용)**

   - 스트림 원소들의 합 반환

   ```java
   IntStream intStream = IntStream.range(1, 5);

   int sum = intStream.sum(); // 10
   ```

   ```java
   LongStream longStream = LongStream.range(1L, 5L);

   long sum = longStream.sum(); // 10L
   ```

   ```java
   DoubleStream intStream = DoubleStream.of(1.5, 2.5, 3.5, 4.5);

   double sum = doubleStream.sum(); // 12.0
   ```

3. **`.average()` (IntStream, LongStream, DoubleStream 전용)**

   - 스트림 원소들의 평균값을 OptionalDouble로 반환

   ```java
   IntStream intStream = IntStream.range(1, 5);

   OptionalDouble avg = intStream.average(); // OptionalDouble[2.5] 반환
   ```

4. **`.max()` (IntStream, LongStream, DoubleStream 전용)**

   - 스트림의 최대값을 OptionalInt, OptionalLong, OptionalDouble 형태로 반환

   ```java
   IntStream intStream = IntStream.range(1, 5);

   OptionalInt max = intStream.max(); // OptionalInt[4] 반환
   ```

5. **`.min()` (IntStream, LongStream, DoubleStream 전용)**

   - 스트림의 최소값을 OptionalInt, OptionalLong, OptionalDouble 형태로 반환

   ```java
   IntStream intStream = IntStream.range(1, 5);

   OptionalInt min = intStream.min(); // OptionalInt[1] 반환
   ```

### 6. 수집

- `.collect(Collector <? super T, A, R> collector)` 활용 시 스트림의 요소를 다양한 자료구조나 문자열로 모을 수 있음
- 매개변수 `collector` : Collector라는 인터페이스를 구현한 구현체
- Java의 Collectors 클래스에서 여러 static 메서드 통해 미리 작성된 Collector 구현체 제공

1. **`.collect(Collectors.toList())`**

   - 스트림의 원소를 List로 수집

   ```java
   Stream<String> stream = Stream.of("a", "b", "c");

   List<String> mutableList = stream.collect(Collectors.toList());
   // ["a", "b", "c"] 가변 리스트
   ```

   - `.toList()`

     - Java 16 버전부터 제공
     - `.collect(Collectors.toList())` : 가변(mutable) 리스트 반환
     - `.toList()` : 불변(immutable) 리스트 반환

     ```java
     Stream<String> stream = Stream.of("a", "b", "c");

     List<String> immutableList = stream.toList();
     // ["a", "b", "c"] 불변 리스트
     ```

2. **`.collect(Collectors.toSet())`**

   - 스트림의 원소를 Set으로 수집

   ```java
   Stream<String> stream = Stream.of("a", "b", "c", "c");

   Set<String> set = stream.collect(Collectors.toSet());
   // {"a", "b", "c"}
   ```

3. **`.collect(Collectors.toMap())`**

   - 스트림의 원소를 key, value 쌍으로 매핑해 Map으로 수집
   - `toMap()` 은 keyMapper와 valueMapper 두 개의 Function이 매개변수로 주어짐
     - `keyMapper` : Map의 key에 설정되는 값을 반환하는 람다 표현식
     - `valueMapper` : Map의 value에 설정되는 값을 반환하는 람다 표현식

   ```java
   Stream<String> stream = Stream.of("a", "b", "c");

   Map<String, Integer> map = stream.collect(Collectors.toMap(
   	s -> s,
   	s -> s.length()
   ));
   // {"a"=1, "b"=1, "c"=1}
   ```

4. **`.collect(Collectors.toCollection(Supplier collectionFactory))`**

   - 스트림의 원소를 원하는 컬렉션 프레임워크로 수집

   ```java
   Stream<String> stream = Stream.of("a", "b", "c", "c");

   Deque<String> deque = stream.collect(Collectors.toCollection(ArrayDeque::new));
   // ArrayDeque("a", "b", "c")
   ```

5. **`.collect(Collectors.joining(String delimiter))`**

   - 문자열 스트림의 원소를 하나의 문자열로 이어붙임

   ```java
   Stream<String> stream = Stream.of("a", "b", "c", "c");

   String joined = stream.collect(Collectors.joining(", "));
   // "a, b, c"
   ```
