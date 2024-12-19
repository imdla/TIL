## <mark color="#fbc956">Collection Framework</mark>

### 1. 컬렉션 프레임워크 (Collection Framework)

- 데이터를 효율적으로 관리하고 처리하기 위한 클래스와 인터페이스의 집합
- 각 클래스와 인터페이스는 하나의 자료구조임
  - 특성에 맞게 데이터를 저장, 조회, 정렬 등 다양한 조작을 수행할 수 있는 메서드 제공
  - 인터페이스는 실제 사용 시 각 구현체를 사용해야 함
- **종류**
  - **`List (리스트)`** : 순서가 있는 자료구조, 데이터의 중복 허용
    - 자주 사용되는 구현체 : `ArrayList`
  - **`Map (맵)`** : 순서가 없는 자료구조, Key(키)와 Value(값)의 쌍으로 저장, 키의 중복을 허용하지 않지만 값의 중복은 허용
    - 자주 사용되는 구현체 : `HashMap`
  - **`Set (집합, 셋)`** : 순서가 없는 자료구조, 데이터의 중복 허용하지 않음
    - 자주 사용되는 구현체 : `HashSet`

### 2. 사용

- 컬렉션 프레임워크는 구현체의 인스턴스 생성해 사용

```java
ArrayList<Integer> 변수명 = new ArrayList<>();
```

- **`제네릭(Generics)`** : `<Integer>` 와 같이 내부에 담는 자료형을 꺽쇠(`<>`) 안에 선언
  - `new` 부분에서는 제네릭 생략 가능
  - 제네릭에는 참조 자료형만 지정 가능
  - 기본 자료형 지정(ex. int, long, double 등) 시 **래퍼 클래스(Wrapper Class)** 활용

### 3. 래퍼 클래스 (Wrapper Class)

- Java의 기본 자료형을 참조 자료형처럼 사용하기 위한 포장 클래스
- 참조 자료형으로 기본 자료형으로는 사용할 수 없는 다양한 메서드 가짐
- **종류**
  | **기본 자료형** | **래퍼 클래스** |
  | --------------- | --------------- |
  | byte | Byte |
  | short | Short |
  | char | Character |
  | int | Integer |
  | long | Long |
  | float | Float |
  | double | Double |
  | boolean | Boolean |
- **변환**

  - 기본 자료형과 래퍼 클래스는 박싱/언박싱 통해 변환 가능

  1. **박싱 (Boxing)**

     - 기본 자료형 값을 래퍼 클래스의 인스턴스로 포장하는 과정

     ```java
     Integer boxedNumber = Integer.valueOf(10);
     ```

  2. **언박싱 (Unboxing)**

     - 래퍼 클래스의 인스턴스를 기본 자료형 값으로 꺼내는 과정

     ```java
     int unboxedNumber = boxedNumber.intValue();
     ```

  - Java 컴파일러는 박싱/언박싱을 자동으로 처리해주는 오토 박싱/언박싱

  1. **오토 박싱 (Auto Boxing)**

     ```java
     Integer boxedNumber = 10;
     ```

  2. **오토 언박싱(Auto Unboxing)**

     ```java
     int unboxedNumber = boxedNumber;
     ```

- **활용**

  - 컬렉션 프레임워크의 제네릭 타입 위해 사용
  - 기본 자료형의 형 변환 위해 사용

  ```java
  public class Main {
  	public static void main(String[] args) {
  		String byteString = "123";
  		String shortString = "123456";
  		String intString = "123546879";
  		String longString = "987654321987654321";
  		String floatString = "3.14";
  		String doubleString = "3.141592653589793";
  		String booleanString "true";

  		byte byteValue = Byte.parseByte(byteString);
  		short shortValue = Short.parseShort(shortString);
  		int intValue = Integer.parseInt(intString);
  		long longValue = Long.parseLong(longString);
  		float floatValue = Float.parseFloat(floatString);
  		double doubleValue = Double.parseDouble(doubleString);
  		boolean booleanValue = Boolean.parseBoolean(booleanString);
  	}
  }
  ```

---

## <mark color="#fbc956">List</mark>

### 1. 리스트 (List)

- **리스트 인터페이스**
  - 중복을 허용, 순서가 있는 자료구조 구현위해 사용
- **구현체**
  - **ArrayList**
  - LinkedList
  - Vector

### 2. `ArrayList`

