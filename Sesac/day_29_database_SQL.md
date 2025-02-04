## <mark color="#fbc956">SQL (Structured Query Language)</mark>

### 1. SQL

- 데이터베이스를 관리하고 처리하기 위한 표준 프로그래밍 언어
- 데이터의 삽입, 조회, 수정, 삭제 등 작업 수행

### 2. 규칙

1. SQL문은 **세미콜론(`;`)으로 끝**나야 함

   ```sql
   SELECT first_name, last_name
   FROM employees
   WHERE department_id = 100;
   ```

2. SQL 키워드는 **대소문자 구분하지 않음**
   - 가독성을 위해 키워드는 대문자로 작성
   - 테이블명과 컬럼명의 대소문자 구분은 데이터베이스 설정에 따라 다름
     - MySQL은 기본적으로 구분하지 않음
     - Oracle, PostgreSQL은 기본적으로 구분함
3. **주석 작성 : `--` / `/* */`**

   ```sql
   -- 한 줄 주석
   /*
   	여러 줄 주석
   */
   ```

4. 여러 개의 공백은 **하나의 공백으로 처리**

   - 가독성을 위해 적절한 공백과 줄바꿈 사용

   ```sql
   SELECT * FROM employees WHERE salary > 50000;

   SELECT *
   FROM employees
   WHERE salary > 50000;

   SELECT * FROM employees
   WHERE salary > 50000;
   ```

### 3. SQL 명령어

- **DDL (Data Defination Language, 데이터 정의어)**
  - 데이터베이스 구조를 정의, 변경하는 명령어
- **DML (Data Manipulation Language, 데이터 조작어)**
  - 데이터를 조작 (입력, 수정, 삭제, 조회)하는 명령어
- **DCL (Data Control Language, 데이터 제어어)**
  - 데이터베이스의 접근 권한을 제어하는 명령어
- **TCL (Transaction Control Language, 트랜잭션 제어어)**
  - 트랜잭션을 제어하는 명령어
  - 트랜잭션
    - 데이터베이스의 상태를 변화시키는 하나의 논리적 작업 단위

---

## <mark color="#fbc956">DDL</mark>

### 1. DDL (Data Defination Language, 데이터 정의어)

- 데이터베이스 구조를 정의하고 변경하는 명령어

### 2. CREATE (생성)

1. **데이터베이스 생성**

   ```sql
   CREATE DATABASE 데이터베이스이름;
   ```

2. **테이블 생성**

   ```sql
   CREATE TABLE 테이블명 (
   	컬럼명1 데이터타입 [제약조건],
   	컬럼명2 데이터타입 [제약조건],
   );
   ```

- **주요 데이터 타입**
  - 숫자 타입
    - INT : 정수
    - DECIMAL(전체 자리수, 소수 자리수) : 실수
    - BIGINT : 큰 정수
  - 문자 타입
    - CHAR(길이) : 고정 길이 문자
    - VARCHAR(길이) : 가변 길이 문자
    - TEXT : 긴 문자열
  - 날짜 / 시간 타입
    - DATE : 날짜
    - TIME : 시간
    - DATETIME : 날짜와 시간
    - TIMESTAMP : 타임 스탬프
- **제약조건**
  - PRIMARY KEY : 기본키
    - AUTO_INCREMENT와 같이 사용
  - NOT NULL : NULL 값 불허용
  - UNIQUE : 중복값 불허용
  - DEFAULT : 기본값 설정
  - FOREIGN KEY : 외래키

```sql
-- 기본 테이블 생성
CREATE TABLE student (
	student_id VARCHAR(7) PRIMARY KEY,
	name VARCHAR(10)
	grade INT,
	major VARCHAR(20)
);

-- 외래키가 있는 테이블 생성
CREATE TABLE attendance (
	attendance_id INT AUTO_INCREMENT PRIMARY KEY,
	student_id VARCHAR(7) REFERENCES student(student_id),
	date DATE,
	status VARCHAR(10)
);
```

