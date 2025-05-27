> 💡 **한 줄 요약**
>
> 페이징 쿼리는 전체 데이터를 부분적으로 나누어 데이터를 조회하거나 처리할 때 사용한다. 이는 데이터베이스나 애플리케이션의 리소스 사용 효율을 증가시켜 로직 처리 시간을 단축할 수 있다.

### 1. 🤔 왜 사용하는가

- **페이징 쿼리(Paging Query)**
  - 전체 데이터를 부분적으로 나누어 데이터를 조회하거나 처리할 때 사용
  - 데이터를 상대적으로 작은 단위로 나누어 처리
    → 데이터베이스나 애플리케이션의 리소스 사용 효율 증가
    → 로직 처리 시간 단축
  - ex. MySQL에서 페이징 쿼리
    - 일반적으로 LIMIT, OFFSET 구문을 사용해 작성
    ```sql
    SELECT *
    FROM subscribe
    LIMIT 500
    OFFSET 0;
    ```

### 2. ⚠️ 단점

> **LIMIT, OFFSET 방식 페이징 쿼리의 단점**

- 뒤에 있는 데이터를 읽을수록 점점 응답 시간이 길어질 수 있음
  - DBMS는 지정된 OFFSET 수만큼 모든 레코드를 읽은 이후에 데이터를 가져옴

### 5. 🔄 개선 방법(최근 기술 동향)

> **LIMIT, OFFSET 방식 페이징 쿼리의 단점의 해결방법**

1. **OFFSET을 활용하지 않는 페이징 쿼리 사용**

   ```sql
   CREATE TABLE subscribe (
   	id int NOT NULL auto_increment,
   	deleted_at datetime NULL,
   	created_at datetime NOT NULL,
   	primary key(id),
   	key idx_deleted_at_id(deleted_at, id)
   );
   ```

   - 특정 기간 동안 구독을 해제한 사용자를 조회하는 쿼리
     ```sql
     SELECT *
     FROM subscribe
     WHERE deleted_at >= ? and deleted_at < ?
     ```

2. **OFFSET을 사용하지 않는 페이징**
   - 이전 페이지의 마지막 데이터 값을 기반으로 다음 페이지를 조회
   - 상황에 따라 첫 페이지 조회 쿼리와 N회차 쿼리의 모양이 다를 수 있음
   - 첫 페이지를 조회하는 쿼리
     ```sql
     SELECT *
     FROM subscribe
     WHERE deleted_at >= ? deleted at < ?
     ORDER BY deleted_at, id
     LIMIT 10;
     ```
   - 첫 페이지 이후의 페이지 : 이전에 조회된 페이지의 마지막 값을 기반으로 다음 페이지를 조회
   - ex. 이전 페이지의 마지막 값의 deleted_at이 ‘2024-01-01’이고, 식별자가 78일 경우
     ```sql
     SELECT *
     FROM subscribe
     		   # deleted_dt이 같은 케이스를 대응
     WHERE (deleted_at = '2024-01-01 00:00:00' and id > 78) or
            # 마지막 데이터 이후 데이터 조회
     	    (deleted_at > '2024-01-01 00:00:00' and deleted_at < ?)
     ORDER BY deleted_at, id
     LIMIT 10;
     ```
