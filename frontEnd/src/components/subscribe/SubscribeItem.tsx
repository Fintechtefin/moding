import { MovieFund } from "@util/types";
import { FaHeart } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

interface Props extends MovieFund {
  handleClick: (id: number) => void;
  isType: boolean;
}

const SubscribeItem = ({
  movieId,
  title,
  status,
  likeCnt,
  poster,
  requestCnt,
  handleClick,
  isType,
}: Props) => {
  const navigate = useNavigate();

  const moveDetailMovie = () => navigate(`/fund/list/${movieId}`);

  const onHeartClick = (e: React.MouseEvent<SVGElement>) => {
    e.stopPropagation(); // 이벤트 버블링을 막습니다.
    handleClick(movieId);
  };

  return (
    <div
      className="w-full relative shadow-test rounded-[1.5vh] cursor-pointer"
      onClick={moveDetailMovie}
    >
      <img
        className="w-full h-full object-cover rounded-[1.5vh] brightness-90"
        src={poster}
        loading="lazy"
        alt="영화포스터"
      />
      <div className="absolute inset-0 flex flex-col justify-between">
        <div className="flex justify-between items-center p-[1vh]">
          {isType && (
            <>
              <div className="relative">
                <div className="absolute inset-0 bg-[#C00202] blur-sm"></div>
                <div className="relative text-[1.5vh] px-[1vh] py-[0.5vh]">
                  {status}
                </div>
              </div>
              <FaHeart
                className="text-[#C00202] text-[2.5vh] cursor-pointer"
                onClick={onHeartClick}
              />
            </>
          )}
        </div>
        <div className="py-[1vh] px-[2vh] flex flex-col gap-[1vh] bg-black rounded-b-[1.5vh] bg-opacity-80 ">
          <div className="text-[2vh]">{title}</div>
          <div className="text-[1.5vh] text-[#C00202] font-bold ">
            {isType ? `${likeCnt}명 좋아해요` : `${requestCnt}명 요청했어요`}
          </div>
        </div>
      </div>
    </div>
  );
};

export default SubscribeItem;
