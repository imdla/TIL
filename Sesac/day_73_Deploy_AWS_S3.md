## <mark color="#fbc956">AWS S3(Simple Storage Service)</mark>

### 1. 클라우드 파일 스토리지

- 서버 생성 없이 정적 파일을 업로드 및 다운로드, 접근할 수 있게 도와주는 서비스
- **버킷(Bucket)** : 데이터를 저장하는 공간 단위
  - 정책을 통해 S3 접근 권한 관리 가능
- 수용자는 저장한 데이터의 양(GB)과 데이터 요청(PUT, GET 등) 수에 따라 비용 지불함

### 2. 활용

- 웹 서비스 정적 파일 저장소
- 정적 웹사이트 호스팅
- AWS 서비스 로그 저장소

### 3. 요금

- 스토리지 요금 : 50TB까지는 GB 당 월 $0.0.23
- 데이터 요청 요금 : PUT, COPY, POST 요청은 1,000건당 $0.0045, GET 요청은 1,000건당 $0.00035
- 데이터 전송 요금 : S3에서 외부 인터넷으로 전송되는 데이터 GB당 $0.009

---

## <mark color="#fbc956">정적 파일 업로드</mark>

### 1. 버킷(Bucket) 생성과 설정

1. AWS → S3 → 버킷 만들기
2. 정보 작성 및 버킷 생성
   - **버킷 이름** : 고유한 이름, 다른 사용자의 버킷과 중복될 수 없음
   - **객체 소유권** : ACL 비활성화됨
   - **이 버킷의 퍼블릭 액세스 차단 설정** : 모두 해제
     - 이 버킷에 저장되는 객체(파일)의 퍼블릭 접근에 대한 설정함
   - **버킷 생성**
3. 버킷 정책 작성
   - 버킷 선택 → 권한 → 버킷 정책 → 편집
     - 버킷 ARN 확인, 노출되면 안되는 정보
     ```json
     {
       "Version": "2012-10-17",
       "Id": "Policy1737974364898",
       "Statement": [
         {
           "Sid": "Stmt1737974362907",
           "Effect": "Allow",
           "Principal": "*",
           "Action": ["s3:DeleteObject", "s3:GetObject", "s3:PutObject"],
           "Resource": "버킷 ARN/*"
         }
       ]
     }
     ```

### 2. 사전 준비

- AWS IAM 사용자 `AmazonS3FullAccess` 정책 연결

  - IAM → 사용자 → 권한 → 권한 추가 → 직접 정책 연결 → `AmazonS3FullAccess` → 다음 → 권한 추가

- AWS S3 접근을 위한 사용자 액세스 키 생성

  - IAM → 사용자 → 보안 자격 증명 → 액세스 키 → 로컬 코드
  - 생성 후, csv 파일 다운로드 받아 보관함

- MySQL 컨테이너 실행

  ```bash
  docker run -p 3310:3306 -e MYSQL_ROOT_PASSWORD=1q2w3e4r! -e MYSQL_DATABASE=demo mysql:8.0
  ```

- 환경 변수 추가 작성

  ```groovy
  # 백엔드 환경 변수
  DATABASE_HOST=localhost
  DATABASE_PORT=3310
  DATABASE_NAME=demo
  DATABASE_USERNAME=root
  DATABASE_PASSWORD=1q2w3e4r!

  # .env
  ACCESS_KEY=
  SECRET_KEY=
  BUCKET_NAME=

  REGION=ap-northeast-2
  ```

- Spring Boot와 의존성 추가

  - `software.amazon.awssdk:s3` : AWS S3 서비스 버킷과 상호 작용위한 의존성

  ```groovy
  implementation 'software.amazon.awssdk:s3:2.20.12'
  ```

