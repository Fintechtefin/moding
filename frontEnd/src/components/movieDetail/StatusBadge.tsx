import { useState, useEffect } from "react";

type props = {
  status: string;
  textSize: string;
};

const StatusBadge = ({ status, textSize }: props) => {
  const [badgeOn, setBadgeOn] = useState(true);
  const [state, setState] = useState("무딩중");

  useEffect(() => {
    setBadgeOn(true);
    if (status === "무딩 준비 중") {
      setBadgeOn(false);
    } else if (status === "무딩예정") {
      setState("무딩예정");
    } else if (status === "무딩종료") {
      setState("무딩종료");
    }
  }, [status]);

  return (
    <>
      {badgeOn && (
        <div className="text-white absolute z-[1] flex justify-end w-[100%] p-[1vh]">
          <div>
            <div
              className={`${
                status == "무딩예정"
                  ? "bg-[#b4bd50]"
                  : status == "무딩종료"
                  ? "bg-[#02c031]"
                  : "bg-[#C00202]"
              }  w-[100%] h-[100%] blur-sm rounded-[25%] `}
            ></div>
            <div
              className={`text-[${textSize}] relative top-[-100%] right-0 my-[0.5vh] mx-[1vh]`}
            >
              {state}
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default StatusBadge;
