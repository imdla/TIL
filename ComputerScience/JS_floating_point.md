> 💡 **한 줄 요약**
>
> 자바스크립트에서 `0.1 + 0.2 === 0.3`을 실행할 경우 부동소수점 형식의 한계로 `false` 가 나온다.

### 1. 🤔 왜 사용하는가

> **자바스크립트에서 `0.1 + 0.2 === 0.3` 실행 결과 ⇒ `false`**
>
> - 원인 : 부동소수점 형식의 한계

- 자바스크립트
  - 모든 숫자는 IEEE 754 표준을 따르는 64비트 부동소수점 형식으로 저장
  - 일부 소수점 연산에서 정확한 값을 저장하지 못하는 한계 가짐
  - ex. `0.1` 과 `0.2` 를 이진수로 변환할 경우
    - 무한 반복되는 소수로 표현됨
    - 컴퓨터는 이를 근사값으로 저장
    - `0.1 + 0.2` 의 실제값은 `0.30000000000000004` 가 됨
      → 이는 `0.3` 과 일치하지 않음
      → `===` 연산자로 비교할 경우 `false` 반환

### 2. 💡 무엇인지 아는가(특징)

> **오차를 해결하기 위한 방법**

1. **특정 자릿수까지 반올림**
   - `toFixed()` , `toPrecision()` 메서드 사용
   - 단점 : 숫자가 문자열로 변환됨
2. **`Number.EPSILON` 을 활용해 비교**

   - `Number.EPSILON` 은 자바스크립트에서 표현할 수 있는 가장 작은 수
   - 이를 이용해 다음과 같이 두 수를 비교하는 함수 작성 가능

   ```jsx
   function isEqual(a, b) {
     return Math.abs(a - b) < Number.EPSILON;
   }

   console.log(isEqual(0.1 + 0.2, 0.3)); // true
   ```
