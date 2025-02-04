## <mark color="#fbc956">Callback and Promise</mark>



### 1. 콜백 (Callback)


> 💡 **Callback ?**
> 
> 콜백은 적절한 시점에 호출될 것으로 예상하여 다른 함수로 전달되는 함수



```jsx
function waitAndRun(){
	setTimeout(() => {
		console.log('완료');
	}, 2000);
}

waitAndRun(); // 완료
```

### 2. 콜백 속 콜백

```jsx
function waitAndRun2(){
	setTimeout(() => {
		console.log('1번 콜백 완료');
		setTimeout(() => {
			console.log('2번 콜백 완료');
			setTimeout(() => {
				console.log('3번 콜백 완료');
			}, 2000);
		}, 2000);
	}, 2000);
}

waitAndRun2();
/**
1번 콜백 완료
2번 콜백 완료
3번 콜백 완료
*/
```

⇒ 콜백 중첩 시 오류 처리가 어려워 질 수 있음, 상위 레벨에서 한 번만 오류를 처리하는 대신 피라미드의 각 레벨에서 오류를 처리해야하는 경우 발생, 콜백 지옥

### 3. Promise

- 미래에 어떤 종류의 결과가 반환됨을 약속해주는 오브젝트
- 비동기 작업에 대한 성공과 실패를 처리하는 문법
- 콜백 지옥을 방지하기 위한 방법 중 하나

### 4. promise() 생성자

- **`new Promise`**에 전달되는 **`executor(실행자, 실행함수)`**
- **`executor`**에서 두 개의 인수 ⇒ 반드시 하나 호출
    - **`resolve` (value)**
        - 비동기 작업이 성공적으로 끝난 경우, 그 결과 나타내는 `value`와 함께 호출
    - **`reject` (error)**
        - 비동기 작업이 에러 발생 시 에러 객체를 나타내는 `error`와 함께 호출

```jsx
let promise = new Promise(function(resolve, reject){
	// 비동기 작업 코드
	
	// executor(실행자, 실행 함수)
	if (조건) {
		resolve(value);
	} else {
		reject(error);
	}
});
```

### **5. Promise.resolve()**

- **`Promise.resolve(value)`**
    - 주어진 값으로 이행하는 `Promise.then` 객체를 반환
    - 그 값이 `promise`인 경우, 해당 `promise` 반환

```jsx
const promise = Promise.resolve(123);

promise.then((value) => {
	console.log(value);
}); // 123
```

### **6. Promise.reject()**

- **`Promise.reject(reason)`**
    - 주어진 이유(reason)로 거부된 `promise` 객체를 반환

```jsx
Promise.reject('에러').then(
	function (reason) {
		// 호출되지 않음
	},
	function (reason) {
		console.log(reason); // 에러
	}
);
```

### 7. Promise 활용

- `then()` , `catch()` , `finally()` 메서드를 이용해 비동기 작업의 결과 처리
    - **`then()`** : `resolve(value)` 함수 호출되었을 때 실행할 코드 정의, 값을 인자로 받음
    - **`catch()`** : `reject()` 함수 호출되었을 때 실행할 코드 정의, 값을 인자로 받음
    - **`finally()`** : 성공 여부 상관없이 작업 완료 후 실행할 코드 정의

```jsx
const newPromise = new Promise((resolve, reject) => {
	const flag = true;
	
	if (flag === true) {
		resolve('작업 성공');
	} else {
		reject('작업 실패');
	}
});

newPromise
	.then((result) => {
		console.log(result);
	})
	.catch((error) => {
		console.log(error);
	})
	.finally(() => {
		console.log('작업 완료');
	})
```

### **8. Promise.prototype.then()**

- **`then()` 메서드**
    
    : Promise를 리턴하고 두 개의 콜백 함수를 인수로 받음,
    
    하나는 Promise가 이행했을 때, 다른 하나는 거부했을 때를 위한 콜백 함수
    
    - **`onFulfilled`** - `Promise`가 수행될 때 호출되는 Function으로, **이행 값(fulfillment value)** 하나를 인수로 받음
    - **`onRejected`** - `Promise`가 거부될 때 호출되는 Function으로, **거부 이유(rejection reacon)** 하나를 인수로 받음

```jsx
p.then(onFulfilled, onRejected);

p.then(function(value) {
  // 이행
}, function(reason) {
  // 거부
});
```

