import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";

export default function PostDetail() {
  const { postId } = useParams();

  const posts = useSelector((state) => state.posts);
  const [post, setPost] = useState();

  // 현재 페이지에서 postId가 맞지 않을 때 확인하는 방법
  // if (!weekData.has(day)) {
  //   console.log("실패");

  //   return <Navigate to="/webtoon" replace></Navigate>;
  //   // return <div>잘못된 페이지야!</div>;
  // } else {
  //   // day를 가지고 fetch(`localhost/webtoon/${day}`) 의 data를 보여줌
  //   return <div>{day} - WebtoonDetail</div>;
  // }

  // useEffect
  // 렌더링이 완료되었을 때 실행
  // 의존성 배열의 변수가 바뀌었을 때 실행
  useEffect(() => {
    setPost(posts.find((post) => post.id === parseInt(postId)));
  });

  return (
    <div>
      detail
      <h3>{post?.title}</h3>
      <p>{post?.content}</p>
    </div>
  );
}
