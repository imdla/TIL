> 💡 **한 줄 요약**
>
> 엔티티 매니저는 효율적인 영속 로직 수행해 영속 컨텍스트를 관리한다. 엔티티의 상태 변경하고 영속성 컨텍스트와 상호작용해 영속 로직을 수행한다.
>
> 엔티티 매니저의 persist, merge, remove, close 메서드를 이용해 엔티티는 비영속, 영속, 준영속, 삭제의 상태를 변경할 수 있다.
>
> 엔티티 매니저는 영속성 컨텍스트의 1차 캐시로부터 엔티티 조회, 쓰기 지연 저장소에 있는 쿼리들을 flush해 DB와 동기화하고, JPQL을 이용해 DB로부터 데이터를 불러오는 등 영속성 컨텍스트와 상호작용을 통해 영속 로직을 수행한다.

### 1. 🤔 왜 사용하는가

- **영속성 컨텍스트 (Persistence Context)**
  - 엔티티를 영구 저장하는 환경
  - 애플리케이션과 데이터베이스 사이 중간 계층 역할
  - 트랜잭션 단위 생성 및 관리
  - 1차 캐싱, 쓰기 지연, 변경 감지 → 영속 로직 효율성 높임
- **엔티티 매니저**
  - 효율적인 영속 로직 수행 위해 엔티티가 영속성 컨텍스트에 관리될 수 있게 해줌
  - 엔티티의 상태 변경, 영속성 컨텍스트와 상호작용해 영속 로직을 수행

### 2. 💡 무엇인지 아는가(특징)

- **엔티티 매니저의 역할**

  - 엔티티는 영속성 컨텍스트와 관련해 4가지 상태(비영속, 영속, 준영속, 삭제)를 가질 수 있음
  - 엔티티 매니저는 persist, merge, remove, close 메서드 이용해 엔티티 상태 변경 가능
  - 영속성 컨텍스트의 1차 캐시로부터 엔티티 조회 가능
  - 쓰기 지연 저장소에 있는 쿼리들을 flush애 DB와 동기화 시킬 수 있음
  - JPQL이나 Native Query를 이용해 직접 DB로부터 데이터 불러들일 수 있음

- **엔티티의 상태**
  1. **비영속 (new/transient)**
     - 엔티티 객체가 새로 생성되었지만, 아직 영속성 컨텍스트와 연관되지 않은 상태
     - 이는 데이터베이스와 전혀 관련 없음
     - 엔티티 객체는 메모리 상에만 존재
  2. **영속 (managed)**
     - 엔티티 객체가 영속성 컨텍스트에 관리되고 있는 상태
     - 엔티티의 변경 사항이 자동으로 데이터베이스에 반영됨
  3. **준영속 (detached)**
     - 엔티티 객체가 한 번 영속성 컨텍스트에 의해 관리되었지만, 현재는 영속성 컨텍스트와 분리된 상태
     - 엔티티 객체의 변경 사항이 더 이상 데이터베이스에 반영되지 않음
     - 영속성 컨텍스트 종료, 트랜잭션 종료 등을 통해 준영속 상태로 전환
  4. **삭제 (removed)**
     - 엔티티 객체가 영속성 컨텍스트에서 제거된 상태
     - 엔티티 객체가 데이터베이스에서 삭제됨