### 3. ALTER (변경)

1. **컬럼 추가**

   ```sql
   ALTER TABLE 테이블명
   ADD 컬럼명 데이터타입 [제약조건];
   ```

   ```sql
   ALTER TABLE student
   ADD phone VARCHAR(20);
   ```

2. **컬럼 수정**

   ```sql
   ALTER TABLE 테이블명
   MODIFY 컬럼명 새로운데이터타입 [새로운제약조건];
   ```

   ```sql
   ALTER TABLE student
   MODIFY name VARCHAR(100) NOT NULL;
   ```

3. **컬럼 삭제**

   ```sql
   ALTER TABLE 테이블명
   DROP COLUMN 컬럼명;
   ```

   ```sql
   ALTER TABLE student
   DROP COLUMN phone;
   ```

### 4. DROP (삭제)

1. **데이터베이스 삭제**

   ```sql
   DROP DATABASE company;
   ```

2. **테이블 삭제**

   ```sql
   DROP TABLE student;
   ```

### 5. TRUNCATE (데이터 전체 삭제)

```sql
TRUNCATE TABLE student;
```

---

## <mark color="#fbc956">DML</mark>

### 1. DML (Data Manipulation Language, 데이터 조작어)

- 데이터를 추가, 조회, 수정, 삭제하는 기본적인 데이터 처리 담당

### 2. SELECT (조회)

1. **모든 필드 조회**

   ```sql
   SELECT * FROM 테이블명;
   ```

   ```sql
   SELECT * FROM student;
   ```

2. **특정 필드 조회**

   ```sql
   SELECT 테이블명.컬럼명1, 테이블명.컬럼명2 FROM 테이블명;
   ```

   - 컬럼 선택 시 테이블 명 생략 가능
   - 생략하지 않는 것이 성능상, 이후 여러 테이블 합쳐 조회 시 이점 있음
     ```sql
     SELECT 컬럼명1, 컬럼명2 FROM 테이블명;
     ```

3. **alias 설정**
   - 테이블명 축약 사용
     ```sql
     SELECT t.컬럼명1, t.컬럼명2 FROM 테이블명 AS t;
     SELECT t.컬럼명1, t.컬럼명2 FROM 테이블면 t;
     ```
   - 조회 시 컬럼의 이름 변경 가능
     ```sql
     SELECT t.컬럼명1 AS first, t.컬럼명2 AS second FROM 테이블명 t;
     SELECT t.컬럼명1 first, t.컬럼명2 second FROM 테이블명 t;
     ```
     ```sql
     SELECT s.student_id, s.name, s.major FROM student s;
     ```
   - as는 생략 가능

### 3. WHERE (조건)

- 데이터 필터링 시 사용하는 조건절
  ```sql
  SELECT * FROM 테이블명 WHERE 조건;
  ```
  ```sql
  SELECT * FROM student WHERE grade = 2;
  ```

### 4. INSERT (추가)

- 새로운 데이터를 테이블에 추가

  ```sql
  INSERT INTO 테이블명 (컬럼1, 컬럼2) VALUES (값1, 값2)
  ```

  ```sql
  INSERT INTO student (student_id, name, grade, major)
  VALUES ('2024001', '김철수', 1, '컴퓨터공학')

  INSERT INTO students VALUES
  	('2024002', '이영희', 2, '경영학')
  	('2024003', '박민수', 3, '물리학')
  ```

### 5. UPDATE (수정)

- 기존 데이터 수정
  ```sql
  UPDATE 테이블명 SET 컬럼 = 새값 WHERE 조건;
  ```
  ```sql
  UPDATE student
  SET grade = 2, major = '경제학'
  WHERE student_id = '2024001';
  ```

### 6. DELETE (삭제)

- 데이터 삭제
  ```sql
  DELETE FROM 테이블명 조건;
  ```
  ```sql
  DELETE FROM student
  WHERE student_id = '2024002';
  ```
