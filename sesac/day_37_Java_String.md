## <mark color="#fbc956">String</mark>

### 1. 문자열 (String)

- 문자(char)들의 집합
- 텍스트 데이터를 표현하는데 사용되는 자료형
- 불변(immutable) 객체
  - 한 번 생성된 문자열은 내용 변경 불가

### 2. 문자열 선언 및 초기화

1. `new String()` 키워드 사용
   - 객체 자체를 새로 생성
2. 큰 따옴표(`""`)로 감싸기
   - 동일한 객체 재사용
   - 메모리 효율성과 가독성 고려, 주로 사용되는 방식

```java
String word = new String("hello");
String word = "hello";
```

- `System.identityHashCode()` : 객체의 원래 해시코드 보여줌
  - 해시코드 : 서로 다른 객체에게 다르게 부여되는 객체 자체 고유 번호

### 3. 문자열 결합 (Concatenation)

- 덧셈 연산자(`+`) 통해 문자열 결합
  - 문자열 및 다른 자료형도 더할 수 있음 (문자열로 형 변환됨)
- 문자열 결합 시 **새로운 문자열 생성**됨 (문자열은 변경 불가능한 객체)

```java
public class Main {
	public static void main (String[] args) {
		System.out.println("Hello" + " " + "world");    // Hello world
		System.out.println(2024 + "-" + 01 + "-" + 01); // 2024-01-01
	}
}
```

## String Method

---

### 1. `.length()`

- 문자열의 길이 반환

```java
public class Main {
	public static void main(String[] args) {
		String word = "hello";

		System.out.println(word.length()); // 5
	}
}
```

### 2. `.isEmpty()`

- 빈 문자열 여부 판별
- 문자열의 길이가 0인지 여부(`true` / `false`) 반환

```java
public class Main {
	public static void main(String[] args) {
		String word1 = "";
		String word2 = "           ";

		System.out.println(word1.isEmpty());   // true
		System.out.println(word2.isEmpty());   // false
	}
}
```

### 3. `.isBlank()`

- 문자열의 길이가 0이거나, 공백 문자만으로 이루어져 있는지 여부(`true`, `false`) 반환
- 공백 문자 : 띄어쓰기, 탭, 개행 등을 포함

```java
public class Main {
	public static void main(String[] args) {
		String word1 = "";
		String word2 = "           ";
		String word3 = "\n";

		System.out.println(word1.isBlank()); // true
		System.out.println(word2.isBlank()); // true
		System.out.println(word3.isBlank()); // true
	}
}
```

### 4. `.charAt(int index)`

- 문자열 인덱싱
- 문자열의 index번째에 위치한 문자(char) 반환

```java
public class Main {
	public static void main(String[] args) {
		String word = "hello";

		System.out.println(word.charAt(0)); // h
	}
}
```

### 5. `.toCharArray()`

- 문자열을 문자(char) 배열로 쪼개 반환

```java
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		String word = "hello";
		char[] characters = word.toCharArray();

		System.out.println(Array.toString(characters)); // [h, e, l, l, o]
	}
}
```

- `toCharArray()` 로 생성된 char를 String으로 사용 위해 형 변환 시켜줄 수 있음

```java
public class Main {
	public static void main(String[] args) {
		String word = "hello";
		char[] characters = word.toCharArray();

		for (char character : characters) {
			String s = String.valueOf(character);
			System.out.println(s);
		}
	}
}
```

### 6. `.contains(String str)`

- 특정 문자열 str의 포함 여부(`true`, `false`) 반환

```java
public class Main {
	public static void main(String[] args) {
		String word = "hello";

		System.out.println(word.contains("h")); // ture
		System.out.println(word.contains("z")); // false
	}
}
```

### 7. `.indexOf(String str)`

- 특정 문자열 str가 원본 문자열의 위치한 인덱스 반환
  - str 두 글자 이상인 경우 : 처음 매칭되는 곳의 인덱스 번호 반환
  - str 찾지 못한 경우 : -1 반환

