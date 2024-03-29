import { NavLink } from "react-router-dom";
import popcorn from "@assets/images/popcorn.webp";
import soda from "@assets/images/soda.webp";
import navModing from "@assets/images/navmoding.webp";
import "@/index.scss";
import "@/assets/styles/Navbar.scss";
import nav1 from "@assets/images/nav1.webp";
import person from "@assets/images/person.webp";
import heart from "@assets/images/heart.webp";
import cinema from "@assets/images/cinema.png";
import coinbank from "@assets/images/coinbank.png";
import { GiTheaterCurtains } from "react-icons/gi";
import { LiaPiggyBankSolid } from "react-icons/lia";

const Navbar = () => {
  const navbarItemClassnames =
    "group relative flex flex-col justify-around items-center p-1 w-[100%] h-[100%] text-xs text-white bg-red-600 rounded-t-[20px]";

  const food = "food w-2.5 h-2/4 flex justify-center self-end translate-y-0.5";

  const modingEar = "moding-ear hidden z-[-1] absolute w-[40px] top-[-18px]";

  const navIcon = "w-[25px] h-[25px]";

  return (
    <div className="nav-container">
      <nav className="nav-footer fixed h-[7vh] w-[100%] bottom-0 flex items-center justify-center z-10">
        <NavLink to={"/movie"} className={navbarItemClassnames}>
          <img src={navModing} alt="" className={modingEar} />
          <img src={nav1} className={navIcon} />
          <div className="">무딩오피스</div>
        </NavLink>
        <div className={food}>
          <img src={popcorn} alt="" />
        </div>
        <NavLink to={"/fund/list"} className={navbarItemClassnames}>
          <img src={navModing} alt="" className={modingEar} />
          <img src={coinbank} className={navIcon} />
          <div className="text-[1.5vh]">무딩중</div>
        </NavLink>
        <div className={food}>
          <img src={soda} alt="" />
        </div>
        <NavLink to={"/"} className={navbarItemClassnames}>
          <img src={navModing} alt="" className={modingEar} />
          <img src={cinema} className={navIcon} />
          <div className="text-[1.5vh]"> 홈</div>
        </NavLink>
        <div className={food}>
          <img src={popcorn} alt="" />
        </div>
        <NavLink to={"/subscribe"} className={navbarItemClassnames}>
          <img src={navModing} alt="" className={modingEar} />
          <img src={heart} className={navIcon} />
          <div className="text-[1.5vh]">좋아요</div>
        </NavLink>
        <div className={food}>
          <img src={soda} alt="" />
        </div>
        <NavLink to={"/user/mypage"} className={navbarItemClassnames}>
          <img src={navModing} alt="" className={modingEar} />
          <img src={person} className={navIcon} />
          <div className="text-[1.5vh]">마이무딩</div>
        </NavLink>
      </nav>
    </div>
  );
};

export default Navbar;
