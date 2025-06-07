> 💡 **한 줄 요약**
>
> `satisfies` 키워드는 기존 타입 정보를 유지하며 해당 값이 특정 타입 조건을 충족하는지 확인할 때 사용한다.

### 1. 🤔 왜 사용하는가

- **`satisfies` 키워드**

  - 기존 타입 정보를 유지하면서 해당 값이 특정 타입 조건을 충족하는지 확인할 때 사용
  - 의도보다 더 넓은 타입으로 추론되는 일을 방지하며 타입 검사 수행 가능
  - ex. 유니온 타입을 다룰 때

    ```tsx
    type Color = "red" | "green" | "blue";
    type RGB = [red: number, green: number, blue: number];

    const palette: Record<Color, string | RGB> = {
      red: [255, 0, 0],
      green: "#00ff00",
      blue: [0, 0, 255],
    };

    // 오류 발생
    const greenNormalized = palette.green.upUpperCase();
    ```

    - `palette.green` 타입은 `string | RGB`로 변환 추론되어 `.toUpperCase()`호출 시 타입 에러 발생

    ```tsx
    const palette = {
      red: [255, 0, 0],
      green: "#00ff00",
      blue: [0, 0, 255],
    } satisfies Record<Color, string | RGB>;

    // 정상 동작
    const greenNormalized = palette.green.toUpperCase();
    ```

    - `satisfies` 활용 시 `palette.green`의 타입이 `string`으로 추론되어 `.toUpperCase()`를 정상적으로 사용 가능

### 2. 💡 무엇인지 아는가(특징)

> **`satisfies` 의 활용**

- 유니온 타입의 타입 체크가 필요할 때
- 객체 리터럴이 필수적인 타입을 만족해야하고, 동시에 추가적인 필드가 존재할 수 있는 경우
