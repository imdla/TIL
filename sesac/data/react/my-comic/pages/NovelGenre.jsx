import React from "react";
import NovelList from "../component/NovelList";

export default function NovelGenre() {
  return (
    <main>
      <h2>Novel Genre</h2>
      <ul>
        <NovelList flag={false}></NovelList>
      </ul>
    </main>
  );
}
