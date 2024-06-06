import { useEffect, useState } from "react";
import { axiosApi } from "@util/commons";
import NoneNavHeader from "@components/NoneNavHeader";
import FundingCompletedMenu from "@components/user/funding/FundingCompletedMenu";
import FundingSuccess from "@components/user/funding/FundingSuccess";
import FundingFail from "@components/user/funding/FundingFail";
import Loading from "@pages/payment/Loading";
import type { FundingCompleted } from "@util/types";

const FundingCompletedPage = () => {
  const [state, setState] = useState<"success" | "fail">("success");
  const [successData, setSuccessData] = useState<FundingCompleted[]>([]);
  const [failData, setFailData] = useState<FundingCompleted[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setState(e.target.value as "success" | "fail");
  };

  useEffect(() => {
    const getAfterModing = async () => {
      try {
        const [successResponse, failureResponse] = await Promise.all([
          axiosApi().get(`/fundings/result/success`),
          axiosApi().get(`/fundings/result/failure`),
        ]);

        setSuccessData(successResponse.data);
        setFailData(failureResponse.data);
      } catch (error) {
        console.log(error);
      } finally {
        setIsLoading(false);
      }
    };

    getAfterModing();
  }, []);

  return (
    <div className="FundingCompletedPage">
      <NoneNavHeader centerText="애프터 무딩" />
      <FundingCompletedMenu state={state} handleChange={handleChange} />
      {isLoading ? (
        <Loading />
      ) : (
        <div className="none-scroll h-[88vh] overflow-auto p-[3vh] flex flex-col gap-[3vh] relative">
          {state === "success" ? (
            <FundingSuccess successData={successData} />
          ) : (
            <FundingFail failData={failData} />
          )}
        </div>
      )}
    </div>
  );
};

export default FundingCompletedPage;
