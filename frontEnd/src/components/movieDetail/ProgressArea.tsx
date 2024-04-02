import { useRef, useEffect, useState } from "react";
import "@/assets/styles/movieDetail/ProgressArea.scss";

interface Props {
  crowd: number;
  joinCnt: number;
}

const ProgressArea = ({ crowd, joinCnt }: Props) => {
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
  }, []);

  return (
    <div className="relative mb-7">
      <div className="absolute top-[-25px] right-3">{percent}%</div>
      <div className="progressbar relative w-[100%] h-[4vh]" ref={divRef}>
        <div
          className={`bar absolute top-[50%] trnaslate-y-[-50%] w-[100px] h-[100%]`}
        >
          <span></span>
        </div>
      </div>
      <div className="absolute mt-2 right-3">
        {joinCnt}/{crowd}
      </div>
    </div>
  );
};

export default ProgressArea;
