## <mark color="#fbc956">SELECT</mark>

### 1. SELECT

1. **모든 필드 조회**

   ```sql
   SELECT * FROM 테이블명;
   ```

   - ex. country 테이블에서 모든 필드 조회
     ```sql
     SELECT * FROM country;
     ```

2. **특정 필드 조회**

   ```sql
   SELECT 테이블명.컬럼명1, 테이블명.컬럼명2 FROM 테이블명;
   ```

   - 컬럼 선택 시 테이블명 생략 가능
     - 생략하지 않는 것이 성능상 및 이후 여러 테이블 병합 조회 시 이점 있음
     ```sql
     SELECT 컬럼명1, 컬럼명2 FROM 테이블명;
     ```

3. **alias 설정**
   - **테이블명 축약 사용**
     ```sql
     SELECT t.컬럼명1, t.컬럼명2 FROM 테이블명 AS t;
     SELECT t.컬럼명1, t.컬럼명2 FROM 테이블명 t;
     ```
   - **조회 시 컬럼명 변경 가능**
     ```sql
     SELECT t.컬럼명1 AS first, t.컬럼명2 AS second FROM 테이블명 AS t;
     SELECT t.컬럼명1 first, 컬럼명2 second FROM 테이블명 t;
     ```
   - **as 생략 가능**
     ```sql
     SELECT s.student_id, s.name, s.major FROM student s;
     ```
   - ex. country 테이블에서 name과 population을 국가, 인구라는 이름으로 조회
     ```sql
     SELECT c.Name 국가, c.Population 인구 FROM country c;
     ```
4. **DISTINCT : 중복제거**

   ```sql
   SELECT DISTINCT 컬럼명 FROM 테이블명;
   ```

   - ex. country 테이블에서 Continent 종류 조회
     ```sql
     SELECT DISTINCT Continent FROM country;
     ```

### 2. WHERE

- 데이터 필터링 시 사용하는 조건절

1. **비교 연산자**

   ```sql
   WHERE Population > 100
   WHERE Population >= 100
   WHERE Population < 100
   WHERE Population <= 100
   WHERE Population = 100
   WHERE Population != 100    -- != 또는 <>
   ```

2. **논리 연산자**

   ```sql
   WHERE Population > 100 AND Continent = 'asia'
   WHERE Population > 100 OR Continent = 'asia'
   WHERE NOT Population < 100
   ```

3. **범위**

   ```sql
   WHERE Population BETWEEN 100 AND 200
   WHERE Population NOT BETWEEN 100 AND 200

   WHERE created_at BETWEEN '2024-01-01' AND '2024-12-31'
   ```

4. **포함**

   ```sql
   WHERE Code IN ('KOR', 'JPN', 'CHN')
   WHERE Code NOT IN ('KOR', 'JPN', 'CHN')
   ```

5. **NULL 여부**

   ```sql
   WHERE LifeExpectancy IS NULL
   WHERE LifeExpectancy IS NOT NULL
   ```

6. **패턴매칭**

   - **`%`** : 0개 이상의 문자
   - **`_`** : 1개의 문자

   ```sql
   WHERE Name LIKE 'S%'       -- S로 시작
   WHERE Name LIKE '%on'      -- on으로 끝남
   WHERE Name LIKE '%on%'     -- on이 포함
   WHERE Name NOT LIKE 'S%'   -- S로 시작하지 않음
   ```

   - **`BINARY`** : 대소문자 구분

   ```sql
   WHERE NameE LIKE BINARY  's%';
   ```

- 예시 코드

  - 인구가 800만 이상인 도시의 Name, Poulation

    ```sql

    SELECT c.`Name`, c.`Population`
    FROM country c
    WHERE c.`Population` >= 8000000;
    ```

  - 한국에 있는 도시의 Name, CountryCode 조회
    ```sql
    SELECT c.`Name`, c.`CountryCode`
    FROM city c
    WHERE c.`CountryCode` = 'KOR';
    ```
  - 이름이 ‘San’으로 시작하는 도시의 Name 조회
    ```sql
    SELECT c.`Name`
    FROM city c
    WHERE c.`Name` LIKE 'San%';
    ```
  - 인구가 100만에서 200만 사이인 한국 도시의 Name 조회
    ```sql
    SELECT c.`Name`
    FROM city c
    WHERE c.`Population` BETWEEN 1000000 AND 2000000
      AND c.`CountryCode` = 'KOR';
    ```
  - 인구가 500만 이상인 한국, 일본, 중국의 도시의 Name, CountryCode, Population 조회
    ```sql
    SELECT c.`Name`, c.`CountryCode`, c.`Population`
    FROM city c
    WHERE c.`Population` >= 5000000
      AND c.`CountryCode` IN ('KOR', 'JPN', 'CHN');
    ```
  - 오세아니아 대륙에서 예상 수명의 데이터가 없는 나라의 Name, LifeExpectancy, Continent 조회
    ```sql
    SELECT c.`Name`, c.`LifeExpectancy`, c.`Continent`
    FROM country c
    WHERE c.`Continent` = 'Oceania'
      AND c.`LifeExpectancy` IS NULL;
    ```

