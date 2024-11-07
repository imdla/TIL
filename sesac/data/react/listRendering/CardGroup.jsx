import React from "react";
import Card from "./Card";

const cards = [
  {
    style: {
      width: "100px",
      height: "200px",
    },
    bgColor: "red",
    title: "card1",
    content: "content1",
  },
  {
    style: {
      width: "100px",
      height: "100px",
    },
    bgColor: "blue",
    title: "card2",
    content: "content2",
  },
  {
    style: {
      width: "100px",
      height: "300px",
    },
    bgColor: "#888",
    title: "card3",
    content: "content3",
  },
];

export default function CardGroup() {
  const cardShow = cards.map((card) => {
    return (
      <Card
        styles={card.style}
        backgroundColor={card.bgColor}
        title={card.title}
        content={card.content}
      ></Card>
    );
  });

  return (
    <div>
      <h2>Card Page</h2>
      {cardShow}
    </div>
  );
}
