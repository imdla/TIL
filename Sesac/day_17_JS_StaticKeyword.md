## <mark color="#fbc956">정적 메서드와 정적 프로퍼티</mark>

### 1. 정적(static) 메서드

- **static method**
  - `prototype`이 아닌 클래스 함수 자체에 메서드를 설정
  - 클래스 안에서 `static` 키워드와 함께 사용
- **`static` 키워드 :** 객체에 귀속되지 않고, 클래스 자체에 귀속됨

```jsx
class Professor {
  name;
  year;

  static groupName = "교수";

  constructor(name, year) {
    this.name = name;
    this.year = year;
  }

  static returnGroupName() {
    return "교수";
  }
}

const john = new Professor("존", 2023);
console.log(john); // Professor { name: '존', year: 2023 }

// 정적 메서드는 메서드를 프로퍼티 형태로 직접 할당하는 것과 동일
console.log(Professor.groupName); // 교수
console.log(Professor.returnGroupName()); // 교수
```

```jsx
class Post() {
	constructor(title, date) {
		this.title = title;
		this.date = date;
	}

	static createPost() {
		return new this("Today's Post", new Date());
	}
}

// Post 클래스의 정적 메서드인 createPost()통해 Post의 전체 클래스 생성
let post = Post.createPost();

console.log(post.title); // Today's Post
```

### 2. 정적 프로퍼티

- 일반 클래스 프로퍼티와 유사, `static` 키워드 붙음
- 클래스에 프로퍼티를 직접 할당한 것과 같음

```jsx
class Professor {
  static name;

  constructor(name) {
    this.name = name;
  }
}

const john = new Professor("존");
console.log(john.name); // 존
```

### 3. factory constructor

```jsx
class Professor {
  name;
  year;

  constructor(name, year) {
    this.name = name;
    this.year = year;
  }

  static fromObject(object) {
    return new Professor(object.name, object.year);
  }
  static fromList(list) {
    return new Professor(list[0], list[1]);
  }
}

const john = Professor.fromObject({
  name: "존",
  year: 2023,
});
console.log(john); // Professor { name: '존', year: 2023 }

const mia = Professor.fromList(["미아", 2022]);
console.log(mia); // Professor { name: '미아', year: 2022 }
```
