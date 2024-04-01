import { useNavigate } from "react-router-dom";
import { IoIosArrowForward } from "react-icons/io";
import { MenuOptionItem } from "@util/types";

interface Props extends MenuOptionItem {}

const OptionItem = ({ name, url }: Props) => {
  const navigate = useNavigate();

  return (
    <div
      className="flex justify-between cursor-pointer"
      onClick={() => navigate(url)}
    >
      <div>{name}</div>
      <div className="flex items-center gap-[1.5vh] text-[2.5vh]">
        <IoIosArrowForward />
      </div>
    </div>
  );
};

export default OptionItem;
