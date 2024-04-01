import { useNavigate } from "react-router";
import ModalButton from "./common/ModalButton";

interface Props {
  isClose: () => void;
}

const HomeModal = ({ isClose }: Props) => {
  const navigate = useNavigate();

  const moveFund = () => navigate("/fund/list");

  return (
    <div className="absolute inset-0 z-20 flex items-center justify-center bg-black bg-opacity-80">
      <div className=" bg-bgGray p-[3vh] flex flex-col gap-[3vh] rounded-[1vh] text-[2vh] shadow-test">
        <div className="">
          <div>예매하신 모바일티켓이 없습니다.</div>
          <div>지금 무딩중 페이지로 이동하시겠습니까?</div>
        </div>
        <div className="flex gap-[2vh] font-bold">
          <ModalButton func={isClose} type={false} title={"취소"} />
          <ModalButton func={moveFund} type={true} title={"확인"} />
        </div>
      </div>
    </div>
  );
};

export default HomeModal;
