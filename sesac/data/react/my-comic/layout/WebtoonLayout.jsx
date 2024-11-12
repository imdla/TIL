import React from "react";
import { Outlet } from "react-router-dom";
import WebtoonHeader from "../component/WebtoonHeader";
import WebtoonFooter from "../component/WebtoonFooter";

export default function WebtoonLayout() {
  return (
    <>
      <WebtoonHeader></WebtoonHeader>
      <Outlet></Outlet>
      <WebtoonFooter></WebtoonFooter>
    </>
  );
}
