import React from "react";
import WebtoonDayDetailContentBox from "../component/WebtoonDayDetailContentBox";
import { useLocation } from "react-router-dom";

export default function WebtoonDayDetailContent() {
  const location = useLocation();
  const { dayWebtoon } = location.state;
  const { title } = dayWebtoon;

  return (
    <div>
      <WebtoonDayDetailContentBox
        webtoontypeTitle={`추천 ${title}`}
      ></WebtoonDayDetailContentBox>
      <WebtoonDayDetailContentBox
        webtoontypeTitle={`전체 ${title}`}
      ></WebtoonDayDetailContentBox>
    </div>
  );
}
