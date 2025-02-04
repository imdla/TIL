import React from "react";
import MenuCategory from "./MenuCategory";

export default function Menu() {
  const menuBeverages = {
    categories: ["coffee", "ade", "tea"],
    result: [
      {
        category: "coffee",
        beverages: [
          { name: "아메리카노", price: "5.0/5.5" },
          { name: "카페라떼", price: "5.0/5.5" },
          { name: "바닐라라떼", price: "5.0/5.5" },
          { name: "캬라멜마끼야또", price: "5.0/5.5" },
        ],
      },
      {
        category: "ade",
        beverages: [
          { name: "레몬에이드", price: "6.0" },
          { name: "자몽에이드", price: "6.0" },
          { name: "유자에이드", price: "6.0" },
        ],
      },
      {
        category: "tea",
        beverages: [
          { name: "홍차", price: "6.0" },
          { name: "녹차", price: "6.0" },
          { name: "밀크티", price: "6.0" },
        ],
      },
    ],
  };

  const { categories, result } = menuBeverages;

  const beverageList = categories.map((category) => {
    for (let el of result) {
      if (category == el.category) {
        return [category, el.beverages];
      }
    }
  });

  const beverageListItem = beverageList.map((el) => {
    const [category, beverages] = el;

    return (
      <MenuCategory category={category} beverages={beverages}></MenuCategory>
    );
  });

  return (
    <>
      <h1>MENU</h1>
      {beverageListItem}
    </>
  );
}
