> 💡 **한 줄 요약**
>
> JCF에서 가변 크기의 자료 구조를 사용할 경우, 초기 용량 설정 시 리사이징 줄여 메모리와 연산 비용을 절약할 수 있다.

### 1. ✅ 장점

> **JCF 자료구조의 초기 용량 지정 시 장점**

- JCF ArrayList 기준
  - ArrayList의 기본 용량(capacity)은 10, 용량이 가득 차면 기존 크기의 1.5배(oldCapacity + (oldCapacity >> 1))로 증가
    - ex. MAX = 5,000,000일 경우
      - 기본 설정으로 리스트 생성 시 여러 번의 리사이징 발생
      - 최종 capacity 6,153,400까지 증가
      - 약 70MB의 메모리 사용
    - ex. `new ArrayList(MAX)` 로 초기 용량 설정할 경우
      - 불필요한 리사이징 없이 5,000,000 크기로 고정
      - 약 20MB의 메모리만 사용
- JCF에서 가변 크기의 자료 주고 사용하는 경우
  → 초기 용량 설정 시 리사이징 줄임
  → 메모리와 연산 비용 절약 가능

```java
public class Main {

	private static final int MAX = 5_000_000;

	public static void main(String[] args) {
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		printUsedHeap(1, memoryMXBean);

		List<String> arr = new ArrayList<>();
		for (int i = 0; i < MAX; i++) {
			arr.add("a");
		}

		printUsedHeap(2, memoryMXBean);
		printUsedHeap(2, memoryMXBean);
	}

	private static void printUsedHeap(int logIndex, MemoryMXBean memoryMXBean {
		MemoryUsage = heapUsage = memoryMXBean.getHeapMemoryUage();
		long used = heapUsage.getUsed();
		System.out.println("[" + longIndex + "]" + "Used Heap Memory: " + used / 1024 / 1024 + " MB");
	}
}
```

### 2. 🤔 왜 사용하는가

> **로드 팩터와 임계점**

- **로드 팩터(load factor)**
  - 특정 크기의 자료 구조에 데이터가 어느 정도 적재되었는지 나타내는 비율
  - 가변적인 크기를 가진 자료구조에서 크기를 증가시켜야하는 임계점(Threshold)을 계산하기 위해 사용
  - ex. JCF에서 HashMap의 경우
    - 내부적으로 배열 사용, 초기 사이즈는 16
    - HashMap의 기준 로드 팩터는 0.75이므로 임계점은 12
      (capacity \* load factor = threshold)
    - HashMap 내부 배열의 사이즈가 12를 넘는 경우
      - 내부 배열의 크기를 2배 늘리고, 재해싱 수행
