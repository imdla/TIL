import React from "react";
import { getImgUrl } from "./utils";

export default function Icon({ path, name, content }) {
  return (
    <li>
      <img src={getImgUrl(path)} alt="" />
      <p>{name}</p>
      <p>{content}</p>
    </li>
  );
}
