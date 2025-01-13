## <mark color="#fbc956">이미지 업로드</mark>

### 1. 이미지 업로드

- HTTP 요청에서 `Content-Type`
  - 기존 사용 : `application/json`
  - 이미지 업로드에서 사용 : `multipart/form-data`

### 2. `@RequestPart`

- `multipart/form-data` 형식으로 전송된 요청 : 파일과 JSON 데이터를 동시에 처리
  - HTTP 요청에서 해당 데이터를 메서드의 파라미터로 전달
- `@RequestParam` : JSON과 함께가 아닌 file 단독으로 처리할 때 사용 가능

### 3. `MultipartFile`

- HTTP 요청에 포함된 파일 데이터를 다루기 위한 인터페이스
- **메서드**
  - `getName()` : HTML Form의 name 속성 반환
  - `getOriginalFilename()` : 업로드한 파일의 원본 파일 이름을 반환
  - `isEmpty()` : 파일이 비어 있는지 확인
  - `transferTo(File dest)` : 파일을 지정된 `File` 객체로 저장
    - `IOException` 을 `throws` 함

### 4. UUID

- Universally Unique Identifier
- 전 세계적으로 유일한 식별자를 생성하는 데 사용
- **특징**
  1. 전 세계적으로 유일
     - UUID는 매우 높은 확률로 다른 UUID와 중복되지 않음
     - 즉, 여러 시스템에서 동시에 생성된 UUID도 서로 겹치지 않도록 보장함
  2. 고유한 식별자
     - UUID는 일반적으로 객체나 레코드를 고유하게 식별하는 데 사용
     - ex. 데이터베이스에서 행을 고유하게 식별할 때
  3. 랜덤성
     - UUID는 기본적으로 랜덤하게 생성, 이를 통해 고유성을 유지

### 5. 실습

> 게시글에 이미지를 함께 업로드

- 프로젝트 루트의 `uploads` 폴더에 이미지 저장

  - `application.properties`
    - 사용할 때 직접 변수로 넣어도 괜찮지만, 설정 관련 변수이므로 설정 파일에 넣음
    ```java
    file.dir=uploads/
    ```
    - 다음 설정으로 파일 크기 제한 가능
    ```java
    spring.servlet.multipart.max-file-size=5MB
    spring.servlet.multipart.max-request-size=5MB
    ```

- `Post`

  - DB에는 이미지에 대한 url만 저장되면 됨
  - 실습 편의 위해 Setter 추가

  ```java
  @Setter
  private String imageUrl;
  ```

- `PostController`

  ```java
  @PostMapping("/images")
  public ResponseEntity<ApiResponse<PostWithImageResponseDto>> createPostWithImage(
  				@RequestPart(value = "data") PostCreateRequestDto requestDto,
          @RequestPart(value = "image", required = false) MultipartFile image
  ) {
      return ResponseEntity.ok(ApiResponse.ok(
             postService.createPostWithImage(requestDto, image)
      ));
  }
  ```

- `PostService`

  - imageUrl을 기본값 `null` 로 설정한 후, 저장에 성공할 경우 저장된 이미지의 주소를 할당

  ```java
  @Transactional
  public PostWithImageResponseDto createPostWithImage(
         PostCreateRequestDto requestDto,
         MultipartFile image
  ) {
      String imageUrl = null;

      if (image != null && !image.isEmpty()) {
          imageUrl = fileService.saveFile(image);
      }

      Post post = requestDto.toEntity();
      post.setImageUrl(imageUrl);

      return PostWithImageResponseDto.from(postRepository.save(post));
  }
  ```

- file을 저장하기 위한 로직 분리

  - `global/common/service/FileService` 생성
  - Post에만 사용할 경우 `PostService` 와 같은 위치에 두어도 무방

