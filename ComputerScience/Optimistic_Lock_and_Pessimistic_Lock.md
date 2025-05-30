> 💡 **한 줄 요약**
>
> 데이터베이스 트랜잭션에서 동시성 제어를 위한 주요 기법으로 낙관적 락과 비관적 락이 있는데, 이는 데이터 무결성을 유지하며 여러 트랜잭션이 동시에 데이터에 접근할 때 발생할 수 있는 충돌 해결 시 사용한다.

### 1. 🤔 왜 사용하는가

- **낙관적 락 & 비관적 락**
  - 데이터베이스 트랜잭션에서 동시성 제어를 위한 주요 기법
  - 데이터 무결성 유지하며 여러 트랜잭션이 동시에 데이터에 접근할 때 발생할 수 있는 충돌 해결 시 사용

### 2. 💡 무엇인지 아는가(특징)

- **낙관적 락(Optimistic Lock)**

  - 데이터 충돌이 적을 것으로 가정
  - 데이터를 읽을 때 락을 설정하지 않고 트랜잭션이 데이터를 수정할 때 충돌이 발생하지 않았는지 확인하는 방식
    - version 같은 별도의 구분 컬럼을 사용해 데이터가 변경되었는지 확인
    - 충돌이 발생하면 데이터베이스가 아닌 애플리케이션에서 직접 롤백이나 재시도 처리 필요

- **비관적 락(Pessimistic Lock)**
  - 데이터 충돌이 많을 것으로 가정
  - 트랜잭션이 시작될 때 공유락(Shared Lock, S-Lock) 또는 베타락(Exclusive Lock, X-Lock)을 설정해 다른 트랜잭션이 해당 데이터에 접근하지 못하도록 하는 방식
    - S-Lock : 다른 트랜잭션에서 읽기는 가능하지만 쓰기는 불가능
    - X-Lock : 다른 트랜잭션에서 읽기, 쓰기 모두 불가능
      - MySQL은 일관된 읽기(Consistent Nonlocking Reads)를 지원해 X-Lock이 걸려 있어도 단순 SELECT로 읽을 수 있음

> **낙관적 락과 비관적 락의 차이점**

1. **충돌 가능성**
   - 낙관적 락 : 충돌이 자주 발생하지 않을 것이라 가정
   - 비관적 락 : 충돌이 자주 발생할 것이라고 가정
2. **데이터베이스 락 사용 여부**
   - 낙관적 락 : 락을 사용하지 않음
   - 비관적 락 : 트랜잭션이 시작될 때 락을 설정
3. **성능**
   - 낙관적 락 : 락을 설정하지 않아 성능이 더 좋을 수 있음
     - 충돌이 발생할 경우 롤백이나 재시도 처리 필요
       → 성능 떨어질 수 있음
       ⇒ 충돌이 발생하면 해결하는 방식
   - 비관적 락 : 락을 설정하기 때문에 다른 트랜잭션이 대기해야 함
     → 성능이 저하될 수 있음
     ⇒ 애초에 충돌을 방지하는 방식

### 3. 💪 적용

> **언제 어떤 락을 사용 ?**

- **비관적 락**
  - 데이터 충돌이 자주 발생
  - 데이터 무결성이 중요한 경우
- **낙관적 락**
  - 조회 작업이 많은 경우
  - 접근 성능이 중요한 경우
