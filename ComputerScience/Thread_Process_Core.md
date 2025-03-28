> 💡 **한 줄 요약**
>
> 스레드, 프로세스, 코어의 수가 많을수록 이를 생성 및 관리하는데 CPU 자원이 소모되어 성능이 향상되지 않을 확률이 크다.

### 1. 🤔 왜 사용하는가

- **스레드, 프로세스, 코어의 수가 많을수록 시스템 성능이 향상되지 않을 확률이 큼**

### 2. 💡 무엇인지 아는가(특징)

1. **스레드가 많으면 ?**

   - 운영체제가 스레드 간 컨텍스트 스위칭을 자주 수행해야 함
     - CPU 자원이 스레드 관리에 소모됨
     - 실제 작업 수행 효율이 떨어질 수 있음
   - 믾은 스레드가 동시에 실행될 경우
     - 메모리나 캐시, 락 등의 자원을 경쟁
     - 성능 저하나 데드 락이 발생할 가능성이 높아짐
   - 동기화와 상태 관리가 복잡해져 버그 발생 가능성 커짐

1. **프로세스 많으면 ?**

   - 각 프로세스는 독립된 메모리 공간 가짐
   - 많은 프로세스가 동시에 실행될 경우 메모리 사용량 급격리 증가
   - 프로세스 생성 및 관리에 상당한 시스템 자원 소모됨
   - 프로세스 간 통신(IPC) 필요 시 성능 저하 발생 가능
     - 프로세스 간 컨텍스트 스위칭은 스레드 간 컨텍스트 스위칭보다 더 많은 오버헤드 수반
     - 프로세스 수 많아질 경우 시스템 성능 저하
   - 운영체제는 동시에 실행할 수 있는 프로세스 수에 제한 있음
     - 이를 초과 시 새로운 프로세스 불가/시스템 불안정

1. **코어가 많으면 ?**
   - 많은 코어를 가진 CPU는 병렬 처리 성능 향상시킬 수 있지만
     - 이를 최대한 활용위해 소프트웨어가 멀티코어 환경에 최적화되어 있어야 함
   - 단일 스레드 작업이 주를 이루는 경우
     - 추가 코어의 이점을 제대로 활용하지 못할 수 있음
   - 코어의 수가 많아질수록 CPU의 비용과 전력 소비 증가 및 발열 관리 복잡해짐
