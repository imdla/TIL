import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
// import { addPost } from "../store/slices/postsSlice";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function PostCreate() {
  const isLogin = useSelector((state) => state.isLogin);
  const [formData, setFormData] = useState({ title: "", content: "" });
  // useDispatch : setter를 실행시키는 함수
  // const dispatch = useDispatch();
  const navigate = useNavigate();

  // 처음 마운트 + isLogin 바뀌었을 때
  useEffect(() => {
    if (!isLogin) {
      navigate("/");
    }
  }, [isLogin]);

  // input value를 formData에 저장
  function handleChange(e) {
    return setFormData({ ...formData, [e.target.name]: e.target.value });
  }

  // form 제출했을 때
  function handleSubmit(e) {
    e.preventDefault();

    // axios로 대체
    // dispatch(addPost({ ...formData, id: id }));
    async function createPost() {
      const url = "http://localhost:3000/posts";
      const response = await axios.post(url, formData);
      const data = response.data;
      const id = data.id;
      navigate(`/posts/${id}`);
    }

    createPost();
  }

  return (
    <>
      <h3>Post Create</h3>
      <form onSubmit={handleSubmit}>
        <label htmlFor="title">제목 : </label>
        <input type="text" name="title" id="title" onChange={handleChange} />

        <label htmlFor="content">내용 : </label>
        <textarea name="content" id="content" onChange={handleChange} />

        <button>생성</button>
      </form>
    </>
  );
}
