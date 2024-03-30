import { useEffect, useState } from "react";
import axios from "axios";
import NoneNavHeader from "@components/NoneNavHeader";
import FundingProgressItem from "@components/user/funding/FundingProgressItem";
import "@assets/styles/FundingProgress.scss";
import { ProgressMovie } from "@util/types";

const FundingProgress = () => {
  const [data, setData] = useState<ProgressMovie[]>([]);

  useEffect(() => {
    console.log(localStorage.getItem("jwt"));

    const getFund = async () => {
      try {
        const res = await axios.get(
          `${import.meta.env.VITE_BASE_URL}/api/fundings/participation`,
          {
            headers: {
              Authorization: localStorage.getItem("jwt"),
            },
          }
        );
        console.log(res.data.joinFundingResponseList);
        setData(res.data.joinFundingResponseList);
      } catch (err) {
        console.log(err);
      }
    };

    getFund();
  }, []);

  return (
    <div className="FundingProgress">
      <NoneNavHeader centerText="무딩중" />
      <div className="none-scroll h-[93vh] overflow-auto p-[3vh] flex flex-col gap-[3vh] ">
        {data.map((item) => {
          return <FundingProgressItem key={item.id} item={item} />;
        })}
      </div>
    </div>
  );
};

export default FundingProgress;
