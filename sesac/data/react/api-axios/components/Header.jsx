import React from "react";
import { useSelector } from "react-redux";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { switchLogin } from "../store/slices/loginSlice";
import { useDispatch } from "react-redux";

export default function Header() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const isLogin = useSelector((state) => state.isLogin);

  function handleClick() {
    // 버튼이 클릭될때 마다
    // isLogin이 false면 -> true로 바꾸고 [로그아웃]
    // isLogin이 true면 -> false로 바꾸고 [로그인]
    dispatch(switchLogin(isLogin));
  }

  function handleLoginCreate() {
    isLogin ? navigate(`/posts/create`) : navigate("/request-login");
  }

  return (
    <header>
      <ul>
        <li>
          <Link to="/">Home으로</Link>
        </li>
        <li>
          <Link to="/posts">게시글로</Link>
        </li>
        <li>
          <a onClick={handleLoginCreate}>게시글 생성</a>
        </li>
      </ul>

      <button
        style={{
          backgroundColor: isLogin ? "gray" : "blue",
        }}
        onClick={handleClick}
      >
        {isLogin ? "로그아웃" : "로그인"}
      </button>
    </header>
  );
}