- Spring Boot AWS S3 설정 파일 `AwsS3Config.java`

  - S3 서비스에 접근하는 클라이언트 객체 생성
  - `REGION`, `ACCESS_KEY` , `SECRET_KEY` 는 환경 변수로 관리

  ```groovy
  // package com.example.backend.config;

  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
  import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
  import software.amazon.awssdk.regions.Region;
  import software.amazon.awssdk.services.s3.S3Client;

  @Configuration
  public class AwsS3Config {
  	@Value("${REGION}")
  	private String REGION;
  	@Value("${ACCESS_KEY}")
  	private String ACCESS_KEY;
  	@Value("${SECRET_KEY}")
  	private String SECRET_KEY;

  	/**
  	 * AWS S3 클라이언트 빈 생성
  	 * S3 서비스에 접근할 수 있는 클라이언트를 구성
  	 *
  	 * @return AWS S3와 상호작용하기 위한 설정이 완료된 S3Client 객체
  	 */
  	@Bean
  	public S3Client s3Client() {
  		StaticCredentialsProvider credential = StaticCredentialsProvider.create(
  				AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)
  		);

  		return S3Client.builder()
  				.region(Region.of(REGION))
  				.credentialsProvider(credential)
  				.build();
  	}
  }
  ```

### 3. 클라이언트 이미지 업로드 폼 및 요청

- 전체 코드

  ```jsx
  import styles from "./ArticleForm.module.css";
  import { useState, useRef } from "react";
  import articlesApi from "../api/articlesApi";

  const INITIAL_FORM_DATA = {
    title: "",
    content: "",
    file: null,
  };

  export default function ArticleForm({ fetchArticles }) {
    const [inputData, setInputData] = useState(INITIAL_FORM_DATA);
    const fileInputRef = useRef(null); // 파일 input 요소에 대한 참조

    const handleFormChange = (e) => {
      const { name, value } = e.target;
      setInputData((prev) => ({ ...prev, [name]: value }));
    };

    const handleFileChange = (e) => {
      const file = e.target.files[0]; // 선택된 첫 번째 파일 가져오기
      setInputData((prev) => ({ ...prev, file })); // 파일 state 업데이트
    };

    const handleSubmit = async (e) => {
      e.preventDefault();

      const formData = new FormData(); // FormData 객체 생성

      formData.append("title", inputData.title);
      formData.append("content", inputData.content);

      // 이미지 파일이 있는 경우에 폼데이터에 추가
      if (inputData.file) {
        formData.append("file", inputData.file);
      }

      try {
        await articlesApi.postArticle(formData);
        fetchArticles();
        resetForm();
      } catch (error) {
        console.error("ERROR : ", error);
      }
    };

    const resetForm = () => {
      setInputData(INITIAL_FORM_DATA);
      if (fileInputRef.current) fileInputRef.current.value = null;
    };
    return (
      <div className={styles.articleFormContainer}>
        <form onSubmit={handleSubmit} className={styles.form}>
          <input
            type="text"
            id="title"
            name="title"
            value={inputData.title}
            onChange={handleFormChange}
            placeholder="title 입력"
            className={styles.input}
          />
          <textarea
            id="content"
            name="content"
            value={inputData.content}
            onChange={handleFormChange}
            placeholder="content 입력"
            className={styles.textarea}
          />
          <input
            type="file"
            name="file"
            id="file"
            ref={fileInputRef}
            onChange={handleFileChange}
            accept="image/*" // 이미지 파일만 선택 가능하도록 제한
          />
          <button type="submit" className={styles.button}>
            생성
          </button>
        </form>
      </div>
    );
  }
  ```

- API 요청 함수 `api/articlesApi.js`

  - 파일 처리를 위해 헤더 `Content-Type` 설정을 추가

  ```jsx
  postArticle: async (data) => {
    const resposne = await api.post(`articles`, data,{
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    return resposne.data;
  },
  ```

- 폼 컴포넌트 - `components/ArticleForm.jsx`

  - 파일 업로드 폼 Ref, 파일 업로드 요소를 제거
    ```jsx
    import { useRef } from "react";
    ```
    ```jsx
    const fileInputRef = useRef(null); // 파일 input 요소에 대한 참조
    ```
  - 파일 업로드 폼
    ```jsx
    <input
      type="file"
      name="file"
      id="file"
      ref={fileInputRef}
      onChange={handleFileChange}
      accept="image/*" // 이미지 파일만 선택 가능하도록 제한
    />
    ```
  - 파일 업로드 이벤트 처리
    ```jsx
    const handleFileChange = (e) => {
      const file = e.target.files[0]; // 선택된 첫 번째 파일 가져오기
      setInputData((prev) => ({ ...prev, file })); // 파일 state 업데이트
    };
    ```
  - 폼 데이터 제출 이벤트 처리

    ```jsx
    const handleSubmit = async (e) => {
      e.preventDefault();
      const formData = new FormData(); // FormData 객체 생성
      formData.append("title", inputData.title);
      formData.append("content", inputData.content);

      // 선택된 이미지 파일이 있으면 FormData 객체에 추가
      if (inputData.file) {
        formData.append("file", inputData.file);
      }

      try {
        await postsApi.postArticle(formData);
        fetchPosts();
        resetForm();
      } catch (error) {
        console.error("ERROR : ", error);
      }
    };
    ```

  - 파일 업로드 폼 초기화 처리
    ```jsx
    const resetForm = () => {
      setInputData(INITIAL_FORM_DATA);
      // 업로드 폼을 초기화한다.
      if (fileInputRef.current) fileInputRef.current.value = null;
    };
    ```

