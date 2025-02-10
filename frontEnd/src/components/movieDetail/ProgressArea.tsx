import { useRef, useEffect, useState } from "react";
import "@/assets/styles/movieDetail/ProgressArea.scss";

interface Props {
  crowd: number;
  joinCnt: number;
  height: string;
  size: string;
  color: string;
}

const ProgressArea = ({ crowd, joinCnt, height, size, color }: Props) => {
  const [percent, setPercent] = useState(0);
  const [gage, setGage] = useState(0);

  const divRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const per = Math.round((joinCnt / crowd) * 100);
    setPercent(per);

    const progressRef = divRef.current;
    const progressWidth = progressRef?.offsetWidth ?? 0;

    setGage(Math.round((progressWidth / 100) * per));
  }, [percent]);

  return (
    <div className="relative">
      {size == "big" && (
        <>
          <div className="absolute top-[-5vh] right-1 text-4xl back-blur">{percent}%</div>
        </>
      )}
      {size == "small" && (
        <>
          <div className="absolute top-[-2.5vh] right-1 text-1xl back-blur">{percent}%</div>
        </>
      )}
      <div className={`${color} progressbar relative w-[100%] h-[${height}]`} ref={divRef}>
        <div
          className={`${color} bar absolute top-[60%] trnaslate-y-[-50%] w-[100px] h-[100%]`}
          style={{
            width: `${gage}px`,
          }}
        >
          <span></span>
        </div>
      </div>
      {size == "big" && (
        <>
          <div className="absolute mt-1 right-2">
            {joinCnt}/{crowd}
          </div>
        </>
      )}
    </div>
  );
};

export default ProgressArea;
