import MainContent from "./MainContent";
import Sidebar from "./Sidebar";

export default function Section() {
  return (
    <div className="section">
      <MainContent></MainContent>
      <Sidebar></Sidebar>
    </div>
  );
}
