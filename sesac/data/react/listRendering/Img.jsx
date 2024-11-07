import React from "react";

export default function Img() {
  const imgUrls = [
    "https://images.dog.ceo/breeds/gaddi-indian/Gaddi.jpg",
    "https://images.dog.ceo/breeds/terrier-westhighland/n02098286_3154.jpg",
    "https://images.dog.ceo/breeds/malamute/n02110063_16752.jpg",
    "https://images.dog.ceo/breeds/bulldog-english/jager-2.jpg",
  ];

  const imgShow = imgUrls.map((url) => {
    return <img src={url}></img>;
  });

  return (
    <div>
      <h2>Img Page</h2>
      <div className="flex">{imgShow}</div>
    </div>
  );
}
