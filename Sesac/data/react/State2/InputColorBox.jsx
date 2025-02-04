import React, { useState } from "react";
import Input from "./Input";

export default function InputColorBox() {
  const [input, setInput] = useState("");

  return (
    <div>
      <Input onChange={(e) => setInput(e.target.value)}>색상 입력 : </Input>
      <div
        className="box"
        style={{
          width: 100,
          height: 100,
          border: "1px solid #fff",
          backgroundColor: input,
        }}
      ></div>
    </div>
  );
}
