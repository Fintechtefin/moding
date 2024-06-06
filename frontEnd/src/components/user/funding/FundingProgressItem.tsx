import { useState } from "react";
import { useNavigate } from "react-router";
import FundingCancelModal from "./FundingCancelModal";
import UserFundProgressBar from "@components/common/UserFundProgressBar";
import type { ProgressMovie } from "@util/types";

interface Props {
  item: ProgressMovie;
  removeFund: (id: number) => void;
}

const FundingProgressItem = ({ item, removeFund }: Props) => {
  const [isOpen, setIsOpen] = useState(false);
  const navigate = useNavigate();

  const moveMovieDetail = () =>
    navigate(`/fund/list/${item.movieId}`, { state: { type: "list" } });

  const calculateDays = (date: string) => {
    const targetDate = new Date(date);
    const today = new Date();
    targetDate.setHours(0, 0, 0, 0);
    today.setHours(0, 0, 0, 0);

    const differenceInMilliseconds = targetDate.getTime() - today.getTime();
    const differenceInDays = Math.round(
      differenceInMilliseconds / (1000 * 60 * 60 * 24)
    );

    return differenceInDays;
  };

  return (
    <div className="flex flex-col bg-bgGray p-[2vh] rounded-lg gap-[2vh] shadow-test">
      <div className="w-full flex gap-[2vh]">
        <img
          className="w-[9vh] h-[13vh] object-cover rounded-md"
          src={item.moviePoster}
          alt=""
          loading="lazy"
          onClick={moveMovieDetail}
        />
        <div className="relative flex flex-col justify-between w-full ">
          <div className="flex items-center w-full gap-[2vh]">
            <div
              className="flex-1 text-[2.5vh] font-bold w-0 overflow-hidden text-ellipsis whitespace-nowrap "
              onClick={moveMovieDetail}
            >
              {item.movieTitle}
            </div>
            <div className=" text-[1.5vh] text-textGray">{`D-${calculateDays(
              item.endAt
            )}`}</div>
          </div>
          <UserFundProgressBar
            type="progress-bar"
            attendCnt={item.participantCount}
            goalCnt={item.recruitedCount}
          />
        </div>
      </div>
      <button
        className="p-[1vh] border border-textGray rounded-[1vh] text-[2vh]"
        onClick={() => setIsOpen(true)}
      >
        펀딩취소
      </button>
      {isOpen && (
        <FundingCancelModal
          id={item.id}
          orderUuid={item.orderUuid}
          handleClickFalse={() => setIsOpen(false)}
          removeFund={removeFund}
        />
      )}
    </div>
  );
};

export default FundingProgressItem;
