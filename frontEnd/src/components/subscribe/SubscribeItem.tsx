import { Subscribe } from "@util/types";
import { FaHeart } from "react-icons/fa";

interface Props extends Subscribe {
  handleClick: (id: number) => void;
  isType: boolean;
}

const SubscribeItem = ({
  id,
  name,
  state,
  count,
  url,
  handleClick,
  isType,
}: Props) => {
  return (
    <div className="w-[100%] relative shadow-test rounded-[1.5vh]">
      <img
        className="w-[100%] h-[100%] object-cover rounded-[1.5vh] brightness-[90%]"
        src={url}
        loading="lazy"
        alt="영화포스터"
      />
      <div className="absolute inset-0 flex flex-col justify-between">
        <div className="flex justify-between items-center p-[1vh]">
          {isType ? (
            <>
              <div className="relative">
                <div className="absolute inset-0 bg-[#C00202] blur-sm"></div>
                <div className="relative text-[1.5vh] px-[1vh] py-[0.5vh]">
                  {state}
                </div>
              </div>
              <FaHeart
                className="text-[#C00202] text-[2.5vh] cursor-pointer"
                onClick={() => {
                  handleClick(id);
                }}
              />
            </>
          ) : (
            <div></div>
          )}
        </div>
        <div className="py-[1vh] px-[2vh] flex flex-col gap-[1vh] bg-black rounded-b-[1.5vh] bg-opacity-80 ">
          <div className="text-[2vh]">{name}</div>
          <div className="text-[1.5vh] text-[#C00202] font-bold ">
            {`${count}명 ${isType ? "좋아해요" : "요청했어요"}`}
          </div>
        </div>
      </div>
    </div>
  );
};

export default SubscribeItem;
