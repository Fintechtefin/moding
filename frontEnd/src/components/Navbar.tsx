import { NavLink, Outlet } from "react-router-dom";
import popcorn from "@assets/images/popcorn.png";
import soda from "@assets/images/soda.png";
import navModing from "@assets/images/navmoding.png";
import "@/index.scss";
import "@/assets/styles/Navbar.scss";
import nav1 from "@assets/images/nav1.png";
import { FcLikePlaceholder } from "react-icons/fc";

const Navbar = () => {
  const navbarItemClassnames =
    "group relative flex flex-col justify-around items-center p-1 w-[100%] h-[100%] text-xs text-white bg-red-600 rounded-t-[20px]";

  const food = "food";

  const modingEar =
    "hidden group-focus:block z-[-1] absolute w-[40px] top-[-18px]";

  const navIcon = "w-[25px] h-[25px]";

  return (
    <div className="nav-container">
      <div className="nav-body relative min-h-[100vh] pb-[7vh]">
        <Outlet />
      </div>
      <nav className="nav-foote fixed h-[7vh] w-[100%] bottom-0 flex items-center justify-center">
        <NavLink to={"/movie/list"} className={navbarItemClassnames}>
          <img src={navModing} alt="" className={modingEar} />
          <img src={nav1} className={navIcon} />
          <div>무딩오피스</div>
        </NavLink>
        <div className={food}>
          <img src={popcorn} alt="" />
        </div>
        <NavLink to={"/fund/list"} className={navbarItemClassnames}>
          <img src={navModing} alt="" className={modingEar} />
          <div>무딩중</div>
        </NavLink>
        <div className={food}>
          <img src={soda} alt="" />
        </div>
        <NavLink to={"/"} className={navbarItemClassnames}>
          <img src={navModing} alt="" className={modingEar} />
          <div> 홈</div>
        </NavLink>
        <div className={food}>
          <img src={popcorn} alt="" />
        </div>
        <NavLink to={"/subscribe"} className={navbarItemClassnames}>
          <img src={navModing} alt="" className={modingEar} />
          <FcLikePlaceholder className={navIcon} />
          <div>좋아요</div>
        </NavLink>
        <div className={food}>
          <img src={soda} alt="" />
        </div>
        <NavLink to={"/user/mypage"} className={navbarItemClassnames}>
          <img src={navModing} alt="" className={modingEar} />
          <div>마이무딩</div>
        </NavLink>
      </nav>
    </div>
  );
};

export default Navbar;
