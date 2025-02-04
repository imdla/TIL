import React from "react";
import Button2 from "./Button2";

export default function Toolbar({ onPlayMovie, onPlayImage }) {
  return (
    <div>
      <Button2 onClick={onPlayMovie} backgroundColor="lightgrey">
        Play Movie
      </Button2>
      <Button2 onClick={onPlayImage} backgroundColor="lightcoral">
        Play Image
      </Button2>
    </div>
  );
}
