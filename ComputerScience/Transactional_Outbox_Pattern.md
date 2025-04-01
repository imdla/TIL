> 💡 **한 줄 요약**
>
> 트랜잭셔널 아웃박스 패턴은 분산 시스템에서 단일 작업에 데이터베이스 쓰기 작업과 메시지 혹은 이벤트 발행이 모두 포함된 경우 발생하는 이중 쓰기 문제를 해결하기 위해 사용하는 것이다.

### 1. 🤔 왜 사용하는가

- **트랜잭셔널 아웃박스 패턴(Transactional Outbox Pattern)**
  - 분산 시스템에서 단일 작업에 데이터베이스 쓰기 작업과 메시지 혹은 이벤트 발행이 모두 포함된 경우 발생하는 이중 쓰기 문제를 해결하기 위해 사용

### 2. 💡 무엇인지 아는가(특징)

> 예시

```java
@Transactional
public void propagateSample() {
	Product product = new Product("신규 상품");
	productRepository.save(product);
	eventPublisher.propagate(new NewProductEvent(product.getId()));
}
```

→ 신규 상품 생성, 이벤트 발생하는 코드를 트랜잭션 AOP 로직이 적용된 간단한 의사코드

```java
public void doInTransaction() {
	try {
		transaction.begin();
		Product product = new Product("신규 상품");
		productRepository.save(product);
		eventPublisher.propagate(new NewProductEvent(product.getId()));
		transaction.commit();
	} catch(Exception e) {
		transaction.rollback();
	}
}
```

→ 트랜잭션은 커밋되었지만, 이벤트 발행은 실패할 수 있고
이벤트 발행은 성공했지만, 커밋 연산이 모종의 이유로 실패해 트랜잭션은 롤백 될 수 있음

- **이중 쓰기로 인해 발생하는 문제**
  → 전체 서비스의 데이터 정합성에 문제 만들거나 서비스 장애로 이어질 수 있음
- **문제 해결**
  - **트랜잭셔널 메시징(Transactional Messaging)**
    : 서비스 로직의 실행과 이벤트 발행을 원자적으로 함께 수행하는 것
    → 트랜잭셔널 아웃박스 패턴의 사용 이유

```java
@Transactional
public void propagateSample() {
	Product product = new Product("신규 상품");
	productRepository.save(product);
	productOutboxRepository.save(new ProductEvent(product.getId()));
}
```

- Product 발행 이벤트를 저장하기 위한 Outbox 테이블 생성
- 같은 트랜잭셔 내부에서 이벤트를 저장
- 원자성을 보장해주는 데이터베이스 트랜잭션 사용
  → 이벤트와 신규 상품은 모두 저장되거나, 모두 저장에 실패함
- **트랜잭셔널 아웃 박스 패턴의 기본적 구현 방식**
  - 별도의 프로세스가 Outbox 테이블에 저장된 레코드들을 주기적으로 폴링해 외부 시스템에 성공할 때까지 이벤트를 발생하는것
