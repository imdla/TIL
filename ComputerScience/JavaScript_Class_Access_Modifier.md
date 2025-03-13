> 💡 **한 줄 요약**
>
> 자바클래스의 접근제어자는 클래스 멤버의 접근 범위를 제어하고, Public, Private, Protected가 있다.

### 1. 🤔 왜 사용하는가

- **자바스크립트의 클래스 접근제어자**
  - 클래스 멤버의 접근 범위를 제어
  1. Public
  2. Private
  3. Protected

### 2. 💡 무엇인지 아는가(특징)

- **`Public`**

  - 별도 키워드를 붙이지 않았을 때 기본 적용되는 접근제어자
  - 클래스 외부에서도 자유롭게 접근 가능
  - 클래스 외부에서 객체 통해 직접 접근 가능
  - ex. `this.name = "John";` 으로 선언된 멤버 → Public

- **`Private`**

  - 멤버 앞에 `#` 키워드를 붙여 적용하는 접근제어자
  - 클래스 내부에서만 접근 가능
  - 객체를 통한 외부 접근 불가능, 상속받은 클래스에서도 사용 불가
  - ex. `this.#secret = "hidden"` 으로 선언된 멤버 → Private

- **`Protected`**
  - 자바스크립트 언어 차원에서 지원하지 않지만, 관습적으로 `_` 를 접두어로 사용해 개발자간 약속으로 처리
  - ex. `this._secret = "hidden";` 으로 선언된 멤버 → Protected
  - 상속받은 클래스에서 접근하는 것은 허용,
    클래스 외부에서는 사용 불가
  - 클래스 외부에서 객체를 통한 접근은 하지 않을 것을 개발자들 간 약속
    - 언어 차원의 강제성은 없음

### 3. ✅ 장점

- **접근 제어자 사용 시 장점**
  - 코드의 캡슐화를 통해 데이터 보호와 유지보수 향상

### 4. ⚠️ 타입스크립트의 접근제어자와 차이점

1. **타입스크립트에서는 자바스크립트에서 지원하지 않는 접근제어자 키워드 제공**

   - 장점 : 더욱 명시적인 방식으로 접근제어자 선언 가능
   - `public` 키워드
     - Public 멤버임을 선언
     - 아무 키워드를 붙이지 않을 때와 동작 동일 (의미를 명시적으로 나타냄)
   - `private` 키워드 : Private 멤버임을 선언
   - `protected` : Protected 멤버임을 선언

   ```tsx
   class Foo {
     public a: string; // 상속 클래스 접근 O, 외부 접근 O
     private b: number; // 상속 클래스 접근 X, 외부 접근 X
     protected c: boolean; // 상속 클래스 접근 O, 외부 접근 X

     constructor(a: string, b: number, c: boolean) {
       this.a = a;
       this.b = b;
       this.c = c;
     }
   }
   ```

1. **타입스크립트에서 파라미터 프로퍼티(Parameter Properties) 기능 제공**

   - 생성자 매개변수에 접근제어자를 붙이면 해당 변수를 멤버로 자동으로 선언하고 초기화 하는 기능
   - **장점** : 간결한 코드 작성

   ```tsx
   class Foo {
     constructor(public a: string, private b: number, protected c: boolean) {
       // 이 부분을 채우지 않아도 자동으로 멤버 선언 및 최소화
     }
   }
   ```
