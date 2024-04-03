import "@/assets/styles/movieDetail/MovieDetailButton.scss";
import { useEffect, useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { getFundingResult } from "@api/funding";

interface Props {
  id: number;
  status: string;
  like: boolean;
  likeCnt: number;
  request: boolean;
  hopeCnt: number;
  modalDown: (state: boolean) => void;
  sendFundingInfo: (type: string) => void;
  fundingId: number;
}

const MovieDetailButton = ({
  id,
  status,
  like,
  likeCnt,
  request,
  hopeCnt,
  modalDown,
  sendFundingInfo,
  fundingId,
}: Props) => {
  const [isDone, setIsDone] = useState(false);
  const [isLiked, setIsLiked] = useState(false);
  const [likeNowCnt, setLikeNowCnt] = useState(0);
  const [isHope, setIsHope] = useState(false);
  const [hopeNowCnt, setHopeNowCnt] = useState(0);
  const [applyAlarm, setApplyAlarm] = useState(false);
  const [openFundingId, setOpenFundingId] = useState(0);

  useEffect(() => {
    if (like) {
      setIsLiked(true);
    }
    if (request) {
      setIsHope(true);
    }
    if (status === "무딩 준비 중" && isHope) {
      setIsDone(true);
    } else if (status === "무딩 예정" && applyAlarm) {
      setIsDone(true);
    } else if (status === "무딩종료") {
      setIsDone(true);
    }
  }, []);

  useEffect(() => {
    setLikeNowCnt(likeCnt);
    setHopeNowCnt(hopeCnt);
  }, [likeCnt, hopeCnt]);

  const postLike = async (id: number) => {
    if (isLiked) {
      console.log(id);
      setIsLiked(false);
      setLikeNowCnt((prev) => prev - 1);
    } else {
      setIsLiked(true);
      setLikeNowCnt((prev) => prev + 1);
      // await putPostLike(postId).then((res) => {
      //   setBoardLike(true);
      //   setLikeCnt((prev) => prev + 1);
      // });
    }
  };

  const postHope = async (id: number) => {
    if (isHope) {
      console.log(id);
      return;
    } else {
      setIsDone(!isDone);
      setIsHope(!isHope);
      setHopeNowCnt((prev) => prev + 1);
    }
  };

  const postAlarm = async (id: number) => {
    if (applyAlarm) {
      console.log(id);
      setIsDone(!isDone);
      setApplyAlarm(!applyAlarm);
    } else {
      setIsDone(!isDone);
      setApplyAlarm(!applyAlarm);
    }
  };

  const { data } = useQuery<boolean, Error>({
    queryKey: ["result", openFundingId], // 쿼리 키를 지정합니다.
    queryFn: () => getFundingResult(openFundingId),
    // getNowRanking 함수를 호출합니다.
  });

  const joinFunding = () => {
    console.log(data);
    console.log(typeof data);
    setOpenFundingId(fundingId);
    if (data) {
      alert("이미 참여하셨습니다");
    } else {
      sendFundingInfo("join");
    }
  };

  const bookTicket = () => {
    sendFundingInfo("book");
  };

  const buttonTextArea = "flex justify-center items-center w-[100%] h-[100%]";
  const buttonText = "text-[3vh] font-bold";
  const buttonSub = "text-[1.3vh]";

  return (
    <div className="relative overflow-hidden flex flex-col just pt-[1vh]">
      <div className="movie-detail-btn flex flex-row h-[7vh] border-red-700">
        <div className="like-area basis-1/6 bg-black border-red-700">
          <div className={`placement`}>
            <div
              className={`heart ${isLiked ? "is-active" : ""}`}
              onClick={() => postLike(id)}
            ></div>
          </div>
          <div className="pt-[4vh] text-center text-[1.8vh]">{likeNowCnt}</div>
        </div>
        <div
          className={`flex justify-center items-center basis-5/6 w-[100%] h-[100%] ${
            isLiked ? "is-active" : ""
          } ${isDone ? "bg-black" : "bg-red-700"}`}
        >
          {status == "무딩 준비 중" && (
            <>
              <div className={buttonTextArea} onClick={() => postHope(id)}>
                <div className="flex items-end gap-1">
                  {isHope && <div className={buttonText}>무딩 요청중</div>}
                  {!isHope && (
                    <div className={buttonText} onClick={() => modalDown(true)}>
                      무딩 요청하기
                    </div>
                  )}
                  <div className={buttonSub}>{hopeNowCnt}명 신청중</div>
                </div>
              </div>
            </>
          )}
          {status == "무딩 예정" && (
            <>
              <div className={buttonTextArea} onClick={() => postAlarm(id)}>
                <div className="flex items-end gap-1">
                  <div className={buttonText}>
                    {applyAlarm ? "알림신청완료" : "알림신청"}
                  </div>
                  {/* <div className={buttonSub}>({alarmNowCnt}명 신청중)</div> */}
                </div>
              </div>
            </>
          )}
          {status == "무딩중" && (
            <>
              <div className={buttonTextArea} onClick={joinFunding}>
                <div className={buttonText}>참여하기</div>
              </div>
            </>
          )}
          {status == "예매예정" && (
            <>
              <div className={buttonTextArea}>
                <div className={buttonText}>예매오픈</div>
              </div>
            </>
          )}
          {status == "예매 진행" && (
            <>
              <div className={buttonTextArea} onClick={bookTicket}>
                <div className={buttonText}>예매하기</div>
              </div>
            </>
          )}
          {status == "무딩종료" && (
            <>
              <div className={buttonTextArea}>
                <div className={buttonText}>무딩 종료</div>
              </div>
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default MovieDetailButton;
