const posts = [
  { id: 1, title: '첫 번째 포스트', content: '첫 번째 포스트 내용' },
  { id: 2, title: '두 번째 포스트', content: '두 번째 포스트 내용' },
  { id: 3, title: '세 번째 포스트', content: '세 번째 포스트 내용' },
];

const comments = [
  { id: 1, postId: 1, content: '첫 번째 댓글' },
  { id: 2, postId: 2, content: '두 번째 댓글' },
  { id: 3, postId: 1, content: '세 번째 댓글' },
  { id: 4, postId: 2, content: '네 번째 댓글' },
  { id: 5, postId: 3, content: '다섯 번째 댓글' },
  { id: 6, postId: 2, content: '여섯 번째 댓글' },
  { id: 7, postId: 3, content: '일곱 번째 댓글' },
  { id: 8, postId: 3, content: '여덟 번째 댓글' },
  { id: 9, postId: 1, content: '아홉 번째 댓글' },
  { id: 10, postId: 3, content: '열 번째 댓글' },
];

// 게시글 번호와 제목
function idAndTitle() {
  for (let el of posts) {
    console.log(`${el['id']} ${el['title']}`);
  }
}

idAndTitle();

console.log();
// 1번의 게시글의 댓글
function postId1Comment() {
  for (let el of comments) {
    if (el['postId'] === 1) {
      console.log(el);
    }
  }

  // let postId1 = comments.filter((obj) => {
  //   return obj['postId'] === 1;
  // });

  // console.log(postId1);
}

postId1Comment();

console.log();
// 모든 게시글과 댓글
function allPostComment() {
  for (let el of posts) {
    let postId = el.id;
    console.log(`${postId}번 Post`);
    console.log(el);

    console.log(`${postId}번 Post의 Comments`);
    // let postComments = comments.filter((el) => {
    //   return el['postId'] === postId;
    // });

    // console.log(postComments);

    for (let el of comments) {
      if (el['postId'] === postId) {
        console.log(el);
      }
    }
  }
}

allPostComment();

console.log();
// 댓글의 수
function commentsNum() {
  for (let el of posts) {
    let postId = el.id;
    let count = 0;

    for (let el of comments) {
      if (el['postId'] === postId) {
        count++;
      }
    }

    console.log(`${el.postId}번 게시글의 댓글 수 ${count}`);
  }
}

commentsNum();
