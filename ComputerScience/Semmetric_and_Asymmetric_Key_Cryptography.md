> 💡 **한 줄 요약**
>
> 평문을 암호화하고 복호화하는 경우 키를 사용할 수 있다. 이때, 암복호화에 사용하는 키가 동일한 경우 대칭키 암호화라고 한다. 암복호화에 사용하는 키가 서로 다를 경우 비대칭키 암호화라고 한다.

### 1. 🤔 왜 사용하는가

- **대칭키 암호화(Symmetric Key Cryptography)**

  - 평문을 암호화하고 복호화할 때, 암복호화에 사용하는 키가 동일한 경우

- **비대칭키 암호화 / 공개키 암호화(Asymmetric Key Cryptography)**
  - 평문을 암호화하고 복호화할 때, 암복호화에 사용하는 키가 서로 다른 경우

### 2. 💡 무엇인지 아는가(특징)

- **대칭키 암호화**

  - **장점** : 비대칭키 암호화에 비해 속도 빠름
  - **단점**
    - 대칭키를 교환하는 과정에서 탈취 위험성 존재 가능
    - 각 통신 참여자 쌍마다 다른 키 필요할 수 있음
      - 통신 대상이 많아질수록 대칭키의 수가 많아져 키 관리가 복잡해질 수 있음

- **비대칭키 암호화**

  - 공개키와 개인키 존재
    1. 송신자는 수신자의 공개키를 이용해 암호화를 수행
    2. 암호화된 데이터는 수신자에게 전달
    3. 수신자에게 전달된 이후, 수신자는 개인키 사용해 복호화 수행
  - **장점** : 대칭키 암호화 방식에서 발생하는 키 교환 문제 해결
  - **단점** : 대칭키 암호화에 비해 느림

- **비대칭키 암호화에서 개인키로 데이터 암호화, 공개키로 복호화하는 경우**
  - 암호화 수행한 자에 대한 검증 / 서명을 위한 용도로 사용
