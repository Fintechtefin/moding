import { ChangeEvent, useEffect, useState } from "react";
import type { MovieFund } from "@util/types";
import { axiosApi } from "@util/commons";
import NavHeader from "@components/NavHeader";
import SubscribeType from "@components/subscribe/SubscribeType";
import Loading from "@pages/payment/Loading";
import Like from "@components/subscribe/Like";
import Request from "@components/subscribe/Request";

const SubscribePage = () => {
  const [check, setCheck] = useState<"like" | "req">("like");
  const [likeFund, setLikeFund] = useState<MovieFund[]>([]);
  const [reqFund, setReqFund] = useState<MovieFund[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setCheck(e.target.value as "like" | "req");
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [likeResponse, reqResponse] = await Promise.all([
          axiosApi().get("/fundings/movies/like"),
          axiosApi().get("/fundings/movies/request"),
        ]);
        setLikeFund(likeResponse.data);
        setReqFund(reqResponse.data.requestMovieResponseList);
      } catch (error) {
        console.error("Error fetching funds", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);

  const renderContent = () => {
    switch (check) {
      case "like":
        return <Like data={likeFund} />;
      case "req":
        return <Request data={reqFund} />;
      default:
        return null;
    }
  };

  return (
    <div className="flex flex-col">
      <NavHeader leftWord="관심 있는 소식만 모았어요" />
      <div>
        <SubscribeType check={check} handleChange={handleChange} />
        <div className="h-[88vh] relative">
          {isLoading ? (
            <Loading />
          ) : (
            <div className="none-scroll max-h-[88vh] overflow-auto p-[3vh] pb-[10vh] grid grid-cols-2 gap-[3vh]">
              {renderContent()}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default SubscribePage;
