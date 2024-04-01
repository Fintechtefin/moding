import UserFundProgressBar from "@components/common/UserFundProgressBar";
import { formatDateWithDay1 } from "@util/commonFunction";
import { FundingCompleted } from "@util/types";

interface Props {
  item: FundingCompleted;
}

const FundingFailItem = ({ item }: Props) => {
  return (
    <div className="flex flex-col bg-bgGray p-[2vh] rounded-[1vh] gap-[2vh] shadow-test">
      <div className="w-full flex gap-[2vh]">
        <img
          className="w-[9vh] h-[13vh] object-cover rounded-[0.5vh] brightness-[90%]"
          src={item.poster}
          alt=""
          loading="lazy"
        />
        <div className="relative flex flex-col justify-between w-full ">
          <div className="flex items-center w-full gap-[3vh] ">
            <div className="flex-1 text-[2.5vh] font-bold w-0 overflow-hidden text-ellipsis whitespace-nowrap ">
              {item.title}
            </div>
            <div className=" text-[1.5vh] text-textGray">{`${formatDateWithDay1(
              item.date
            )}`}</div>
          </div>
          <UserFundProgressBar
            type="fail-bar"
            attendCnt={item.attendCnt}
            goalCnt={item.goalCnt}
          />
        </div>
      </div>
    </div>
  );
};

export default FundingFailItem;