### 4. 서버 이미지 업로드 처리

- Entity - `domain/Article.java`

  ```java
  package com.example.backend.domain;

  import jakarta.persistence.*;
  import lombok.*;
  import org.springframework.data.annotation.CreatedDate;
  import org.springframework.data.annotation.LastModifiedDate;
  import org.springframework.data.jpa.domain.support.AuditingEntityListener;

  import java.time.LocalDateTime;

  @Table(name = "articles")
  @Entity
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @EntityListeners(AuditingEntityListener.class)
  public class Article {
  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;

  	@Column(nullable = false)
  	private String title;

  	@Column(nullable = false, length = 1000)
  	private String content;

  	/**
  	 * 사용자가 업로드한 원본 이미지 파일명
  	 * 웹 서비스 화면에 표시할 파일명
  	 * 예: profile.jpg
  	 */
  	@Column(nullable = true)
  	private String originalFileName;

  	/**
  	 * S3에 업로드된 이미지의 접근 가능한 URL
  	 * 이미지를 웹에서 표시하기 위한 전체 URL
  	 * 예: https://bucket.s3.region.amazonaws.com/articles/uuid_profile.jpg
  	 */
  	@Column(nullable = true)
  	private String imageUrl;

  	/**
  	 * S3 버킷에서 객체를 식별하는 키(key)
  	 * 버킷 내 객체의 고유 식별자로 전체 경로를 포함
  	 * 예: articles/uuid_profile.jpg
  	 */
  	@Column(nullable = true)
  	private String s3Key;


  	@CreatedDate
  	@Column(nullable = true, updatable = false)
  	private LocalDateTime createdAt;

  	@LastModifiedDate
  	@Column(nullable = true)
  	private LocalDateTime updatedAt;
  }

  ```

- RequestDto - `dto/ArticleRequestDto.java`

  ```java
  import lombok.*;
  import org.springframework.web.multipart.MultipartFile;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public class PostRequestDto {
  	private String title;
  	private String content;
  	private MultipartFile file;
  }
  ```

- ResponseDto - `dto/ArticleResponseDto.java`

  ```java
  import lombok.*;
  import java.time.LocalDateTime;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public class PostResponseDto {
  	private Long id;
  	private String title;
  	private String content;
  	private String imageUrl;
  	private String originalFileName;
  	private LocalDateTime createdAt;
  	private LocalDateTime updatedAt;
  }
  ```

