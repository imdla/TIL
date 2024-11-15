import React from "react";
import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";

export default function PostList() {
  const navigate = useNavigate();
  const posts = useSelector((state) => state.posts);

  return (
    <div>
      <h2>posts</h2>

      <ul>
        {posts.map((post) => {
          const { id, title, content } = post;
          return (
            <li key={id}>
              <Link to={`/posts/${id}`}>
                <h3>{title}</h3>
              </Link>
              <h3
                onClick={() => {
                  // 이동을 하고 싶다
                  navigate(`/posts/${id}`);
                }}
              >
                {title}
              </h3>
            </li>
          );
        })}
      </ul>

      <button>
        <Link to="/posts/create">게시글 생성</Link>
      </button>
    </div>
  );
}