### 3. GROUP BY

- 데이터를 지정된 컬럼 기준으로 그룹화하고, 각 그룹에 대한 집계 함수 사용 가능
- GROUP BY가 포함된 SELECT절에는 GROUP BY에 포함된 컬럼이나 집계 함수만 사용 가능

- **집계 함수(Aggregation function)**

  - 여러 행의 데이터를 요약해 하나의 결과값을 반환하는 함수

  1. `COUNT()` : 행 개수
  2. `SUM()` : 합계
  3. `AVG()` : 평균
  4. `MIN()` : 최소값
  5. `MAX()` : 최대값

- 예시 코드
  - 대륙별 국가 수
    ```sql
    SELECT c.`Continent`, COUNT(*)
    FROM country c
    GROUP BY c.`Continent`;
    ```
  - Region별 평균 인구
    ```sql
    SELECT c.`Region`, AVG(c.`Population`)
    FROM country c
    GROUP BY c.`Region`;
    ```
  - 대륙별 최소/최대 인구
    ```sql
    SELECT c.`Continent`, MIN(c.`Population`), MAX(c.`Population`)
    FROM country c
    GROUP BY c.`Continent`;
    ```
  - 대륙별 인구가 1000만 이상인 국가의 수
    ```sql
    SELECT c.`Continent`, COUNT(*)
    FROM country c
    WHERE c.`Population` > 10000000
    GROUP BY c.`Continent`;
    ```
  - 대륙별 총 인구수
    ```sql
    SELECT c.`Continent`, SUM(c.`Population`)
    FROM country c
    GROUP BY c.`Continent`;
    ```
  - Region 별로 GNP가 가장 높은 Region
    ```sql
    SELECT c.`Region`, MAX(c.`GNP`)
    FROM country c
    GROUP BY c.`Region`;
    ```
  - 대륙별 평균 GNP와 평균 인구
    ```sql
    SELECT c.`Continent`, AVG(c.`GNP`), AVG(c.`Population`)
    FROM country c
    GROUP BY c.`Continent`;
    ```
  - 인구가 50만에서 100만 사이인 도시들에 대해 District별 도시 수
    ```sql
    SELECT c.`District`, COUNT(*)
    FROM city c
    WHERE c.`Population` BETWEEN 500000 AND 1000000
    GROUP BY c.`District`;
    ```
  - 아시아 대륙 국가들의 Region별 총 GNP
    ```sql
    SELECT c.`Region`, SUM(c.`GNP`)
    FROM country c
    WHERE c.`Continent` = 'asia'
    GROUP BY c.`Region`;
    ```

### 4. HAVING

- GROUP BY절의 결과에 조건을 거는 절
- WHERE와 비교

  - WHERE : 개별 행 데이터의 필터링
  - HAVING : 그룹화된 결과 필터링

- 예시 코드
  - 대륙별 국가수가 20개가 넘는 대륙, 국가 수 조회
    ```sql
    SELECT c.`Continent`, COUNT(*)
    FROM country c
    GROUP BY c.`Continent`
    HAVING COUNT(*) > 20;
    ```
  - Region별 평균 인구가 10,000,000이 넘는 지역, 평균 인구 조회
    ```sql
    SELECT c.`Region`, AVG(c.`Population`)
    FROM country c
    GROUP BY c.`Region`
    HAVING AVG(c.`Population`) > 10000000;
    ```
  - 대륙별 인구가 1,000만 이상인 국가의 수가 10개가 넘는 대륙, 국가 수 조회
    ```sql
    SELECT c.`Continent`, COUNT(*)
    FROM country c
    WHERE c.`Population` > 10000000
    GROUP BY c.`Continent`
    HAVING COUNT(*) > 10;
    ```
  - 평균 인구 수가 10,000,000이 넘는 대륙, 국가 수
    ```sql
    SELECT c.`Continent`, COUNT(*)
    FROM country c
    GROUP BY c.`Continent`
    HAVING AVG(c.`Population`) > 10000000;
    ```
  - 각 국가별 도시가 10개 이상인 국가의 CounrtyCode, 도시 수 조회
    ```sql
    SELECT c.`CountryCode`, COUNT(*)
    FROM city c
    GROUP BY c.`CountryCode`
    HAVING COUNT(*) >= 10;
    ```
  - District별 평균 인구가 100만 이상이면서 도시 수가 3개 이상인 District, 도시 수, 총 인구 조회
    ```sql
    SELECT c.`District`, SUM(c.`Population`), COUNT(*)
    FROM city c
    GROUP BY c.`District`
    HAVING AVG(c.`Population`) >= 1000000
      AND COUNT(*) >= 3;
    ```
  - 아시아 대륙 국가들 중, Region별 평균 GNP가 1,000 이상인 Region, 평균 GNP 조회
    ```sql
    SELECT c.`Continent`, c.`Region`, AVG(c.`GNP`)
    FROM country c
    WHERE c.`Continent` = 'asia'
    GROUP BY c.`Region`
    HAVING AVG(c.`GNP`) > 1000;
    ```
  - 독립연도가 1900년 이후인 국가들 중에서, 대륙별 평균 기대수명이 70세 이상인 Continent, 평균 기대수명 조회
    ```sql
    SELECT c.`Continent`, AVG(c.`LifeExpectancy`)
    FROM country c
    WHERE c.`IndepYear` >= 1900
    GROUP BY c.`Continent`
    HAVING AVG(c.`LifeExpectancy`) >= 70;
    ```
  - CountryCode별 도시 평균 인구가 100만 이상이고, CountryCode별 도시 최소 인구가 50만 이상인 데이터에서 CountryCode, 총 도시 수, 총 인구수 조회
    ```sql
    SELECT c.`CountryCode`, COUNT(*), SUM(c.`Population`)
    FROM city c
    GROUP BY c.`CountryCode`
    HAVING AVG(c.`Population`) >= 1000000
      AND MIN(c.`Population`) >= 500000;
    ```
  - 인구가 50만 이상인 city 중 CountryCode별 도시 평균 인구가 100만 이상인 국가의 CountryCode, 총 도시 수, 총 인구수 조회
    ```sql
    SELECT c.`CountryCode`, COUNT(*), SUM(c.`Population`)
    FROM city c
    WHERE c.`Population` >= 500000
    GROUP BY c.`CountryCode`
    HAVING AVG(c.`Population`) >= 1000000;
    ```

