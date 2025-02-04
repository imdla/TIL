import React, { useState } from "react";
import Input from "./Input";

export default function InputBox() {
  const [input, setInput] = useState("");

  return (
    <div>
      <Input onChange={(e) => setInput(e.target.value)}>내용 입력 : </Input>
      <p>입력한 값 : {input}</p>
    </div>
  );
}
