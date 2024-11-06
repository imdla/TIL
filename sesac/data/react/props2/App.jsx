import Button from "./Button";
import CancelBtn from "./CancelBtn";

function App() {
  return (
    <>
      <CancelBtn>취소</CancelBtn>
      <CancelBtn>삭제</CancelBtn>
      <CancelBtn>경고</CancelBtn>
      <CancelBtn>오류</CancelBtn>
      <CancelBtn>되돌리기</CancelBtn>

      <Button backgroundColor="blue" text="확인">
        <div>태그</div>
        <p>태그</p>
      </Button>

      <Button backgroundColor="blue" text="확인"></Button>
      <Button backgroundColor="red" text="취소"></Button>
      <Button backgroundColor="gray" text="보류"></Button>
    </>
  );
}

export default App;
