import { calculatePercent } from "@util/commonFunction";
import "@assets/styles/FundingCompletedPage.scss";

interface Props {
  type: string;
  attendCnt: number;
  goalCnt: number;
}

const UserFundProgressBar = ({ type, attendCnt, goalCnt }: Props) => {
  const per = calculatePercent(attendCnt, goalCnt);

  const [confirmContent, confirmClassName] =
    type === "success-bar"
      ? ["펀딩성공", "bg-[#2CD9AA]"]
      : type === "fail-bar"
      ? ["펀딩실패", "bg-[#c00202]"]
      : ["진행중", "bg-[#fffbb1]"];

  return (
    <div>
      <div className="text-[1.5vh] py-[1vh] font-bold">
        {`${per}% ${confirmContent}`}
      </div>
      <div className={`w-full h-[2vh] relative ${type}`}>
        <div
          style={{
            width: `${per}%`,
          }}
          className={`rounded-full h-full brightness-100 ${confirmClassName}`}
        ></div>
      </div>
    </div>
  );
};

export default UserFundProgressBar;
