import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function PostDetail() {
  const navigate = useNavigate();

  const { postId } = useParams();
  const [post, setPost] = useState();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchPost() {
      try {
        const url = `http://localhost:3000/posts/${postId}`;
        const response = await axios.get(url);

        const data = response.data;
        setPost(data);
      } catch (err) {
        // navigate("/posts");
        // TODO : 나중에 고칠 것
        navigate("/notfound", { replace: true });
      } finally {
        setLoading(false);
      }
    }

    fetchPost();
  }, []);

  if (loading) {
    return <div>로딩 중</div>;
  }

  return (
    <div>
      detail
      <h3>{post?.title}</h3>
      <p>{post?.content}</p>
    </div>
  );
}
