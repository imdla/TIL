> 💡 **한 줄 요약**
>
> 캐시 스탬피드 현상은 Cache Aside 전략 사용할 때, 수 많은 요청들이 캐시 미스를 확인하고 원본 저장소에서 데이터를 가져와 캐시에 적재하는 상황으로 이를 해결하기 위해 잠금, 외부 재계산, 확률적 조기 재계산 방식으로 문제를 풀 수 있다.

### 1. 🤔 왜 사용하는가

- **캐시 스탬피드 현상 / Thundering Herd 문제**
  - 대규모 트래픽 환경에서 캐시 운용하며 Cache Aside(캐시 미스 발생 시 적재) 전략을 사용할 때
  - 수 많은 요청들이 캐시 미스를 확인하고 원본 저장소에서 데이터를 가져와 캐시에 적재하는 상황

### 2. 💡 해결

> **문제 해결**

1. **잠금(Locking) 방식**
   - 한 요청 처리 스레드가 해당 캐시 키에 대한 잠금 획득
     → 다른 요청 처리 스레드들은 잠시 대기
     → 잠금 획득한 스레드는 사용자 요청에 응답하는 과정 동안
     캐시 적재 작업은 비동기 스레드로 처리 가능
   - 잠금을 사용해 성능 저하 가능성 존재
   - 잠금 획득 스레드의 실패, 잠금의 생명 주기, 데드락 등 다양한 상황 고려 필요
2. **외부 재계산(External Recomputation)**
   - 모든 요청 처리 스레드가 캐시 적재를 수행하지 않음
   - 캐시를 주기적으로 모니터링하는 스레드를 별도로 관리해 캐시의 만료시간이 얼마 남지 않은 경우, 데이터를 갱신해 문제를 예방
   - 다시 사용되지 않은 데이터를 포함해 갱신함
   - 메모리에 대한 불필요한 연산 발생
   - 메모리 공간을 비효율적으로 사용할 가능성 존재
3. **확률적 조기 재계산(Probabilistic Early Recomputation) 방식**
   - 캐시 만료 시간이 얼마 남지 않았을 경우, 확률이라는 개념을 사용해 여러 요청 처리 스레드 중에서 적은 수만이 캐시를 적재하는 작업
