import React, { useState } from "react";
import Button from "./Button";

export default function GoodBtn() {
  const [isGood, setIsGood] = useState(true);

  return (
    <div>
      <Button
        // backgroundColor={isGood ? "blue" : "gray"}
        className={`${isGood ? "blue" : "gray"}`}
        onClick={() => setIsGood((prev) => !prev)}
      >
        {isGood ? "좋아요" : "좋아요 취소"}
      </Button>
    </div>
  );
}
