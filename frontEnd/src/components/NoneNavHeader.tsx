import { useNavigate } from "react-router-dom";
import { MdArrowBackIos } from "react-icons/md";
import { CiShare2 } from "react-icons/ci";
import { IoSettingsOutline } from "react-icons/io5";

interface Props {
  centerText?: string;
  type?: string;
  onBackButtonClick?: () => void; // 여기에 새로운 prop 타입을 추가
}

const NoneNavHeader = ({
  centerText = "",
  type = "",
  onBackButtonClick,
}: Props) => {
  const navigate = useNavigate();

  const handleBackClick = () => {
    if (onBackButtonClick) {
      onBackButtonClick();
    } else {
      navigate(-1);
    }
  };

  const iconSelect = () => {
    switch (type) {
      case "share":
        return <CiShare2 className="text-[3vh] text-white cursor-pointer" />;
      case "notification":
        return (
          <IoSettingsOutline
            className="text-[3vh] text-white cursor-pointer"
            onClick={() => navigate("setting")}
          />
        );
      default:
        return "";
    }
  };

  return (
    <div className="h-[7vh] px-[3vh] flex items-center justify-between">
      <MdArrowBackIos
        className="text-[3vh] cursor-pointer"
        onClick={handleBackClick}
      />
      <div className="text-[3vh] font-bold">{centerText}</div>
      <div className="w-[3vh] h-[3vh] flex items-center justify-center">
        {iconSelect()}
      </div>
    </div>
  );
};

export default NoneNavHeader;
