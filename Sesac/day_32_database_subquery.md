## <mark color="#fbc956">Subquery</mark>

### 1. Subquery

- 서브쿼리는 다른 SQL 쿼리 내부에 포함된 쿼리문
- 메인 쿼리 실행 전 서브 퀄리가 먼저 실행 → 결과를 메인 쿼리에서 사용
- 서브쿼리는 괄호 `()` 안에 작성, 단독으로 실행 가능한 완전한 SELECT 문

### 2. 단일행 서브쿼리

- 서브쿼리의 결과가 단 하나의 행만 반환
- 비교 연산자 사용 가능
- 주로 집계 함수와 함께 사용
- 예시 코드
  - 평균 인구 수보다 인구가 많은 도시들 조회
    ```sql
    SELECT c.Name, c.Population
    FROM city c
    WHERE c.Population > (
    	SELECT AVG(c.Population)
    	FROM city c
    );
    ```
  - 가장 많은 인구를 가진 도시의 국가 정보
    ```sql
    SELECT Name, Population, GNP
    FROM country
    WHERE Code = (
    	SELECT CountryCode
    	FROM city
    	ORDER BY Population DESC
    	LIMIT 1
    )
    ```

### 3. 다중행 서브쿼리

- 서브쿼리의 결과가 여러 행을 반환
- IN, ANY, ALL, EXISTS등의 연산자 사용
  - `IN` : 목록 중 일치하는 값이 있는지 확인
    - `NOT IN` : 목록 중 일치하는 값이 없는지 확인
  - `ANY` : 조건을 만족하는 값이 하나라도 있는지 확인
  - `ALL` : 모든 값이 조건을 만족하는지 확인
  - `EXISTS` : 서브쿼리 결과가 존재하는지 확인
    - `NOT EXISTS` : 서브쿼리 결과가 존재하지 않는지 확인
- 예시코드
  - 인구 500만 이상인 도시가 있는 국가 찾기
    ```sql
    SELECT code, name
    FROM country
    WHERE code IN (
    	SELECT countrycode
    	FROM city
    	WHERE population >= 5000000
    );
    ```
  - 적어도 한 번이라도 대여된 적 있는 영화 찾기
    ```sql
    SELECT f.title
    FROM film f
    WHERE f.film_id IN (
    	SELECT DISTINCT i.film_id
    	FROM rental r
    	JOIN inventory i ON r.inventory_id = i.inventory_id
    );
    ```
    ```sql
    SELECT f.title
    FROM film f
    WHERE EXISTS (
    	SELECT 1
    	FROM rental r
    	JOIN inventory i ON r.inventory_id = i.inventory_id
    	WHERE i.film_id = f.film_id
    )
    ```
  - Action 카테고리에 속한 영화들 찾기
    ```sql
    SELECT title
    FROM film
    WHERE film_id IN (
    	SELECT film_id
    	FROM film_catecory
    	WHERE categoty_id = (
    		SELECT category_id
    		FROM category
    		WHERE name = 'Action'
    	)
    )
    ```

### 4. 상관 서브쿼리

- 메인쿼리의 값을 서브쿼리가 사용
- 메인쿼리와 서브쿼리가 서로 연관되며, 메인쿼리의 각 행마다 서브쿼리 실행
- 실행 속도가 상대적으로 느릴 수 있음
- 예시 코드
  - 각 국가별 가장 인구가 많은 도시 찾기
    ```sql
    SELECT c1.name, c1.population, c1.countrycode
    FROM city c1
    WHERE population = (
    	SELECT MAX(population)
    	FROM city c2
    	WHERE c1.countrycode = c2.countrycode
    )
    ```
  - 각 고객의 가장 최근 대여 기록 찾기
    ```sql
    SELECT c.first_name, c.last_name, r.rental_date
    FROM customer c
    JOIN rental r ON c.coustomer_id = r.customer_id
    WHERE r.rental_date = (
    	SELECT MAX(rental_date)
    	FROM rental
    	WHERE r.customer_id = c.customer_id
    )
    ```

---

### ☀️ 오늘의 배움

- SQL 작성시 필요한 데이터 간의 관계 파악
  - A → B로 조인 시 B가 값이 없을 경우 그 값이 필요한지 유무에 따라 INNER JOIN / LEFT JOIN
- `SHOW INDEX FROM 테이블명`
