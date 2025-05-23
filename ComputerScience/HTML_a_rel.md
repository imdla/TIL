> 💡 **한 줄 요약**
>
> `<a>` 태그를 통해 외부 링크 연결 시 `rel` 속성을 설정해 중요한 정보가 유출되지 않도록 할 수 있다. `noopener` 은 외부 페이지에서 `opener` 객체에 접근할 수 없도록하고, `noreferrer` 은 Referer 헤더 정보가 노출되지 않도록 막고, `nofollow` 는 검색 엔진이 외부 링크를 따라가지 않도록 한다.

### 1. 🤔 왜 사용하는가

- **`<a>` 를 이용해 외부 링크 연결**
  → `rel` 속성을 적절히 설정해 중요 정보 유출되지 않도록 함

### 2. 💡 무엇인지 아는가(특징)

1. **`rel="noopener"` 설정**

   : 외부 페이지에서 `opener` 객체에 접근할 수 없도록 함

   - 기존 : `target="_blank"` 설정된 `<a>` 태그 통해 열린 외부 사이트에서 `window.opener` 객체에 접근 가능
   - `window.opener` 객체에 접근 허용 시
     - 악의적으로 피싱 사이트에 연결시키는 탭 내빙(Tabnabbing) 공격 가능

2. **`rel=”noreferrer”` 설정**

   : Referer 헤더 정보가 노출되지 않도록 막음

   - 기존 : 브라우저는 링크 연결 시 Referer 헤더를 통해 사용자가 어떤 웹사이트에서 이동된 것인지 전달 받음
     - 이 정보에 민감한 데이터가 포함될 수 있음
     - ex. URL에 세션 ID나 사용자 식별 정보가 포함될 경우
       → 이 정보가 외부로 유출될 수 있음
   - `noopener` 와 마찬가지로 `opener` 를 생략하는 기능 포함됨

3. **`rel=”nofollow”` 설정**

   : 검색 엔진이 외부 링크를 따라가지 않도록 함

   - 기존 : 검색 엔진은 페이지 내의 링크를 따라가며 웹 크롤링함
     - 검증되지 않은 외부 링크나 상업적 링크가 검색 엔진에 의해 인덱싱될 경우 SEO 상 불리할 수 있음
   - 검증되지 않은 외부 링크가 SEO에 악영향을 미치는 일을 막음
     - ex. 사용자 생성 컨텐츠에서 스팸링크 방지
     - ex. 광고 링크가 SEO에 영향을 미치지 않도록함

- `noopener` , `noreferrer`, `nofollow`세 속성은 함께 적용 가능
  - `rel=”noopener noreferrer nofollow”`

> **탭 내빙 공격 ?**

- 사용자가 새롭게 열린 탭에서 기존 탭으로 돌아올 때, 해당 탭을 원본 사이트와 유사한 피싱 사이트로 이동시켜 공격을 시도하는 기법
- 해결 : `rel=”noopener”` 설정 시 새로운 탭이 원본 탭을 컨트롤을 가질 수 없어 공격 불가능
