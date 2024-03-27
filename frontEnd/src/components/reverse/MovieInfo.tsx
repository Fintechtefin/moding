import { selectSeatsLengthSelector } from "@recoil/reserveStore";
import { useRecoilValue } from "recoil";
import moviePost from "@assets/images/영화포스터.jpg";
import toast, { Toaster } from "react-hot-toast";

interface Props {
  max: number;
}

const MovieInfo = ({ max }: Props) => {
  const selectSeatsLength = useRecoilValue(selectSeatsLengthSelector);

  const handleClick = () => {
    if (selectSeatsLength < max) {
      toast("인원을 선택해주세요", { duration: 1500 });
      return;
    }
  };

  return (
    <div className="text-black bg-white w-[100%] h-[20vh] rounded-t-[3vh]">
      <div className="p-[2vh] flex gap-[3vh]">
        <div className="flex flex-col justify-between grow text-[2.5vh]">
          <div className="flex gap-[2vh]">
            <div className="font-bold">CGV 강남</div>
            <div>3.15(금) 15:00</div>
          </div>
          <div className="flex justify-between">
            <div className="font-bold">엘리멘탈</div>
            <div>{`인원 ${selectSeatsLength} / ${max}`}</div>
          </div>
        </div>
        <img
          className="h-[10vh] object-cover rounded-[0.8vh]"
          src={moviePost}
          alt="영화포스터"
        />
      </div>
      <button
        className=" w-[100%] h-[6vh] bg-red-600 shadow-bgRed text-white font-bold text-[2.5vh] "
        onClick={handleClick}
      >
        예매
      </button>
      <Toaster
        containerStyle={{
          margin: "0 auto",
        }}
        toastOptions={{
          // Define default options
          style: {
            background: "#363636",
            color: "#fff",
            fontSize: "2vh",
          },
        }}
      />
    </div>
  );
};

export default MovieInfo;
