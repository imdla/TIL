## <mark color="#fbc956">SQL Database</mark>

### CREATE DATABASE

- 새로운 SQL 데이터베이스 생성

```sql
CREATE DATABASE 데이터베이스명;
```

### DROP DATABASE

- 기존 DQL 데이터베이스 삭제

```sql
DROP DATABASE 데이터베이스명;
```

### BACKUP DATABASE

- 기존 SQL 데이터베이스의 전체 백업 생성

```sql
BACKUP DATABASE 데이터베이스명
TO DISK = '파일 경로';
```

- `BACKUP WITH DIFFENTIAL` : 차등 백업
  - 마지막 전체 데이터베이스 백업 이후 변경된 데이터베이스 부분만 백업
  ```sql
  BACKUP DATABASE 데이터베이스명
  TO DISK = '파일 경로'
  WITH DIFFERENTIAL;
  ```

### CREATE TABLE

- 데이터베이스에 새로운 테이블 생성
  ```sql
  CREATE TABLE 테이블명 (
  	컬럼명1 데이터타입,
  	컬럼명2 데이터타입,
  	컬럼명3, 데이터타입,
  	...
  );
  ```
- 다른 테이블 사용해 테이블 생성
  ```sql
  CREATE TABLE 새테이블명 AS
  	SELECT 컬럼명1, 컬럼명2, ...
  	FROM 복사할 테이블명
  	WHERE 조건;
  ```

### DROP TABLE

- 데이터베이스에서 기존 테이블 삭제

```sql
DROP TABLE 테이블명;
```

### ALTER TABLE

- 기존 테이블에 열을 추가, 삭제, 수정
- 기존 테이블에 다양한 제약 조건 추가 및 삭제

1. 열 추가

   ```sql
   ALTER TABLE 테이블명
   ADD 컬럼명 데이터타입;
   ```

2. 열 삭제

   ```sql
   ALTER TABLE 테이블명
   DROP COLUMN 컬럼명;
   ```

3. 열 이름 변경

   ```sql
   ALTER TABLE 테이블명
   RENAME COLUMN 바꿀이름 to 새이름;
   ```

4. 열의 데이터 유형 변경

   - ALTER / MODIFY DATATYPE

   ```sql
   # SQL 서버 / MS 엑세스
   ALTER TABLE 테이블명
   ALTER COLUMN 컬럼명 데이터타입;

   # MySQL / Oracle
   ALTER TABLE 테이블명
   MODIFY COLUMN 컬럼명 데이터타입;
   ```

### SQL 제약 조건

- 테이블의 데이터에 대한 규칙 지정
- `CREATE TABLE` 을 통해 테이블 생성 시 지정 가능
- `ALTER TABLE` 을 통해 테이블 생성 후 지정 가능

```sql
CREATE TABLE 테이블명 (
	컬럼명1 데이터타입 제약조건,
	컬럼명2 데이터타입 제약조건,
	컬럼명3 데이터타입 제약조건,
	...
)
```

- **제약 조건**
  - `NOT NULL` : 열이 NULL 값을 가질 수 없음
  - `UNIQUE` : 열의 모든 값이 서로 다른지 확인
    - `PRIMARY KEY` 는 자동으로 제약조건 적용
  - `PRIMARY KEY NOT NULL` : 테이블의 각 행을 고유하게 식별
  - `FOREIGN KEY` : 테이블 간 링크를 파괴하는 작업 방지
  - `CHECK` : 열의 값이 특정 조건을 만족하는지 확인
  - `DEFAULT` : 값이 지정되지 않은 경우 열에 대한 기본값 설정
  - `CREATE INDEX` : 데이터베이스에서 빠른 데이터 생성과 검색에 사

### 제약조건 알아보기

1. **`NOT NULL`**

   - 열이 NULL 값을 허용하지 않도록 함
   - CREATE TABLE 경우
     ```sql
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입 NOT NULL,
     	...
     );
     ```
   - ALTER TABLE 경우

     ```sql
     -- SQL 서버 / MS 액세스
     ALTER TABLE 테이블명
     ALTER COLUMN 컬럼명 데이터타입 NOT NULL;

     -- MySQL / Oracle
     ALTER TABLE 테이블명
     MODIFY COLUMN 컬럼명 데이터타입 NOT NULL;
     ```

