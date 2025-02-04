## <mark color="#fbc956">Web Server & Web Application Server</mark>

### 1. Web Server

- HTML, CSS, JavaScript, 이미지, 동영상 등 정적(static) 콘텐츠를 클라이언트에게 제공하는 서버
- 클라이언트와의 기본적인 HTTP 요청 & 응답 처리하는 서버
- 대표적 서버 : Nginx, Apache HTTP Server 등

### 2. Web Application Server(WAS)

- 데이터베이스 조회 등 동적(dynamic) 로직을 처리하는 서버
- 대표적 서버 : Apache Tomcat 등

### 3. 2개 서버의 필요성

- 캐싱이 가능한 정적 콘텐츠는 웹 서버가 처리, 무거운 작업은 WAS가 처리해 성능 부하 줄일 수 있음
- 확장 필요 시 선택해 독립적으로 하나만 확장 가능

---

## <mark color="#fbc956">IP & DNS & Port</mark>

### 1. IP

- 네트워크상 호스트(컴퓨터)를 식별하는 주소
- IPv4와 IPv6 2개의 체계 존재
  - **IPv4**
    - 4 바이트로 주소 표기
    - 약 43억개 존재 가능
    - NAT(Network Address Translation)를 통해 하나의 공인(Public) IP를 공유해 사용
  - **IPv6**
    - 16바이트로 주소 표기
    - 사실상 무한 개가 존재 가능
    - IPv4 주소 문제 부족을 해결하기 위해 만들어진 체계, 아직 전환 이루어지고 있음
- **NAT**
  - 사설(Private) IP를 공인(Public) IP로 변환하는 기술
  - 공인 IP는 한정적이기 때문에 여러 개의 사설 IP와 하나의 공인 IP를 매핑해 부족한 공인 IP 대체함
  - 또한, 장치의 사설 IP를 공인 IP 뒤에 숨길 수 있어 보안 강화 가능

### 2. DNS (Domain Name System)

- 도메인 이름과 IP 주소를 변환해주는 시스템
- IP 주소는 외울 수 없음, IP 주소가 변경된 경우에도 도메인 이름만 기억하면 해당 주소 찾아갈 수 있음

### 3. 포트 (Port)

- 하나의 네트워크(IP)에서 여러 프로그램의 통신을 구분하시 위해 사용하는 식별 번호
- 포트 번호는 0부터 65535까지 존재
- 영역
  - 0 ~ 1023 (Well-known Ports) : 특정 시스템이 사용하도록 예약된 번호
    - 80 (HTTP), 443 (HTTPS), 22(SSH)
  - 1024 ~ 49151 (Registerd Ports) : 특정 애플리케이션이 할당받아 사용
    - 3306 (DB), 8080 (Spring)
  - 49152 ~
- 서버 PC에서 보안 위해 사용하는 포트만 허용하고,
  - 일반적으로 사용되는 포트 번호(3306, 22 등)는 변경하면 좋음
