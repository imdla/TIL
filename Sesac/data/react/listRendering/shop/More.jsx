import React from "react";
import Icon from "./icon";

const icons = [
  {
    path: "mail.png",
    name: "Email Adress",
    content: "email@gmai.com",
  },
  {
    path: "telephone.png",
    name: "Phone Number",
    content: "010-1234-5678",
  },
  {
    path: "circle.png",
    name: "Location",
    content: "서울시 도곡동",
  },
  {
    path: "clock.png",
    name: "Working Hours",
    content: "9am-6pm",
  },
];

export default function More() {
  const liIcons = icons.map((icon) => {
    const { path, name, content } = icon;

    return <Icon path={path} name={name} content={content}></Icon>;
  });

  return (
    <div className="more">
      <h2>More</h2>
      <ul className="flex">{liIcons}</ul>
    </div>
  );
}