- 배열(Array)을 활용해 List를 구현한 자료구조
- 생성 시 내부적으로 배열 존재, 첫 번째로 저장한 데이터는 해당 배열 0번째에 위치
- 내부적으로 배열 사용하지만 길이 고정되어 있지 않고 동적으로 변화
  → 데이터를 유종적으로 관리 가능
  - 배열 가득 차면 크기가 증가한 새로운 배열 생성해 데이터 복하
- **선언**
  ```java
  ArrayList<Integer> list = new ArrayList<>();
  ```
  - 상위 타입 선언
  ```java
  List<Integer> list = new ArrayList<>();
  ```
  - 다형성 활용해 ArrayList 대신 다른 List 구현체가 사용되어도 변경 최소화 가능
  - 상위 타입 선언 시, List 인터페이스에 정의된 메서드만 사용 가능

### 3. `.add(Object element)`

- ArrayList의 끝에 새로운 원소 element 삽입

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);

		System.out.println(list); // [1, 2]
	}
}
```

### 4. `.add(int index, Object element)`

- ArrayList의 index 위치에 새로운 원소 element 삽입

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void mian(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(0, 100);

		System.out.println(list); // [100, 1, 2]
	}
}
```

### 5. `.get(int index)`

- ArrayList의 index 위치에 있는 원소 조회

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);

		System.out.println(list.get(0)); // 1
	}
}

```

### 6. `.set(int index, Object element)`

- ArrayList의 index 위치에 있는 원소를 새로운 원소 element로 교체

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.set(0, 100);

		System.out.println(list); // [100, 2]
	}
}

```

### 7. `.size()`

- ArrayList의 길이 반환

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);

		System.out.println(list.size()); // 2
	}
}

```

### 8. `isEmpty()`

- ArrayList가 비어있는지 여부(`true` / `false`) 반환

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);

		System.out.println(list.isEmpty()); // true
	}
}

```

### 9. `.contains(Object element)`

- ArrayList에 특정 원소 element의 포함 여부(`true` / `false`) 반환

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);

		System.out.println(list.contains(1)); // true
		System.out.println(list.contains(100)); // false
	}
}

```

### 10. `.remove(int index)`

- ArrayList에서 특정 index 위치의 원소 제거

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.remove(0)

		System.out.println(list); // [2]
	}
}

```

---

## <mark color="#fbc956">Map</mark>

### 1. 맵 (Map)

- **맵 인터페이스**
  - 데이터를 키(Key)와 값(Value)의 쌍으로 저장하는 자료구조 구현위해 사용
  - 키는 중복 허용하지 않음, 각 키는 자신만의 값과 매칭
  - 값은 중복 허용
- **구현체**
  - **HashMap**
  - LinkedHashMap
  - TreeMap
  - HashTable

### 2. `HashMap`

- 해시(Hash) 테이블을 활용해 Map을 구현한 자료구조
- 특정 키(Key)를 해시 함수에 넣었을 때 반환되는 해시 값이 해시 테이블의 인덱스, 해당 인덱스에 값(Value)가 저장됨
- 동일한 키 → 항상 같은 해시값 반환 → 중복 데이터 허용하지 않음
- 중복 여부 판별 : 객체의 `equals()` , `hashCode()` 메서드의 반환값 기준 판별
  → HashMap에 삽입되는 객체에 대해 해당 메서드를 알맞게 재정의 필요
- **선언**
  - 키와 값의 쌍으로 저장, 제네릭도 키와 값 타입을 각각 지정 (`<키 타입, 값 타입>`)
  ```java
  HashMap<String, Integer> map = new HashMap<>();
  ```
  - 상위 타입 선언
  ```java
  Map<String, Integer> map = new HashMap<>();
  ```
  - 상위 타입 선언 시 Map 인터페이스에 정의된 메서드만 사용할 수 있음

### 3. `.put(Object key, Object value)`

- **삽입** : 새로운 `key=value` 쌍 삽입

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("john", 1);
		map.put("mia", 2);

		System.out.println(map); // {john=1, mia=2}
	}
}
```

- **교체** : 해당 key에 대한 value 존재 시 value를 새로운 값으로 교체

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("john", 1);
		map.put("mia", 2);

		map.put("john", 100);

		System.out.println(map); // {john=100, mia=2}
	}
}
```

### 4. `.get(Object key)`

