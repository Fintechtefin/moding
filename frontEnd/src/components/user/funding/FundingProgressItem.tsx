import { useState } from "react";
import FundingCancelModal from "./FundingCancelModal";

interface Props {
  item: {
    id: number;
    title: string;
    url: string;
    per: number;
    date: string;
  };
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

  return (
    <div className="flex flex-col bg-bgGray p-[2vh] rounded-[1vh] gap-[2vh] shadow-test">
      <div className="w-[100%] h-[13vh] flex gap-[2vh]">
        <img
          className="w-[9vh] h-[13vh] object-cover rounded-[0.5vh] brightness-[90%]"
          src={item.url}
          alt=""
          loading="lazy"
        />
        <div className=" flex flex-col justify-between relative w-[100%]">
          <div className="flex items-center justify-between ">
            <div className="text-[2.5vh] font-bold sm:w-[200px] w-[40vw] overflow-hidden text-ellipsis whitespace-nowrap ">
              {item.title}
            </div>
            <div className=" text-[2vh] font-bold text-textGray">{`D-${aaa(
              item.date
            )}`}</div>
          </div>
          <div>
            <div className="text-[1.5vh] py-[1vh] font-bold">
              {`${item.per}% 진행중`}
            </div>
            <div className="w-[100%] h-[1.5vh] rounded-[1vh] parent">
              <div
                style={{ width: `${item.per}%` }}
                className={`bg-[#fffbb1] rounded-[1vh] h-[100%] brightness-100`}
              ></div>
            </div>
          </div>
        </div>
      </div>
      <button
        className="p-[1vh] border border-textGray rounded-[1vh] text-[2vh]"
        onClick={handleClickTrue}
      >
        펀딩취소
      </button>
      {isOpen && (
        <FundingCancelModal id={item.id} handleClickFalse={handleClickFalse} />
      )}
    </div>
  );
};

export default FundingProgressItem;
