import React from "react";
import WebtoonList from "../component/WebtoonList";

export default function WebtoonDays() {
  return (
    <main>
      <h2>Webtoon Days</h2>
      <ul>
        <WebtoonList flag={false}></WebtoonList>
      </ul>
    </main>
  );
}