```java
public class Main {
	public static void main(String[] args) {
		String word = "hello";

		System.out.println(word.indexOf("e")); // 1
		System.out.println(word.indexOf("z")); // -1
	}
}
```

### 8. `.trim()`

- 문자열의 앞뒤 공백 제거한 새로운 문자열 반환

```java
public class Main {
	public static void main(String[] args) {
		String word = "       Hello world       ";
		String trimmedWord = word.trim();

		System.out.println(word);         //        Hello world
		System.out.println(trimmedWord);  // Hello world
	}
}
```

### 9. `.replace(String old, String new)`

- 특정 문자열에서 old 문자열을 모두 new 문자열로 교체한 새로운 문자열 반환
- 원본이 변경되는 것이 아니라 변경된 모습의 새로운 문자열 반환

```java
public class Main {
	public static void main(String[] args) {
		String word = "hello";

		System.out.println(word.replace("h", "o")); // oello
		System.out.println(word.replace("h", "")); // ello
		System.out.println(word); // hello
	}
}
```

### 10. `.substring(int start, int end)`

- 문자열을 특정 구간만큼 자른 새로운 문자열 반환
- `start` 포함, `end` 미포함

```java
public class Main {
	public static void main(String[] args) {
		String word = "hello";

		System.out.println(word.substring(1, 3)); // el
	}
}
```

### 11. split(String regex)

- 문자열을 정규표현식 regex 기준으로 분할해 새로운 배열로 반환

```java
public class Main {
	public static void main(String[] args) {
		String sentence = "Hello world";
		String[] tokens = sentence.split(" ");

		System.out.println(Arrays.toString(tokens)); // [Hello, world]
	}
}
```

### 12. `.equals(String str)`

- 특정 문자열 str와 같은 문자열인지 여부(`true`, `false`) 반환
- `==` 와 비교
  - `==`
    - 두 변수의 값이 서로 같은지 확인하는 연산자
    - 정수나 실수와 같이 기본 자료형을 비교함
    - 참조 자료형을 비교할 경우 값이 같은지 확인하지 않고 서로 같은 객체인지 확인함
      (동일성)
  - `.equals()`
    - 문자열과 같은 참조 자료형을 비교 (동등성)

```java
public class Main {
	public static void main(String[] args) {
		String word1 = new String("hello");
		String word2 = "hello";

		System.out.println(word1 == word2);      // false
		System.out.println(word1.equals(word2)); // true
	}
}
```

### 13. `String.join(String delimiter, String[] words)`

- 문자열 배열 words를 구분 문자 delimiter를 기준으로 결합한 새로운 문자열 반환

```java
public class Main {
	public static void main(String[] args) {
		String[] words = {"Hello", "world"};
		String sentence = String.join("-", words);

		System.out.println(sentence); // Hello-world
	}
}
```

---

## <mark color="#fbc956">String formatting</mark>

### 1. 문자열 포매팅 (String formatting)

- 동적으로 변하는 값들을 이용해 문자열을 원하는대로 만듦

### **2. `System.out.printf(String pattern, Object… args)`**

- 형식에 맞춰 문자열 포매팅한 결과 출력
- `System.out.println()` 과 달리 문장의 끝에서 개행하지 않음
- 지시자(Specifier)를 통해 각 값 대입 가능
  - 지시자와 값의 순서 일치해야 함
- **지시자의 종류**
  - `%s` : 문자열
  - `%c` : 문자
  - `%d` : 10진수
  - `%o` : 8진수
  - `%x`, `%X` : 16진수
  - `%f` : 부동 소수점
  - `%b` : boolean
  - `%n` : 개행

```java
public class Main {
	public static void main(String[] args) {
		String pattern = "저는 %s, %d살 입니다.";
		String name = "john";
		int age = 20;

		System.out.printf(pattern, name, age); // 저는 john, 20살 입니다.
	}
}
```

### **3. `String.format(String pattern, Object… args)`**

- 형식에 맞춰 문자열 포매팅 결과 반환
- `System.out.printf()` 와 달리 바로 출력하는 것이 아니라 포매팅한 문자열 자체를 반환함
  - 결과를 변수에 할당 가능
