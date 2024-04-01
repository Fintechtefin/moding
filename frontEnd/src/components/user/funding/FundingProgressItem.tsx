import { useState } from "react";
import FundingCancelModal from "./FundingCancelModal";
import { ProgressMovie } from "@util/types";
import UserFundProgressBar from "@components/common/UserFundProgressBar";

interface Props {
  item: ProgressMovie;
}

const FundingProgressItem = ({ item }: Props) => {
  const [isOpen, setIsOpen] = useState(false);

  const handleClickTrue = () => setIsOpen(true);
  const handleClickFalse = () => setIsOpen(false);

  const aaa = (date: string) => {
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

  // const per = calculatePercent(item.participantCount, item.recruitedCount);

  return (
    <div className="flex flex-col bg-bgGray p-[2vh] rounded-[1vh] gap-[2vh] shadow-test">
      <div className="w-full flex gap-[2vh]">
        <img
          className="w-[9vh] h-[13vh] object-cover rounded-[0.5vh] brightness-[90%]"
          src={item.moviePoster}
          alt=""
          loading="lazy"
        />
        <div className="relative flex flex-col justify-between w-full ">
          <div className="flex items-center w-full gap-[2vh]">
            <div className="flex-1 text-[2.5vh] font-bold w-0 overflow-hidden text-ellipsis whitespace-nowrap ">
              {item.movieTitle}
            </div>
            <div className=" text-[1.5vh] text-textGray">{`D-${aaa(
              item.endAt
            )}`}</div>
          </div>
          <UserFundProgressBar
            type="progress-bar"
            attendCnt={item.participantCount}
            goalCnt={item.recruitedCount}
          />
          {/* <div>
            <div className="text-[1.5vh] py-[1vh] font-bold">
              {`${per}% 진행중`}
            </div>
            <div className="w-full h-[1.5vh] rounded-[1vh] parent">
              <div
                style={{ width: `${per}%` }}
                className={`bg-[#fffbb1] rounded-[1vh] h-full brightness-100`}
              ></div>
            </div>
          </div> */}
        </div>
      </div>
      <button
        className="p-[1vh] border border-textGray rounded-[1vh] text-[2vh]"
        onClick={handleClickTrue}
      >
        펀딩취소
      </button>
      {isOpen && (
        <FundingCancelModal
          id={item.id}
          orderUuid={item.orderUuid}
          handleClickFalse={handleClickFalse}
        />
      )}
    </div>
  );
};

export default FundingProgressItem;
