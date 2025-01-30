## <mark color="#fbc956">AWS (Amazon Web Services)</mark>

### 1. 클라우드 컴퓨팅 플랫폼

- 서버, 데이터베이스, 스토리지 등 매우 다양한 컴퓨팅 자원을 제공하는 클라우드 서비스
- 사용자는 클라우드 서비스를 통해 물리적으로 접근하는 대신 인터넷을 통해 가상의 컴퓨팅 자원을 빌려 사용할 수 있음

### 2. 리전(Region)과 가용 영역(AZ)

- **리전 (Region)**

  - 리전(Region) : 전 세계에 존재하는 특정 위치의 데이터 센터(가용 영역, AZ)들의 그룹
  - 각 리전은 서로 독립적인 네트워크를 구성함
  - 일부 AWS 서비스를 제외하고, AWS 서비스는 리전마다 독립적 생성 & 관리 & 운영됨
  - 리전마다 지원하는 서비스에 차이가 있고 요금이 다름
  - 각 리전은 하나 이상의 AZ로 구성됨
  - ex. 서울 : ap-northeast-2

- **가용 영역 (AZ)**
  - 하나 이상의 물리적 데이터 센터가 고속 네트워크로 연결됨
  - 각 데이터 센터는 재해 등의 이상 상황에 대처하기 위해 물리적으로 먼 거리에 떨어져 있음
  - ex. 서울 : ap-northeast-2a, ap-northeast-2b, ap-northeast-2c, ap-northeast-2d
    (4개의 가용 영역 존재)
  - **Wavelength Zone**
    - 5G 네트워크에 최적화된 AWS 서비스
    - 5G 네트워크 망이 구축된 일부 리전에만 존재

## <mark color="#fbc956">IAM (Identity and Access Management)</mark>

### 1. IAM

- 사용자(User)를 생성, 사용자를 그룹(Group)으로 나눔, 그룹마다 다른 권한(Permission)을 부여하기 위한 서비스

- **루트 (Root)**

  - 회원가입 시 생성되는 최초의 계정
  - 모든 서비스에 접근할 수 있어 엄중한 관리 필요
  - 개발 & 운영과 같은 직업에는 절대 사용하지 않고, 계정 비용 관리에 사용

- **사용자 (User)**
  - 루트 계정이 생성한 사용자 개별 계정
  - 사용자마다 다른 권한을 부여해 각 사용자가 필요한 서비스만 접근할 수 있게 함
  - ex. 하나의 계정에 관리자, 개발자, 재무 사용자를 만들어 권한 부여 가능
    - 관리자 - 모든 권한
    - 개발자 - 개발자 관련된 서버, 데이터베이스, 저장소 접근 권한
    - 재무 - 비용 관련 정보 접근 권한
  - 사용자마다 권한을 제한해 실수 방지 및 중요한 내용의 노출 막을 수 있음

### 2. IAM 정책 (Policies)

- 사용자의 서비스 권한을 부여
- 이미 생상된 정책을 사용하거나 직접 정책을 생성 가능
- **정책 구성 요소**
  ```yaml
  {
    "Version": "2012-10-17",
    "Id": "S3-Account-Permissions",
    "Statement":
      [
        {
          "Sid": "1",
          "Effect": "Allow",
          "Principal": { "AWS": ["arn:aws:iam::123456789012:root"] },
          "Action": ["s3:GetObject", "s3:PutObject"],
          "Resource": ["arn:aws:s3:::mybucket/*"],
        },
      ],
  }
  ```
  - `Version` : 정책 언어 버전
  - `Statement` : 정책의 본문, 특정 서비스의 특정 행동 접근 허용 및 거부
  - `Sid` : Statement ID
  - `Effect` : Allow / Deny, 접근 허용 및 거부를 결정
  - `Principal` : 정책이 사용될 사용자 / 그룹
  - `Action` : Effect에 의해 허용 / 거부될 행동 목록
  - `Resource` : 정책이 적용될 Resource 목록

### 3. MFA (Multi-Factor Authentication)

- 계정을 보호하기 위한 추가 인증 시스템
- MFA를 설정하면 계정에 로그인할 때 일회용 인증 코드(OTP) 입력해야 함
- 설정 방법
  1. 스마트폰 - OTP 인증 위한 앱 설치
     - Google Authenticator
  2. PC - AWS 로그인
  3. AWS IAM 서비스 이동
  4. 내 보안 자격 증명 클릭
  5. MFA 디바이스 할당 클릭
  6. 디바이스 이름 작성 / 인증 관리자 앱 선택 및 다음 클릭
  7. 스마트폰 - Google Authenticator 실행 후 QR 코드 스캔 후 6자리 코드 입력
     1. 스캔 후 6자리 수 MFA 코드 1에 입력
     2. 코드 갱신 후 6자리 수 MFA 코드 2에 입력

### 4. 역할 (Role)

- 특정 AWS 서비스가 다른 AWS 서비스를 사용하기 위한 권한 부여
- ex. EC2 인스턴스에서 IAM에 접근하기 위해
  - 역할 생성, 해당 역항에 IAMReadOnlyAccess 정책 연결 후 인스턴스에 해당 역할 설정

---

## <mark color="#fbc956">프리 티어 (Free Tier)</mark>

### 1. 프리 티어

- 새로운 AWS 사용자에게 일부 서비스를 무료로 체험을 제공하는 정책
- 프리 티어는 사용 한도 존재, 한도 넘으면 요금 청구되어 주의해서 사용 필요
- 12 개월 무료 / 항상 무료 / 체험 제공으로 나뉨

### 2. 12개월 무료

- **EC2**
  - t2.micro 인스턴스를 매월 750시간 무료 사용 가능
  - 인스턴스 하나당 시간을 계산해 2개 사용 시 사용 시간이 1/2로 줄어듦
- **S3**
  - 5GB 용량
  - 매월 20,000건의 GET 요청
  - 매월 2,000건의 PUT 요청
- **RDS**
  - db.t2.micro 인스턴스를 매월 750시간 무료로 사용 가능
  - 20GB 데이터베이스 용량
  - 지원 데이터베이스 : MySQL, PostgreSQL, MariaDB, Oracle BYOL, SQL Server

### 3. 항상 무료

- **Lambda**
  - 매월 1,000,000건의 요청
  - 매월 400,000GB-초의 컴퓨팅 시간
- **DynamoDB (NoSQL DB)**
  - 25GB 데이터베이스 용량
