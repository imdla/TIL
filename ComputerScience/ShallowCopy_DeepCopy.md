> 💡 **한 줄 요약**
>
> 얕은 복사는 같은 객체를 참조해 변동이 있을 경우, 원본과 복사본 둘 다 변동된다.
>
> 깊은 복사는 새로운 객체를 만들어 서로 다른 객체를 참조해, 이를 변경해도 원본에는 변동이 없다.

### 1. 🤔 왜 사용하는가

- 예시 코드
  ```java
  class Book {

      private String name; // 책 이름
      private Author author; // 저자

      public Book(String name, Author author) {
          this.name = name;
          this.author = author;
      }

      public Book shallowCopy() { // 얕은 복사
          return new Book(this.name, this.author);
      }

      public Book deepCopy() { // 깊은 복사
          Author copiedAuthor = new Author(this.author.getName());
          return new Book(this.name, copiedAuthor);
      }

      public void changeAuthor(String name) { // 저자 이름 변경
          author.setName(name);
      }

      @Override
      public String toString() {
          return "Book name : " + name + ", " + author;
      }

      static class Author {

          private String name; // 저자 이름

          public Author(String name) {
              this.name = name;
          }

          public String getName() { // 저자 이름 반환
              return name;
          }

          public void setName(String name) { // 저자 이름 변경
              this.name = name;
          }

          @Override
          public String toString() {
              return "Author : " + name;
          }
      }
  ```
- **얕은 복사**
  - 새로운 객체를 만들어 내부의 객체는 원본과 동일한 객체를 참조
  - 둘이 같은 객체를 공유해 변동이 있을 경우, 둘다 변동됨
- **깊은 복사**
  - 새로운 객체를 만들어 이를 변경해도 원본에는 변동 없음
  - 서로 다른 객체 참조
