## <mark color="#fbc956">Composition</mark>

### 1. 컴포지션 (Composition)

- (= 포함, 구성, 합성)
- 하나의 클래스의 멤버 변수로 다른 클래스 타입의 참조형 변수를 가지는 것
- 하나의 타입 안에 다른 타입의 변수를 포함시키는 것

### 2. 상속과 비교

1. **상속**

   ```java
   public class Address {
   	private String city;
   	private String street;

   	public Address(String city, String street) {
   		this.city = city;
   		this.street = street;
   	}

   	public String getCity() {
   		return city;
   	}

   	public String getStreet() {
   		return street;
   	}
   }
   ```

   ```java
   public class Building extends Address {

   	public Building(String city, String street) {
   		super(city, street);
   	}

   	public void showAddress() {
   		System.out.prtinln(getCity() + getStreet());
   	}
   }
   ```

   → Address 클래스를 상속해 Building 클래스가 시와 도의 정보 가지도록 함

2. **컴포지션**

   ```java
   public class Building {
   	private Address address;

   	public Building(Address address) {
   		this.address = address;
   	}

   	public void showAddress() {
   		System.out.prtinln(getCity() + getStreet());
   	}
   }
   ```

- **비교**
  - **상속** : 계층 관계 형성
    - `is-a` 관계에 주로 사용
    - `~은 ~이다` 로 표현할 수 있는 관계
  - **컴포지션** : 포함 관계 형성
    - `has-a` 관계에 주로 사용
    - `~은 ~을 가진다` 로 표현할 수 있는 관계

---

## <mark color="#fbc956">상속의 한계</mark>

### 1. 상속은 결합도 높임

- **결합도**
  - 하나의 클래스가 다른 클래스에 대해 얼마나 알고 있는가
- 객체지향 프로그래밍에서 클래스 간의 결합도는 낮게 유지하는 것을 권장
  - 결합도가 높을 경우, 한 쪽 클래스의 내용 변경할 때 마다 다른 쪽 클래스의 내용 변경해야 할 가능성 높아짐 → 유지보수성 떨어짐

> Address와 Building 클래스가 상속 관계일 때, 결합도 확인하기

```java
public class Address {
	private String city;
	private String street;

	public Address(String city, String street) {
		this.city = city;
		this.street = street;
	}
}
```

```java
public class Building extends Address {
	public Building(String city, String street) {
		super(city, street);
	}
}
```

- Address에 postCode 추가

```java
public class Address {
	private String city;
	private String street;
	private String postCode;

	public Address(String city, String street, String postCode) {
		this.city = city;
		this.street = street;
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getPostCode() {
		return postCode;
	}
}
```

- Building 클래스에도 postCode 대한 정보 추가

```java
public Class Building extends Address {
	public Building(String city, String street, String postCode) {
		super(city, street, postCode);
	}
}
```

> Building 클래스가 Address를 컴포지션으로 가지고 있을 경우

```java
public class Building {
	private Address address;

	public Building(Address address) {
		this.address = address;
	}
}
```

- Building은 Address의 인스턴스 자체를 가지고 있어 Address 클래스의 내부가 변경되어도 Building 클래스에 영향을 끼치지 않음
  → Building과 Address의 결합도는 낮게 유지됨

### 2. Java는 다중 상속 불가능

> Building 클래스가 Address뿐만 아니라 Owner 클래스의 정보도 가지고 있어야 할 경우 (**상속**)

```java
public class Address {
	private String city;
	private String street;

	public Address(String city, String street) {
		this.city = city;
		this.street = street;
	}
}
```

```java
public class Building extends Address {
	public Building(String city, String street) {
		super(city, street);
	}
}
```

- Building 클래스에 Owner에 대한 정보를 추가

```java
public class Owner {
	private String name;
	private int age;

	public Owner(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
```

```java
// 불가능
public class Building extends Address, Owner {
	public Building(String city, String street) {
		super(city, street);
	}
}
```

→ 클래스는 다른 여러 클래스들을 상속 받을 수 없음

- Address ← Owner ← Building 과 같은 연쇄적 상속을 통해 Building은 정보를 모두 가질 수 있지만
  - 세 클래스는 결합도가 높고, 복잡한 코드와 더불어 유지보수 훨씬 어려움

> Building 클래스가 Address 및 Owner 클래스의 정보도 가지고 있어야 할 경우 (**컴포지션**)

```java
public class Building {
	private Address address;
	private Owner owner;

	public Building(Address address, Owner owner) {
		this.address = address;
		this.owner = owner;
	}
}
```

- Building이 Address와 Owner의 인스턴스 자체를 가지고 있음
  - 어느 곳에서 변경이 발생해도 Building 클래스에는 영향을 끼치지 못함
  - 세 클래스의 결합도가 낮게 유지됨

```java
Address address = new Address();
Owner owner = new Owner();

Building building = new Building(address, owner);

building.address.변수 및 메서드 // address의 변수 및 메서드
building.owner.변수 및 메서드 // owner의 변수 및 메서드

building.method();
```

---

### ☀️ 오늘의 배움

- 상속 `is a` 관계
  - 높은 결합도
- 컴포지션 `hase a` 관계
  - 낮은 결합도
