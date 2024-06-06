interface Props {
  setOpen: (value: React.SetStateAction<boolean>) => void;
}

const TicketModal = ({ setOpen }: Props) => {
  const handleClick = () => setOpen(false);

  return (
    <div className="absolute inset-0 flex items-center justify-center bg-black/80">
      <div className="bg-bgGray w-[43vh] h-[78vh] rounded-2xl border border-solid border-red-600 flex flex-col shadow-bgTT shadow-red-600">
        <div className="p-[1vh] flex flex-col items-center ">
          <div className="p-[3vh] text-[3vh] font-bold">상영안내</div>
          <hr className="w-[90%] border border-red-600 shadow-bgTT shadow-red-600" />
          <div className="flex flex-col items-center justify-center h-[19vh] text-[2vh]">
            <div>모바일 티켓으로 현장 발권없이</div>
            <div>바로 입장이 가능합니다.</div>
          </div>
          <hr className="w-[90%] border border-red-600 shadow-bgTT shadow-red-600" />
          <div className="flex flex-col items-center justify-center h-[19vh] text-[2vh]">
            <div>쾌적한 관람을 위해</div>
            <div>상영시간 전에 입장 부탁드립니다.</div>
          </div>
          <hr className="w-[90%] border border-red-600 shadow-bgTT shadow-red-600" />
          <div className="flex flex-col items-center justify-center h-[19vh] text-[2vh]">
            <div>입장 지연에 따른 관람 불편을 최소화하기 위해</div>
            <div>본 영화는 10분 후 상영이 시작됩니다.</div>
          </div>
        </div>
        <button
          className="w-[100%] border-none bg-red-600 text-[3vh] flex-1 rounded-b-2xl text-white cursor-pointer shadow-bgRed"
          onClick={handleClick}
        >
          확인
        </button>
      </div>
    </div>
  );
};

export default TicketModal;
