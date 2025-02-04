import Button from "./Button";
import Container from "./Container";

function App() {
  return (
    <>
      <Container>
        <h3>Container One</h3>
        <Button backgroundColor="gray" text="보류"></Button>
      </Container>
      <Container>
        <h3>Container Two</h3>
        <Button backgroundColor="blue" text="확인"></Button>
      </Container>
      <Container>
        <h3>Container Three</h3>
        <Button backgroundColor="red" text="취소"></Button>
      </Container>
    </>
  );
}

export default App;
