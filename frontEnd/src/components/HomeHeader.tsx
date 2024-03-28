import { IoTicketOutline } from "react-icons/io5";
import { LuBellRing } from "react-icons/lu";
import { useNavigate } from "react-router-dom";
import HomeModal from "./HomeModal";
import { useState } from "react";

const TopNavbar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const navigate = useNavigate();

  const handleClick = () => {
    setIsOpen(true);
    // navigate("user/ticket")
  };

  const isClose = () => setIsOpen(false);

  return (
    <div className="h-[7vh] flex gap-4 justify-end items-center px-[3vh] text-[3vh]">
      <IoTicketOutline className="cursor-pointer" onClick={handleClick} />
      <LuBellRing
        className="cursor-pointer"
        onClick={() => navigate("user/notification")}
      />
      {isOpen && <HomeModal isClose={isClose} />}
    </div>
  );
};

export default TopNavbar;
