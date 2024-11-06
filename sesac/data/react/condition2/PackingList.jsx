import React from "react";
import Item from "./Item";

export default function PackingList({ name }) {
  return (
    <section className="pakingList">
      <h2>{name}'s Packing List</h2>
      <ul>
        <Item item="clothes" isPacked={true}></Item>
        <Item item="shose" isPacked={true}></Item>
        <Item item="glasses" isPacked={false}></Item>
        <Item item="phone" isPacked={false}></Item>
        <Item item="cap" isPacked={true}></Item>
      </ul>
    </section>
  );
}
