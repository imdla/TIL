import React from "react";
import Button2 from "./Button2";

export default function PlayButton({ movieName }) {
  function handlePalyMovie() {
    alert(`Playing ${movieName}`);
  }

  return (
    <Button2 onClick={handlePalyMovie} backgroundColor="tomato">
      Play "{movieName}"
    </Button2>
  );
}
