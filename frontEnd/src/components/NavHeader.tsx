import { Link } from "react-router-dom";
import { IoSettingsOutline } from "react-icons/io5";
import { IconType } from "react-icons/lib";

interface IconMapping {
  [key: string]: IconType;
}

interface NavHeaderProps {
  leftWord: string;
  rightWord?: string[];
}

const ICONS: IconMapping = {
  IoSettingsOutline,
};

const NavHeader = ({
  leftWord = "",
  rightWord = ["", "", ""],
}: NavHeaderProps) => {
  const IconComponent = ICONS[rightWord[0]];
  const jwt = localStorage.getItem("jwt");

  return (
    <div className="h-[7vh] flex justify-between items-center px-[3vh]">
      <div className="text-[2.8vh] font-bold">{leftWord}</div>
      {IconComponent && (
        <Link
          className="flex text-[3vh] text-white"
          to={jwt ? rightWord[1] : "/user/edit"}
        >
          <IconComponent />
        </Link>
      )}
    </div>
  );
};

export default NavHeader;