- **then 사용, 성공적으로 이행된 promise의 예**
    
    ```jsx
    let promise = new Promise(function(resolve,reject){
    	setTimeout(() => resolve('완료'), 1000);
    });
    
    promise.then(
    	result => console.log(result),
    	error => console.log(error)
    ); // 완료
    ```
    
- **then 사용, promise가 거부된 경우**
    
    ```jsx
    let promise = new Promise(function(resolve, reject){
    	setTimeout(() => reject(new Error('에러 발생')), 1000);
    });
    
    promise.then(
    	result => console.log(result),
    	error => console.log(error)
    ); // Error: 에러 발생
    ```
    
- **작업이 성공적으로 처리된 경우만 다룰 때**
    
    ```jsx
    const timeoutPromise = new Promise((resolve, reject) => {
    	setTimeout(() => {
    		resolve('완료');
    	}, 2000);
    });
    
    timeoutPromise.then((res) => {
    	console.log('---then---');
    	console.log(res);
    });
    /**
    ---then---
    완료
    */
    ```
    

### **9. Promise.prototype.catch()**

- **`catch()` 메서드**
    
    : Promise가 거부될 때 호출될 함수를 예약
    
    - **`onRejected`** - Promise가 거부될 때 비동기적으로 실행되는 함수, 반환값은 `catch()` 에서 반환한 약속의 이행 값이 됨

```jsx
promiseInstance.catch(onRejected)
```

```jsx
let promise = new Promise((resolve, reject) => {
	setTimeout(() => reject('에러 발생'), 1000);
});

promise.catch((res) => {
	console.log(res);
}); // 에러 발생
```

### **10. Promise.prototype.finally()**

- **`finally()`**
    - 프로미스의 이행과 거부 여부 상관없아 처리될 경우 항상 호출되는 처리기 콜백 추가
    - 이행한 값 그대로 이행하는 새로운 프로미스 반환

```jsx
new Promise((resolve, reject) => {
  setTimeout(() => resolve('성공'), 2000)
})
  .finally(() => console.log('완료되었습니다'))
  .then(result => console.log(result));
/**
완료되었습니다
성공
*/
```

### 11. then, catch, finally 모두 사용

```jsx
const getPromise = (seconds) => new Promise((resolve, reject) => {
	setTimeout(() => {
		reject('에러');
	}, seconds * 1000);
});

getPromise(3)
	.then((res) => {
		console.log('---first then---')
		console.log(res);
	}).catch((res) => {
		console.log('---first catch---');
		console.log(res);
	}).finally((res) => {
		console.log('---finally---');
	});
/**
---first catch---
에러
---finally---
*/
```

### **12. Promise.all()**

- **`all()`**
    - 주어진 모든 프로미스가 이행하거나, 한 프로미스가 거부될 때까지 대기하는 새로운 프로미스를 반환
    - 반환하는 프로미스가 이행한다면, 매개변수로 제공한 프로미스 각각의 이행 값을 모두 모아 놓은 배열로 이행, 배열 요소의 순서는 매개변수에 지정한 프로미스의 순서 유지
    - 반환하는 프로미스가 거부된다면, 매개변수의 프로미스 중 거부된 첫 프로미스의 사유를 그대로 사용

```jsx
const getPromise = (seconds) => new Promise((resolve, reject) => {
	setTimeout(() => {
		resolve('성공');
	}, seconds * 1000);
});

Promise.all([
	getPromise(1),
	getPromise(4),
	getPromise(1),
]).then((res) => {
	console.log(res);
}); // [ '성공', '성공', '성공' ]
```

### 13. 콜백지옥을 promise로 바꾸기

```jsx
// 위의 콜백지옥의 예시를 promise를 통해 변경
const getPromise = (seconds) => new Promise((resolve, reject) => {
	setTimeout(() => {
		resolve('완료');
	}, seconds * 1000);
});

getPromise(3)
	.then((res) => {
		console.log('---first then');
		console.log(res);

		return getPromise(3);
	}).then((res) => {
		console.log('---second then---');
		console.log(res);

		return getPromise(4);
	}).then((res) => {
		console.log('---third then---');
		console.log(res);

		return getPromise(4);
	});
/**
---first then
완료
---second then---
완료
---third then---
완료
*/
```

### 14. Promise 활용

- **`fetch(url)`**
    - 네트워크 요청을 처리하는 비동기 함수, `promise` 반환
    
    ```jsx
    const url = 'URL';
    
    const data = fetch(url)
    	.then((response) => {
    		return response.json();
    	})
    	.then((json) => {
    		console.log(json);
    	})
    	.catch((error) => {
    		console.log(error);
    	});
    ```