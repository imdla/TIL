## <mark color="#fbc956">데이터베이스(Database)</mark>

### 1. 데이터베이스

- 공동으로 사용하는 데이터를 통합해 저장한 운영 데이터의 집합
- 체계적으로 구조화된 데이터의 집합
- 여러 사용자가 공유해 사용 가능
- 중복 최소화, 일관성 유지하며 데이터 저장

### 2. DBMS (DataBase Management System)

- 데이터를 효율적으로 저장, 조회, 수정, 삭제할 수 있도록 도와주는 시스템
- 데이터베이스를 생성 및 관리해주는 기능을 제공하는 소프트웨어 패키지/시스템

### 3. 관계형 데이터베이스(Relational Database)

- 데이터를 테이블로 구성
  - 테이블은 열과 행으로 구성
- 자료를 여러 테이블로 나눠 관리
- 테이블간 관계 설정해 여러 데이터 쉽게 조작 가능
- SQL(Strucrured Query Language) 사용해 데이터를 관리하고 검색함

1. **테이블 (Table)**
   - 데이터베이스에서 데이터를 저장하는 기본 단위
   - 행과 열의 격자 형태 구성
   - 하나의 테이블은 특정 유형의 데이터 나타냄
2. **행 (Row) (= 레코드, 튜플)**
   - 격자의 가로 부분
   - 데이터 유형의 개별 항목
   - 각 행은 테이블 내 유일, 기본 키(Primary Key)로 구분
3. **열 (Column) (= 필드, 속성)**

   - 행이 가져야 하는 데이터의 종류 정의

4. **기본키 (Primart Key)**
   - 레코드를 고유하게 식별하는 필드 또는 필드의 조합
   - 특징
     - 유일성 : 중복된 값 가질 수 없음
     - 최소성 : 필요 최소한의 필드로 구성
     - NOT NULL : NULL 값을 가질 수 없음
5. **외래키 (Foreign Key)**
   - 한 테이블의 필드(attribute) 중 다른 테이블의 행(row)을 식별할 수 있는 키
   - 1 : N 을 구별하기 위한 키

---

## <mark color="#fbc956">데이터베이스 관계</mark>

### 1. 1 : N 관계 (one to many relationship)

- 한 테이블의 한 행이 다른 테이블의 여러 행과 관련이 있는 경우
- N이 1의 FK 가짐

### 2. M : N 관계 (many to many relationship)

- 한 테이블(A)의 한 행이 다른 테이블(B)의 여러 행과 관련이 있고, 반대로 한 테이블(B)의 한 행이 다른 테이블(A)의 여러 행과 관련이 있는 경우
- FK 로는 표현하기 부족함
  - 각각 여러개의 키를 같이 가질 수 없음
    - 고객 ID 와 상품 ID (고객 ID 1개에 상품 ID 1개)
- **중계 테이블 (association table)**
  - M : N 관계에서 두 테이블 간의 관계를 표현하기 위해 사용
    - M : N 관계는 데이터베이스에서 직접 표현 불가
    - 중계 테이블 통해 두 개의 1 : N 관계로 분해해 표현
  - 중계 테이블에는 두 테이블의 기본키(PK)가 외래키(FK)로서 포함됨
  - 새로운 **의미**(주문이라는)가 생길 수 있음
    → 1 : N 관계 (고객이랑 주문, 주문이랑 상품)

### 3. 1 : 1 관계 (one to one relationship)

- 한 테이블의 한 행이 다른 테이블의 단 하나의 행과만 관련이 있는 경우
  - 사용
    - 보안이 필요한 민감한 데이터 분리
    - 자주 사용하는 데이터와 그렇지 않은 데이터 분히
    - 선택적으로 존재하는 데이터 관리
    - 매우 큰 데이터를 별도 관리