import React from "react";
import { useParams, useLocation, Navigate } from "react-router-dom";
import { Outlet } from "react-router-dom";

export default function WebtoonDayDetail() {
  // const { dayId } = useParams();
  // const weekData = new Set(["mon", "tue", "wed", "thu", "fri", "sat", "sun"]);

  // 현재 location으로 component/WebtoonList에서 dayWebtoon 변수 받아와 사용하고 있어
  // url로 요일별 webtoon 접근 시 에러 페이지 나타낼 수 있게 하는 기능 사용 불가
  // 해결 방법
  // 1. index에서 loader 사용해보기
  // 2. dayWebtoon data를 전역 상태로 관리하기
  // if (!weekData.has(dayId)) {
  //   console.log("잘못된 페이지");

  //   <Navigate to="/webtoon" replace></Navigate>;
  // } else {
  const location = useLocation();
  const { dayWebtoon } = location.state;
  const { title, content } = dayWebtoon;

  return (
    <main>
      <h3>{title}</h3>
      <p className="gray">{content}</p>
      <Outlet></Outlet>
    </main>
  );
  // }
}
