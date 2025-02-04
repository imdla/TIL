## <mark color="#fbc956">M:N 실습</mark>

> **postList에서 comment, postTag 구할 경우**

1. **분리**
   - POST, COMMENT 따로 가져옴
2. **LAZY** (POST, COMMENT, POSTTAG, TAG)
   - POST LIST 조회일 경우 (POST LIST 순회, map)
     - 하나의 POST대해
       - COMMENT, PT - T 가져옴
3. **batch size**
   - 테이블 마다 긁어옴 (덩어리마다 가져옴)
   - 연관관계 만큼은 가져옴
   - POST in (덩어리), COMMENT in (덩어리), POSTTAG in (덩어리), TAG in (덩어리)
     - 덩어리 = batch size

> **postList에서 comment count 구할 경우**

- **LAZY**
  - 데이터에 접근할 때 마다 가져옴
  - JOIN해도 사용하지 않으면 쿼리는 1개
- **EAGER**
  - 처음에 한 번에 다 가져옴 (최적화를 하지 않음)
- **fetch join**
  - 처음에 한 번에 다 가져옴 (최적화)
  - postList에서 join할 때 상대 엔티티(comment)를 사용한다면 fetch join 사용하는 것이 효율적
    (comment를 가져옴) - 만약, 정보만 필요하다면 lazy를 사용하는 것이 효율적
    (comment를 가져오지 않고도 comment 정보 사용 가능) - 정보만 필요할 때, fetch join 사용하면 사용하지 않는 comment도 가져옴 - ex. postList에서 comment count 구하기

---

### READ - 전체

> **PostList 조회 (comments + postTags + tag)**

![relation.jpg](/Sesac/assets/day57.jpg)

- **현재 Post, Comment, Tag의 JSON**
  ```json
  {
    "message": "Success",
    "code": "SUCCESS",
    "data": [
      {
        "id": 1,
        "title": "new title",
        "content": "new content",
        "author": "author",
        "createdAt": "2025-01-09T17:35:19.564866",
        "updatedAt": "2025-01-09T17:35:19.564866",
        "comments": [
          {
            "id": 4,
            "content": "comment content",
            "createAt": "2025-01-09T17:35:52.78984"
          },
          {
            "id": 5,
            "content": "comment content",
            "createAt": "2025-01-09T17:35:53.769875"
          },
          {
            "id": 6,
            "content": "comment content",
            "createAt": "2025-01-09T17:35:54.703596"
          }
        ],
        "tags": ["hello", "world"]
      },
      {
        "id": 2,
        "title": "new title",
        "content": "new content",
        "author": "author",
        "createdAt": "2025-01-09T17:35:38.366266",
        "updatedAt": "2025-01-09T17:35:38.366266",
        "comments": [
          {
            "id": 2,
            "content": "comment content",
            "createAt": "2025-01-09T17:35:48.454401"
          },
          {
            "id": 3,
            "content": "comment content",
            "createAt": "2025-01-09T17:35:49.307609"
          }
        ],
        "tags": ["java"]
      },
      {
        "id": 3,
        "title": "new title",
        "content": "new content",
        "author": "author",
        "createdAt": "2025-01-09T17:35:39.693247",
        "updatedAt": "2025-01-09T17:35:39.693247",
        "comments": [
          {
            "id": 1,
            "content": "comment content",
            "createAt": "2025-01-09T17:35:44.647629"
          }
        ],
        "tags": ["spring"]
      }
    ]
  }
  ```

1. **LAZY**

   - 코드 및 쿼리 → **쿼리 7개 발생**
     - Posts + Comments + PostTags + Tag 4개
     ```java
     @Query("SELECT p FROM Post p " +
     				"LEFT JOIN p.comments c " +
     				"LEFT JOIN p.postTags pt " +
     				"LEFT JOIN pt.tag")
     List<Post> findWithCommentAndTag();
     ```
     ```java
     Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id left join post_tag pt1_0 on p1_0.id=pt1_0.post_id
     Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
     Hibernate: select pt1_0.post_id,pt1_0.id,pt1_0.create_at,pt1_0.tag_id from post_tag pt1_0 where pt1_0.post_id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
     Hibernate: select t1_0.id,t1_0.name from tag t1_0 where t1_0.id=?
     Hibernate: select t1_0.id,t1_0.name from tag t1_0 where t1_0.id=?
     Hibernate: select t1_0.id,t1_0.name from tag t1_0 where t1_0.id=?
     Hibernate: select t1_0.id,t1_0.name from tag t1_0 where t1_0.id=?
     ```

