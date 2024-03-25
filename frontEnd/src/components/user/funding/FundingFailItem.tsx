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

const FundingFailItem = ({ item }: Props) => {
  return (
    <div className="w-[100%] h-[13vh] flex gap-[2vh]">
      <img
        className="w-[9vh] h-[13vh] object-cover rounded-[0.5vh] brightness-[90%]"
        src={item.url}
        alt=""
        loading="lazy"
      />
      <div className=" flex flex-col justify-between relative w-[100%]">
        <div className="flex items-center justify-between ">
          <div className="text-[2.5vh] font-bold w-[23vh] overflow-hidden text-ellipsis whitespace-nowrap">
            {item.title}
          </div>
          <div className=" text-[2vh] font-bold">{`${getDate(item.date)}`}</div>
        </div>
        <div>
          <div className="text-[1.5vh] py-[1vh] font-bold">
            {`${item.per}% 펀딩실패`}
          </div>
          <div className="w-[100%] h-[1.5vh] rounded-[1vh] fail">
            <div
              style={{ width: `${item.per}%` }}
              className={`bg-[#C00202] rounded-[1vh] h-[100%] brightness-100`}
            ></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FundingFailItem;
