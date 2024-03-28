import { getDate } from "@util/commonFunction";

interface Props {
  item: {
    id: number;
    title: string;
    url: string;
    per: number;
    date: string;
  };
}

const FundingSuccessItem = ({ item }: Props) => {
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
            <div className=" text-[2vh] font-bold text-textGray">{`${getDate(
              item.date
            )}`}</div>
          </div>
          <div>
            <div className="text-[1.5vh] py-[1vh] font-bold">
              {`${item.per}% 펀딩성공`}
            </div>
            <div className="w-[100%] h-[1.5vh] rounded-[1vh] success">
              <div
                style={{ width: `${item.per}%` }}
                className={`bg-[#2CD9AA] rounded-[1vh] h-[100%] brightness-[70%]`}
              ></div>
            </div>
          </div>
        </div>
      </div>
      <button className="p-[1vh] border border-textGray rounded-[1vh] text-[2vh]">
        티켓확인
      </button>
    </div>
  );
};

export default FundingSuccessItem;
