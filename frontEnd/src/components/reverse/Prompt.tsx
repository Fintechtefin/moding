interface Props {
  onClose: () => void;
  onConfirm: () => void;
}

const Prompt = ({ onClose, onConfirm }: Props) => {
  return (
    <div className="absolute w-[100%] h-[100vh] flex justify-center items-center bg-black bg-opacity-70">
      <div className="b bg-bgGray rounded-[1vh] w-[40vh] h-[20vh] p-[3vh] flex flex-col justify-between">
        <div className="text-[2vh]">
          <p>선택한 좌석 정보가 취소됩니다.</p>
          <p>계속 하시겠습니까?</p>
        </div>
        <div className="flex justify-end gap-[5vh]">
          <button
            type="button"
            onClick={onClose}
            className="text-red-600 bg-transparent border-none cursor-pointer"
          >
            취소
          </button>
          <button
            type="submit"
            onClick={onConfirm}
            className="text-red-600 bg-transparent border-none cursor-pointer"
          >
            확인
          </button>
        </div>
      </div>
    </div>
  );
};

export default Prompt;
