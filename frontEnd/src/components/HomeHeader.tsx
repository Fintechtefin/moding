import { IoTicketOutline } from "react-icons/io5";
import { LuBellRing } from "react-icons/lu";
import { useNavigate } from "react-router-dom";
import HomeModal from "./HomeModal";
import { useState } from "react";
import { axiosApi } from "@util/commons";

const TopNavbar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const navigate = useNavigate();
  const api = axiosApi();

  const handleClick = async () => {
    try {
      const { data } = await api.get("/reservations/recent");
      console.log(data);

      if (data) {
        navigate("user/ticket");
      } else {
        setIsOpen(true);
      }
    } catch (error) {
      console.error("Error fetching funds", error);
    }
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
