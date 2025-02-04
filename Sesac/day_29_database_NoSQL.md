## <mark color="#fbc956">비관계형 데이터베이스</mark>

### 1. 비관계형 데이터베이스

- NoSQL (Not only SQL)은 전통적인 관계형 데이터베이스의 한계 극복 위해 등장함
- 다양한 형태의 데이터 저장 가능, 대규모 분산 데이터 처리에 적합하나 정합성 떨어질 수 있음

### 2. key-value Database

- key-value 형태로 데이터 저장
- 매우 빠른 읽기 및 쓰기 성능 제공
- 캐싱, 세션 관리, 실시간 분석에 적합
- ex. redis

### 3. Graph Database

- 노드(Entities), 엣지(Relationships), 속성(Properties)을 사용해 데이터 간의 복잡한 관계를 표현
- RDB와 비교
  - RDB - table 간의 관계 나타냄
  - Graph Database - 데이터간의 관계 나타냄

### 4. Document Database

- JSON과 같은 문서 형식으로 데이터 저장
- 스키마가 자유로워 다양한 구조의 데이터 저장 가능
- 계층적 데이터 구조를 자연스럽게 표현 가능
- ex. MongoDB
