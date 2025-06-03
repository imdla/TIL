### 1. 🤔 왜 사용하는가

- **Serial GC**

  - JDK에 도입된 최초의 가비지 컬렉터
  - 단일 스레드로 동작하는 가장 단순한 형태
  - 작은 힙 메모리와 단일 CPU 환경에 적합
  - Stop-The-World 시간이 가장 길게 발생

- **Parallel GC**

  - Java 5부터 8까지 default 가비지 컬렉터로 사용
  - Serial GC와 달리 Young 영역의 GC를 멀티 스레드로 수행
  - 높은 처리량에 초점을 두기에 Throughput GC라고도 불림

- **Parallel Old GC**

  - Parallel GC의 향상된 버전
  - Old 영역에서도 멀티 스레드를 활용해 GC 수행

- **CMS(Concurrent Mark-Sweep) GC**

  - Java 5부터 8까지 사용된 가비지 컬렉터
  - 장점 : 애플리케이션 스레드와 병렬로 실행되어 Stop-The-World 시간을 최소화하도록 설계
  - 단점 : 메모리와 CPU 사용량이 많음, 메모리 압축을 수행하지 않아 메모리 단편화 문제 있음
  - Java 9부터 deprecated됨, Java 14에서 완전히 제거

- **G1(Garbage First) GC**

  - Java 9부터 default 가비지 컬렉터
  - 기존의 GC 방식과 달리 힙을 여러 개의 region으로 나누어 논리적으로 Young, Old 영역을 구분
  - 처리량과 Stop-The-World 시간 사이의 균형을 유지함
  - 32GB보다 작은 힙 메모리를 사용할 때 가장 효과적
  - GC 대상이 많은 region을 먼저 회수해 garbage first라는 이름 붙음

- **ZGC**

  - Java 11 부터 도입된 가비지 컬렉터
  - 10ms 이하의 Stop-The-World 시간과 대용량 힙을 처리할 수 있도록 설계

- **Shenandoah GC**

  - Red Hat에서 개발한 가비지 컬렉터
  - Java 12부터 도입
  - G1 GC와 마찬가지로 힙을 여러 개의 region으로 나누어 처리
  - ZGC처럼 저지연 Stop-The-World와 대용량 힙 처리를 목표로 함

- **Epslion GC**
  - Java 11부터 도입
  - GC 기능이 없는 실험용 가비지 컬렉터
  - 애플리케이션 성능 테스트에서 GC 영향 분리 및 GC 오버헤드 없이 메모리 한계 테스트 시 사용
  - 프로덕션 환경에는 적합하지 않음

### 2. 💡 무엇인지 아는가(특징)

> **G1 GC에서 Humongous 객체란 ?**

- **Humongous 객체**
  - region 크기의 50% 이상을 차지하는 큰 객체
  - 크기에 따라 하나 또는 여러 개의 연속된 region 차지 가능
  - region 내 잉여 공간
    - 다른 객체에 할당되지 않아 메모리 단편화 발생 가능
  - Young 영역을 거치지 않고 바로 Old 영역에 할당되러 Full GC가 발생할 가능성 높아짐
    - 문제 해결 : `-XX:G1HeapRegionSize` 옵션 사용
      - region 크기 조정
      - 큰 객체를 작은 객체로 분할해 처리 가능

> **2vCPU, 1GB 메모리 가진 Linux 서버에 JDK 17 설치 시 사용되는 가비지 컬렉터**

- JDK 9부터 G1 GC가 default 가바지 컬렉터임
  - 서버 스펙에 따라 자동으로 결정
- OpenJDK
  - CPU 코어 수가 2개 이상, 메모리가 2GB 이상일 경우
    → 서버를 Server-Class Machine으로 인식
  1. Server-Class Machine이면 → 가비지 컬렉터로 G1 GC가 선택
  2. 이 서버는 조건 미충족으로 → Serial GC 선택
     - G1 GC를 사용하기 위해
       - 서버 스케일업
       - `-XX: +UseG1GC` 옵션을 명시적으로 설정 필요

```bash
# 실행 중인 JVM의 GC 확인 방법
sudo jcmd {jar PID} VM.info
# or
sudo jinfo {jar PID}
```
