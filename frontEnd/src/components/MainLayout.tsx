import { Suspense, useEffect } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import Navbar from "./Navbar";
import getIsLogin from "@util/commonFunction";

interface Props {
  type: string;
}

const MainLayout = ({ type }: Props) => {
  const navigate = useNavigate();

  useEffect(() => {
    const { pathname } = window.location;
    const isLogin = true;
    const isLoginPage = pathname.startsWith("/login");

    if (!isLogin && !isLoginPage) navigate("/login", { replace: true });
  }, []);

  return (
    <>
      <Suspense fallback={<div className="h-[100vh] bg-black" />}>
        <div className="nav-body relative min-h-[100vh]">
          <Outlet />
        </div>
      </Suspense>
      {type === "nav" && <Navbar />}
    </>
  );
};

export default MainLayout;
