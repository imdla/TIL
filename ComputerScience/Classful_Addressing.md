> 💡 **한 줄 요약**
>
> 클래스풀 주소 체계는 IP 주소를 규격화된 크기별로 구분시키는 방식으로, 클래스별로 유형화시켜 쉽게 식별 가능하지만, 클래스별로 네트워크 크기가 고정되어 있어 다수의 IP 주소가 낭비될 수 있다.
>
> 이 한계를 해결하기 위해 나온 것이 클래스리스 주소 체계로, 이는 클래스가 아닌 서브넷 마스크를 이용해 네트워크 주소와 호스트 주소를 구분하는 IP 주소 체계이다.

### 1. 🤔 왜 사용하는가

- **클래스풀 주소 체계(Classful Addressing)**
  - IP 주소를 규격화된 크기별로 구분시키는 방식
  - IP 주소를 클래스(A, B, C 등)별로 규격화(유형화)시켜, 쉽게 식별 가능

### 2. 💡 무엇인지 아는가(특징)

- **IPv4 주소**

  - 4바이트(32비트)
  - 1바이트씩 끊어서 표기
  - 각 바이트를 옥텟이라고 부름
  - 각 옥텟은 0 ~ 255까지 숫자 표현
  - ex. 0.0.0.0 ~ 255.255.255.255

- **클래스**
  1. **A 클래스**
     - 초기 비트가 0(2진수)으로 시작하는 1옥텟을 네트워크 주소로 사용
     - 3옥텟을 호스트 주소로 사용
     - 호스트 주소의 0과 255는 특수한 용도로 사용
       - 0 : 네트워크 식별
       - 255 : 브로드캐스트 주소
     - 네트워크 수 : $2^7$개
     - 호스트 수 : $2^{24} - 2$개
  2. **B 클래스**
     - 초기 비트가 10(2진수)으로 시작하는 2옥텟을 네트워크 주소로 사용
     - 2옥텟을 호스트 주소로 사용
     - 네트워크 수 : $2^{14}$개
     - 호스트 수 : $2^{16}-2$개
  3. **C 클래스**
     - 초기 비트가 110(2진수)으로 시작하는 3옥텟을 네트워크 주소로 사용
     - 1옥텟을 호스트 주소로 사용
     - 네트워크 수 : $2^{21}$개
     - 호스트 수 : $2^8 - 2$개

### 4. ⚠️ 단점

- **클래스풀 주소 체계의 단점**
  - 클래스별로 네트워크 크기 고정
    → 다수의 IP 주소가 낭비될 수 있음
  - ex. 특정 조직에 컴퓨터 255개일 경우
    - C 클래스 주소 사용하지 못하고 B 클래스 사용해야 함
      → IP 주소 낭비 발생 가능

### 4. 🔄 개선 방법(최근 기술 동향)

- **클래스리스 주소 체계(Classless Addressing)**

  - 클래스풀 주소 체계의 문제 해결
    - 더욱 유동적인 방식으로 네트워크를 구획 가능
  - 클래스가 아닌 서브넷 마스크를 이용해 네트워크 주소와 호스트 주소를 구분하는 IP 주소 체계
    - 서브넷 마스크 : 네트워크 구분을 위한 비트열
    - 해당 비트열
      - 네트워크 주소 : 연속된 1(이진수)로 표현
      - 호스트 주소 : 연속된 0(이진수)으로 표현
  - 특정 IP 주소와 서브넷 마스크를 비트 연산 수행 시, 네트워크 주소알 수 있음
    - ex. 서브넷 마스크가 255.255.255.0,
      IP 주소가 168.168.168.168인 경우 - 비트 연산 수행 시 네트워크 주소인 168.168.168.0을 알아냏 수 있음 - 1옥텟을 호스트 주소로 사용할 수 있음

- **CIDR(Classless Inter Domain Routing Notation) 표기법**
  - 서브넷 마스크 표기 시 사용
  - 형식 : `IP 주소 / 서브넷 마스크 비트열의 1의 수`
  - ex. IP 주소가 168.168.168.168이며,
    서브넷 마스크가 255.255.255.0인 경우 - 168.168.168.168/24와 같이 표기 가능
