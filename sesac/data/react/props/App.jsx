import Welcome from "./Welcome";
import Card from "./Card";
import Button from "./Button";

function App() {
  return (
    <>
      <Welcome></Welcome>
      <Card
        size={{
          width: "100px",
          height: "100px",
        }}
        title={"제목"}
        content={"내용"}
      ></Card>
      <Card
        size={{
          width: "100px",
          height: "200px",
        }}
        title={"제목"}
        content={"내용"}
      ></Card>
      <Button backgroundColor={"blue"} name={"확인"}></Button>
      <Button backgroundColor={"red"} name={"취소"}></Button>
      <Button backgroundColor={"grey"} name={"보류"}></Button>
      <Button backgroundColor={"pink"} name={"1억년`"}></Button>
    </>
  );
}

export default App;
