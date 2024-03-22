import "@/assets/styles/movieDetail/MovieDetailButton.scss";
import { useEffect, useState } from "react";

interface Props {
  id: number;
  status: string;
  likeCnt: number;
  hopeCnt: number;
  alarmCnt: number;
}

const MovieDetailButton = ({ id, status, likeCnt, hopeCnt, alarmCnt }: Props) => {
  const [isDone, setIsDone] = useState(false);
  const [isLiked, setIsLiked] = useState(true);
  const [likeNowCnt, setLikeNowCnt] = useState(likeCnt);
  const [isHope, setIsHope] = useState(true);
  const [hopeNowCnt, setHopeNowCnt] = useState(hopeCnt);
  const [applyAlarm, setApplyAlarm] = useState(false);
  const [alarmNowCnt, setAlarmNowCnt] = useState(alarmCnt);

  useEffect(() => {
    if (status === "무딩 준비 중" && isHope) {
      setIsDone(true);
    } else if (status === "무딩 예정" && applyAlarm) {
      setIsDone(true);
    } else if (status === "무딩 종료") {
      setIsDone(true);
    }
  }, []);

  const postLike = async (id: number) => {
    if (isLiked) {
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
      setIsDone(!isDone);
      setIsHope(!isHope);
      setHopeNowCnt((prev) => prev - 1);
    } else {
      setIsDone(!isDone);
      setIsHope(!isHope);
      setHopeNowCnt((prev) => prev + 1);
    }
  };

  const postAlarm = async (id: number) => {
    if (applyAlarm) {
      setIsDone(!isDone);
      setApplyAlarm(!applyAlarm);
      setAlarmNowCnt((prev) => prev - 1);
    } else {
      setIsDone(!isDone);
      setApplyAlarm(!applyAlarm);
      setAlarmNowCnt((prev) => prev + 1);
    }
  };

  const buttonTextArea = "flex justify-center items-center w-[100%] h-[100%]";
  const buttonText = "text-[3vh] font-bold";
  const buttonSub = "text-[1.3vh]";

  return (
    <div className="relative overflow-hidden flex flex-col just pt-[1vh]">
      <div className="movie-detail-btn flex flex-row h-[7vh] border-red-700">
        <div className="like-area basis-1/6 bg-black border-red-700">
          <div className="placement">
            <div className={`heart ${isLiked ? "is-active" : ""}`} onClick={() => postLike(id)}></div>
          </div>
          <div className="pt-[4vh] text-center text-[1.8vh]">{likeNowCnt}</div>
        </div>
        <div className={`flex justify-center items-center basis-5/6 w-[100%] h-[100%] ${isLiked ? "is-active" : ""} ${isDone ? "bg-black" : "bg-red-700"}`}>
          {status == "무딩 준비 중" && (
            <>
              <div className={buttonTextArea} onClick={() => postHope(id)}>
                <div className="flex items-end gap-1">
                  <div className={buttonText}>{isHope ? "무딩 요청중" : "무딩 요청하기"}</div>
                  <div className={buttonSub}>{hopeNowCnt}명 신청중</div>
                </div>
              </div>
            </>
          )}
          {status == "무딩 예정" && (
            <>
              <div className={buttonTextArea} onClick={() => postAlarm(id)}>
                <div className="flex items-end gap-1">
                  <div className={buttonText}>{applyAlarm ? "알림신청완료" : "알림신청"}</div>
                  <div className={buttonSub}>({alarmNowCnt}명 신청중)</div>
                </div>
              </div>
            </>
          )}
          {status == "무딩 중" && (
            <>
              <div className={buttonTextArea}>
                <div className={buttonText}>참여하기</div>
              </div>
            </>
          )}
          {status == "예매 예정" && (
            <>
              <div className={buttonTextArea}>
                <div className={buttonText}>예매오픈</div>
              </div>
            </>
          )}
          {status == "예매 진행" && (
            <>
              <div className={buttonTextArea}>
                <div className={buttonText}>예매하기</div>
              </div>
            </>
          )}
          {status == "무딩 종료" && (
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
