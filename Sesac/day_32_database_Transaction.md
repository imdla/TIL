## <mark color="#fbc956">트랜잭션</mark>

### 1. 트랜잭션

- 데이터베이스의 상태를 변화시키는 하나의 논리적 작업 단위
- 여러 개의 SQL 명령문들을 하나의 작업으로 묶어 처리
- 전체가 성공 or 실패하는 “All or Nothing” 방식

- **트랜잭션 명령어**
  ```sql
  -- 트랜잭션 시작
  START TRANSACTION; (또는 BEGIN)

  -- SQL 문장들 실행

  -- 정상적으로 완료 시
  COMMIT;

  -- 문제 발생 시
  ROLLBACK;
  ```

### 2. ACID 속성

1. **원자성 (Atomicity)**
   - 트랜잭션의 모든 연산이 완전히 수행되거나 전혀 수행되지 않아야 함
   - 에러 발생 시 롤백(Rollback) 수행
2. **일관성 (Consistency)**
   - 트랜잭션 실행 전후의 데이터베이스가 일관된 상태를 유지해야 함
3. **격리성 (lsolation)**
   - 동시에 실행되는 트랜잭션들이 서로 영향을 미치지 않도록 격리해야 함
4. **지속성 (Durability)**
   - 성공적으로 완료된 트랜잭션의 결과는 영구적으로 반영함
   - 시스템 장애가 발생하더라도 커밋된 트랜잭션의 내용은 보존함
   - Write-Ahead Logging(로그에 먼저 기록해 시스템 발생 시 로그를 확인해 미완료된 트랙잭션 복구), 체크포인트 등으로 구현 가능

### 3. 동시성 문제

- 여러 트랜잭션이 동시에 실행될 때 발생할 수 있는 데이터 불일치 문제
- 적절한 처리를 하지 않으면 데이터 정합성이 깨질 수 있음

### 4. 데이터 읽기 관련 문제

1. **Dirty Read**

   - 커밋되지 않은 데이터를 다른 트랜잭션이 읽는 현상

   ```sql
   -- 트랜잭션 1
   START TRANSACTION;
   UPDATE accounts SET balance = balance - 10000 WHERE id = 'A';
   -- 아직 커밋되지 않음

   -- 트랜잭션 2
   START TRANSACTION;
   SELECT balance FROM accounts WHERE id = 'A';
   -- 커밋되지 않은 잔액을 읽음 (Dirty Read 발생)
   ```

   - 해결 방안
     - READ COMMITTED 이상의 격리 수준 사용

2. **Non-repeatable Read**

   - 한 트랜잭션 내에서 같은 데이터를 두 번 읽었을 때 결과가 다른 현상

   ```sql
   -- 트랜잭션 1
   START TRANSACTION;
   SELECT balance FROM accounts WHERE id = 'A'; -- 최초 읽기 : 10000원

   -- 트랜잭션 2
   START TRANSACTION;
   UPDATE accounts SET balance = 20000 WHERE id = 'A';
   COMMIT;

   -- 트랜잭션 1
   SELECT balance FROM accounts WHERE id = 'A'; -- 두 번째 읽기 : 20000원
   -- 같은 트랜잭션인데 다른 결과
   ```

   - 해결 방안
     - REPEATABLE READ 이상의 격리 수준 사용

3. **Phantom Read**

   - 같은 조건으로 조회했을 때 결과 집합이 변경되는 현상

   ```sql
   -- 트랜잭션 1
   START TRANSACTION;
   SELECT * FROM accounts WHERE balance > 10000; -- 2개 행 조회

   -- 트랜잭션 2
   START TRANSACTION;
   INSERT INTO accounts VALUES ('C', 15000);
   COMMIT;

   -- 트랜잭션 1
   SELECT * FROM accounts WHERE balance > 10000; -- 3개 행 조회
   -- 같은 조건인데 결과 집합이 변경됨
   ```

   - 해결 방안
     - SERIALIZABLE 격리 수준 사용

4. **Non-repeatable Read와 Phantom Read의 차이점**
   - **Non-repeatable Read**
     - 기존 데이터의 변경에 관한 문제
     - 같은 행의 데이터가 변경되어 다른 값이 조회되는 현상
   - **Phantom Read**
     - 데이터 집합의 변경에 관한 문제
     - 같은 조건으로 조회했는데 행의 개수가 변경되는 현상

### 5. 데이터 수정 관련 문제

1. **Lost Update**

   - 여러 트랜잭션이 동시에 같은 데이터를 수정할 때 발생

   ```sql
   -- 게시글 조회수 증가 예시
   -- 원래 조회수 : 100

   -- 사용자 1
   UPDATE posts SET views = views + 1 WHERE id = 1; -- 101

   -- 사용자 2 (동시에)
   UPDATE posts SET views = views + 1 WHERE id = 1; -- 101
   -- 예상결과 : 102, 실제결과 : 101
   ```

   - 해결 방안
     - SELECT FOR UPDATE 사용

2. **Deadlock**

   - 여러 트랜잭션이 서로의 자원을 기다리며 무한 대기하는 상태

   ```sql
   -- 트랜잭션 1
   UPDATE accounts SET balance = balance - 100 WHERE id = 'A';
   UPDATE accounts SET balance = balance + 100 WHERE id = 'B';

   -- 트랜잭션 2 (동시에)
   UPDATE accounts SET balance = balance - 100 WHERE id = 'B';
   UPDATE accounts SET balance = balance + 100 WHERE id = 'A';
   ```

   - 해결 방안
     - 트랜잭션에서 테이블 접근 순서 동일
     - 트랜잭션을 가능한 짧게 유지
     - 한 번에 수정하는 데이터의 범위를 최소화

### 6. Lock

- 여러 트랜잭션이 동시에 데이터를 조작할 때 일관성과 무결성 유지 위한 메커니즘
- 하나의 트랜잭션이 사용 중인 데이터를 다른 트랜잭션이 접근하지 못하도록 제한

1. **공유 락 (Shared Lock, S Lock)**

   - 데이터를 읽을 때 사용
   - 다른 트랜잭션의 읽기는 허용하지만 쓰기는 막음

   ```sql
   SELECT * FROM accounts WHERE id = 'A' LOCK IN SHARE MODE;
   ```

2. **배타적 락 (Exclusive Lock, X Lock)**

   - 데이터를 변경할 때 사용
   - 다른 트랜잭션의 읽기와 쓰기 모두 막음

   ```sql
   SELECT * FROM accounts WHERE id = 'A' FOR UPDATE;
   ```

### 7. 트랜잭션 격리 수준

1. **READ UNCOMMITTED**
   - 커밋되지 않은 데이터 읽기 가능
   - Dirty Read 발생 가능
   - 정합성이 중요하지 않은 대량 데이터 집계 시 사용
2. **READ COMMITED**
   - 커밋된 데이터만 읽기 가능
   - Non-repeatable Read 발생 가능
3. **REPEATABLE READ**
   - MySQL의 기본 격리 수준
   - 동일 트랜잭션 내에서 동일한 결과 보장
   - Phantom Read 발생 가능
4. **SERIALIZABLE**
   - 가장 높은 격리 수준
   - 완벽한 격리 보장
   - 성능 저하 가능성
