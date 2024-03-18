import { Suspense } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "./Navbar";

interface Props {
  type: string;
}

const MainLayout = ({ type }: Props) => (
  <>
    <Suspense fallback={<div className="h-[100vh] bg-black" />}>
      <div className="nav-body relative min-h-[100vh] pb-[7vh]">
        <Outlet />
      </div>
    </Suspense>
    {type === "nav" && <Navbar />}
  </>
);

export default MainLayout;