- key에 해당하는 value 조회
- key가 존재하지 않는 경우 null을 반환

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("john", 1);
		map.put("mia", 2);

		System.out.println(map.get("mia")); // 2
		System.out.println(map.get("jenny")); // null
	}
}
```

### 5. `.getOrDefault(Object key, Object defaultValue)`

- key에 해당하는 vlaue 조회
- key가 존재하지 않을 경우 `defaultValue` 반환

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("john", 1);
		map.put("mia", 2);

		System.out.println(map.getOrDefault("mia", 0)); // 2
		System.out.println(map.getOrDefault("jenny", 0)); // 0
	}
}
```

### 6. `.size()`

- HashMap의 크기 반환

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("john", 1);
		map.put("mia", 2);

		System.out.println(map.size()); // 2
	}
}
```

### 7. `.containsKey(Object key)` , `.containsValue(Object value)`

- 특정 key 혹은 value의 존재 여부(`true`, `fasle`) 반환

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("john", 1);
		map.put("mia", 2);

		System.out.println(map.containsKey("john")); // true
		System.out.println(map.containsKey("jenny")); // false

		System.out.println(map.containsValue(1)); // true
		System.out.println(map.containsValue(0)); // false
	}
}
```

### 8. `.remove(Object key)`

- 특정 key에 해당하는 `key=value` 쌍을 제거

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("john", 1);
		map.put("mia", 2);
		map.remove("john")

		System.out.println(map); // {mia=2}
	}
}
```

### 9. `. keySet()`

- key의 집합 반환

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("john", 1);
		map.put("mia", 2);

		for (String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
			// john : 1
			// mia : 2
		}
	}
}
```

---

## <mark color="#fbc956">Set</mark>

### 1. 집합 (Set)

- **셋 인터페이스**
  - 데이터의 중복 허용하지 않으며 순서 없는 자료구조 구현위해 사용
- **구현체**
  - **HashSet**
  - TreeSet
  - LinkedHashSet

### 2. `HashSet`

- 내부적으로 HashMap을 활용해 Set을 구현한 자료구조
- 해시 테이블을 이용해 데이터의 중복을 허용하지 않음
- 중복 여부 판별 : 객체의 `equals()` , `hashCode()` 메서드의 반환값 기준 판별
  → HashMap에 삽입되는 객체에 대해 해당 메서드를 알맞게 재정의 필요
- **선언**
  ```java
  HashSet<Integer> set = new HashSet<>();
  ```
  - 상위 타입 선언
  ```java
  Set<Integer> set = new HashSet<>();
  ```
  - 상위 타입 선언 시 Set 인터페이스에 정의된 메서드만 사용 가능

### 3. `.add(Object element)`

- 새로운 원소 element를 삽입

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);

		System.out.println(set); // [1, 2]
	}
}
```

### 4. `.addAll(Collection c)`

- 컬렉션 c의 모든 원소 삽입

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);

		Set<Integer> set = new HashSet<>();
		set.addAll(list);

		System.out.println(set); // [1, 2, 3]
	}
}
```

### 5. `.size()`

- HashSet의 크기 반환

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);

		System.out.println(set.size()); // 2
	}
}
```

### 6. `isEmpty()`

- HashSet이 비어있는지 여부(`true` , `false`) 반환

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);

		System.out.println(set.isEmpty()); // false
	}
}
```

### 7. `.contains(Object element)`

- HashSet에 특정 원소 element의 포함 여부(`true`, `false`) 반환

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);

		System.out.println(set.contains(1)); // true
		System.out.println(set.contains(0)); // false
	}
}
```

### 8. `.remove(Object element)`

- HashSet에 특정 원소 element를 제거

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.remove(1);

		System.out.println(set); // [2]
	}
}
```

---

### ☀️ 오늘의 배움

- **추상 클래스**
  - 하나 이상의 추상 메서드를 포함하는 클래스
- **`equals`**

  - 같은게 무엇인지 정의하기 위해 equals 메서드를 재정의해서 사용
  - equals 재정의할 때 hashCode도 같이 변경해줘야 함

- **컬렉션 프레임워크**
  - 인터페이스에 대한 구현체를 사용할 것임
  - 쓰레드 기능
  ```java
  List a = new LinkedList();
  List a = new ArrayList();
  ```
  - ArrayList → 접근에 좋음
  - LinkedList → 삽입과 삭제에 좋
- 제네릭 `<>`
  - 타입을 정하는 방법의 형태
    → 래퍼 클래스가 들어감
- `ctrl+alt+v`
  - 자동 완성, 타입을 알 수 있음
- `iter`
  - 반복문 생성
