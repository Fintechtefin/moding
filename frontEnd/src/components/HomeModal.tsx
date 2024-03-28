import { useNavigate } from "react-router";

interface Props {
  isClose: () => void;
}

const HomeModal = ({ isClose }: Props) => {
  const navigate = useNavigate();

  return (
    <div className="absolute inset-0 z-20 flex items-center justify-center bg-black bg-opacity-80">
      <div className=" bg-bgGray p-[3vh] flex flex-col gap-[3vh] rounded-[1vh] text-[2vh] shadow-test">
        <div className="">
          <div>예매하신 모바일티켓이 없습니다.</div>
          <div>지금 무딩중 페이지로 이동하시겠습니까?</div>
        </div>
        <div className="flex gap-[2vh] font-bold">
          <button
            onClick={isClose}
            className="flex-1 p-[1.5vh] rounded-[1vh] bg-white text-black shadow-bgwhite"
          >
            취소
          </button>
          <button
            onClick={() => navigate("/fund/list")}
            className="flex-1 bg-red-600 p-[1.5vh] rounded-[1vh] shadow-bgRed"
          >
            확인
          </button>
        </div>
      </div>
    </div>
  );
};

export default HomeModal;
