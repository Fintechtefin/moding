import NoticeBoxArea from "./NoticeBoxArea";
import ProgressArea from "./ProgressArea";
import { MdArrowForwardIos } from "react-icons/md";
import "@/assets/styles/movieDetail/InfoArea.scss";
import type { FundingInfo } from "@util/types/movieType";

interface Props {
  status: string;
  fundInfo: FundingInfo;
}

const InfoArea = ({ status, fundInfo }: Props) => {
  return (
    <div className="info-area">
      <div>
        {status === "무딩 예정" || status === "무딩 종료" ? (
          <NoticeBoxArea status={status} />
        ) : (
          <div className="mt-4 mb-7">
            {fundInfo && (
              <ProgressArea
                crowd={fundInfo.crowdCnt}
                joinCnt={fundInfo.peopleCnt}
                height="4vh"
                size="big"
              />
            )}
          </div>
        )}
      </div>
      {fundInfo && (
        <div className="flex flex-col gap-2 p-2">
          <div className="flex items-end gap-5">
            <div className="basis-1/4">목표인원</div>
            <div>{fundInfo.crowdCnt}명</div>
          </div>
          <div className="flex gap-5">
            <div className="basis-1/4">모집마감</div>
            {/* <div>{.startAt}</div> */}
          </div>
          <div className="flex gap-5">
            <div className="basis-1/4">상영장소</div>
            <div>{fundInfo.cinemaName}</div>
          </div>
          <div className="flex gap-5">
            <div className="basis-1/4">금액</div>
            <div>{fundInfo.price}원</div>
          </div>
        </div>
      )}
      <div className="flex justify-around items-center mt-6">
        <div className={"relative flex flex-col items-center gap-2"}>
          <div
            className={`text-[1.5vh] ${
              status === "무딩중" ? "text-red-500" : ""
            }`}
          >
            무딩중
          </div>
          <div
            className={`${status === "무딩중" ? "active" : "not-active"}`}
          ></div>
          <div
            className={`text-[1.5vh] ${
              status === "무딩중" ? "text-red-500" : ""
            }`}
          >
            3/18 ~ 3/24
          </div>
        </div>
        <div>
          <MdArrowForwardIos className="text-[4vh]" />
        </div>
        <div className="flex flex-col items-center gap-2">
          <div
            className={`text-[1.5vh] ${
              status === "예매 예정" || status === "예매 진행"
                ? "text-red-500"
                : ""
            }`}
          >
            좌석 예매일
          </div>
          <div
            className={`${
              status === "예매 예정" || status === "예매 진행"
                ? "active"
                : "not-active"
            }`}
          ></div>
          <div
            className={`text-[1.5vh] ${
              status === "예매 예정" || status === "예매 진행"
                ? "text-red-500"
                : ""
            }`}
          >
            3/25일 예정
          </div>
        </div>
        <div>
          <MdArrowForwardIos className="text-[4vh]" />
        </div>
        <div className="flex flex-col items-center gap-2">
          <div
            className={`text-[1.5vh] ${
              status === "무딩 종료" ? "text-red-500" : ""
            }`}
          >
            관람 예정일
          </div>
          <div
            className={`${status === "무딩 종료" ? "active" : "not-active"}`}
          ></div>
          <div
            className={`text-[1.5vh] ${
              status === "무딩 종료" ? "text-red-500" : ""
            }`}
          >
            {fundInfo?.movieDate}
          </div>
        </div>
      </div>
    </div>
  );
};

export default InfoArea;
