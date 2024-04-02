import { ChangeEvent, useEffect, useState } from "react";
import NavHeader from "@components/NavHeader";
import Subscribe from "@components/subscribe/Subscribe";
import SubscribeType from "@components/subscribe/SubscribeType";
import { MovieFund } from "@util/types";
import { axiosApi } from "@util/commons";
import Loading from "@pages/payment/Loading";

const SubscribePage = () => {
  const [check, setCheck] = useState("like");
  const [likeFund, setLikeFund] = useState<MovieFund[]>([]);
  const [reqFund, setReqFund] = useState<MovieFund[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setCheck(e.target.value);
  };

  const getFund = async (type: "like" | "req") => {
    const endpoint = `/fundings/movies/${type === "like" ? "like" : "request"}`;
    try {
      const { data } = await axiosApi().get(endpoint);
      console.log(data);

      if (type === "like") {
        setLikeFund(data);
      } else {
        setReqFund(data.requestMovieResponseList);
        setIsLoading(false);
      }
    } catch (error) {
      console.error("Error fetching funds", error);
    }
  };

  useEffect(() => {
    getFund("like");
    getFund("req");
  }, []);

  return (
    <div className="flex flex-col subscribe-container">
      <NavHeader leftWord="관심 있는 소식만 모았어요" />
      <div>
        <SubscribeType check={check} handleChange={handleChange} />
        <div className="h-[88vh] relative">
          {isLoading ? (
            <Loading />
          ) : (
            <div className="none-scroll max-h-[88vh] overflow-auto p-[3vh] pb-[10vh] grid grid-cols-2 gap-[3vh]">
              {check === "like" ? (
                <Subscribe type="like" data={likeFund} setData={setLikeFund} />
              ) : (
                <Subscribe type="req" data={reqFund} setData={setReqFund} />
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default SubscribePage;
