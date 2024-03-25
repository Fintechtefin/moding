import { useEffect, useState } from "react";
import "@/assets/styles/movieDetail/HopeSurvey.scss";
import map from "@assets/images/map.webp";
import redCheck from "@/assets/images/redcheck.png";
import yellowCheck from "@/assets/images/yellowcheck.png";
import greenCheck from "@/assets/images/greencheck.png";
import blueCheck from "@/assets/images/bluecheck.png";

interface Props {
  modalDown: (state: boolean) => void;
}

const HopeSurvey = ({ modalDown }: Props) => {
  const [checkArea, setCheckArea] = useState(0);
  const [checkTime, setCheckTime] = useState(0);

  useEffect(() => {
    document.body.setAttribute("style", "overflow: hidden");
    return () => document.body.setAttribute("style", "overflow: auto");
  }, []);

  const surveyArea =
    "flex flex-col justify-between items-center w-[85%] h-[70vh] shadow-[0_0_15px_1px_red] rounded-[2vh] border-solid border-red-700 bg-black";

  return (
    <div className="survey-area absolute top-0 left-0 flex justify-center w-[100%] h-[100%] mt-[10vh] bg-black bg-opacity-50">
      <div className={surveyArea}>
        <div className="p-4">원하는 상영관 위치를 골라주세요</div>
        <div className="relative w-[90%] h-[30vh] p-3">
          <img
            className="absolute w-[100%] h-[100%] top-0 left-0"
            src={
              checkArea == 1
                ? redCheck
                : checkArea == 2
                ? yellowCheck
                : checkArea == 3
                ? greenCheck
                : checkArea == 4
                ? blueCheck
                : map
            }
            alt=""
          />
          <svg
            className="absolute w-[100%] h-[100%] top-0 left-0 opacity-0"
            viewBox="0 0 600 470"
          >
            <polygon
              points="240,20 141,207 145,237 200,279 211,280 211,289 248,292 253,301 260,308 284,336 305,332 311,336 343,325 367,298 353,278 377,250 369,220 352,222 343,196 317,194 324,175 301,70"
              fill="red"
              onClick={() => setCheckArea(1)}
            />
            <polygon
              points="330,-60 303,90 316,112 309,123 324,177 321,194 345,196 353,220 371,219 378,249 355,279 369,293 397,290 430,313 479,311 504,256 513,-30"
              fill="yellow"
              onClick={() => setCheckArea(2)}
            />
            <polygon
              points="60,120 141,218 142,235 200,281 243,295 284,336 312,336 310,349 323,353 318,378 330,440 350,475 170,600 160,450 96,450 0,250"
              fill="green"
              onClick={() => setCheckArea(3)}
            />
            <polygon
              points="334,441 392,550 474,500 554,450 593,320 600,190 510,220 479,313 440,321 386,291 310,342 330,356 317,374"
              fill="blue"
              onClick={() => setCheckArea(4)}
            />
          </svg>
        </div>
        <div>원하는 상영 시간을 골라주세요(시작시간 기준)</div>
        <div className="grid grid-cols-2 gap-[2vh]">
          <div
            className={`text-center p-4 ${checkTime == 1 ? "choose" : ""}`}
            onClick={() => setCheckTime(1)}
          >
            9:00~12:00
          </div>
          <div
            className={`text-center p-4 ${checkTime == 2 ? "choose" : ""}`}
            onClick={() => setCheckTime(2)}
          >
            12:00~15:00
          </div>
          <div
            className={`text-center p-4 ${checkTime == 3 ? "choose" : ""}`}
            onClick={() => setCheckTime(3)}
          >
            15:00~18:00
          </div>
          <div
            className={`text-center p-4 ${checkTime == 4 ? "choose" : ""}`}
            onClick={() => setCheckTime(4)}
          >
            18:00~21:00
          </div>
        </div>
        <div
          className="w-[100%] text-center survey-submit px-auto py-4 border-red-700"
          onClick={() => modalDown(false)}
        >
          확인
        </div>
      </div>
    </div>
  );
};

export default HopeSurvey;
