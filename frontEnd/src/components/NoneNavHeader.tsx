import { MdArrowBackIos } from "react-icons/md";
import { CiShare2 } from "react-icons/ci";
import { useNavigate } from "react-router-dom";

const NoneNavHeader = ({ centerText = "", type = "" }) => {
  const navigate = useNavigate();

  return (
    <div className="h-[7vh] pl-[3vh] pr-[2vh] flex items-center justify-between">
      <MdArrowBackIos className="text-[4vh] cursor-pointer" onClick={() => navigate(-1)} />
      <div className="text-[2.5vh] font-bold">{centerText}</div>
      <div className="w-[3vh] h-[3vh]"></div>
      {type == "share" && <CiShare2 className="text-[4vh] text-white cursor-pointer" />}
    </div>
  );
};

export default NoneNavHeader;