- Service - `service/S3Service.java`

  - 전체 코드

    ```java
    package com.example.backend.service;

    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Service;
    import org.springframework.web.multipart.MultipartFile;
    import software.amazon.awssdk.core.sync.RequestBody;
    import software.amazon.awssdk.services.s3.S3Client;
    import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
    import software.amazon.awssdk.services.s3.model.PutObjectRequest;

    import java.io.IOException;
    import java.util.Map;
    import java.util.UUID;

    @Service
    @RequiredArgsConstructor
    public class S3Service {

        // AWS S3 서비스와 상호작용하기 위한 클라이언트
        private final S3Client s3Client;

        // S3 버킷 이름
        @Value("${BUCKET_NAME}")
        private String bucketName;

        // AWS 리전
        @Value("${REGION}")
        private String region;

        // S3에 저장되는 파일의 기본 경로
        private static final String FILE_PATH_PREFIX = "articles/";

        // S3 URL 형식 (%s: 버킷명, 리전, 파일경로가 순서대로 들어감)
        private static final String IMAGE_URL_FORMAT = "https://%s.s3.%s.amazonaws.com/%s";

        /**
         * 파일을 S3에 업로드하고 URL과 객체 키를 반환한다.
         *
         * @param file 업로드할 MultipartFile 객체
         * @return Map (imageUrl, s3Key를 포함)
         */
        public Map<String, String> uploadFile(MultipartFile file) {
    				// 파일 객체 키 생성
    		    String s3Key = FILE_PATH_PREFIX + UUID.randomUUID() + "_" + file.getOriginalFilename();

    		    // file을 s3Key 경로에 업로드
    		    uploadFileToS3(file, s3Key);

    		    // 접근 가능한 URL 생성
    		    String imageUrl = String.format(IMAGE_URL_FORMAT, bucketName, region, s3Key);

    		    // URL과 키를 Map으로 반환
    		    return Map.of(
    		        "imageUrl", imageUrl,
    		        "s3Key", s3Key
    		    );
    		}

        /**
         * 파일을 S3 버킷에 업로드한다.
         *
         * @param file 업로드할 MultipartFile 객체
         * @param s3Key S3 객체 키
         * @throws RuntimeException 파일 업로드 실패 시 발생
         */
        private void uploadFileToS3(MultipartFile file, String s3Key) {
            try {
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(s3Key)
                        .contentType(file.getContentType())
                        .contentLength(file.getSize())
                        .build();

                s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            } catch (IOException e) {
                throw new RuntimeException("파일 업로드 실패: " + e.getMessage());
            }
        }


        /**
         * S3 객체를 삭제한다.
         *
         * @param s3Key S3 파일 객체 키
         * @throws RuntimeException 파일 삭제 실패 시 발생
         */
        public void deleteFile(String s3Key) {
            try {
                DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(s3Key)
                        .build();
                s3Client.deleteObject(deleteObjectRequest);
            } catch (Exception e) {
                throw new RuntimeException("파일 삭제 실패: " + e.getMessage());
            }
        }

    }
    ```

  - S3 서비스와 상호작용 로직을 담당하는 서비스
  - 필수 변수

    ```java
    // AWS S3 서비스와 상호작용하기 위한 클라이언트
    private final S3Client s3Client;

    // S3 버킷 이름
    @Value("${BUCKET_NAME}")
    private String bucketName;

    // AWS 리전
    @Value("${REGION}")
    private String region;

    // S3에 저장되는 파일의 기본 경로
    private final String FILE_PATH_PREFIX = "articles/";

    // Image URL 형식 (%s: 버킷명, 리전, 파일경로가 순서대로 들어감)
    private final String IMAGE_URL_FORMAT = "https://%s.s3.%s.amazonaws.com/%s";
    ```

  - S3 파일 업로드 처리 - `uploadFile` / `uploadFileToS3`

    ```java
    /**
     * 파일을 S3에 업로드하고 URL과 객체 키를 반환한다.
     *
     * @param file 업로드할 MultipartFile 객체
     * @return Map (imageUrl, s3Key를 포함)
     */
    public Map<String, String> uploadFile(MultipartFile file) {
    		// 파일 객체 키 생성
        String s3Key = FILE_PATH_PREFIX + UUID.randomUUID() + "_" + file.getOriginalFilename();

        // file을 s3Key 경로에 업로드
        uploadFileToS3(file, s3Key);

        // 접근 가능한 URL 생성
        String imageUrl = String.format(IMAGE_URL_FORMAT, bucketName, region, s3Key);

        // URL과 키를 Map으로 반환
        return Map.of(
            "imageUrl", imageUrl,
            "s3Key", s3Key
        );
    }

    /**
     * 파일을 S3 버킷에 업로드한다.
     *
     * @param file 업로드할 MultipartFile 객체
     * @param s3Key S3 객체 키
     * @throws RuntimeException 파일 업로드 실패 시 발생
     */
    private void uploadFileToS3(MultipartFile file, String s3Key) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();

            s3Client.putObject(putObjectRequest,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패: " + e.getMessage());
        }
    }
    ```

  - S3 파일 삭제 처리 - `deleteFile`
    ```java
    /**
     * S3 파일을 삭제한다.
     *
     * @param s3Key S3 파일 객체 키
     * @throws RuntimeException 파일 삭제 실패 시 발생
     */
    public void deleteFile(String s3Key) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException("파일 삭제 실패: " + e.getMessage());
        }
    }
    ```

