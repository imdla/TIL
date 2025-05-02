> 💡 **한 줄 요약**
>
> Micrometer는 벤더 중립적인 메트릭 계측 라이브러리로, 애플리케이션에서 발생하는 다양한 지표를 수집한다.

### 1. 🤔 왜 사용하는가

- **Micrometer**
  - 벤더 중립적인 메트릭 계측 라이브러리
  - 애플리케이션에서 발생하는 다양한 지표(ex. CPU 사용량, 메모리 소비, HTTP 요청 및 커스텀 이벤트)를 수집
  - Prometheus, Datadog, Graphite 등 여러 모니터링 시스템에 메트릭을 전송할 수 있도록 단순하고 일관된 API를 제공
    - 각 백엔드 클라이언트의 복잡한 세부 구현을 감춤
    - Spring Boot Actuator와 깊이 통합해 기본 메트릭을 자동으로 수집 및 노출할 수 있음

### 2. 💡 무엇인지 아는가(특징)

> **Spring Boot Actuator와 Micrometer의 관계**

- **Spring Boot Actuator**
  - 애플리케이션의 상태, 헬스 체크, 환경, 로그 등 여러 운영 정보를 노출하는 관리 엔드포인트를 제공
- **내부적으로 Actuator**
  - Micrometer를 사용해 JVM, HTTP, 데이터베이스 등 다양한 메트릭 수집
  - Actuator : 모니터링 및 관리 인터 페이스 제공
  - Micrometer : 그 밑에서 실제 메트릭을 계측, 여러 모니터링 시스템으로 전송하는 역할

> **Micrometer를 사용해 커스텀 메트릭을 생성하는 방법**

- ex. Micrometer 활용해 커스텀 메트릭(카운터, 타이머, 게이지)을 생성하고 업데이트

  - HTTP 요청의 건수와 처리 시간, 그리고 현재 활성 세션 수를 측정

  ```java
  package com.example.metrics;

  import io.micrometer.core.instrument.Counter;
  import io.micrometer.core.instrument.Gauge;
  import io.micrometer.core.instrument.MeterRegistry;
  import io.micrometer.core.instrument.Timer;
  import org.springframework.stereotype.Service;

  @Service
  public class CustomMetricsService {

  	private final Counter requestCounter;
  	private final Timer requestTimer;
  	private final CustomGauge customgauge;

  	// 생성자에서 MeterRegistry를 주입받아 필요한 메트릭 등록
  	public CustomMetricsService(MeterRegistry meterRegistry) {
  		// HTTP 요청 총 건수를 세는 Counter(태그로 엔드포인트 구분)
  		this.requestCounter = meterRegistry.counter("custom.requests.total", "endpoint", "/api/test");

  		// HTTP 요청 처리 시간을 측정하는 Timer(태그로 엔드포인트 구분)
  		this.requestTimer = meterRegistry.timer("sutem.request.duration", "endpoint", "/api/test")

  		// Gauge : 현재 활성 세션 수를 측정하기 위한 커스텀 객체 등록
  		this.customGauge = new CustomGauge();
  		Gauge.builder("custom.active.sessions", customGauge, CustomGauge::getActiveSessions)
  					.tag("region", "us-east")
  					.register(meterRegister);
  	}

  	/**
  	* 실제 비즈니스 로직을 실행할 때 요청 카운트와 처리 시간 측정
  	* @param requestLogic 실제 처리할 로직 (ex. HTTP 요청 처리)
  	**/
  	public void processRequest(Runnable requestLogic) {
  		// 요청 수 증가
  		requestCounter.increment();
  		// 요청 처리 시간 측정
  		requestTimer.record(requestLogic);
  	}

  	/**
  	* 활성 세션 수 업데이트(ex. 로그인/로그아웃 이벤트에서 호출)
  	* @param activeSessions 현재 활성 세션 수
  	**/
  	public void updateActiveSessions(int activeSessions) {
  		customGauge.setActiveSessions(activeSessions);
  	}

  	/**
  	* 커스텀 Gauge의 값을 저장하는 내부 클래스
  	**/
  	private static class CustomGauge {
  		// 현재 활성 세션 수를 저장(volatile을 사용해 스레드 안정성 확보)
  		private volatile double activeSessions = 0;

  		public double getActiveSessions() {
  			return activeSessions;
  		}

  		public void setActiveSessions(double activeSessions) {
  			this.activeSessions = activeSessions;
  		}
  	}
  }
  ```

  - MeterRegistry 사용
    - 생성자에서 MeterRegistry를 주입받아 애플리케이션의 모든 메트릭을 중앙에서 관리, 설정된 모니터링 백엔드로 주기적으로 전송
  - Counter
    - requestCounter는 /api/test 엔드포인트에 대한 요청 건수 카운트
    - 매 HTTP 요청마다 increment() 호출로 증가시킴
  - Timer
    - requestTimer는 HTTP 요청 처리 시간 측정
    - record() 메서드를 사용해 실제 로직 실행 시간을 기록
  - Gauge
    - customGauge는 현재 활성 세션 수를 측정하는데 사용
    - Gauge는 항상 현재 상태를 조회하는 함수를 호출해 실시간 값을 반영
