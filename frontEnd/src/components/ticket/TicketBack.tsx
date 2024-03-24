import ticket from "@assets/images/ticket.png";

const TicketBack = () => {
  return (
    <div className="card-face card-back">
      <img className=" w-[100%] h-[77vh]" src={ticket} />
      <div className="absolute left-1/2 transform -translate-x-1/2 -translate-y-1/2 top-1/2 w-[90%] h-[80%] z-10 border-solid border-[2px] border-red-600 text-red-600">
        <div className="absolute w-[100%] flex justify-between top-[-2.5vh] text-[1.5vh] font-bold">
          <div>예약번호:</div>
          <div>전체관람가</div>
        </div>
        <div className="dash-line w-[100%] h-[10vh] p-[0.5vh] flex flex-col">
          <div className="text-[2vh] font-bold">Title</div>
          <div className="flex items-center justify-center flex-1 text-[2.5vh] font-bold">
            엘리멘탈
          </div>
        </div>
        <div className="flex h-[10vh]">
          <div
            className="dash-line flex-1 p-[0.5vh] flex flex-col"
            style={{ borderRight: "2px dashed red" }}
          >
            <div className="text-[2vh] font-bold">Date</div>
            <div className="flex items-center justify-center flex-1 text-[2.5vh] font-bold">
              4/6(토)
            </div>
          </div>
          <div className="dash-line flex-1 p-[0.5vh] flex flex-col">
            <div className="text-[2vh] font-bold">Time</div>
            <div className="flex items-center justify-center flex-1 text-[2.5vh] font-bold">
              12:02
            </div>
          </div>
        </div>
        <div className="dash-line w-[100%] h-[10vh] p-[0.5vh] flex flex-col">
          <div className="text-[2vh] font-bold">Place</div>
          <div className="flex items-center justify-center flex-1 text-[2.5vh] font-bold">
            CGV 건대입구 4층 4관
          </div>
        </div>
        <div className="dash-line w-[100%] h-[10vh] p-[0.5vh] flex flex-col">
          <div className="text-[2vh] font-bold">Seat</div>
          <div className="flex items-center justify-center flex-1 text-[2.5vh] font-bold">
            F12
          </div>
        </div>
        <div className="w-[100%] h-[10vh] p-[0.5vh] flex flex-col">
          <div className="text-[2vh] font-bold">QR</div>
          <div className="flex items-center justify-center flex-1 text-[2.5vh] font-bold">
            엘리멘탈
          </div>
        </div>
      </div>
    </div>
  );
};

export default TicketBack;
