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
    <div className="w-[100%] h-[13vh] flex gap-[2vh]">
      <img
        className="w-[9vh] h-[13vh] object-cover rounded-[0.5vh] brightness-[90%]"
        src={item.url}
        alt=""
        loading="lazy"
      />
      <div className=" flex flex-col justify-between relative w-[100%]">
        <div className="flex justify-between items-center ">
          <div className="text-[2.5vh] font-bold w-[23vh] overflow-hidden text-ellipsis whitespace-nowrap">
            {item.title}
          </div>
          <div className=" text-[2vh] font-bold">{`D-${aaa(item.date)}`}</div>
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
  );
};

export default FundingProgressItem;
