> 💡**한 줄 요약**
>
> 자바스크립트의 비동기 처리는 콜백 함수, Promise, async/await으로 처리할 수 있는데 async 함수를 정의하고 await 키워드를 통해 비동기 작업의 결과를 기다려 비동기 코드를 동기 코드처럼 작성할 수 있다.

### 1. 🤔 왜 사용하는가

- **콜백 함수**
  - 자바스크립트에서 비동기 처리를 구현하는 가장 기본적 방법
  - 다른 함수의 인자로 전달되어, 특정 작업이 완료된 후 호출됨
- **프로미스**
  - 콜백 함수의 단점 보안위해 ES6에 도입된 비동기 처리 방법
  - 비동기 작업의 성공 또는 실패를 나타냄
  - 비동기 작업의 상태를 관리하고, 체이닝을 통해 여러 비동기 작업을 순차적으로 처리 가능
  - then catch 메서드를 사용해 미동기 작업의 결과 처리
- **async/await**
  - 프로미스를 더욱 간결하고 직관적으로 사용할 수 있게하는 ES8의 비동기 처리 방법
  - async 함수는 항상 프로미스 반환
  - await 키워드는 프로미스가 해결될 때까지 기다림

### 2. 💡 무엇인지 아는가(특징)

- **콜백 함수**
  - 중첩이 깊어질수록 코드 복잡, 가독성 떨어짐 → 콜백 지옥
- **프로미스**
  - 콜백 지옥을 피할 수 있음, 가독성 향상
  - 체이닝이 길어질 경우 코드 복잡
- **async/await**
  - 코드의 가독성과 유지보수성 향상

### 3. ❔ 질문

- **모던 브라우저에서 비동기 로직 처리 방식**

  - 자바스크립트는 싱글스레드 언어로 멀티스레딩을 지원하지 않지만, 자바스크립트 함수로 호출한 브라우저의 Web API는 서로 다른 스레드에서 동시에 실행됨
  - 브라우저에의 콜 스택에는 호출된 함수들이 스택 구조로 쌓이는데, 비동기 함수는 처리되는 동안 콜 스택에서 기다리지 않고 곧바로 사라짐
  - 비동기 함수가 완료된 후 동작은 콜백 함수 통해 이루어짐
  - 비동기 함수를 호출할 때 인자로 콜백 함수를 넘겨주면, 콜백 함수가 별도의 메시지 큐에 쌓여 다시 콜 스택에 쌓이기를 기다림
  - 브라우저의 이벤트 루프는 주기적으로 콜 스택을 확인하고 콜 스택이 비면 메시지 큐의 태스크를 콜 스택으로 이동시킴
  - 이렇게 콜 스택은 비동기 함수가 처리되는 동안 정체되지 않고 다음 태스크 수행 가능

- **자바스크립트의 비동기 로직 지원 방식**

  - 비동기 처리는 콜백 함수를 통해 이루어졌지만, 호출하는 비동기 함수의 깊이가 깊어질수록 에러 핸들링이 까다로움
  - 이를 개선하기 위해 자바스크립트 ES6에서 비동기 함수의 성공과 실패 여부를 then catch라는 블록으로 구분해 받을 수 있는 Promise를 지원
  - Promise는 생성자 함수를 사용해 만들어, 생성자 함수는 콜백 함수를 인자로 받음
  - resolve, reject라는 인자를 순서대로 받아 각각의 함수를 통해 성공과 실패 케이스에 대해 처리 가능
  - resolve 시킨 결과는 호출자 입장에서 then 블록으로 받을 수 있음
  - reject 시킨 결과는 호출자 입장에서 catch 블록으로 받을 수 있음
  - Promise 객체는 프로퍼티로 여러 개의 비동기 함수를 병렬로 수행하고록 하는 all과 모든 비동기 함수가 완료된 후에 수행하도록 하는 allSettled 등의 정적 메서드도 가지고 있음

  - 여러 개의 비동기 함수 호출이 모두 완료된 후 수행해야 할 태스크가 있다면 ES8에 도입된 aync/awiat을 구문을 사용 가능
  - async 예약어를 붙여 선언한 함수는 리턴 타입이 Promise 객체임
  - Promise 객체의 최종 결과값을 then catch 블록으로 받거나, async 함수를 호출할 때, await 예약어를 붙여 호출하면 됨
  - await으로 호출한 비동기 함수는 동기 함수처럼 작동해, 해당 코드 라인이 완료되어 콜백 함수의 결과값이 받아진 이후에 다음 코드 라인 실행됨

- **동기와 비동기, 블로킹과 논블로킹**
  - 동기와 비동기는 호출자와 피호출자의 수행 시점과 관련
    1. 동기 : 메인 함수가 첫 번째 태스크를 처리하기 위해 서브 함수를 호출하고, 서브 함수의 처리 결과 받아 확인 후 두 번째 태스트 수행할 경우
    2. 비동기 : 메인 함수가 서브 함수를 호출만 하고 처리 결과를 확인하지 않고 두 번째 태스크를 수행할 경우
  - 블로킹과 논블로킹은 호출자와 피호출자의 제어권과 관련
    - 제어권 : 함수를 실행할 권리
    1. 블로킹 방식 : 피호출자가 함수를 끝까지 실행한 후, 제어권을 호출자에게 돌려줌
    2. 논블로킹 방식 : 피호출자가 호출자에게 제어권을 바로 돌려줌
       - 제어권을 잃은 피호출자의 태스크 수행 → 브라우저가 지원하는 이벤트 루프와 메시지 큐 활용
       - 피호출자는 제어권을 돌려주고 콜백 함수는 메시지 큐에 등록되어 대기하다가 이벤트 루프에 의해 콜 스택으로 이동