import React from "react";
import Menu from "./Menu";

const coffeeMenu = [
  {
    name: "아메리카노",
    price: "5.0/5.5",
  },
  {
    name: "카페라떼",
    price: "6.0/6.5",
  },
  {
    name: "바닐라라떼",
    price: "6.0/6.5",
  },
  {
    name: "캬라멜마끼야또",
    price: "6.5/7.0",
  },
];

const adeMenu = [
  {
    name: "레몬에이드",
    price: "6.0",
  },
  {
    name: "자몽에이드",
    price: "5.0",
  },
  {
    name: "유자에이드",
    price: "6.0",
  },
];

export default function CafeMenu() {
  return (
    <section className="cafeMenu">
      <h1>Menu</h1>
      <Menu menuName={"Coffee"} menuItems={coffeeMenu}></Menu>
      <Menu menuName={"Ade"} menuItems={adeMenu}></Menu>
    </section>
  );
}
