## <mark color="#fbc956">Closure (클로저)</mark>

### 1. Closure (클로저)

- 클로저는 어떤 함수와 해당 함수가 선언된 렉시컬 환경의 조합
- 외부 변수를 기억하고 이 외부 변수에 접근할 수 있는 함수 의미
- JavaScript에서 함수는 클로저 문맥을 생성함

### 2. 클로저 비교

- **일반**
  ```jsx
  // 일반
  function getNumber() {
    var number = 5; // ***실행 - 1번***

    function innerGetNumber() {
      return number; // 3번, 4번
    }

    return innerGetNumber(); // 2번, 5번
  }

  // number는 getNumber()안에 선언되어 있음
  console.log(number); // ReferenceError: number is not defined

  console.log(getNumber()); // 5 ***6번***
  ```
  - `getNumber()` 실행 → 1번
  - `innerGetNumber()` 실행 → 3번
  - `innerGetNumber()` 실행 종료 → 4번
  - `getNumber()` 실행 종료 → 6번
- **클로저**
  ```jsx
  // 클로저
  function getNumber() {
    var number = 5; // ***실행 - 1번***

    function innerGetNumber() {
      return number; // 5번, 6번
    }

    // 함수 자체 반환
    return innerGetNumber; // 2번, 3번
  }

  const runner = getNumber();

  console.log(runner); // [Function: innerGetNumber]
  console.log(runner()); // 5 ***4번, 7번***
  ```
  - `getNumber()` 실행 → 1번
  - `getNumber()` 실행 종료 → 3번
  - `innerGetNumber()` 실행 → 5번
  - `innerGetNumber()` 실행 종료 → 7번

### 3. 클로저 활용

1. **데이터 캐싱 - 오래 걸리는 계산에 사용**

   ```jsx
   // 일반
   function cacheFunc(newNum) {
     // 아래 계산을 매우 오래걸리는 계산인 가정
     var number = 10 * 10;

     return number * newNum;
   }

   console.log(cacheFunc(10)); // 1000
   console.log(cacheFunc(20)); // 2000
   console.log(cacheFunc(30)); // 3000

   // 클로저
   function cacheFunction(newNumb) {
     // 아래 계산을 매우 오래걸리는 계산인 가정
     var number = 10 * 10;

     function innerCacheFunction(newNumb) {
       return number * newNumb;
     }

     return innerCacheFunction;
   }

   // 복잡한 계산을 한번만 함 => number에 저장
   const runner = cacheFunction();
   // 저장한 number 값을 runner에서 number * newNumb만 실행
   console.log(runner(10)); // 1000
   console.log(runner(20)); // 2000
   ```

2. **데이터 캐싱 - 반복적으로 특정 값을 변환해야 할 때**

   ```jsx
   function cacheFunction2() {
     var number = 99;

     function increment() {
       number++;
       return number;
     }

     return increment;
   }

   const runner2 = cacheFunction2();
   console.log(runner2()); // 100
   console.log(runner2()); // 101
   console.log(runner2()); // 102
   ```

3. **정보 은닉**

   ```jsx
   function Professor(name, subject) {
     this.name = name;

     // subject는 this 키워드로 저장하지 않아
     // 객체의 프로퍼티를 액세스 할 수 없음
     var _subject = subject;

     // 하지만 생성이 완료 된 후, 함수의 메서드 안에
     // 정의된 새로운 함수에서 나중에 가져올 수 있음
     this.sayNameAndSubject = function () {
       return `안녕하세요 저는 ${this.name}입니다. ${_subject}를 맡고있습니다.`;
     };
   }

   const mia = new Professor("미아", "수학");
   console.log(mia.sayNameAndSubject()); // 안녕하세요 저는 미아입니다. 수학를 맡고있습니다.

   console.log(mia.name); // 미아
   console.log(mia._subject); // undefined
   ```
