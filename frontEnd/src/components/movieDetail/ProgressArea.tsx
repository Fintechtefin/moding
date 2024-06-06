import { useRef, useEffect, useState } from "react";
import "@/assets/styles/movieDetail/ProgressArea.scss";

interface Props {
  crowd: number;
  joinCnt: number;
  height: string;
  size: string;
}

const ProgressArea = ({ crowd, joinCnt, height, size }: Props) => {
  const [percent, setPercent] = useState(0);
  const [gage, setGage] = useState(0);

  console.log(gage);

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
          <div className="absolute top-[-6vh] right-1 text-4xl">{percent}%</div>
        </>
      )}
      <div
        className={`progressbar relative w-[100%] h-[${height}]`}
        ref={divRef}
      >
        <div
          className={`bar absolute top-[50%] trnaslate-y-[-50%] w-[100px] h-[100%]`}
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
