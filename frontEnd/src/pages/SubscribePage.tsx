import NavHeader from "@components/NavHeader";
import { ChangeEvent, useState } from "react";
import MovieLike from "@components/subscribe/MovieLike";
import FundingRequest from "@components/subscribe/FundingRequest";
import SubscribeType from "@components/subscribe/SubscribeType";

const SubscribePage = () => {
  const [check, setCheck] = useState("like");

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setCheck(e.target.value);
  };

  return (
    <div className="flex flex-col subscribe-container">
      <NavHeader leftWord="관심 있는 소식만 모았어요" />
      <div className="flex flex-col gap-[2vh]">
        <SubscribeType check={check} handleChange={handleChange} />
        {check === "like" ? <MovieLike /> : <FundingRequest />}
      </div>
    </div>
  );
};

export default SubscribePage;
