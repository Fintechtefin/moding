import { useEffect, useState } from "react";
import "@/assets/styles/modingList/ModingListPage.scss";
import ModingList from "@components/modingList/ModingList";

const ModingListPage = () => {
  const [modingCategory, setModingCategory] = useState("progress");

  const [modingBack, setModingBack] = useState("");

  const getModingBack = (url: string) => {
    setModingBack(url);
  };

  useEffect(() => {}, [getModingBack]);

  const fundingTitle = "flex flex-col items-center text-[2vh] basis-1/2 p-1";

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
        <div className={`${fundingTitle} border-red-700 ${modingCategory == "progress" ? "select" : ""}`} onClick={() => setModingCategory("progress")}>
          <div>무딩중</div>
          <div>TOP10</div>
        </div>
        <div className={`${fundingTitle} border-red-700 ${modingCategory == "request" ? "select" : ""}`} onClick={() => setModingCategory("request")}>
          <div>무딩 요청</div>
          <div>TOP10</div>
        </div>
      </div>
      <ModingList status={modingCategory} getModingBack={getModingBack} />
    </div>
  );
};

export default ModingListPage;