1. **fetch join**

   - 코드 및 쿼리 → **쿼리 2개 발생**
     - (Posts + PostTags + Tags) + Comments
     ```java
     @Query("SELECT p FROM Post p " +
     				"LEFT JOIN p.comments c " +
     				"LEFT JOIN FETCH p.postTags pt " +
     				"LEFT JOIN FETCH pt.tag")
     List<Post> findWithCommentAndTag();
     ```
     ```java
     Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,pt1_0.post_id,pt1_0.id,pt1_0.create_at,t1_0.id,t1_0.name,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id left join post_tag pt1_0 on p1_0.id=pt1_0.post_id left join tag t1_0 on t1_0.id=pt1_0.tag_id
     Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
     ```
   - ⚠️ 위의 값은 중복 결과가 나올 수 있다.
     - 결과 JSON
       ```json
       {
         "message": "Success",
         "code": "SUCCESS",
         "data": [
           {
             "id": 1,
             "title": "new title",
             "content": "new content",
             "author": "author",
             "createdAt": "2025-01-09T17:35:19.564866",
             "updatedAt": "2025-01-09T17:35:19.564866",
             "comments": [
               {
                 "id": 4,
                 "content": "comment content",
                 "createAt": "2025-01-09T17:35:52.78984"
               },
               {
                 "id": 5,
                 "content": "comment content",
                 "createAt": "2025-01-09T17:35:53.769875"
               },
               {
                 "id": 6,
                 "content": "comment content",
                 "createAt": "2025-01-09T17:35:54.703596"
               }
             ],
             "tags": ["world", "hello", "world", "hello", "world", "hello"]
           },
           {
             "id": 2,
             "title": "new title",
             "content": "new content",
             "author": "author",
             "createdAt": "2025-01-09T17:35:38.366266",
             "updatedAt": "2025-01-09T17:35:38.366266",
             "comments": [
               {
                 "id": 2,
                 "content": "comment content",
                 "createAt": "2025-01-09T17:35:48.454401"
               },
               {
                 "id": 3,
                 "content": "comment content",
                 "createAt": "2025-01-09T17:35:49.307609"
               }
             ],
             "tags": ["java", "java"]
           },
           {
             "id": 3,
             "title": "new title",
             "content": "new content",
             "author": "author",
             "createdAt": "2025-01-09T17:35:39.693247",
             "updatedAt": "2025-01-09T17:35:39.693247",
             "comments": [
               {
                 "id": 1,
                 "content": "comment content",
                 "createAt": "2025-01-09T17:35:44.647629"
               }
             ],
             "tags": ["spring"]
           }
         ]
       }
       ```
     - **해결** : `DISTINCT` 추가
       ```java
       @Query("SELECT DISTINCT p FROM Post p " +
       				"LEFT JOIN p.comments c " +
       				"LEFT JOIN p.postTags pt " +
       				"LEFT JOIN pt.tag")
       List<Post> findWithCommentAndTag();
       ```

2. **batch size**

   - 코드 및 쿼리 → **쿼리 4개 발생 (**`comments` BatchSize = 100**)**
     - Posts + Comments + PostTags + Tags
     ```java
     @Query("SELECT p FROM Post p " +
     				"LEFT JOIN p.comments c " +
     				"LEFT JOIN p.postTags pt " +
     				"LEFT JOIN pt.tag")
     List<Post> findWithCommentAndTag();
     ```
     ```java
     @BatchSize(size = 100)
     @OneToMany(mappedBy = "post",  fetch = FetchType.LAZY)
     private List<Comment> comments;

     @BatchSize(size = 100)
     @OneToMany(mappedBy = "post",  fetch = FetchType.LAZY)
     private List<PostTag> postTags;
     ```
     ```java
     spring.jpa.properties.hibernate.default_batch_fetch_size=100
     ```
     ```java
     Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id left join post_tag pt1_0 on p1_0.id=pt1_0.post_id
     Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
     Hibernate: select pt1_0.post_id,pt1_0.id,pt1_0.create_at,pt1_0.tag_id from post_tag pt1_0 where pt1_0.post_id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
     Hibernate: select t1_0.id,t1_0.name from tag t1_0 where t1_0.id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
     ```
   - 코드 및 쿼리 → **쿼리 5개 발생 (**`comments` BatchSize = 2**)**
     - Posts + Comments + Comments + PostTags + Tags
     ```java
     @Query("SELECT p FROM Post p " +
     				"LEFT JOIN p.comments c " +
     				"LEFT JOIN p.postTags pt " +
     				"LEFT JOIN pt.tag")
     List<Post> findWithCommentAndTag();
     ```
     ```java
     Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id left join post_tag pt1_0 on p1_0.id=pt1_0.post_id
     Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id in (?,?)
     Hibernate: select pt1_0.post_id,pt1_0.id,pt1_0.create_at,pt1_0.tag_id from post_tag pt1_0 where pt1_0.post_id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
     Hibernate: select t1_0.id,t1_0.name from tag t1_0 where t1_0.id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
     Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
     ```

