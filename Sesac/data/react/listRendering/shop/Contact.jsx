import React from "react";
import { getImgUrl } from "./utils";

export default function Contact() {
  const path = "apparel3.jpg";

  return (
    <div className="contact">
      <h2>Contact Us</h2>
      <div className="flex">
        <form className="flex" action="">
          <label htmlFor="username">Username</label>
          <input type="text" id="username" name="username" />
          <label htmlFor="email">Eamil</label>
          <input type="email" id="email" name="email" />
          <button type="submit">제출</button>
        </form>
        <img src={getImgUrl(path)} alt="" />
      </div>
    </div>
  );
}
