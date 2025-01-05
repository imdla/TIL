> 💡 **한 줄 요약**
>
> Checked Exception은 컴파일 시점에 확인되며 반드시 처리해야 하는 예외로, 이를 유발하는 메서드 호출 시 throw 사용해 호출자에게 예외 위임하거나 메서드 내에서 try-catch 사용해 해당 예외를 반드시 처리해야 한다.
>
> Unchecked Exception은 런타임 시점에 발생하는 예외로, 컴파일러가 처리 여부를 강제하지 않는다. 이는 일반적으로 프로그래머의 실수나 코드 오류로 인해 발생한다.

### 1. 🤔 왜 사용하는가

- **Checked Exception**
  - 컴파일 시점에 확인
  - 반드시 처리해야 하는 예외
  - Checked Exception 유발하는 메서드 호출 시, 메서드 시그니처에 `throws` 사용해 호출자에게 예외를 위임하거나 메서드 내에서 try-catch 사용해 해당 예외를 반드시 처리해야 함
  - ex. `IOEexception` , `SQLException`
- **Unchecked Exception**
  - 런타임 시점에 발생하는 예외
  - 컴파일러가 처리 여부를 강제하지 않음
  - 일반적으로 프로그래머의 실수나 코드 오류로 발생
  - ex. `RuntimeException`

### 2. 💡 무엇인지 아는가(특징)

- **언제 사용해야 할까?**

  - **Checked Exception**
    - 외부 환경과 상호작용에서 발생할 가능성이 높은 예외에 적합
    - 예측 가능하며, 호출하는 쪽에서 적절히 처리할 수 있는 여지 있음
    - ex. 파일 입출력, 네트워크 통신 등
  - **Unchecked Exception**
    - 코드 오류, 논리적 결함 등 프로그래머의 실수로 인해 발생할 수 있는 예외에 적합
    - ex. null 참조, 잘못된 인덱스 접근 등 (호출자가 미리 예측할 수 없음)

- **Error와 Exception 차이**
  - **Error**
    - 주로 JVM에서 발생하는 심각한 문제
    - 시스템 레벨에서 발생하는 오류
    - 프로그램에서 처리하지 않으며, 회복이 어려운 오류
    - 애플리케이션에서 복구할 수 없는 심각한 문제
    - ex. `OutOfMemoryError` , `StackOverFlowError`
  - **Exception**
    - 프로그램 실행 중 발생할 수 있는 오류 상황
    - 대부분 회복 가능성 있음
    - 프로그램 내에서 예외 처리 통해 오류 상황 제어 가능
    - 종류
      - Checked Exception
      - Unchecked Exception