- Service - `service/ArticleService.java`

  - 전체 코드

    ```java
    package com.example.backend.service;

    import com.example.backend.domain.Article;
    import com.example.backend.dto.ArticleRequestDto;
    import com.example.backend.dto.ArticleResponseDto;
    import com.example.backend.repository.ArticleRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;
    import java.util.Map;
    import java.util.stream.Collectors;

    /**
     * 게시글 관련 비즈니스 로직을 처리하는 서비스 클래스
     *
     * 이 클래스는 다음과 같은 기능을 제공합니다:
     * - 게시글 생성: 새로운 게시글을 생성하고 이미지를 S3에 업로드
     * - 게시글 조회: 전체 게시글 목록 또는 특정 ID의 게시글 조회
     * - 게시글 삭제: 게시글과 연관된 S3 이미지 파일 함께 삭제
     * - DTO 변환: 엔티티와 DTO 간의 변환 처리
     */
    @Service
    @RequiredArgsConstructor
    public class ArticleService {

    	private final ArticleRepository articleRepository;
      private final S3Service s3Service;

      /**
       * 새로운 게시글을 생성한다.
       *
       * @param requestDto 게시글 생성 요청 DTO
       * @return 생성된 게시글의 응답 DTO
       */
    	@Transactional
    	public ArticleResponseDto createArticle(ArticleRequestDto requestDto) {
        // S3에 파일을 업로드하고 접근 가능한 URL과 객체 키를 반환받음
        Map<String, String> uploadResult = s3Service.uploadFile(requestDto.getFile());

        String imageUrl = uploadResult.get("imageUrl");
        String s3Key = uploadResult.get("s3Key");

        // 게시글 엔티티를 생성한다
        Article article = Article.builder()
    				.title(requestDto.getTitle())
    				.content(requestDto.getContent())
    				.imageUrl(imageUrl)
    				.originalFileName(requestDto.getFile().getOriginalFilename())
    				.s3Key(s3Key)
    				.build();

        // 게시글을 데이터베이스에 저장한다
    		Article savedArticle = articleRepository.save(article);

        // 저장된 게시글을 응답 DTO로 변환하여 반환한다
    		return toResponseDto(savedArticle);
    	}

      /**
       * 모든 게시글 목록을 조회한다.
       *
       * @return 게시글 응답 DTO 목록
       */
    	@Transactional
    	public List<ArticleResponseDto> getArticles() {

    		return articleRepository.findAll()
    				.stream()
    				.map(this::toResponseDto)
    				.collect(Collectors.toList());
    	}

      /**
       * ID로 특정 게시글을 조회한다.
       *
       * @param id 게시글 ID
       * @return 게시글 응답 DTO
       * @throws IllegalArgumentException 게시글이 존재하지 않을 경우
       */
      @Transactional
    	public ArticleResponseDto getArticleById(Long id)  {
    		Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + id));

    		return toResponseDto(article);
    	}

      /**
       * ID로 특정 게시글을 삭제한다.
       * S3에 저장된 이미지 파일도 함께 삭제한다.
       *
       * @param id 게시글 ID
       * @throws IllegalArgumentException 게시글이 존재하지 않을 경우
       */
      @Transactional
      public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + id));

        // S3에서 이미지 파일을 삭제한다
        s3Service.deleteFile(article.getS3Key());

        articleRepository.delete(article);
      }

      /**
       * Article 엔티티를 ArticleResponseDto로 변환한다.
       *
       * @param article 변환할 Article 엔티티
       * @return 변환된 ArticleResponseDto
       */
    	private ArticleResponseDto toResponseDto(Article article) {

    		return ArticleResponseDto.builder()
    				.id(article.getId())
    				.title(article.getTitle())
    				.content(article.getContent())
    				.createdAt(article.getCreatedAt())
    				.updatedAt(article.getUpdatedAt())
    				.originalFileName(article.getOriginalFileName())
    				.imageUrl(article.getImageUrl())
    				.build();
    	}
    }
    ```

  - 객체 생성 처리 - `createArticle`

    ```java
    /**
     * 새로운 게시글을 생성한다.
     *
     * @param requestDto 게시글 생성 요청 DTO
     * @return 생성된 게시글의 응답 DTO
     */
    @Transactional
    public ArticleResponseDto createArticle(ArticleRequestDto requestDto) {
      // S3에 파일을 업로드하고 접근 가능한 URL과 객체 키를 반환받음
      Map<String, String> uploadResult = s3Service.uploadFile(requestDto.getFile());

      String imageUrl = uploadResult.get("imageUrl");
      String s3Key = uploadResult.get("s3Key");

      // 게시글 엔티티를 생성한다
      Article article = Article.builder()
    			.title(requestDto.getTitle())
    			.content(requestDto.getContent())
    			.imageUrl(imageUrl)
    			.originalFileName(requestDto.getFile().getOriginalFilename())
    			.s3Key(s3Key)
    			.build();

      // 게시글을 데이터베이스에 저장한다
    	Article savedArticle = articleRepository.save(article);

      // 저장된 게시글을 응답 DTO로 변환하여 반환한다
    	return toResponseDto(savedArticle);
    }
    ```

  - 객체 삭제 처리 - `deleteArticle`

    ```java
    /**
     * ID로 특정 게시글을 삭제한다.
     * S3에 저장된 이미지 파일도 함께 삭제한다.
     *
     * @param id 게시글 ID
     * @throws IllegalArgumentException 게시글이 존재하지 않을 경우
     */
    @Transactional
    public void deleteArticle(Long id) {
      Article article = articleRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + id));
      // S3에서 이미지 파일을 삭제한다
      s3Service.deleteFile(article.getS3Key());

      articleRepository.delete(article);
    }
    ```

  - 응답 DTO 생성 - `toResponseDto`

    ```java
    /**
     * Article 엔티티를 ArticleResponseDto로 변환한다.
     *
     * @param article 변환할 Article 엔티티
     * @return 변환된 ArticleResponseDto
     */
    private ArticleResponseDto toResponseDto(Article article) {

    	return ArticleResponseDto.builder()
    			.id(article.getId())
    			.title(article.getTitle())
    			.content(article.getContent())
    			.createdAt(article.getCreatedAt())
    			.updatedAt(article.getUpdatedAt())
    			.originalFileName(article.getOriginalFileName())
    			.imageUrl(article.getImageUrl())
    			.build();
    }
    ```

