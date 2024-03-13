import { NavLink, Outlet } from "react-router-dom";
import "@/index.scss";

const Navbar = () => {
  const navbarItemClassnames =
    "flex bg-red-200 justify-center items-center w-[100%] h-[100%]";

  return (
    <div className="nav-container">
      <div className="nav-body relative min-h-[100vh] pb-[7vh]">
        <Outlet />
      </div>
      <nav className="nav-foote fixed h-[7vh] w-[100%] bottom-0 flex items-center justify-center">
        <NavLink to={"/movie/list"} className={navbarItemClassnames}>
          무딩오피스
        </NavLink>
        <NavLink to={"/fund/list"} className={navbarItemClassnames}>
          무딩중
        </NavLink>
        <NavLink to={"/"} className={navbarItemClassnames}>
          홈
        </NavLink>
        <NavLink to={"/subscribe"} className={navbarItemClassnames}>
          구독
        </NavLink>
        <NavLink to={"/user/mypage"} className={navbarItemClassnames}>
          마이무딩
        </NavLink>
      </nav>
    </div>
  );
};

export default Navbar;
