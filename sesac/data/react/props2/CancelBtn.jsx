import React, { Children } from "react";
import Button from "./Button";

export default function CancelBtn({ children }) {
  return (
    <div>
      <Button backgroundColor="red" text={children}></Button>
    </div>
  );
}
