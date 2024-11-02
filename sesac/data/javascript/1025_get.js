// get - 특정 id의 리소스 가져옴

// function 바꿈
function fetchPostId(postId) {
  fetch(`https://jsonplaceholder.typicode.com/posts/${postId}`)
    .then((response) => response.json())
    .then((json) => {
      console.log(json);
    })
    .catch((error) => console.error(error));
}

fetchPostId(1);

// fetch
fetch('https://jsonplaceholder.typicode.com/posts/1')
  .then((response) => response.json())
  .then((json) => {
    console.log(json);
  })
  .catch((error) => console.error(error));

// async
async function getPostsById() {
  try {
    const response = await fetch(
      'https://jsonplaceholder.typicode.com/posts/1'
    );
    const data = await response.json();
    console.log(data);
  } catch (error) {
    console.log('Error:', error);
  }
}

getPostsById();

// get - posts 리스트 가져옴
fetch('https://jsonplaceholder.typicode.com/posts')
  .then((response) => response.json())
  .then((json) => {
    console.log(json);
  })
  .catch((error) => console.error(error));

async function getPosts() {
  try {
    const response = await fetch('https://jsonplaceholder.typicode.com/posts');
    const data = await response.json();
    console.log(data);
  } catch (error) {
    console.log('Error:', error);
  }
}

getPosts();

// post - 새로운 리소스 생성
fetch('https://jsonplaceholder.typicode.com/posts', {
  method: 'POST',
  body: JSON.stringify({
    title: 'foo',
    body: 'bar',
    userId: 1,
  }),
  headers: {
    'Content-type': 'application/json; charset=UTF-8',
  },
})
  .then((response) => response.json())
  .then((json) => console.log(json))
  .catch((error) => console.error(error));

async function createPost() {
  try {
    const response = await fetch('https://jsonplaceholder.typicode.com/posts', {
      method: 'POST',
      body: JSON.stringify({
        title: 'foo',
        body: 'bar',
        userId: 1,
      }),
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
    });

    const data = await response.json();
    console.log(data);
  } catch (error) {
    console.log('Error: ', error);
  }
}

createPost();

// PUT
fetch('https://jsonplaceholder.typicode.com/posts', {
  method: 'PUT',
  body: JSON.stringify({
    id: 1,
    title: 'foo',
    body: 'bar',
    userId: 1,
  }),
  headers: {
    'Content-type': 'application/json; charset=UTF-8',
  },
})
  .then((response) => response.json())
  .then((json) => console.log(json))
  .catch((error) => console.error(error));

async function updatePost() {
  try {
    const response = await fetch('https://jsonplaceholder.typicode.com/posts', {
      method: 'PUT',
      body: JSON.stringify({
        id: 1,
        title: 'foo',
        body: 'bar',
        userId: 1,
      }),
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
    });

    const data = await response.json();
    console.log(data);
  } catch (error) {
    console.log('Error: ', error);
  }
}

updatePost();

// PATCH
fetch('https://jsonplaceholder.typicode.com/posts', {
  method: 'PATCH',
  body: JSON.stringify({
    title: 'foo',
  }),
  headers: {
    'Content-type': 'application/json; charset=UTF-8',
  },
})
  .then((response) => response.json())
  .then((json) => console.log(json))
  .catch((error) => console.error(error));

async function partialUpdatePost() {
  try {
    const response = await fetch('https://jsonplaceholder.typicode.com/posts', {
      method: 'PATCH',
      body: JSON.stringify({
        title: 'foo',
      }),
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
    });

    const data = await response.json();
    console.log(data);
  } catch (error) {
    console.log('Error: ', error);
  }
}

partialUpdatePost();

// DELETE
fetch('https://jsonplaceholder.typicode.com/posts', {
  method: 'DELETE',
});

async function deletePost() {
  const response = await fetch('https://jsonplaceholder.typicode.com/posts', {
    method: 'DELETE',
  });
}

deletePost();