2. **`UNIQUE`**

   - 열의 모든 값이 다르다는 것을 보장
   - PRIMARY KEY 에는 자동으로 UNIQUE 제약조건 적용됨
   - CREATE TABLE 경우

     ```sql
     -- SQL 서버 / 오라클 / MS 액세스
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL UNIQUE,
     	컬럼명2 데이터타입 NOT NULL,
     	...
     );

     -- MySQL
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입 NOT NULL,
     	...,
     	UNIQUE (컬럼명1)
     );

     -- 여러 열에 대한 UNIQUE 제약조건 정의
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입 NOT NULL,
     	CONSTRAINT 새컬럼명 UNIQUE (컬럼명1, 컬럼명2)
     );
     ```

   - ALTER TABLE 경우

     ```sql
     -- MySQL / SQL 서버 / Oracle / MS 액세스
     ALTER TABLE 테이블명
     ADD UNIQUE (컬럼명);

     -- 여러 열에 대한 UNIQUE 제약조건 정의
     ALTER TABLE 테이블명
     ADD CONSTRAINT 새컬럼명 UNIQUE (컬럼명1, 컬럼명2);
     ```

   - UNIQUE 제약 조건 삭제

     ```sql
     -- MySQL
     ALTER TABLE 테이블명
     DROP INDEX 새테이블명;

     -- SQL 서버 / Oracle / MS 액세스
     ALTER TABLE 테이블명
     DROP CONSTRAINT 새컬럼명;
     ```

3. **`PRIMARY KEY`**

   - 테이블의 각 레코드를 고유하게 식별
   - 기본 키는 고유한 값 포함, NULL 값 포함 불가
   - 테이블에는 기본 키가 하나만 존재 가능, 기본 키는 단일 또는 여러 개의 열로 구성 가능
   - CREATE TABLE의 경우

     ```sql
     -- MySQL
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입,
     	...,
     	PRIMARY KEY (컬럼명1)
     );

     -- SQL 서버 / Oracle / MS 액세스
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL PRIMARY KEY,
     	컬럼명2 데이터타입 NOT NULL,
     	...
     );

     -- 여러 열에 대한 PRIMARY KEY 제약조건
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입 NOT NULL,
     	...,
     	CONSTRAINT 새컬럼명 PRIMARY KEY (컬럼명1, 컬럼명2)
     );
     ```

   - ALTER TABLE의 경우

     ```sql
     -- MySQL / SQL 서버 / Oracle / MS 액세스
     ALTER TABLE 테이블명
     ADD PRIMARY KEY (컬럼명);

     -- 여러 열에 대한 PRIMARY KEY 제약조건
     ALTER TABLE 테이블명
     ADD CONSTRAINT 새컬럼명 PRIMARY KEY (컬럼명1, 컬럼명2);
     ```

   - PRIMARY KEY 제약 조건 삭제

     ```sql
     -- MySQL
     ALTER TABLE 테이블명
     DROP PRIMARY KEY;

     -- SQL 서버 / Oracle / MS 액세스
     ALTER TABLE 테이블명
     DROP CONSTRAINT 새컬럼명
     ```

4. **`FOREIGN KEY`**

   - 테이블 간의 링크를 파괴하는 동작 방지
   - 한 테이블의 필드 또는 필드 모음
   - 다른 테이블의 필드를 참조
     - 자식 테이블 : 외래 키가 있는 테이블
     - 참조 테이블(부모 테이블) : 기본 키가 있는 테이블
   - CREATE TABLE의 경우

     ```sql
     -- MySQL
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입 NOT NULL,
     	컬럼명3 데이터타입,
     	PRIMARY KEY (컬럼명1),
     	FOREIGN KEY (컬럼명2) REFERENCES 부모 테이블명(컬럼명2)
     );

     -- SQL 서버 / Oracle / MS 액세스
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL PRIMARY KEY,
     	컬럼명2 데이터타입 NOT NULL,
     	컬럼명3 데이터타입 FOREIGN KEY REFERENCES 부모 테이블명(컬럼명3)
     );

     -- 여러 열에 대한 FOREIGN KEY 제약조건
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입 NOT NULL,
     	컬럼명3 데이터타입,
     	PRIMARY KEY (컬럼명1),
     	CONSTRAINT 새컬럼명 FOREIGN KEY (컬럼명3)
     		REFERENCES 부모 테이블명(컬럼명3)
     );
     ```

   - ALTER TABLE의 경우

     ```sql
     -- MySQL / SQL 서버 / Oracle / MS 액세스
     ALTER TABLE 테이블명
     ADD FOREIGN KEY (컬럼명) REFERENCES 부모 테이블명(컬럼명)

     -- 여러 열에 대한 FOREIGN KEY 제약조건
     ALTER TABLE 테이블명
     ADD CONSTRAINT 새컬럼명
     FOREIGN KEY (컬럼명) REFERENCES 부모 테이블명(컬럼명)
     ```

   - FOREIGN KEY 제약 조건 삭제

     ```sql
     -- MySQL
     ALTER TABLE 테이블명
     DROP FOREIGN KEY 삭제할 컬럼명

     -- SQL 서버 / Oracle / MS 액세스
     ALTER TABLE 테이블명
     DROP CONSTRAINT 삭제할 컬럼명;
     ```

