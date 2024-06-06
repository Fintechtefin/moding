import { calculatePercent } from "@util/commonFunction";
import "@assets/styles/FundingCompletedPage.scss";

interface Props {
  type: "success-bar" | "fail-bar" | "progress-bar";
  attendCnt: number;
  goalCnt: number;
}

const PROGRESS_BAR_STYLES = {
  "success-bar": { content: "펀딩성공", className: "bg-[#2CD9AA]" },
  "fail-bar": { content: "펀딩실패", className: "bg-[#c00202]" },
  "progress-bar": { content: "진행중", className: "bg-[#fffbb1]" },
};

const UserFundProgressBar = ({ type, attendCnt, goalCnt }: Props) => {
  const per = calculatePercent(attendCnt, goalCnt);

  const { content, className } = PROGRESS_BAR_STYLES[type];

  return (
    <div>
      <div className="text-[1.5vh] py-[1vh] font-bold">
        {`${per}% ${content}`}
      </div>
      <div className={`w-full h-[2vh] relative ${type}`}>
        <div
          style={{
            width: `${per}%`,
          }}
          className={`rounded-full h-full brightness-100 ${className}`}
        ></div>
      </div>
    </div>
  );
};

export default UserFundProgressBar;
