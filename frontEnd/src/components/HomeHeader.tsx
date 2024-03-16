import { IoTicketOutline } from "react-icons/io5";
import { LuBellRing } from "react-icons/lu";

const TopNavbar = () => {
  return (
    <div className="h-[7vh] flex gap-4 justify-end items-center px-[3vh] text-[3vh]">
      <IoTicketOutline />
      <LuBellRing />
    </div>
  );
};

export default TopNavbar;
