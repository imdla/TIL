import React from "react";
import Child from "./Child";

export default function Parent() {
  return (
    <div className="parent">
      <Child></Child>
      <Child></Child>
    </div>
  );
}
