> 💡 **한 줄 요약**
>
> JPA 연관관계는 데이터베이스의 테이블 간 관계를 객체와 매핑하는 방법으로, 객체의 참조와 테이블의 외래키를 매핑한다.
>
> 테이블의 외래키는 양방향 조인이 가능하지만, 객체의 참조는 한쪽 방향으로만 접근이 가능해 방향에 따라 단방향, 양방향 연관관계로 나뉜다.
>
> 연관관계의 종류는 다대일, 일대다, 다대다, 일대일 관계가 있다.
>
> 다대일은 데이테베이스의 외래키가 존재하는 테이블에 작성하고, @JoinColumn으로 매핑한다.
>
> 일대다에서 단방향은 부모 엔티티가 자식 엔티티를 참조하지 않아 자식 엔티티가 부모 엔티티의 외래키를 저장할 수 없어 별도 테이블이 필요해 이는 권장하지 않는다. 다대일의 상대방으로 사용되는데 @OneToMany 관계를 명시하며 연관관계 주인 지정(mappedBy), 영속성 전이 설정(cascade), 고아 객체 제거 여부(orphanRemoval), 로딩 전략(fetch)를 설정할 수 있다.
>
> 일대일에서 단방향은 부모 엔티티가 자식 엔티티의 식별자를 알 수 없어 추가 쿼리가 발생할 수 있고, 양방향은 부모 엔티티가 조회될 때 자식 엔티티도 함께 로딩 되어 성능 저하를 발생시킬 수 있다.
>
> 다대다는 중간 엔티티를 만들어 일대다, 다대일 관계로 만들어 외래키를 관리한다.

## 1. 🤔 왜 사용하는가

- **JPA**
  - 객체와 관계형 데이터베이스 간의 매핑을 제공하는 ORM 기술
  - 데이터베이스와 상호작용 간소화, 객제 자향적인 프로그래밍 가능하게 함
- **연관 관계**

  - 서로 다른 엔티티들 간의 관계
  - 애플리케이션 내 데이터를 효율적으로 구성하기위해 중요

- **JPA 연관관계 방향**
  - 단방향 관계 : 한 쪽의 엔티티만 다른 엔티티 참조
  - 양방향 관계 : 양쪽 엔티티가 서로를 참조
- **연관관계 종류**
  - 다대일 (N : 1)
  - 일대다 (1 : N)
  - 일대일 (1 : 1)
  - 다대다 (N : N)

## 2. 💡 무엇인지 아는가(특징)

### **Many-to-One (다대일)**

- 데이터베이스의 FK가 존재하는 테이블에 작성
- 관계 명시 : `@ManyToOne`
- 외래키 매핑 : `@JoinColumn(name = "매핑할 외래키 컬럼명")`

### **One-to-Many (일대다)**

- 한 엔티티가 여러 엔티티와 연결되는 관계
- **단방향 one-to-many** : 권장히지 않음

  > 문제

  - 부모 엔티티가 자식 엔티티를 직접 참조하지 않음
  - 자식 엔티티가 부모 엔티티의 외래키를 저장할 수 없음
  - 부모-자식 간의 관계 관리위해 별도 연결 테이블 필요
    → 연결 테이블 생성은 메모리 사용 증가, 성능 저하 발생 가능
  - 새로운 자식 추가 시 INSERT와 DELETE 쿼리가 여러 번 실행
    → 성능 저하 발생 가능

  > 해결

  - @JoinColumn 추가 시 자식 테이블의 외래키를 부모 엔티티가 직접 제어할 수 있어 쿼리 수 줄어듦
    - @JoinColumn : 엔티티 사이의 외래키 관계 정의
      → 여전히 추가 업데이트문 발생해 성능 저하 발생 가능

- **양방향 one-to-many**
  - 부모 측에 전이 타입 설정해야 함
  - 자식 측에 many-to-one 전이타입 설정하지 않아야 함
    - 관계 명시 : `@OneToMany`
      - mappedBy : 양방향 관계에서 연관관계의 주인 지정
      - cascade : 영속성 전이 설정
      - orphanRemoval : 고아 객체 제거 여부
      - fetch : 연관된 엔티티의 로딩 전략 설정
  - 전이 : 부모 엔티티의 상태가 자식 엔티티로 전파되는 것

### **Many-to-Many (다대다)**

- 여러 엔티티가 여러 엔티티와 연결되는 관계
- 관계 명시 : `@ManyToMany`
- **양방향 many-to-many**
  - 부모 역할을 하는 양측, 연결 테이블을 통해 두 개의 외래 키 관리
    > 문제
  - 리스트 처리 방식의 삭제 시 여러 쿼리 발생
    > 해결
  - set 사용 시 하나의 DELETE문 만으로 처리 가능해 성능 개선
  - PERSIST와 MERGE를 명시적으로 사용하는 것이 안전, 제거 전이 피하는 것이 좋음
  - 지연 로딩 사용이 필수적
    - 즉시 로딩 사용 시 성능 저하와 비효율적인 데이터 로딩 초래
  - `CascadeType.All` 및 `CascadeType.REMOVE` 사용 피해야함
    - CascadeType : 연관 엔티티의 상태를 어떻게 처리할지 설정하는 방법

### **One-to-One (일대일)**

- 한 엔티티가 다른 엔티티와 정확히 1 : 1로 연결되는 관계
- 반대편도 일대일 관계 가지며, 주 테이블이나 대상 테이블 중에 외래키 선택 가능
- 관계 명시 : `@OnetoOne`

- **단방향 one-to-one**
  - 부모 엔티티가 자식 엔티티의 식별자를 알 수 없어 추가 쿼리 발생
    → 성능 저하 발생 가능
- **양방향 one-to-one**

  - 부모 엔티티가 조회될 때 자식 엔티티도 함께 로딩
    → 추가적인 리소스 낭비와 성능 저하 초래

- 자식 엔티티의 존재 유무에 따라 참조 상태 중요
- 자식 엔티티 가져오지 않을 경우 Hibernate 처리에 어려움

> 해결

- 단방향과 양방향 One-to-One 관계 문제 해결 방법 중 하나 : @Mapsld
- 자식 측에 @Mapsld 추가해 자식 테이블이 부모 테이블과 기본키 공유
  - @Mapsld : 자식 엔티티가 부모 엔티티의 기본키 공유하도록 설정
  - 연관관계 최적화
  - 불필요한 추가 쿼리 호출하지 않도록 성능 개선
  - 기본 키 공유해 메모리 사용량 줄어듦
  - 인덱싱 필요성 최소화로 성능 향상