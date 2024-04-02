import { useEffect, useState } from "react";
import NoneNavHeader from "@components/NoneNavHeader";
import FundingProgressItem from "@components/user/funding/FundingProgressItem";
import "@assets/styles/FundingProgress.scss";
import { ProgressMovie } from "@util/types";
import { axiosApi } from "@util/commons";
import NoneData from "@components/common/NoneData";
import Loading from "@pages/payment/Loading";

const FundingProgress = () => {
  const [data, setData] = useState<ProgressMovie[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  const api = axiosApi();

  useEffect(() => {
    const getFund = async () => {
      try {
        const { data } = await api.get("/fundings/participation");
        console.log(data.joinFundingResponseList);
        setData(data.joinFundingResponseList);
        setIsLoading(false);
      } catch (err) {
        console.log(err);
      }
    };

    getFund();
  }, []);

  return (
    <div className="flex flex-col FundingProgress">
      <NoneNavHeader centerText="무딩중" />
      {isLoading ? (
        <Loading />
      ) : (
        <div className="none-scroll h-[93vh] overflow-auto p-[3vh] flex flex-col gap-[3vh] relative">
          {data.length ? (
            data.map((item) => {
              return <FundingProgressItem key={item.id} item={item} />;
            })
          ) : (
            <NoneData content="진행 중인 펀딩이 없습니다." />
          )}
        </div>
      )}
    </div>
  );
};

export default FundingProgress;
