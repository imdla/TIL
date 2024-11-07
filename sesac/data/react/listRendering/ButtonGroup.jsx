import React from "react";
import Button from "./Button";

const btnSetting = [
  {
    bgColor: "blue",
    content: "확인",
  },
  {
    bgColor: "red",
    content: "취소",
  },
  {
    bgColor: "gray",
    content: "보류",
  },
  {
    bgColor: "pink",
    content: "1억년",
  },
];

export default function ButtonGroup() {
  const buttons = btnSetting.map((btn) => {
    const { bgColor, content } = btn;
    return <Button backgroundColor={bgColor}>{content}</Button>;
  });

  return (
    <div>
      <h2>Button Page</h2>
      <div className="flex">{buttons}</div>
    </div>
  );
}
