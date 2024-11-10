import Button from "./eventHandler/Button";
import Box from "./eventHandler/Box";
import Input from "./eventHandler/Input";
import Button2 from "./eventHandler/Button2";
import PlayButton from "./eventHandler/PlayButton";
import Toolbar from "./eventHandler/Toolbar";

function App() {
  return (
    <>
      <div>
        <Button>버튼</Button>

        <Box></Box>

        <Input></Input>
      </div>

      <div>
        <Button2 onClick={() => alert("확인 완료")} backgroundColor="blue">
          확인
        </Button2>
        <Button2 onClick={() => alert("취소 완료")} backgroundColor="red">
          취소
        </Button2>
        <Button2 onClick={() => alert("보류 완료")} backgroundColor="gray">
          보류
        </Button2>
        <Button2
          onClick={() => alert("1억원을 얻었습니다")}
          backgroundColor="pink"
        >
          1억년
        </Button2>
      </div>

      <div>
        <PlayButton movieName={"고질라"}></PlayButton>

        <Toolbar
          onPlayMovie={() => alert("Movie!")}
          onPlayImage={() => alert("Image!")}
        ></Toolbar>
      </div>
    </>
  );
}

export default App;
