import NoneNavHeader from "@components/NoneNavHeader";
import Ticket from "@components/ticket/Ticket";
import TicketModal from "@components/ticket/TicketModal";
import { useState } from "react";

const TicketPage = () => {
  const [open, setOpen] = useState(false);

  const handleClick = () => setOpen(true);

  return (
    <div className="TicketPage">
      <NoneNavHeader centerText="모바일 티켓" />
      <div className="h-[93vh] flex flex-col items-center justify-between p-[4vh]">
        <Ticket />
        <div className="flex justify-between w-[100%] text-[2.5vh]">
          <div className="flex gap-[1vh]">
            <div>입장알림</div>
            <div className="text-[#4EFF8A] font-bold">ON</div>
          </div>
          <div className="cursor-pointer" onClick={handleClick}>
            안내사항
          </div>
        </div>
      </div>
      {open && <TicketModal setOpen={setOpen} />}
    </div>
  );
};

export default TicketPage;
