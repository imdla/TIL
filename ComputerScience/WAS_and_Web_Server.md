> 💡 **한 줄 요약**
>
> 웹 서버는 정적 컨텐츠를 제공하는 역할을 하고, WAS는 동적인 컨텐츠를 생성하거나 데이터 처리하는 역할을 한다. 웹 서버를 분리할 경우, WAS의 과부화를 방지하고, 웹 서버와 WAS의 업무를 분담할 수 있어 각 로직에 집중할 수 있다. 또한, 시스템 리소스를 효율적으로 관리할 수 있고, 로드밸런싱이나 캐싱 및 압축, HTTPS 등을 웹 서버에서 처리할 수 있도록 할 수 있다.

### 1. 🤔 왜 사용하는가

- **Web Server**

  - 정적 컨텐츠(HTML, CSS, JS, 이미지 등)를 제공하는 역할
  - 동적 컨텐츠 요청 시 요청을 WAS로 전달 가능
  - 대표적 웹 서버 : Apache, Nginx 등

  ⇒ 정적 컨텐츠 제공에 특화

- **WAS(Web Application Server)**
  - 자바에서 WAS는 서블릿 컨테이너 기능 제공
  - 동적 컨텐츠 생성 및 애플리케이션 로직 실행
  - 대표적 WAS : Tomcat
    ⇒ 동적인 컨텐츠 생성 및 데이터 처리에 특화

### 2. 💡 무엇인지 아는가(특징)

> **WAS도 정적 콘텐츠 제공 가능한데, Web Server의 필요성 ?**

- **웹 서버 따로 분리하지 않을 경우**
  - WAS가 너무 많은 역할 담당 시 **과부화**
- **웹 서버 따로 분리할 경우**
  1. **업무 분담**
     - WAS → 중요한 애플리케이션 로직에 집중 가능
     - Web Server → 정적 리소스 처리하며 업무 분담 가능
  2. **시스템 리소스 효율적 관리 가능**
     - 정적 컨텐츠 많이 사용되는 경우 → Web Server 증설
     - 애플리케이션 자원 많이 사용되는 경우 → WAS 증성
  3. **로드 밸런싱, 캐싱 및 압축, HTTPS 등을 Web Server에서 처리 가능**
