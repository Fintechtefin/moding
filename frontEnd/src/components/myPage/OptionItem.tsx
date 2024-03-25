import { useNavigate } from "react-router-dom";
import { MdOutlineArrowForwardIos } from "react-icons/md";

const OPTION_CONTENT_ITEM = "flex justify-between cursor-pointer";
const RIGHT_OPTION_CONTENT = "flex items-center gap-[1.5vh]";

interface Props {
  name: string;
  url: string;
}

const OptionItem = ({ name, url }: Props) => {
  const navigate = useNavigate();
  const jwt = localStorage.getItem("jwt");

  const handleClick = () => {
    navigate(jwt ? "/login" : url);
  };

  return (
    <div className={OPTION_CONTENT_ITEM} onClick={handleClick}>
      <div>{name}</div>
      <div className={RIGHT_OPTION_CONTENT}>
        <MdOutlineArrowForwardIos />
      </div>
    </div>
  );
};

export default OptionItem;
