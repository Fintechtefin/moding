import { useNavigate } from "react-router-dom";
import { IoIosArrowForward } from "react-icons/io";
import { MenuOptionItem } from "@util/types";
import { axiosApi } from "@util/commons";

interface Props extends MenuOptionItem {}

const OptionItem = ({ name, type, url, disable }: Props) => {
  const navigate = useNavigate();

  const logout = async () => {
    try {
      const res = await axiosApi().delete("users/user/logout");
      console.log(res);
    } catch (err) {
      console.log(err);
    }
  };

  return disable ? (
    <div className="flex justify-between cursor-not-allowed">
      <div>{name}</div>
      <div className="flex items-center px-[1vh] text-[2vh] border-red-600 border text-red-600 rounded">
        추후 공개
      </div>
    </div>
  ) : (
    <div
      className="flex justify-between cursor-pointer"
      onClick={() => (type === "navigate" ? navigate(url) : logout())}
    >
      <div>{name}</div>
      <div className="flex items-center text-[2.5vh]">
        <IoIosArrowForward />
      </div>
    </div>
  );
};

export default OptionItem;
