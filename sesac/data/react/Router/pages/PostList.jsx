import React from "react";
import { Link, useNavigate } from "react-router-dom";

export default function PostList() {
  // const navigate = useNavigate();

  const posts = [
    {
      id: 1,
      title: "첫 번째 프로젝트",
      content:
        "첫 프로젝트에서 간단한 Todo 앱을 만들었습니다. UI 구성과 상태 관리의 중요성을 배웠습니다.",
    },
    {
      id: 2,
      title: "리액트와 함께한 성장",
      content:
        "리액트로 컴포넌트 기반 개발을 익히고, 재사용성을 높이는 방법을 이해하게 되었습니다.",
    },
    {
      id: 3,
      title: "팀 프로젝트 경험",
      content:
        "팀원들과 협업하여 일정 관리 앱을 개발했습니다. Git을 활용한 버전 관리와 코드 리뷰의 중요성을 체감했습니다.",
    },
  ];

  return (
    <>
      <h3>Posts</h3>
      <ul>
        {posts.map((post) => {
          const { id, title, content } = post;

          return (
            // 1. JSX 내부에서 사용하기 좋음
            <li key={id}>
              <Link to={`/posts/${id}`} state={{ post }}>
                <h3>{title}</h3>
              </Link>

              {/* 2. JavaScript에서 이동 지정할때 */}
              <h3
                onClick={() => {
                  navigate(`/posts/${id}`);
                }}
              >
                {title}
              </h3>
            </li>
          );
        })}
      </ul>
    </>
  );
}
