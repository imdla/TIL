> 💡**한 줄 요약**
>
> 스프링 컨테이너는 스프링 빈을 관리하는 역할이며, 스프링 빈은 컨테이너가 관리하는 자바 객체이다.

### 1. 🤔 왜 사용하는가

- @Component, @Bean

- **스프링 컨테이너**
  - 애플리케이션의 구성 요소를 생성하고 관리하는 역할
  - 개발자가 직접 객체를 생성하고 관리하는 부담 줄여줌
  - 애플리케이션의 설정 정보 바탕으로 스프링 빈 생성, 이들의 의존 관계 주입하는 역할
- **스프링 빈**
  - 스프링 컨테이너가 관리하는 객체
  - 스프링 프레임워크의 제어 역전과 의존성 주입 구현
  - 생명 주기
    - 생성, 의존성 주입, 사용, 소멸
