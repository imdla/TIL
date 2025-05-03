> 💡 **한 줄 요약**
>
> try-with-resources는 커넥션, 입출력 스트림과 같은 자원을 자동으로 해제하는 기능으로, 자원을 해제해 성능 문제 및 메모리 누수 등을 방지한다.

### 1. 🤔 왜 사용하는가

- **try-with-resources**
  - 커넥션, 입출력 스트림과 같은 자원을 사용한 후 자원을 해제해 성능 문제, 메모리 누수 등을 방지해야 할 때, 이러한 자원을 자동으로 해제하는 기능
  - java 7부터 도입
  - 동작
    - `AutoCloseable` 인터페이스 구현한 객체 사용
    - `try()` 괄호 내 변수 선언
    ```java
    try (BufferedReader br = new BufferedReader(new FileReader("path"))) {
    	return br.readLine();
    } catch (IOException e) {
    	return null;
    }
    ```

### 2. 💡 무엇인지 아는가(특징)

- **try-catch-finally 대신 try-with-resources 사용 이유**

  ```java
  BufferReader br = null;
  try {
  	br = new BufferedReader(new FileReader("path"));
  } catch (IOException e) {
  	return null;
  } finally {
  	if (br != null) {
  		try {
  			br.close();
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	}
  }
  ```

  - **try-catch-finally**
    - finally 블록에서 `close()` 를 명시적으로 호출해야 함
    1. `close()` 호출을 누락하거나 이 과정에서 또 다른 예외 발생할 경우
       - 예외 처리 복잡해짐
    2. 여러 개의 자원을 다룰 경우
       - `close()` 를 호출한 자원에서 에러가 발생하면 다음에 `close()` 호출한 자원은 해제되지 않음
       - 해결하려면 추가적인 try-catch-finally가 필요
         → 가독성 떨어짐, 실수할 가능성 높음
  - **try-with-resources**
    - try 블록이 종료될 때 `close()` 를 자동으로 호출해 자원을 해제함
    - finally 블록 없이도 자원을 안전하게 정리 → 코드 간결
    - try 문에서 여러 자원 선언 시 선언된 반대 순서로 자동 해제됨

- **Suppressed Exception (억제된 예외)**

  - 예외가 발생했지만 무시되는 예외
  - try-with-resources는 `close()` 과정에서 발생한 예외를 Suppressed Exception으로 관리함

  ```java
  class CustomResource implements AutoCloseable {

  	@Override
  	public void close() throws Exception {
  		throw new Exception("Close Exception 발생");
  	}

  	void process() throws Exception {
  		throw new Exception("Primary Exception 발생")
  	}
  }

  public class Main {

  	public static void main (String[] args) throws Exception {
  		try (CustomResource resouce = new CustomResource()) {
  			resourve.process();
  		}
  	}
  }
  ```

  ```java
  Exception in thread "main" java.lang.Exception: Primary Exception 발생
      at CustomResource.process(CustomResource.java:9)
      at Main.main(Main.java:5)
      Suppressed: java.lang.Exception: Close Exception 발생
          at CustomResource.close(CustomResource.java:5)
          at Main.main(Main.java:4)
  ```

  - Suppressed Exception 필요성
    - 원래 예외(Primary Exception)를 유지하면서 추가 예외 함께 추적 가능
    - 자원을 안전하게 해제하며 예외를 효율적으로 처리 가능
  - try-catch-finally
    - `close()` 를 호출할 때 예외 발생하면 예외가 사라지고 `close()` 에서 발생한 예외만 남을 수 있음

  ```java
  public class Main {

  	public static void main (String[] args) throws Exception {
  		CustomResource resource = null;
  		try {
  			resource = new CustomResource();
  			resource.process();
  		} finally {
  			if (resource != null) {
  				resource.close();
  			}
  		}
  	}
  }
  ```

  ```java
  Exception in thread "main" java.lang.Exception: Close Exception 발생
      at CustomResource.close(CustomResource.java:5)
      at Main.main(Main.java:16)
  ```

  - 이처럼 예외 사라질 경우 → 디버깅 어려워짐
  - Throwable의 `addSuppressed()` 사용 시 문제 해결 가능
    - 코드 복잡해 try-with-resources 사용하는 것이 좋음
