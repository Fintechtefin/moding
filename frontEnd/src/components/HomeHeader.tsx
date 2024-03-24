import { IoTicketOutline } from "react-icons/io5";
import { LuBellRing } from "react-icons/lu";
import { useNavigate } from "react-router-dom";

const TopNavbar = () => {
  const navigate = useNavigate();

  return (
    <div className="h-[7vh] flex gap-4 justify-end items-center px-[3vh] text-[3vh]">
      <IoTicketOutline />
      <LuBellRing
        className="cursor-pointer"
        onClick={() => navigate("user/notification")}
      />
    </div>
  );
};

export default TopNavbar;