5. **`CHECK`**

   - 열에 배치할 수 있는 값의 범위 제한(해당 열에 대한 특정 값만 허용)
   - 테이블에 조건 정의 시 CHECK 행의 다른 열의 값에 따라 특정 열의 값 제한 가능
   - CREATE TABLE의 경우

     ```sql
     -- MySQL
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입 NOT NULL,
     	컬럼명3 데이터타입,
     	CHECK (조건)
     );

     -- SQL 서버 / Oracle / MS 액세스
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입 NOT NULL,
     	컬럼명3 데이터타입 CHECK (조건)
     );

     -- 여러 열에 대한 CHECK 제약조건
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입 NOT NULL,
     	CONSTRAINT 새컬럼명 CHECK (여러 컬럼에 대한 조건)
     );
     ```

   - ALTER TABLE의 경우

     ```sql
     ALTER TABLE 테이블명
     ADD CHECK (조건)

     -- 여러 열에 대한 CHECK 제약조건
     ALTER TABLE 테이블명
     ADD CONSTRAINT 새컬럼명 CHECK (여러 컬럼에 대한 조건)
     ```

   - CHECK 제약 조건 삭제

     ```sql
     -- MySQL / Oracle / MS 액세스
     ALTER TABLE 테이블명
     DROP CONSTRAINT 새컬럼명;

     -- MySQL
     ALTER TABLE 테이블명
     DROP CHECK 새컬럼명;
     ```

6. **`DEFAULT`**

   - 열의 기본값 설정
   - CREATE TABLE의 경우
     ```sql
     -- MySQL / SQL 서버 / Oracle / MS 액세스
     CREATE TABLE 테이블명 (
     	컬럼명1 데이터타입 NOT NULL,
     	컬럼명2 데이터타입 NOT NULL,
     	컬럼명3 데이터타입 DEFAULT 지정할 기본값
     );
     ```
   - ALTER TABLE의 경우

     ```sql
     -- MySQL
     ALTER TABLE 테이블명
     ALTER 컬럼명 SET DEFAULT 지정할 기본값;

     -- SQL 서버
     ALTER TABLE 테이블명
     ADD CONSTRAINT 새컬럼명
     DEFAULT 지정할 기본값 FOR 컬럼명;

     -- MS 액세스
     ALTER TABLE 테이블명
     ALTER COLUMN 컬럼명 SET DEFAULT 지정할 기본값
     ```

   - 기본 제약 조건 삭제

     ```sql
     -- MySQL
     ALTER TABLE 테이블명
     ALTER 컬럼명 DROP DEFAULT;

     -- SQL 서버 / Oracle / MS 액세스
     ALTER TABLE 테이블명
     ALTER COLUMN 컬럼명 DROP DEFAULT;
     ```

### CREATE INDEX

- 테이블에 인덱스 생성
- **인덱스**
  - 데이터베이스에서 데이터를 다른 방법보다 더 빨리 검색에 사용
  - 사용자는 인덱스 볼 수 없음, 검색 및 쿼리 속도 높이는데 사용

1. CREATE INDEX 구문

   ```sql
   CREATE INDEX 인덱스명
   ON 테이블명 (컬럼명1, 컬럼명2, ...);
   ```

2. CREATE UNIQUE INDEX 구문

   ```sql
   CREATE UNIQUE INDEX 인덱스명
   ON 테이블명 (컬럼명1, 컬럼명2, ...);
   ```

### DROP INDEX

- 테이블의 인덱스 삭제

```sql
# MS 액세스
DROP INDEX 인덱스명 ON 테이블명;

# SQL 서버
DROP INDEX 테이블명.인덱스명;

# DB2, 오라클
DROP INDEX 인덱스명;

# MySQL
ALTER TABLE 테이블명
DROP INDEX 인덱스명;
```

### AUTO_INCREMENT

- 테이블에 새 레코드가 삽입될 때 고유한 번호가 자동으로 생성

```sql
-- MySQL
CREATE TABLE 테이블명 (
	컬럼명1 데이터타입 NOT NULL AUTO_INCREMENT,
	컬럼명2 데이터타입 NOT NULL,
	컬럼명3 데이터타입,
	PRIMARY KEY (컬럼명1)
);
```

### DATE

- 날짜 데이터 유형 (MySQL)
  - `DATE` : YYYY-MM-DD
  - `DATETIME` : YYYY-MM-DD HH:MI:SS
  - `TIMESTAMP` : YYYY-MM-DD HH:MI:SS
  - `YEAR` : YYYY 또는 YY

### VIEW

- SQL문의 결과 집합을 기반으로 하는 가상 테이블
- 실제 테이블과 마찬가지로 행과 열 포함
- 뷰의 필드는 데이터베이스의 하나 이상의 실제 테이블의 필드

1. CREATE VIEW 구문

   ```sql
   CREATE VIEW 뷰이름 AS
   SELECT 컬럼명1, 컬럼명2, ...
   FROM 테이블명
   WHERE 조건;
   ```

2. CREATE OR REPLACE VIEW 구문

   - 업데이트 가능

   ```sql
   CREATE OR REPLACE VIEW 뷰이름 AS
   SELECT 컬럼명1, 컬럼명2, ...
   FROM 테이블명
   WHERE 조건;
   ```

3. DROP VIEW 구문

   ```sql
   DROP VIEW 뷰이름;
   ```

### INJECTION

- 데이터베이스 파괴할 수 있는 코드 주입 기술
