import moviePost from "@assets/images/영화포스터.jpg";

interface Props {
  count: number;
  max: number;
}

const MovieInfo = ({ count, max }: Props) => {
  return (
    <div className="fixed bottom-0 text-black bg-white w-[100%] h-[20vh] rounded-t-[3vh]">
      <div className="p-[2vh] flex gap-[3vh]">
        <div className="flex flex-col justify-between grow text-[2.5vh]">
          <div className="flex gap-[2vh]">
            <div className="font-bold">CGV 강남</div>
            <div>3.15(금) 15:00</div>
          </div>
          <div className="flex justify-between">
            <div className="font-bold">엘리멘탈</div>
            <div>{`인원 ${count} / ${max}`}</div>
          </div>
        </div>
        <img
          className="h-[10vh] object-cover rounded-[0.8vh]"
          src={moviePost}
          alt="영화포스터"
        />
      </div>
      <button className="fixed bottom-0 w-[100%] h-[6vh] border-none bg-[#C00202] text-white font-bold text-[2.5vh] ">
        예매
      </button>
    </div>
  );
};

export default MovieInfo;