- `System.out.printf()` 와 마찬가지로 지시자를 통해 각 값 대입 가능
  - 지시자와 값의 순서가 일치해야 함

```java
public class Main {
	public static void main(String[] args) {
		String pattern = "저는 %s, %d살 입니다.";
		String name = "john";
		int age = 20;

		String sentence = String.format(pattern, name, age);

		System.out.println(sentence); // 저는 john, 20살 입니다.
	}
}
```

### **4. `MessageFormat.format(String pattern, Object… args)`**

- `java.text` 패키지에 있는 `MessageFormat` 이용한 문자열 포매팅
- `System.out.println()` 이나 `String.format()` 과 달리 지시자를 이용하지 않음
  - 0번째부터 시작하는 위치로 어디에 대입할지 결정
- 위치 통해 지정해 직관적, 각 대입 값들의 타입을 신경쓰지 않아도 됨

```java
import java.text.MessageFormat;

public class Main {
	public static void main(String[] args) {
		String pattern = "저는 {0}, {1}살 입니다.";
		String name = "john";
		int age = 20;

		String sentence = MessageFormat.format(pattern, name, age);

		System.out.println(sentence); // 저는 john, 20살 입니다.
	}
}
```

---

## <mark color="#fbc956">StringBuilder</mark>

### 1. StringBuilder

- 문자열에 대한 연산이나 변경이 잦은 경우 위해 Java에서 제공하는 변경 가능한(mutable) 클래스 자료 구조
- 연산 시 새로운 객체자 생성되지 않고 기존 객체에 대한 내용이 변경됨 → 메모리 효율성이 좋음

### 2. 선언 및 초기화

- `new StringBuilder()` : 새로운 StringBuilder 객체 생성

```java
StringBuilder stringBuilder = new StringBuilder();
```

### 3. `.append(String str)`

- StringBuilder에 문자열 str 삽입
- StringBuilder를 문자열로 변경 시 `.toString()` 사용

```java
public class Main {
	public static void main(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("a");
		stringBuilder.append("b");
		stringBuilder.append("c");

		String word = stringBuilder.toString();
		System.out.println(word); // abc
	}
}
```

### 4. `.insert(int index, String str)`

- StringBuilder의 index 위치에 특정 문자열 str 삽입

```java
public class Main {
	public static void main(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("hello");

		stringBuilder.insert(1, "z");

		String word = stringBuilder.toString();
		System.out.println(word); //hzello
	}
}
```

### 5. `.delete(int start, int end)`

- StringBuilder에서 특정 구간만큼 문자열 삭제
- `start` 포함, `end` 미포함

```java
public class Main {
	public static void main(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("hello");

		stringBuilder.delete(1, 3);

		String word = stringBuilder.toString();
		System.out.println(word); // hlo
	}
}
```

- `deleteCharAt(int index)` : 특정 인덱스 문자열 1개만 지울 수 있음

```java
public class Main {
	public static void main(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("hello");

		stringBuilder.deleteCharAt(2);

		String word = stringBuilder.toString();
		System.out.println(word); // hllo
	}
}
```

### 6. `.substring(int start, int end)`

- StringBuilder를 특정 구간만큼 잘라 새로운 문자열 반환
- 원본은 변하지 않음
- `start` 포함, `end` 미포함

```java
public class Main {
	public static void main(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("hello");

		String token = stringBuilder.substring(1, 3);
		String word = stringBuilder.toString();

		System.out.println(token); // el
		System.out.println(word);  // hello
	}
}
```

### 7. `.reverse(int start, int end)`

- StringBuilder의 문자열을 뒤집음

```java
public class Main {
	public static void main(String[] args) {
		StringBuilder stringBuilder = new StringBuilder("hello");
		stringBuilder.reverse();

		String word = stringBuilder.toString();
		System.out.println(word); // olleh
	}
}
```

---

### ☀️오늘의 배움

- 요청과 응답 통신
  - 웹소켓
    - 양방향 통신 가능
  - http
    - 요청과 응답 → 끝
