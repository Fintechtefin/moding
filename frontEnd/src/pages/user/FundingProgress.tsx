import { useEffect, useState } from "react";
import { axiosApi } from "@util/commons";
import NoneNavHeader from "@components/NoneNavHeader";
import FundingProgressItem from "@components/user/funding/FundingProgressItem";
import NoneData from "@components/common/NoneData";
import Loading from "@pages/payment/Loading";
import type { ProgressMovie } from "@util/types";
import "@assets/styles/FundingProgress.scss";

const FundingProgress = () => {
  const [data, setData] = useState<ProgressMovie[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  const removeFund = (id: number) =>
    setData((prev) => prev.filter((item) => item.id !== id));

  useEffect(() => {
    const fetchFund = async () => {
      try {
        const res = await axiosApi().get("/fundings/participation");
        setData(res.data.joinFundingResponseList);
      } catch (err) {
        console.log(err);
      } finally {
        setIsLoading(false);
      }
    };

    fetchFund();
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
              return (
                <FundingProgressItem
                  key={item.id}
                  item={item}
                  removeFund={removeFund}
                />
              );
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
