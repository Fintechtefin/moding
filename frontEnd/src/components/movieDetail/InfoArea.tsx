import NoticeBoxArea from "./NoticeBoxArea";
import ProgressArea from "./ProgressArea";
import { useState } from "react";
import { MdArrowForwardIos } from "react-icons/md";
import "@/assets/styles/movieDetail/InfoArea.scss";

interface Props {
  status: string;
}

interface FundingDetail {
  id: number;
  cinemaName: string;
  crowd: number;
  startAt: string;
  price: number;
  joinCnt: number;
}

const InfoArea = ({ status }: Props) => {
  const [cinemaList, setCinemaList] = useState<FundingDetail>({
    id: 1,
    cinemaName: "CGV 상무",
    crowd: 200,
    startAt: "2024.03.20",
    price: 12000,
    joinCnt: 110,
  });

  return (
    <div className="info-area">
      <div>{status === "무딩 예정" || status === "무딩 종료" ? <NoticeBoxArea status={status} /> : <ProgressArea crowd={cinemaList.crowd} joinCnt={cinemaList.joinCnt} />}</div>
      <div className="flex flex-col gap-2 p-2">
        <div className="flex items-end gap-5">
          <div className="basis-1/4">목표인원</div>
          <div>{cinemaList.crowd}명</div>
        </div>
        <div className="flex gap-5">
          <div className="basis-1/4">모집마감</div>
          <div>{cinemaList.startAt}</div>
        </div>
        <div className="flex gap-5">
          <div className="basis-1/4">상영장소</div>
          <div>{cinemaList.cinemaName}</div>
        </div>
        <div className="flex gap-5">
          <div className="basis-1/4">금액</div>
          <div>{cinemaList.price}원</div>
        </div>
      </div>
      <div className="flex justify-around items-center mt-6">
        <div className={"relative flex flex-col items-center gap-2"}>
          <div className={`text-[1.5vh] ${status === "무딩 중" ? "text-red-500" : ""}`}>무딩중</div>
          <div className={`${status === "무딩 중" ? "active" : "not-active"}`}></div>
          <div className={`text-[1.5vh] ${status === "무딩 중" ? "text-red-500" : ""}`}>3/18 ~ 3/24</div>
        </div>
        <div>
          <MdArrowForwardIos className="text-[4vh]" />
        </div>
        <div className="flex flex-col items-center gap-2">
          <div className={`text-[1.5vh] ${status === "예매 예정" || status === "예매 진행" ? "text-red-500" : ""}`}>좌석 예매일</div>
          <div className={`${status === "예매 예정" || status === "예매 진행" ? "active" : "not-active"}`}></div>
          <div className={`text-[1.5vh] ${status === "예매 예정" || status === "예매 진행" ? "text-red-500" : ""}`}>3/25일 예정</div>
        </div>
        <div>
          <MdArrowForwardIos className="text-[4vh]" />
        </div>
        <div className="flex flex-col items-center gap-2">
          <div className={`text-[1.5vh] ${status === "무딩 종료" ? "text-red-500" : ""}`}>관람 예정일</div>
          <div className={`${status === "무딩 종료" ? "active" : "not-active"}`}></div>
          <div className={`text-[1.5vh] ${status === "무딩 종료" ? "text-red-500" : ""}`}>3/30일 오후 2시</div>
        </div>
      </div>
    </div>
  );
};

export default InfoArea;
