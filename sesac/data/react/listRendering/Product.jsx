import React from "react";

const products = [
  { id: 1, name: "노트북", price: 55000 },
  { id: 2, name: "마우스", price: 15000 },
  { id: 3, name: "키보드", price: 45000 },
  { id: 4, name: "마우스패드", price: 8000 },
  { id: 5, name: "모니터", price: 150000 },
];

const highproducts = products
  .filter((product) => {
    return product.price >= 30000;
  })
  .map((product) => {
    return <div>상품명 : {product.name}</div>;
  });

export default function Product() {
  return (
    <>
      <h2>Product Page</h2>
      <div>{highproducts}</div>
    </>
  );
}