### 5. ORDER BY

- 결과를 정렬하는 구문
  - `ASC` : 오름차순 (기본값, 생략 가능)
  - `DESC` : 내림차순
  ```sql
  ORDER BY 컬럼명 [ASC/DESC]
  ```
- **`IS NULL`**

  - NULL true : 1
  - NULL false : 0

- 예시 코드
  - 인구 많은 순으로 정렬
    ```sql
    SELECT c.`Name`, c.`Population`
    FROM city c
    ORDER BY c.`Population` DESC;
    ```
  - 국가 순으로 정렬 후, 같은 국가 내에서는 인구순 정렬
    ```sql
    SELECT c.`Name`, c.`CountryCode`, c.`Population`
    FROM city c
    ORDER BY c.`CountryCode`, c.`Population` DESC;
    ```
  - 기본적으로 NULL은 작은 값으로 취급
    - 오름차순 내 NULL이 뒤로 정렬
      ```sql
      SELECT c.`Name`, c.`IndepYear`
      FROM country c
      ORDER BY c.`IndepYear` IS NULL, c.`IndepYear`;
      ```
    - 내림차순 내 NULL이 먼저 정렬
      ```sql
      SELECT c.`Name`, c.`IndepYear`
      FROM country c
      ORDER BY c.`IndepYear` IS NULL DESC, c.`IndepYear` DESC;
      ```
  - 대륙별 정렬, 같은 대륙 내에서는 GNP가 높은 순으로 정렬해 Name, Continent, GNP 조회
    ```sql
    SELECT c.`Name`, c.`Continent`, c.`GNP`
    FROM country c
    ORDER BY c.`Continent`, c.`GNP` DESC;
    ```
  - 기대수명이 짧은 순으로 정렬, NULL 값은 마지막에 나오도록 정렬해 Name, LifeExpectancy 조회
    ```sql
    SELECT c.`Name`, c.`LifeExpectancy`
    FROM country c
    ORDER BY c.`LifeExpectancy` IS NULL, c.`LifeExpectancy` ASC;
    ```
  - 대륙별 국가 수가 많은 순서대로 Continent, 국가수 조회
    ```sql
    SELECT c.`Continent`, COUNT(*)
    FROM country c
    GROUP BY c.`Continent`
    ORDER BY COUNT(*) DESC;
    ```
  - 독립연도가 있는 국가들의 대륙별 평균 기대수명이 높은 순서대로 Continent, 평균 기대수명 조회
    ```sql
    SELECT c.`Continent`, AVG(c.`LifeExpectancy`)
    FROM country c
    WHERE c.`IndepYear` IS NOT NULL
    GROUP BY c.`Continent`
    ORDER BY AVG(c.`LifeExpectancy`) DESC;
    ```

### 6. LIMIT, OFFSET

- 조회할 레코드들의 개수 제한

1. `LIMIT` : 갯수
2. `OFFSET` : OFFSET에 해당하는 다음 값부터

- 예시 코드
  - 앞에서부터 5개 조회 (ID가 1~5)
    ```sql
    SELECT * FROM city LIMIT 5;
    ```
  - 10개 이후로부터 5개 조회 (ID가 11~15)
    ```sql
    SELECT * FROM city LIMIT 5 OFFSET 10;
    ```
  - 인구가 많은 도시 중 11위부터 20위까지 조회
    ```sql
    SELECT c.`Name`, c.`Population`
    FROM city c
    ORDER BY c.`Population` DESC
    LIMIT 10 OFFSET 10;
    ```
