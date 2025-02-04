## <mark color="#fbc956">SQL Basic</mark>

### SELECT

- **조회**
  ```sql
  SELECT 컬럼명 FROM 테이블명;
  ```
- **`DISTINCT`** : 중복 제거
  ```sql
  SELECT DISTINCT 컬럼명 FROM 테이블명;
  ```

### WHERE

- **레코드 필터링**
  ```sql
  SELECT 컬럼명
  FROM 테이블명
  WHERE 조건;
  ```
- **연산자**
  | **Operator** | **설명** |
  | ------------ | ------------- |
  | = | 같음 |
  | > | 큼 |
  | < | 작음 |
  | >= | 크거나 같음 |
  | <= | 작거나 같음 |
  | <> or != | 다름 |
  | BETWEEB | 범위 사이 |
  | LIKE | 패턴 조회 |
  | IN | 여러 값 중 해 |

### ORDER BY

- 결과 집합을 **정렬**
  ```sql
  SELECT 컬럼명
  FROM 테이블명
  ORDER BY 컬럼명 [ASC|DESC]; -- default는 ASC
  ```

### AND, OR

- `AND` : 모든 조건 참
- `OR` : 조건 중 하나라도 참

```sql
SELECT 컬럼명
FROM 테이블명
WHERE 조건1 AND 조건2 OR 조건3;
```

### NOT

- **반대 결과** 제공
  ```sql
  SELECT 컬럼명
  FROM 테이블명
  WHERE NOT 조건;
  ```

### INSERT INTO

- 테이블에 **새로운 레코드 삽입**

  1. 삽입할 **열 이름과 값 모두 지정**

     ```sql
     INSERT INTO 테이블명(컬럼명1, 컬럼명2, ...)
     VALUES (값1, 값2, ...)
     ```

  2. **값만 지정**

     : 테이블의 모든 열에 대한 값을 추가하는 경우, 값의 순서가 테이블의 열과 동일한 순서

     ```sql
     INSERT INTO 테이블명
     VALUES (값1, 값2, ...)
     ```

### NULL

- 레코드 생성 중 비워둔 필드
- NULL값 테스트하기

  1. **IS NULL**

     ```sql
     SELECT 컬럼명
     FROM 테이블명
     WHERE 컬럼명 IS NULL;
     ```

  2. **IS NOT NULL**

     ```sql
     SELECT 컬럼명
     FROM 테이블명
     WHERE 컬럼명 IS NOT NULL;
     ```

### UPDATE

- **기존 레코드 수정**
  ```sql
  UPDATE 테이블명
  SET 컬럼명1 = 값1, 컬럼명2 = 값2, ...
  WHERE 조건;
  ```
  - WHERE절 생략 시 모든 레코드 업데이트

### DELETE

- **기존 레코드 삭제**
  ```sql
  DELETE FROM 테이블명 WHERE 조건;
  ```
  - WHERE절 생략 시 모든 레코드 삭제

### SELECT TOP

- **반환할 레코드 수 지정**
  ```sql
  SELECT TOP 반환할 수|퍼센트 컬럼명
  FROM 테이블명
  WHERE 조건;
  ```
  - MySQL 구문
    ```sql
    SELECT 컬럼명
    FROM 테이블명
    WHERE 조건
    LIMIT 반환할 수;
    ```

### Aggregate Function (집계 함수)

- 값 집합에 대한 계산 수행, 단일 값 반환
- 종류
  - `MIN()` : 가장 작은 값 반환
  - `MAX()` : 가장 큰 값 반환
  - `COUNT()` : 집합의 행 수 반환
  - `SUM()` : 열의 총합 반환
  - `AVG()` : 열의 평균 값 반

### LIKE

- WHERE절의 **지정된 패턴 검색**
  - **`%`** : 0개 ~ 여러개의 문자
  - **`_`** : 하나의 문자 (단일 문자)
  - **`[]`** : 내부 문자 중 하나라도 일치하는 경우 반환
  - **`-`** : 다양한 문자 범위 지
  ```sql
  SELECT 컬럼명
  FROM 테이블명
  WHERE 컬럼명 LIKE 지정패턴;
  ```

### IN

- WHERE절에 여러 개의 값 지정 가능
  ```sql
  SELECT 컬럼명
  FROM 테이블명
  WHERE 컬럼명 IN (값1, 값2, ...)
  ```

### BETWEEN

- 주어진 범위 내에서 값을 선택
- 시작 값과 끝 값이 포함
  ```sql
  SELECT 컬럼명
  FROM 테이블명
  WHERE 컬럼명 BETWEEN 값1 AND 값2
  ```

### ALIAS

- 별칭은 테이블이나 테이블의 열에 임시 이름 지정
- 키워드 : `AS`
  ```sql
  SELECT 컬럼명 AS 컬럼 별칭 FROM 테이블명 AS 테이블 별칭;
  ```

### JOIN

- 두 개 이상의 테이블에서 관련 열을 기준으로 행 결합
- 유형

  1. **`(INNER) JOIN`** : 두 테이블 모두에서 일치하는 값을 갖는 레코드 반환
  2. **`LEFT (OUTER) JOIN`** : 왼쪽 테이블의 모든 레코드와 오른쪽 테이블의 일치하는 레코드 반환
  3. **`RIGHT (OUTER) JOIN`** : 오른쪽 테이블의 모든 레코드와 왼쪽 테이블의 일치하는 레코드 반환
  4. **`FULL (OUTER) JOIN`** : 왼쪽 또는 오른쪽 테이블에 일치 항목이 있는 경우 모든 레코드 반

  ```sql
  SELECT 컬럼명
  FROM 테이블1
  [INNER|LEFT|RIGHT|FULL] JOIN 테이블2 ON 테이블1.컬럼명 = 테이블2.컬럼
  ```

  1. **`SELF JOIN`** : 일반 조인이지만 테이블 자체가 자체적으로 조인됨

     ```sql
     SELECT 컬럼명
     FROM 테이블1, 테이블2
     WHERE 조건;
     ```

