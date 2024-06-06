import ticket from "@assets/images/ticket.png";
import { addMinutesToTime, formatDateWithDay } from "@util/commonFunction";
import type { ITicket } from "@util/types";
import qr from "@assets/images/QR.webp";

const TicketBack = ({ state }: { state: ITicket }) => {
  return (
    <div className="card-face card-back">
      <img className=" w-[100%] h-[77vh]" src={ticket} />
      <div className="absolute left-1/2 transform -translate-x-1/2 -translate-y-1/2 top-1/2 w-[90%] h-[80%] z-10 border-solid border-[2px] border-red-600 text-red-600">
        <div className="absolute w-[100%] flex justify-end top-[-2.5vh] text-[1.5vh] font-bold">
          {state.age}
        </div>
        <div className="dash-line w-[100%] h-[10vh] p-[0.5vh] flex flex-col">
          <div className="text-[2vh] font-bold">Title</div>
          <div className="flex items-center justify-center flex-1 text-[2.5vh] font-bold">
            {state.title}
          </div>
        </div>
        <div className="flex h-[10vh]">
          <div
            className="dash-line flex-1 p-[0.5vh] flex flex-col"
            style={{ borderRight: "2px dashed red" }}
          >
            <div className="text-[2vh] font-bold">Date</div>
            <div className="flex items-center justify-center flex-1 text-[2vh] font-bold">
              {formatDateWithDay(state.date)}
            </div>
          </div>
          <div className="dash-line flex-1 p-[0.5vh] flex flex-col">
            <div className="text-[2vh] font-bold">Time</div>
            <div className="flex items-center justify-center flex-1 text-[2vh] font-bold">
              {state.startTime}
              <div className="text-[2vh]">
                ~{addMinutesToTime(state.startTime, state.runningTime)}
              </div>
            </div>
          </div>
        </div>
        <div className="dash-line w-[100%] h-[10vh] p-[0.5vh] flex flex-col">
          <div className="text-[2vh] font-bold">Place</div>
          <div className="flex items-center justify-center flex-1 text-[2.5vh] font-bold">
            {`${state.name} ${state.number}관`}
          </div>
        </div>
        <div className="dash-line w-[100%] h-[10vh] p-[0.5vh] flex flex-col">
          <div className="text-[2vh] font-bold">Seat</div>
          <div className="flex items-center justify-center flex-1 text-[2.5vh] font-bold">
            {state.seats.join(" ")}
          </div>
        </div>
        <div className="w-[100%] h-[10vh] p-[0.5vh] flex flex-col">
          <div className="text-[2vh] font-bold">QR</div>
          <div className="flex items-center justify-center flex-1 text-[2.5vh] font-bold">
            <img src={qr} alt="" className="h-[17vh]" />
          </div>
          {/* <img src={qr} alt="" className="h-[10vh] w-[10vh]" /> */}
        </div>
      </div>
    </div>
  );
};

export default TicketBack;