- `FileService`

  ```java
  @Service
  @RequiredArgsConstructor
  public class FileService {
      @Value("${file.dir}")
      private String fileDir;

      public String saveFile(MultipartFile image) {
          try {
              // 현재 프로젝트 디렉토리
              String projectDir = Paths.get("").toAbsolutePath().toString();

              // 디렉토리 관련 객체 생성
              File directory = new File(projectDir, fileDir);

              // 디렉토리가 없으면 생성
              if (!directory.exists()) {
                  directory.mkdirs();
              }

              // 원본 파일명
              String originalName = image.getOriginalFilename();

              // 저장할 파일명 (UUID + 원본파일명)
              String savedFileName = UUID.randomUUID() + "_" + originalName;

              // 파일 저장
              image.transferTo(new File(directory, savedFileName));

              return savedFileName;

          } catch (IOException e) {
              throw new FileUploadException();
          }
      }
  }
  ```

- `@Value`

  - Spring의 환결 설정 값을 직접 필드에 주입할 때 사용
  - `application.properties` 에서 설정한 `file.dir=uploads/` 의 값을 가져오는데 사용
  - `import org.springframework.beans.factory.annotation.Value;`

- `Paths.get().toAbsolutePath().toString();`

  - 현재 작업 디렉토리의 절대 경로 가져옴
  - 추후 다른 이미지 저장 경로 활용 시 사용하지 않음

- `PostWithImageResponseDto`

  - `imageUrl` 필드 추가

  ```java
  @Getter
  @Builder
  public class PostWithImageResponseDto {
      private final Long id;
      private final String title;
      private final String content;
      private final String author;
      private final LocalDateTime createdAt;
      private final LocalDateTime updatedAt;
      private final String imageUrl;

      public static PostWithImageResponseDto from(Post entity) {
          return PostWithImageResponseDto.builder()
                  .id(entity.getId())
                  .title(entity.getTitle())
                  .content(entity.getContent())
                  .author(entity.getAuthor())
                  .createdAt(entity.getCreatedAt())
                  .updatedAt(entity.getUpdatedAt())
                  .imageUrl(entity.getImageUrl())
                  .build();
      }
  }
  ```

- `FileUploadException` 생성 및 `GlobalExceptionHandler` 에서 처리

- Postman에 Body / form-data에 JSON과 image를 함께 업로드
  - data에 JSON 전달, Content-Type을 application/json으로 설정
  - image는 File을 선택해 업로드

### 6. 이미지 접근

- `/global/config/WebConfig` 생성
- `WebConfig`
  ```java
  @Configuration
  public class WebConfig implements WebMvcConfigurer {
      @Value("${file.dir}")
      private String fileDir;

      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
          String uploadPath = Paths.get(fileDir).toAbsolutePath().toString();

          registry.addResourceHandler("/images/**")
                  .addResourceLocations("file:" + uploadPath + "/");
      }
  }

  ```
  - `@Configuration` : 스프링의 설정 클래스임을 명시
  - `WebMvcConfigurer` : 스프링 MVC 설정을 커스터마이징할 수 있는 인터페이스
  - `addResourceHandlers` : 정적 리소스의 경로를 설정하는 메소드
  - `registry.addResourceHandler("/images/**")`
    - `/images/파일이름` 으로 요청 오면
    - `.addResourceLocations("file:" + uploadPath + "/");` 에 있는 파일을 응답함
    - `file:` 로컬 파일 시스템의 경로임을 명시
  - [`http://localhost:8080/images/파일이름`](http://localhost:8080/images/파일이름) 에 요청 보내면 해당 파일을 응답함

### 7. 여러 개의 이미지 저장

- 게시글 - 이미지를 1:N 연관관계를 만들어 저장
- `Tag` 를 사용한 방식과 유사하게 작성

### 8. 추가 고려 사항

1. 파일 확장자 검증
   - jpg, jpeg, png, gif 등 허용된 이미지 확장자만 업로드 가능하도록 설정
2. 이미지 리사이징 및 압축
   - 너무 큰 파일의 경우 압축 및 리사이징
     - webP
3. 썸네일 생성
4. 비동기 처리 고려
5. 배포를 위한 이미지 저장 설정 분리
