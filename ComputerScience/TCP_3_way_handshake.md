> 💡 **한 줄 요약**
>
> TCP 3-way handshake는 클라이언트와 서버 간에 신뢰할 수 있는 연결을 설정하기 위해 세 개의 메시지를 교환하는 과정을 포함하는데, 이는 TCP/IP 네트워크에서 안정적이고 연결 지향적인 통신을 설정하기 위해 사용되는 절차이다.

### 1. 🤔 왜 사용하는가

- **TCP 3-way handshake**
  - TCP/IP 네트워크에서 안정적이고 연결 지향적인 통신을 설정하기 위해 사용되는 절차
  - 클라이언트와 서버 간에 신뢰할 수 있는 연결을 설정하기 위해 세 개의 메시지(세그먼트)를 교환하는 과정

### 2. 💡 무엇인지 아는가(특징)

1. 클라이언트는 서버에 연결 요청하는 SYN 세그먼트 보냄
   - 세그먼트에는 초기 순서 번호(Sequence Number)와 윈도우 크기(Window Size) 정보가 포함됨
2. 서버는 클라이언트의 요청 수락, SYN과 ACK 플래그가 설정된 세그먼트를 클라이언트에 보냄
   - 이 세그먼트는 서버의 초기 순서 번호와 클라이언트의 초기 순서 번호에 대한 응답(ACK=클라이언트의 초기 순서 번호 + 1)을 포함
3. 클라이언트는 서버의 응답 확인 후, ACK 플래그가 설정된 세그먼트를 서버에 보냄
   - 이 세그먼트는 서버의 순서 번호에 대한 응답(ACK=서버의 초기 순서 번호 + 1)을 포함

### 3. ✅ 장점

- 절차 완료 시 클라이언트와 서버 간에 신뢰할 수 있는 연결 설정 및 데이터 전솔 시작할 수 있다.