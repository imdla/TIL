import React from "react";
import Menu from "./menu";

const beverages = [
  {
    type: "coffee",
    name: "아메리카노",
    price: "5.0/5.5",
  },
  {
    type: "coffee",
    name: "카페라떼",
    price: "6.0/6.5",
  },
  {
    type: "coffee",
    name: "바닐라라떼",
    price: "6.0/6.5",
  },
  {
    type: "coffee",
    name: "카라멜마끼야또",
    price: "6.5/7.0",
  },
  {
    type: "ade",
    name: "레몬에이드",
    price: "6.0",
  },
  {
    type: "ade",
    name: "자몽에이드",
    price: "6.0",
  },
  {
    type: "ade",
    name: "유자에이드",
    price: "6.0",
  },
];

export default function CafeMenu() {
  const coffee = [];
  const ade = [];

  for (let beverage of beverages) {
    if (beverage.type === "coffee") {
      coffee.push(beverage);
    } else {
      ade.push(beverage);
    }
  }

  return (
    <div>
      <h1>Menu</h1>
      <h2>Coffee</h2>
      <Menu menus={coffee}></Menu>
      <h2>Ade</h2>
      <Menu menus={ade}></Menu>
    </div>
  );
}
