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
      <div>
        <SubscribeType check={check} handleChange={handleChange} />
        <div className="none-scroll max-h-[88vh] overflow-auto p-[3vh] pb-[10vh] grid grid-cols-2 gap-[3vh]">
          {check === "like" ? <MovieLike /> : <FundingRequest />}
        </div>
      </div>
    </div>
  );
};

export default SubscribePage;
