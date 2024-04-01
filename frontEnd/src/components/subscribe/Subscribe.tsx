import type { MovieFund } from "@util/types";
import SubscribeItem from "./SubscribeItem";
import { toastMsg } from "@util/commonFunction";
import { ToasterMsg } from "@components/Common";
import { axiosApi } from "@util/commons";
import NoneData from "@components/common/NoneData";

interface Props {
  type: "like" | "req";
  data: MovieFund[];
  setData: (value: React.SetStateAction<MovieFund[]>) => void;
}

const Subscribe = ({ type, data, setData }: Props) => {
  const isType = type === "like";

  const deleteFunding = async (id: number) => {
    try {
      const res = await axiosApi().delete(`/fundings/movies/${id}/likes`);

      console.log(res);

      setData((prev) => prev.filter((item) => item.movieId !== id));
      toastMsg(isType ? "좋아요를 취소했어요." : "요청을 취소했어요.");
    } catch (error) {
      console.log(error);
      toastMsg("나중에 다시 시도해주세요.");
    }
  };

  return (
    <>
      {data.length ? (
        data.map((item) => (
          <SubscribeItem
            key={item.movieId}
            {...item}
            handleClick={deleteFunding}
            isType={isType}
          />
        ))
      ) : (
        <NoneData
          content={isType ? "찜한 펀딩이 없습니다" : "요청한 펀딩이 없습니다."}
        />
      )}
      <ToasterMsg />
    </>
  );
};

export default Subscribe;