- Controller - `controller/ArticleController.java`

  - 전체 코드

    ```java
    package com.example.backend.controller;

    import lombok.RequiredArgsConstructor;
    import com.example.backend.dto.ArticleRequestDto;
    import com.example.backend.dto.ArticleResponseDto;
    import com.example.backend.service.ArticleService;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import java.util.List;

    @RestController
    @RequestMapping("/api/articles")
    @RequiredArgsConstructor
    public class ArticleController {
    	private final ArticleService articleService;

      /**
       * 모든 게시글 목록을 조회한다.
       *
       * @return 게시글 응답 DTO 목록
       */
    	@GetMapping
    	public List<ArticleResponseDto> getArticles() {
    		return articleService.getArticles();
    	}

      /**
       * 새로운 게시글을 생성한다.
       *
       * @param title 게시글 제목
       * @param content 게시글 내용
       * @param file 업로드할 이미지 파일
       * @return 생성된 게시글의 응답 DTO
       */
    	@PostMapping
    	public ArticleResponseDto createArticle(
        // @RequestParam은 HTTP 요청 폼 데이터를 메서드의 파라미터로 입력한다.
        // 폼 데이터의 각 Key와 파라미터를 매핑한다.
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("file") MultipartFile file) {

        ArticleRequestDto requestDto = ArticleRequestDto.builder()
    				.title(title)
    				.content(content)
    				.file(file)
    				.build();

    		return articleService.createArticle(requestDto);
    	}

      /**
       * ID로 특정 게시글을 조회한다.
       *
       * @param id 게시글 ID
       * @return 게시글 응답 DTO
       * @throws IllegalArgumentException 게시글이 존재하지 않을 경우
       */
      @GetMapping("/{id}")
    	public ArticleResponseDto getArticleById(@PathVariable Long id) {
    		return articleService.getArticleById(id);
    	}

      /**
       * ID로 특정 게시글을 삭제한다.
       * S3에 저장된 이미지 파일도 함께 삭제한다.
       *
       * @param id 게시글 ID
       * @throws IllegalArgumentException 게시글이 존재하지 않을 경우
       */
      @DeleteMapping("/{id}")
      public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
      }

    }

    ```

  ```java
  import lombok.RequiredArgsConstructor;
  import com.example.backend.dto.ArticleRequestDto;
  import com.example.backend.dto.ArticleResponseDto;
  import com.example.backend.service.ArticleService;
  import org.springframework.web.bind.annotation.*;
  import java.util.List;

  import org.springframework.web.multipart.MultipartFile;

  @RestController
  @RequestMapping("/api/articles")
  @RequiredArgsConstructor
  public class ArticleController {
  	private final ArticleService articleService;

    /**
     * 새로운 게시글을 생성한다.
     *
     * @param title 게시글 제목
     * @param content 게시글 내용
     * @param file 업로드할 이미지 파일
     * @return 생성된 게시글의 응답 DTO
     */
  	@PostMapping
  	public ArticleResponseDto createArticle(
      // @RequestParam은 HTTP 요청 폼 데이터를 메서드의 파라미터로 입력한다.
      // 폼 데이터의 각 Key와 파라미터를 매핑한다.
      @RequestParam("title") String title,
      @RequestParam("content") String content,
      @RequestParam("file") MultipartFile file) {

      ArticleRequestDto requestDto = ArticleRequestDto.builder()
  				.title(title)
  				.content(content)
  				.file(file)
  				.build();

  		return articleService.createArticle(requestDto);
  	}

  	// ... 생략
  }
  ```

