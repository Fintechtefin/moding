import { useState } from "react";
import NoneNavHeader from "@components/NoneNavHeader";
import FundingCompletedMenu from "@components/user/funding/FundingCompletedMenu";
import FundingSuccess from "@components/user/funding/FundingSuccess";
import FundingFail from "@components/user/funding/FundingFail";
import "@assets/styles/FundingCompletedPage.scss";

const FundingCompletedPage = () => {
  const [state, setState] = useState("success");

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setState(e.target.value);
  };

  return (
    <div className="FundingCompletedPage">
      <NoneNavHeader centerText="애프터 무딩" />
      <div className="FundingCompletedPage-body ">
        <FundingCompletedMenu state={state} handleChange={handleChange} />
        <div className="none-scroll h-[88vh] overflow-auto p-[3vh] flex flex-col gap-[3vh] ">
          {state === "success" ? <FundingSuccess /> : <FundingFail />}
        </div>
      </div>
    </div>
  );
};

export default FundingCompletedPage;
