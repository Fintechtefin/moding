import ModalButton from "@components/common/ModalButton";

interface Props {
  onClose: () => void;
  onConfirm: () => void;
}

const Prompt = ({ onClose, onConfirm }: Props) => {
  return (
    <div className="absolute inset-0 flex justify-center items-center bg-black bg-opacity-80">
      <div className="bg-bgGray rounded-lg w-[40vh] p-[3vh] flex flex-col justify-between gap-[3vh]">
        <div className="text-[2vh]">
          <p>선택한 좌석 정보가 취소됩니다.</p>
          <p>계속 하시겠습니까?</p>
        </div>
        <div className="flex gap-[2vh] font-bold">
          <ModalButton func={onClose} type={false} title={"취소"} />
          <ModalButton func={onConfirm} type={true} title={"확인"} />
        </div>
      </div>
    </div>
  );
};

export default Prompt;
