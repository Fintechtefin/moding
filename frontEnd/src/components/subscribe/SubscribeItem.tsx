import type { MovieFund } from "@util/types";
import { FaHeart } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import "@components/subscribe/SubscribeItem.scss";

interface Props extends MovieFund {
  type: "like" | "req";
}

const SubscribeItem = ({
  movieId,
  title,
  status,
  likeCnt,
  poster,
  requestCnt,
  type,
}: Props) => {
  const navigate = useNavigate();

  const isType = type === "like";

  const moveDetailMovie = () =>
    navigate(`/fund/list/${movieId}`, { state: { type: "list" } });

  return (
    <div
      className="w-full relative shadow-test rounded-lg cursor-pointer"
      onClick={moveDetailMovie}
    >
      {isType && (
        <div className=" absolute z-10 top-[1vh] right-[1vh]">
          <div className="absolute inset-0 bg-[#C00202] blur-sm"></div>
          <div className="relative text-[1.5vh] px-[1vh] py-[0.5vh]">
            {status}
          </div>
        </div>
      )}
      <div className="bbb relative">
        <img
          className="w-full h-full object-cover rounded-t-lg "
          src={poster}
          loading="lazy"
          alt="영화포스터"
        />
      </div>
      <div className="bg-black rounded-b-lg pb-[1vh] px-[2vh] flex flex-col gap-[1vh]">
        <div className="text-[2vh] font-bold">{title}</div>
        <div className="text-[1.5vh] text-[#C00202] font-bold flex items-center gap-[1vh]">
          <FaHeart className="" />
          {isType ? `${likeCnt}명 좋아해요` : `${requestCnt}명 요청했어요`}
        </div>
      </div>
    </div>
  );
};

export default SubscribeItem;
