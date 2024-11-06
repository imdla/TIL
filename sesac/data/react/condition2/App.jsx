import Login from "./Login";
import LoginAnd from "./LoginAnd";
import Page from "./Page";
import OtherPage from "./OtherPage";
import PackingList from "./PackingList";

function App() {
  return (
    <>
      <Login isLogin={true}></Login>
      <Login isLogin={false}></Login>

      <LoginAnd isLogin={false}></LoginAnd>

      <Page userType={"admin"}></Page>
      <Page userType={"user"}></Page>

      <OtherPage userType={"admin"}></OtherPage>
      <OtherPage userType={"manager"}></OtherPage>
      <OtherPage userType={"user"}></OtherPage>

      <PackingList name={"John"}></PackingList>
    </>
  );
}

export default App;
