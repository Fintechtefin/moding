import { useState } from "react";
import "@/assets/styles/modingList/ModingListPage.scss";
import ModingList from "@components/modingList/ModingList";

const ModingListPage = () => {
  const [modingCategory, setModingCategory] = useState(0);

  const [modingBack, setModingBack] = useState("");
  const getModingBack = (url: string) => {
    setModingBack(url);
  };

  const fundingTitle = "flex flex-col items-center text-[1.5vh] basis-1/3 p-3";

  return (
    <div
      className="fundinglist-container"
      style={{
        backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.9)), url(${modingBack})`,
        backgroundSize: "auto 800px, contain",
        backgroundRepeat: "no-repeat",
      }}
    >
      <div className="text-center text-3xl pt-3">MODING</div>
      <div className="flex justify-around mt-2">
        <div className={`${fundingTitle} border-red-700 ${modingCategory == 0 ? "select" : ""}`} onClick={() => setModingCategory(0)}>
          <div>무딩중</div>
          <div>TOP10</div>
        </div>
        <div className={`${fundingTitle} border-red-700 ${modingCategory == 1 ? "select" : ""}`} onClick={() => setModingCategory(1)}>
          <div>무딩 요청</div>
          <div>TOP10</div>
        </div>
        <div className={`${fundingTitle} border-red-700 ${modingCategory == 2 ? "select" : ""}`} onClick={() => setModingCategory(2)}>
          <div>무딩 예정중</div>
          <div>TOP10</div>
        </div>
      </div>
      <ModingList status={modingCategory} getModingBack={getModingBack} />
    </div>
  );
};

export default ModingListPage;
