> 💡 **한 줄 요약**
>
> `box-sizing` 은 CSS에서 요소의 크기를 어떻게 계산할지 결정하는 속성이다.

### 1. 🤔 왜 사용하는가

- **`box-sizing`**
  - CSS에서 요소의 크기를 어떻게 계산할지 결정하는 속성

1. **`box-sizing : content-box` (기본값)**
   - 적용 시 요소의 width와 height 값이 내용 영역만의 크기를 나타냄
   - width와 height는 요소의 실제 콘텐츠 크기만을 정의
     - 그 안에 추가되는 padding과 border는 크기에 포함되지 않음
2. **`box-sizing : border-box`**
   - width와 height 값이 내용 영역, padding, border를 모두 포함하는 크기를 나타냄
   - width와 height는 실제 콘텐츠의 크기뿐만 아니라 패딩과 테두리까지 포함된 크기로 설정