3. **batch size + fetch join**
   - 코드 및 쿼리 → **쿼리 2개 발생 (**`comments` BatchSize = 100**)**
     - Comments + (Posts + PostTags + Tags)
     ```java
     @Query("SELECT p FROM Post p " +
     				"LEFT JOIN p.comments c " +
     				"LEFT JOIN FETCH p.postTags pt " +
     				"LEFT JOIN FETCH pt.tag")
     List<Post> findWithCommentAndTag();
     ```
     ```java
     Hibernate: select distinct p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,pt1_0.post_id,pt1_0.id,pt1_0.create_at,t1_0.id,t1_0.name,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id left join post_tag pt1_0 on p1_0.id=pt1_0.post_id left join tag t1_0 on t1_0.id=pt1_0.tag_id
     Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
     ```
   - 코드 및 쿼리 → **쿼리 3개 발생 (**`comments` BatchSize = 2**)**
     - (Posts + PostTags + Tags) + Comments + Comments
     ```java
     @Query("SELECT p FROM Post p " +
     				"LEFT JOIN p.comments c " +
     				"LEFT JOIN FETCH p.postTags pt " +
     				"LEFT JOIN FETCH pt.tag")
     List<Post> findWithCommentAndTag();
     ```
     ```java
     Hibernate: select distinct p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,pt1_0.post_id,pt1_0.id,pt1_0.create_at,t1_0.id,t1_0.name,p1_0.title,p1_0.updated_at from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id left join post_tag pt1_0 on p1_0.id=pt1_0.post_id left join tag t1_0 on t1_0.id=pt1_0.tag_id
     Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id in (?,?)
     Hibernate: select c1_0.post_id,c1_0.id,c1_0.content,c1_0.created_at,c1_0.updated_at from comment c1_0 where c1_0.post_id=?
     ```

---

### READ - Tag별 게시글 조회

- 사용 코드

  - `PostController`
    ```java
    @GetMapping("/tags")
    public ResponseEntity<ApiResponse<List<PostWithTagResponseDto>>> readPostsByTag(@RequestParam String tag) {
    	return ResponseEntity.ok(ApiResponse.ok(
    		postService.readPostsByTag(tag)
    ));
    }
    ```
  - `PostService`
    ```java
    public List<PostWithTagResponseDto> readPostsByTag(String tag) {
    	return postRepository.findAllByTagName(tag).stream()
    					.map(PostWithTagResponseDto::from)
    					.toList();
    }
    ```
  - `PostRepository`
    ```java
    @Query("SELECT p FROM Post p " +
            "JOIN p.postTags pt " +
            "JOIN pt.tag t " +
            "WHERE t.name = :tagName")
    List<Post> findAllByTagName(@Param("tagName") String tagName);
    ```

- **LAZY** → 쿼리 3개 발생
  ```java
  @Query("SELECT p FROM Post p " +
  				"JOIN p.postTags pt " +
  				"JOIN pt.tag t " +
  				"WHERE t.name = :tagName")
  List<Post> findAllByTagName(@Param("tagName") String tagName);
  ```
  ```java
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 join post_tag pt1_0 on p1_0.id=pt1_0.post_id join tag t1_0 on t1_0.id=pt1_0.tag_id where t1_0.name=?
  Hibernate: select pt1_0.post_id,pt1_0.id,pt1_0.create_at,pt1_0.tag_id from post_tag pt1_0 where pt1_0.post_id=?
  Hibernate: select t1_0.id,t1_0.name from tag t1_0 where t1_0.id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
  ```
  ```json
  {
    "message": "Success",
    "code": "SUCCESS",
    "data": [
      {
        "id": 1,
        "title": "new title",
        "createdAt": "2025-01-09T17:35:19.564866",
        "updatedAt": "2025-01-09T17:35:19.564866",
        "tags": ["hello", "world"]
      }
    ]
  }
  ```
