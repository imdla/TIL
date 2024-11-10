import React, { useState } from "react";
import Button from "./Button";

export default function Count() {
  const [count, setCount] = useState(0);

  return (
    <div>
      <div>Count : {count}</div>
      <Button backgroundColor="gray" onClick={() => setCount(count - 1)}>-</Button>
      <Button backgroundColor="blue" onClick={() => setCount(count + 1)}>+</Button>
    </div>
  );
}