### UNION

- 두 개 이상의 명령문의 결과 집합을 결합
- 기본적으로 고유한 값만 선택
- 조건
  - SELECT 문장은 UNION과 동일한 수의 열을 가져야 함
  - 열에도 유사한 데이터 유형이 있어야 함
  - 모든 문장의 열도 SELECT와 같은 순서여야 함
  ```sql
  SELECT 컬럼명 FROM 테이블1
  UNION
  SELECT 컬럼명 FROM 테이블2;
  ```
- **UNION ALL**
  - 중복 값 허용
  ```sql
  SELECT 컬럼명 FROM 테이블1
  UNION ALL
  SELECT 컬럼명 FROM 테이블2;
  ```

### GROUP BY

- 동일한 값을 갖는 행을 요약 행으로 그룹화
- 집계 함수와 함께 사용되어 결과 집합을 하나 이상의 열로 그룹화함
  ```sql
  SELECT 컬럼명
  FROM 테이블명
  WHERE 조건
  GROUP BY 컬럼명
  ORDER BY 컬럼명;
  ```

### HAVING

- 그룹화 된 것을 필터링

```sql
SELECT 컬럼명
FROM 테이블명
WHERE 조건
GROUP BY 컬럼명
HAVING 조건
ORDER BY 컬럼명;
```

### EXISTS

- 하위 쿼리에 레코드가 존재하는지 테스트
  - 하나 이상의 레코드가 있을 경우 `TRUE`

```sql
SELECT 컬럼명
FROM 테이블명
WHERE EXISTS
(SELECT 컬럼명 FROM 테이블명 WHERE 조건);
```

### ANY, ALL

- `ANY` : 하위 쿼리 값 중 하나라도 조건을 충족하는 경우 `TRUE`
  ```sql
  SELECT 컬럼명
  FROM 테이블명
  WHERE 컬럼명 연산자 ANY
  	(SELECT 컬럼명 FROM 테이블명 WHERE 조건);
  ```
- `ALL` : 모든 하위 쿼리 값이 조건을 충족하는 경우 `TRUE`

  - SELECT, WHERE, HAVING 과 함께 사용

  1. SELECT 사용

     ```sql
     SELECT ALL 컬럼명
     FROM 테이블명
     WHERE 조건;
     ```

  2. WHERE 또는 HAVING 사용

     ```sql
     SELECT 컬럼명
     FROM 테이블명
     WHERE 컬럼명 연산자 ALL
     	(SELECT 컬럼명 FROM 테이블명 WHERE 조건);
     ```

### SELECT INTO

- 한 테이블의 데이터를 새 테이블로 복사

1. 모든 열을 새 테이블에 복사

   ```sql
   SELECT *
   INTO 새테이블명 [IN 데이터베이스 파일]
   FROM 전테이블명
   WHERE 조건;
   ```

2. 일부 열만 새 테이블에 복사

   ```sql
   SELECT 컬럼명1, 컬럼명2, 컬럼명3, ...
   INTO 새테이블명 [IN 데이터베이스 파일]
   FROM 전테이블명
   WHERE 조건;
   ```

### INSERT INTO SELECT

- 한 테이블의 데이터를 복사해 다른 테이블에 삽입
- 소스 테이블과 대상 테이블 데이터 유형이 일치해야 함
  (대상 테이블의 기존 레코드는 영향 받지 않음)

1. 한 테이블의 모든 열을 다른 테이블로 복사

   ```sql
   INSERT INTO 테이블명2
   SELECT * FROM 테이블명1
   WHERE 조건;
   ```

2. 한 테이블의 일부 열만 다른 테이블로 복사

   ```sql
   INSERT INTO 테이블명2 (컬럼명1, 컬럼명2, 컬럼명3, ...)
   SELECT 컬럼명1, 컬럼명2, 컬럼명3
   FROM 테이블명1
   WHERE 조건;
   ```

### CASE 표현식

- 조건을 거치고 첫 번째 조건이 충족되면 값 반환 (`if-else` 문과 동일)
  - 조건이 참일 경우 → 읽기 중단 후 결과 반환
  - 조건이 거짓일 경우 → `ELSE` 절의 값을 반환

```sql
CASE
	WHEN 조건1 THEN 결과1
	WHEN 조건2 THEN 결과2
	WHEN 조건3 THEN 결과3
	ELSE 결과
END;
```

### NULL() 함수

- `IFNULL()` , `ISNULL()`, `COALESCE()`, `NVL()`
- 표현식이 NULL인 경우 대체 값 반환 가능
  ```sql
  SELECT 컬럼명1, 컬럼명2 * (컬럼명 + INSULL(컬럼명, 0))
  FROM 테이블명;
  ```
  ```sql
  SELECT 컬럼명1, 컬럼명2 * (컬럼명 + COALESCE(컬럼명, 0))
  FROM 테이블명
  ```

### STORED PROCEDURES

- 저장할 수 있는 SQL 코드, 재사용 가능
- 반복적으로 작성하는 SQL 쿼리가 있는 경우 저장 프로시저로 저장 후 호출해 실행
- 저장 프로시저에 매개변수 전달하면, 저장 프로시저가 전달된 매개변수 값에 따라 작동 가능

1. 저장 프로시저 구문

   ```sql
   CREATE PROCEDURE 프로시저명
   AS
   sql 구문
   GO;
   ```

2. 저장 프로시저 실행

   ```sql
   EXEC 프로시저명;
   ```