- **FETCH JOIN** → 쿼리 1개 발생
  ```java
  @Query("SELECT p FROM Post p " +
  				"JOIN FETCH p.postTags pt " +
  				"JOIN FETCH pt.tag t " +
  				"WHERE t.name = :tagName")
  List<Post> findAllByTagName(@Param("tagName") String tagName);
  ```
  ```java
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,pt1_0.post_id,pt1_0.id,pt1_0.create_at,t1_0.id,t1_0.name,p1_0.title,p1_0.updated_at from post p1_0 join post_tag pt1_0 on p1_0.id=pt1_0.post_id join tag t1_0 on t1_0.id=pt1_0.tag_id where t1_0.name=?
  ```
  ```json
  {
    "message": "Success",
    "code": "SUCCESS",
    "data": [
      {
        "id": 1,
        "title": "new title",
        "createdAt": "2025-01-09T17:35:19.564866",
        "updatedAt": "2025-01-09T17:35:19.564866",
        "tags": ["hello"]
      }
    ]
  }
  ```
- **batch size** → 쿼리 3개 발생
  ```java
  @Query("SELECT p FROM Post p " +
  				"JOIN p.postTags pt " +
  				"JOIN pt.tag t " +
  				"WHERE t.name = :tagName")
  List<Post> findAllByTagName(@Param("tagName") String tagName);
  ```
  ```java
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,p1_0.title,p1_0.updated_at from post p1_0 join post_tag pt1_0 on p1_0.id=pt1_0.post_id join tag t1_0 on t1_0.id=pt1_0.tag_id where t1_0.name=?
  Hibernate: select pt1_0.post_id,pt1_0.id,pt1_0.create_at,pt1_0.tag_id from post_tag pt1_0 where pt1_0.post_id=?
  Hibernate: select t1_0.id,t1_0.name from tag t1_0 where t1_0.id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
  ```
  ```json
  {
    "message": "Success",
    "code": "SUCCESS",
    "data": [
      {
        "id": 1,
        "title": "new title",
        "createdAt": "2025-01-09T17:35:19.564866",
        "updatedAt": "2025-01-09T17:35:19.564866",
        "tags": ["hello", "world"]
      }
    ]
  }
  ```
- **batch size & FETCH JOIN** → 쿼리 1개 발생

  ```java
  @Query("SELECT p FROM Post p " +
  				"JOIN FETCH p.postTags pt " +
  				"JOIN FETCH pt.tag t " +
  				"WHERE t.name = :tagName")
  List<Post> findAllByTagName(@Param("tagName") String tagName);
  ```

  ```java
  Hibernate: select p1_0.id,p1_0.author,p1_0.content,p1_0.created_at,pt1_0.post_id,pt1_0.id,pt1_0.create_at,t1_0.id,t1_0.name,p1_0.title,p1_0.updated_at from post p1_0 join post_tag pt1_0 on p1_0.id=pt1_0.post_id join tag t1_0 on t1_0.id=pt1_0.tag_id where t1_0.name=?
  ```

  ```json
  {
    "message": "Success",
    "code": "SUCCESS",
    "data": [
      {
        "id": 1,
        "title": "new title",
        "createdAt": "2025-01-09T17:35:19.564866",
        "updatedAt": "2025-01-09T17:35:19.564866",
        "tags": ["hello"]
      }
    ]
  }
  ```

- ⚠️ 현재 fetch join 사용할 경우, 게시글에 있는 tag는 해당하는 tag만 표시됨
  - 해당하는 tag의 게시글에서, 게시글 내부의 tag를 다 보일 수 있도록 batch size 사용

  - fetch join 미사용 시 데이터
  | post | postTag | tag |
  | ---- | ------- | --- |
  | 1    | 1       | 1   |
  |      | 2       | 2   |
  |      | 3       | 3   |
  | 2    | 4       | 1   |
  |      | 5       | 2   |
  |      | 6       | 3   |
  - fetch join 사용 시 데이터
  | post | postTag | tag |
  | ---- | ------- | --- |
  | 1    | 1       | 1   |
  |      | 2       | 2   |
  |      | 3       | 3   |
  | 2    | 4       | 1   |
  |      | 5       | 2   |
  |      | 6       | 3   |

