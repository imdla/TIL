## <mark color="#fbc956">@OneToMany 추가 어노테이션</mark>

### 1. 영속성 전이 (CASCADE)

- JPA에서 엔티티의 상태 변화를 연관된 엔티티에도 함께 적용하는 것
- 부모 엔티티의 상태 변화가 자식 엔티티에도 전파되는 것

- **사용 방법**
  - @OneToMany 어노테이션에 cascade 속성 지정
  - ex. @OneToMany(cascade = CacadeType.ALL)

### 2. 사용 예시

- Order - OderItem

```java
@Entity
public class Order {
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
}
```

```java
@Service
@RequiredArgsConstuctor
public class OrderService {
	private final OrderRepository orderRepository;

	@Transactional
	public Long order(List<OrderItem> orderItems) {
		// 1. 주문 생성
		Order order = new Order();

		// 2. 주문상품 연관관계 설정
		for (OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}

		// 3. 주문 저장 (CASCADE로 인해 orderItems도 함께 저장)
		orderRepository.save(order);

		return order.getId();
	}
}
```

- 삭제도 마찬가지로 사용 가능

### 3. 사용 경우

- 게시글 - 댓글 관계에서 댓글이 존재하는 게시글을 삭제하려고 시도할 경우
  1. 게시글이 삭제 되지 않음 (댓글이 존재하기 때문)
     - 기본값
  2. 게시글이 삭제되고, 댓글들고 함께 삭제
     - CacadeType.ALL
  3. 게시글이 삭제되고, 댓글들은 유지
     - Soft Delete

### 4. 고아 객체 제거

- 부모 엔티티와의 관계가 끊어진 자식 엔티티를 자동으로 삭제하는 기능

- **사용 방법**
  - @OneToMany 어노테이션에 orphanRemoval 속성을 true로 지정
  - ex. @OneToMany(orphanRemoval = true)

### 5. 사용

- `ExapleController`

```java
@GetMapping("/orphan/{postId}")
@Transactional
public void orphan(@PathVariable Long postId) {
	Post post = postRepository.findById(postId)
							.orElseThrow(() -> new ResourceNorFoundException());

	post.getComments().remove(0); // Dirty Checking 으로 인해 참조가 사라
}
```
