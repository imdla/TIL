import React from "react";
import { Outlet } from "react-router-dom";
import NovelHeader from "../component/NovelHeader";
import NovelFooter from "../component/NovelFooter";

export default function NovelLayout() {
  return (
    <>
      <NovelHeader></NovelHeader>
      <Outlet></Outlet>
      <NovelFooter></NovelFooter>
    </>
  );
}