---

### TagPost Create 개선

> **존재하지 않는 태그에 대한 연결 요청**

- **Post 생성할 때, tag 생성하거나 연결**
- 태그의 종류는 정해진 것이 아니라, 게시글 작성할 때 어떤 String이든 태그로 등록 가능
- 존재하지 않는 태그는 예외를 던지는 것이 아니라, 새로운 태그를 생성

- 1️⃣ **post와 tag가 각각 존재할 때, 연결 시키기**
  ```java
  @Transactional
      public void addTagToPost(Long id, TagRequestDto requestDto) {
          Post post = postRepository.findById(id)
                          .orElseThrow(ResourceNotFoundException::new);
          Tag tag = tagRepository.findByName(requestDto.getName())
                          .orElseThrow(ResourceNotFoundException::new);

          PostTag postTag = new PostTag();

          postTag.addTag(tag);

          postTag.addPost(post);
          post.getPostTags().add(postTag);

          postTagRepository.save(postTag);
      }
  ```
- 2️⃣ **post는 존재하고, tag는 확인** (존재할 때 그것을 사용, 존재하지 않으면 만들기)
  ```java
  @Transactional
      public void addTagToPost(Long id, TagRequestDto requestDto) {
          Post post = postRepository.findById(id)
                          .orElseThrow(ResourceNotFoundException::new);
          Tag tag = tagRepository.findByName(requestDto.getName())
                  .orElseGet(() -> {
                      Tag newTag = new Tag(requestDto.getName());
                      return tagRepository.save(newTag);
                  });

          PostTag postTag = new PostTag();

          postTag.addTag(tag);

          postTag.addPost(post);
          post.getPostTags().add(postTag);

          postTagRepository.save(postTag);
      }
  ```
  ```java
  // tag 가져오자
  // 만약에 없으면 만들자

  // if (tag 확인) {
  //      있으면 가져오기
  // } else {
  //      없으면 만들기
  // }
  // -> 옵셔널
  // => if 사용하지 않아도 옵셔널로 사용하면 됨 !

  Tag tag = tagRepository.findByName(requestDto.getName()) // tag 있으면 가져오기
            .orElseGet(() -> {                             // tag 없으면 생성하기

  							Tag newTag = new Tag(requestDto.getName());
  							return tagRepository.save(newTag);

  //            tagRepository의 메서드로 가져오기 (태그 생성 및 저장)
  //            tagRepository.save(requestDto.toEntity());
            });
  ```
- ✅ **tag 생성 시 validation 체크**
  ```java
  // version 1. post와 tag를 가지고 연결시켜주기 (PostTag)
  @PostMapping("/{id}/tags")
  public void addTagToPost(
  	@PathVariable Long id,
  	@Valid @RequestBody TagRequestDto requestDto
   ) {
  	postService.addTagToPost(id, requestDto);
  }
  ```

> **중간 테이블의 중복 레코드**

- 하나의 게시글에 같은 태그가 2개 생성될 수 없음 → validation 체크
- **중간 테이블의 레코드들은 unique해야 함**
- **(post, tag) 쌍에 대한 unique 설정**
  1. DB에서 확인
     - `@UniqueConstraint(columnNames={"username"})`
  2. Service 에서 확인 필요
     - Controller에서는 요청에 대한 유효성 검사를 하고, Service에서는 DB와 연결해 검사
       → 쌍에 대해 데이터베이스에 있는지 확인해야하기 때문에 Service에서 확인 필요
     - 사용 코드
       - `PostService`
       ```java
           @Transactional
           public void addTagToPost(Long id, TagRequestDto requestDto) {
               Post post = postRepository.findById(id)
                               .orElseThrow(ResourceNotFoundException::new);
               Tag tag = tagRepository.findByName(requestDto.getName())
                       .orElseGet(() -> {
                           Tag newTag = new Tag(requestDto.getName());
                           return tagRepository.save(newTag);
                       });

               if (postTagRepository.existsByPostAndTag(post, tag)) {
                   throw new DuplicationEntityException();
               }

               PostTag postTag = new PostTag();

               postTag.addTag(tag);

               postTag.addPost(post);
               post.getPostTags().add(postTag);

               postTagRepository.save(postTag);
           }
       ```
       - `PostTagRepository`
       ```java
       boolean existsByPostAndTag(Post post, Tag tag);
       ```
       - `DuplicateEntityException`
       ```java
       public class DuplicateEntityException extends RuntimeException{
           public DuplicateEntityException(String message) {
               super(message);
           }

           public DuplicateEntityException() {
               super("이미 존재하는 데이터입니다.");
           }
       }
       ```
       - `GlobalExceptionHandler`
       ```java
       @ExceptionHandler(DuplicationEntityException.class)
       public ResponseEntity<ApiResponse<Void>> handleUserNotFound(DuplicationEntityException ex) {
       	return ResponseEntity
       					.status(HttpStatus.BAD_REQUEST)
       					.body(ApiResponse.error(ex.getMessage(), "DUPLICATE_ENTITY"));
       }
       ```

