import { MdArrowBackIos } from "react-icons/md";
import { useNavigate } from "react-router-dom";

const NoneNavHeader = ({ centerText = "" }) => {
  const navigate = useNavigate();

  return (
    <div className="h-[7vh] px-[3vh] flex items-center justify-between">
      <MdArrowBackIos
        className="text-[3vh] cursor-pointer"
        onClick={() => navigate(-1)}
      />
      <div className="text-[2.5vh] font-bold">{centerText}</div>
      <div className="w-[3vh] h-[3vh]"></div>
    </div>
  );
};

export default NoneNavHeader;
