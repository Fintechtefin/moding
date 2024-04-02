import { useEffect, useState } from "react";
import NoneNavHeader from "@components/NoneNavHeader";
import FundingCompletedMenu from "@components/user/funding/FundingCompletedMenu";
import FundingSuccess from "@components/user/funding/FundingSuccess";
import FundingFail from "@components/user/funding/FundingFail";
import axios from "axios";
import { FundingCompleted } from "@util/types";
import Loading from "@pages/payment/Loading";

const FundingCompletedPage = () => {
  const [state, setState] = useState("success");
  const [successData, setSuccessData] = useState<FundingCompleted[]>([]);
  const [failData, setFailData] = useState<FundingCompleted[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setState(e.target.value);
  };

  const removeFund = (reservationId: number) => {
    setSuccessData((prev) =>
      prev.filter((item) => item.reservationId !== reservationId)
    );
  };

  useEffect(() => {
    const getAfterModing = async () => {
      try {
        const res = await axios.get<FundingCompleted[]>(
          `${import.meta.env.VITE_BASE_URL}/api/fundings/result`,
          {
            headers: {
              Authorization: localStorage.getItem("jwt"),
            },
          }
        );

        const completedData = res.data;

        console.log(completedData);

        setSuccessData(completedData.filter((data) => data.fundingFinalResult));
        setFailData(completedData.filter((data) => !data.fundingFinalResult));
        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };

    getAfterModing();
  }, []);

  return (
    <div className="FundingCompletedPage">
      <NoneNavHeader centerText="애프터 무딩" />
      <div className="FundingCompletedPage-body ">
        <FundingCompletedMenu state={state} handleChange={handleChange} />
        {isLoading ? (
          <Loading />
        ) : (
          <div className="none-scroll h-[88vh] overflow-auto p-[3vh] flex flex-col gap-[3vh] relative">
            {state === "success" ? (
              <FundingSuccess
                successData={successData}
                removeFund={removeFund}
              />
            ) : (
              <FundingFail failData={failData} />
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default FundingCompletedPage;
