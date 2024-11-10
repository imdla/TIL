import React from "react";
import MenuCategoryItem from "./MenuCategoryItem";

export default function MenuCategory({ category, beverages }) {
  const beverageItem = beverages.map((beverage) => {
    return <MenuCategoryItem {...beverage}></MenuCategoryItem>;
  });

  return (
    <section>
      <h2>{category}</h2>
      <ul>{beverageItem}</ul>
    </section>
  );
}
