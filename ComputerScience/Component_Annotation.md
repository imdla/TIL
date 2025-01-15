> 💡 **한 줄 요약**
>
> @Controller, @Service, @Repository 어노테이션은 클래스가 어떤 역할을 수행하는지 명시적으로 나타내며, @Controller 어노테이션을 내부적으로 사용하고 있고, @ComponentScan 기능으로 자동으로 Bean 등록된다.
>
> @Component는 특정 역할에 종속되지 않는 일반적인 Spring Bean을 나타내며, 공통 기능 제공하는 유틸리티 클래스 등에 사용한다.
>
> @Service는 비즈니스 로직을 수행하는 클래스에 사용하며, 서비스 레이어의 Bean을 나타낸다.
>
> @Controller는 Spring MVC에서 웹 요청을 처리하는 컨트롤러 클래스에 사용되며, 프레젠테이션 레이어의 Bean을 나타낸다.
>
> @Repository는 데이터베이스와의 상호작용을 수행하는 클래스에 사용되며, 데이터 액세스 레이어의 Bean을 나타낸다.

### 1. 🤔 왜 사용하는가

- **@Component, @Service, @Controller, @Repository 어노테이션**
  - 각 클래스를 특정 역할을 수행하는 Spring Bean으로 등록할 때 사용
  - 클래스가 **어떤 역할을 하는지 명시적**으로 나타냄
  - Spring의 **@ComponentScan** 기능을 통해 **자동으로 Bean 등록**됨
  - @Service, @Controller, @Repository는 내부적으로 @Component 어노테이션을 사용하고 있음

### 2. 💡 무엇인지 아는가(특징)

- **@Component**

  - 가장 일반적인 형태의 어노테이션
  - 특정 역할에 종속되지 않는 일반적인 Spring Bean
  - 사용 : 공통 기능을 제공하는 유틸리티 클래스, 특정 계층에 속하지 않는 일반적인 컴포넌트 정의

- **@Service**

  - 비즈니스 로직을 수행하는 클래스에 사용
  - 서비스 레이어의 Bean 나타냄

- **@Controller**

  - Spring MVC에서 웹 요청을 처리하는 컨트롤러 클래스에 사용
  - 프레젠테이션 레이어의 Bean을 나타냄

- **@Repository**
  - 데이터베이스와의 상호작용을 수행하는 클래스에 사용
  - 데이터 액세스 레이어의 Bean을 나타냄

> **@Controller, @Repository 대신 @Component 사용 ?**

1. Spring 6 이전 버전에서는 @Component + @RequestMapping 으로 Bean 및 핸들러 등록 가능
   - Spring 6 이후부터, @Controller 외에는 핸들러로 등록하지 않음
   - @Controller 미사용 시 웹 요청을 정상적으로 수행 불가
2. @Repository를 @Component로 대체할 경우, `PersistenceExceptionTranslationPostProcessor` 의해 예외가 `DataAccessException` 으로 변환되지 않음
   - 데이터 액세스 계층에서 발생하는 예외 처리에 영향
3. @Service, @Repository는 각 특정 계층 나타냄
   - AOP의 포인트컷 정의 시 유용하게 사용 가능
   - @Component를 사용 시 계층 구분이 불분명해져 AOP 적용 어려움
