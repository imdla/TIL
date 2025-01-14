## <mark color="#fbc956">Spring 관련 추가 사항</mark>

### 1. JDBC 예시 코드

```java
public class JDBCExample {
	// 데이터베이스 연결 정보
	private static final String URL = "jdbc:mysql://localhost:3306/mydb";
	private static final String USER = "username";
	private static final String PASSWORD = "password";

	public void basicJDBCExample() {
		// JDBC 드라이버 로드
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		// 데이터베이스 연결
		try (Connection conn = DriverManager.getConnection(URL, UER, PASSWORD)) {
			// 1. INSERT 예제
			String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
				pstmt.setString(1, "홍길동");
				pstmt.setString(2, "hong@example.com");
				int affectedRows = pstmt.executeUpdate();
				System.out.println(affectedRows + "행이 추가되었습니다.");
			}

			// 2. SELECT 예제
			String selectSQL = "SELECT * FROM users WHERE name = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
				pstmt.setString(1, "홍길동");
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						String name = rs.getString("name");
						String email = rs.getString("email");
						System.out.println("이름: " + name + ", 이메일: " + email);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
```

### 2. 쓰레드

- **프로세스 (Process)**

  - 컴퓨터에서 실행 중인 프로그램
  - 프로그램에 필요한 모든 자원(메모리, 파일, 네트워크 연결 등)을 포함
  - Spring 애플리케이션이 실행되면 하나의 프로세스로 실행

- **쓰레드 (Thread)**

  - 하나의 프로세스 내에서 실행되는 독립적인 작업 단위
  - 프로세스는 여러 개의 쓰레드를 가질 수 있으며, 각 쓰레드는 자신만의 실행 흐름 가짐
  - 쓰레드들은 프로세스의 자원을 공유함
  - 멀티쓰레딩으로 병렬 처리 가능

- **멀티쓰레딩**

  - 하나의 프로세스에서 여러 쓰레드가 동시에 실행되는 것
  - **장점**
    - 자원 공유가 효율적
    - 메모리와 자원 공유해 경제적
  - **단점**
    - 동기화 문제 발생 가능
    - 디버깅이 어려움
    - 데드락 등의 문제 발생 가능

- **쓰레드 풀 (Thread Pool)**

  - 미리 생성해놓은 쓰레드들의 집합
  - 쓰레드 생성/삭제 비용을 줄일 수 있음
  - 동시 실행 가능한 쓰레드 수 제한 가능

- **커넥션 풀 (Connection Pool)**

  - 데이터베이스 연결을 미리 생성해두고 관리하는 것
  - 연결 생성/종료 비용을 줄일 수 있음
  - HikariCP가 대표적인 커넥션 풀 라이브러리

- **풀링에서의 고려사항**
  - 풀 사이즈 : 너무 크면 리소스 낭비, 너무 작으면 성능 저하
  - 타임아웃 : 무한 대기를 방지하기 위한 설정
  - 모니터링 : 풀의 사용량과 성능을 지속적으로 관찰

### 3. Bean Scope

- singleton : 스프링 컨테이너달 하나의 인스턴스만 생성 (기본값)
- prototype : 요청할 때 마다 새로운 인스턴스 생성
- request : HTTP 요청당 하나의 인스턴스
- session : HTTP 세션당 하나의 인스턴스

일반적으로, 특별한 이유 없을 경우 기본값인 singleton 사용

### 4. singleton

- 클래스의 인스턴스가 단 하나만 생성되는 것을 보장하는 디자인 패턴

```java
public class ClassicSingleton {
	private static ClassicSingleton instance;

	private ClassicSingleton() {} // private 생성자

	public static synchronized ClassicSingleton getIstance() {
		if (instance == null) {
			instance = new ClassicSingleton();
		}
		return instance;
	}
}
```

- **장점**

  1. 메모리 사용 최적화
     - 한 번만 인스턴스화되어 메모리 사용이 효율적
     - 애플리케이션 시작 시 초기화되어 빠른 응답 시간
  2. 상태 공유
     - 모든 요청에서 같은 인스턴스 사용
     - 공통 설정이나 캐시 데이터 공유에 적합

- Spring의 싱글톤 Bean은 thread-safe하지 않은 가변 상태를 가지면 안됨
  - 읽기 전용 상태
  - 또는 Thread-safe한 가변 상태 가져야 함
    (읽기 전용 상태만 사용해도 충분)

---

## <mark color="#fbc956">spring.jpa.hibernate.ddl-auto 옵션</mark>

- `none` : 아무 작업도 하지 않음 (기본값)
- `create` : 기존 테이블을 삭제하고 새로 생성, 매번 실행할 때 마다 테이블을 새로 만듦
- `create-drop` : 시작할 때 테이블을 생성, 종료할 때 테이블을 삭제 (주로 테스트 환경에서 사용)
- `update` : 기존 테이블 유지, 변경사항만 반영, 컬럼 추가는 되지만 삭제되지 않음
- `validate` : 엔티티와 테이블이 정상적으로 매핑되었는지만 확인

- 실제 운영 환경에서 보통 `none` / `validate` 사용
- 개발 환경에서 `update` / `create` 사용

- `spring.jpa.show-sql` , `spring.jpa.properties.hibernate.format_sql` 등의 옵션
  - 배포 환경에서는 false로 설정
