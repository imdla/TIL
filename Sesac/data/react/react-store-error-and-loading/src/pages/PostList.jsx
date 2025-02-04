import React, { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

export default function PostList() {
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchPosts() {
      try {
        const url = "http://localhost:3000/posts/";
        const response = await axios.get(url);
        const data = response.data;

        setPosts(data);
      } catch (err) {
        setError(err.message);
        console.error(err);
        console.log("무엇인가 error");
      } finally {
        setLoading(false);
      }
    }

    fetchPosts();
  }, []);

  if (error) {
    return <div>{error}</div>;
  }

  if (loading) {
    return <div>로딩 중</div>;
  }

  return (
    <div>
      <h2>posts</h2>

      <ul>
        {posts.map((post) => {
          const { id, title } = post;
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