### 5. 클라이언트 이미지 출력 컴포넌트

- 게시글 출력 컴포넌트 - `components/Article.jsx`
  - 응답 데이터의 `imageUrl` 을 활용해 화면에 이미지 출력
  ```jsx
  <img src={article.imageUrl} />
  ```

### 6. Vite Proxy Server 설정

- `vite.config.js`

  ```jsx
  import { defineConfig } from "vite";
  import react from "@vitejs/plugin-react";
  import compression from "vite-plugin-compression";

  // https://vite.dev/config/
  export default defineConfig({
    plugins: [
      react(),
      compression({
        algorithm: "gzip", // Gzip 알고리즘 사용
        ext: ".gz", // 생성되는 파일의 확장자
        threshold: 10240, // 압축 최소 크기 (10KB 이상만 압축)
      }),
    ],
    server: {
      proxy: {
        "/api": {
          target: "http://localhost:8080/api/",
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, ""),
          secure: false,
          ws: true,
        },
      },
    },
  });
  ```

---

### ☀️ 오늘의 배움

- Paas
  - 구드
  - 원드
- Saas

  - S3

- **S3**

  - 사용자는 저장한 데이터의 양과 데이터 요청 수, 데이터 전송 비용에 따라 비용을 지불
  - 정적 파일 저장 및 응답 가능
  - 서비스 로그 저장소
  - 저장된 파일을 객체라 함

  - **ACL**
    - 다른 계정에서 해당 버킷의 소유권 여부 설정

- **버킷**

  - **객체**

    - **키** : 객체 삭제 시 사용
      - 고유해야 함 → UUID 활용
    - **객체 URL** : 해당 객체의 주소

  - 버킷 정책
    ```jsx
    {
        "Version": "2012-10-17",
        "Id": "Policy1737974364898",
        "Statement": [
            {
                "Sid": "Stmt1737974362907",
                // Action을 허용
                "Effect": "Allow",
                "Principal": "*",
                "Action": [
    		            // 객체 삭제
                    "s3:DeleteObject",
                    // 객체 접근
                    "s3:GetObject",
                    // 객체 생성
                    "s3:PutObject"
                ],
                // 정책을 부여할 객체 경로
                "Resource": "버킷 ARN/*"
            }
        ]
    }
    ```

> **실습**

![image.png](/Sesac/assets/day73.png)

- **IAM**
  - 액세스 키 : username 역할
  - 비밀 액세스 키 : password 역할
- MySQL 실행
- 환경 변수 생성 및 작성
- Spring Boot
  - 의존성 추가
  - AWS S3 설정 파일
