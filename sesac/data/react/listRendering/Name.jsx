import React from "react";

export default function Name() {
  const names = ["김철수", "이영희", "박민수", "정지원", "최동욱"];

  const namesShow = names.map((name) => {
    return <li>{name}</li>;
  });

  return (
    <>
      <h2>Name List Page</h2>
      <ul>{namesShow}</ul>
    </>
  );
}