> **Post 생성 시 Tag 함께 추가**

- 사용 코드
  - `PostController`
    ```java
    @PostMapping("/tags")
    public ResponseEntity<ApiResponse<PostWithCommentAndTagResponseDto>> createPostWithTags(@Valid @RequestBody PostCreateWithTagsRequestDto requestDto) {
    	return ResponseEntity
    					.status(HttpStatus.CREATED)
    					.body(
    						ApiResponse.ok(
    							"게시글이 성공적으로 작성되었습니다.",
    							"CREATED",
    							postService.createPostWithTags(requestDto)
    						)
    					);
    }
    ```
  - `PostCreatedWithTagsRequestDto`
    - 입력을 List로 받을 경우 validation에 대한 어노테이션 추가 가능
    ```java
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class PostCreateWithTagsRequestDto {
        @NotBlank
        @Length(max = 20)
        private String title;

        @NotBlank
        @Length(min = 5)
        private String content;

        @Length(min = 2, max = 10)
        private String author;

        private List<@NotBlank String> tags;

        public Post toEntity() {
            return Post.builder()
                    .title(this.title)
                    .content(this.content)
                    .author(this.author)
                    .build();
        }
    }
    ```
  - `PostService`
    - 기존 방식과 동일하게 `post` 객체 생성
    - 입렫받은 String 바탕으로 Tag 객체 가져오거나 생성, 저장 후 Post에 넣어줌
    ```java
    @Transactional
    public PostWithTagResponseDto createPostWithTags(PostCreateWithTagsRequestDto requestDto) {
    	Post post = postRepository.save(requestDto.toEntity());
    	List<String> tagNames = requestDto.getTags();

    	for (String tagName : tagNames) {
    		Tag tag = tagRepository.findByName(tagName).orElseGet(() -> {
    			Tag newTag = new Tag(tagName);
    			return tagRepository.save(newTag);
    		});

    		PostTag postTag = new PostTag(post, tag);
    		postTagRepository.save(postTag);
    		post.getPostTags().add(postTag);
    	}

    	return PPostWithTagResponseDto.from(post, new ArrayList<>());
    }
    ```
  - Post에 있는 postTags의 영속성 전이 활용
    - postTag에 대한 명시적 저장 없앨 수 있음
  - `Post`
    ```java
    @BatchSize(size = 100)
        @OneToMany(mappedBy = "post",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PostTag> postTags = new ArrayList<>();
    ```
  - `PostService`
    ```java
    @Transactional
    public PostWithTagResponseDto createPostWithTags(PostCreateWithTagsRequestDto requestDto) {
    	Post post = postRepository.save(requestDto.toEntity());
    	List<String> tagNames = requestDto.getTags();

    	for (String tagName : tagNames) {
    		Tag tag = tagRepository.findByName(tagName).orElseGet(() -> {
    			Tag newTag = new Tag(tagName);
    			return tagRepository.save(newTag);
    		});

    		PostTag postTag = new PostTag(post, tag);
    		// postTagRepository.save(postTag);
    		post.getPostTags().add(postTag);
    	}

    	return PPostWithTagResponseDto.from(post, new ArrayList<>());
    }
    ```
    - `post.getPostTags().add(postTag)` 를 더티체킹해 postTag 저장된 것
