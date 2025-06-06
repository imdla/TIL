> 💡**한 줄 요약**
>
> CAP 정리는 분산 데이터베이스 시스템이 CAP 중 2개의 속성만을 제공할 수 있다는 이론이다. 일관성, 가용성, 분할 내성 등 3가지 속성을 만족하는 데이터베이스 시스템은 존재하지 않는다.

### 1. 🤔 왜 사용하는가

- **CAP 정리**
  - 분산 데이터베이스 시스템이 CAP 중 2개의 속성만을 제공할 수 있다는 이론
  - 일관성(Consistency), 가용성(Availability), 분할 내성(Partition Tolerance) 등 3가지 속성을 모두 만족하는 데이터베이스 시스템은 존재하지 않음

### 2. 💡 무엇인지 아는가(특징)

> **각 속성 알아보기**

- **일관성 (Consistency)**
  - 모든 클라이언트 요청이 어느 노드에 연결되어도 같은 데이터를 볼 수 있음
- **가용성 (Availability)**
  - 노드 일부에 문제가 발생하여도 시스템은 클라이언트의 모든 요청에 유효한 응답을 전해줄 수 있어야 함
- **분할 내성 (Partition Tolerance)**
  - 노드 사이에 통신이 불가능한 상황(파티션)에서도 시스템이 계속 동작함

> **각 속성의 조합**

- 3개의 분산된 데이터베이스 존재
- 해당 분산 데이터베이스 시스템에서는 특정 서버에 쓰기 작업이 발생하면 나머지 서버에 데이터 전파됨
- 만약, A 파티션(1대의 노드), B 파티션 (2대의 노드)으로 네트워크 분할되어있을 경우, 파티션 간 노드들은 서로 통신할 수 없어 데이터 전파가 불가능

1. **CA 시스템**
   - 일관성과 가용성을 지원, 분할 내성을 희생
   - 통상적으로 네트워크 장애는 피할 수 없는 일로 여겨져 분할 시스템에서 분할 내성은 희생하기 어려움
   - 실세계에 CA 시스템은 존재하지 않음
2. **CP 시스템**
   - 파티션이 해결되기 전까지 다른데이터베이스의 연산을 중단시켜 일관성을 지키고, 가용성을 희생함
3. **AP 시스템**
   - 파티션 문제가 발생해도 읽기 및 쓰기 작업을 중단하지 않음
   - 이 경우 일관성은 희생되지만 파티션 문제가 해결되는 경우, 동기화 작업을 수행해 최종적인 일관성을 보장할 수 있음
