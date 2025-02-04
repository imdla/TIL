import React from "react";
import MenuCategory from "./MenuCategory";

export default function Menu() {
  const beverages = {
    categories: ["coffee", "ade", "tea"],
    result: {
      coffee: [
        { name: "아메리카노", price: "5.0/5.5" },
        { name: "카페라떼", price: "6.0/6.5" },
        { name: "바닐라라떼", price: "6.0/6.5" },
      ],
      ade: [
        { name: "레몬에이드", price: "5.0" },
        { name: "자몽에이드", price: "6.0" },
        { name: "유자에이드", price: "6.0" },
      ],
      tea: [
        { name: "녹차", price: "5.0" },
        { name: "둥굴레차", price: "6.0" },
        { name: "홍차", price: "6.0" },
      ],
    },
  };

  const { categories, result } = beverages;

  const beveragesList = categories.map((category) => {
    return [category, result[category]];
  });

  const menuCategories = beveragesList.map((el) => {
    const [category, beverages] = el
    
    return <MenuCategory category={category} beverages={beverages}></MenuCategory>
  });

  return (
    <>
      <h1>MENU</h1>
      {menuCategories}
    </>
  );
}
