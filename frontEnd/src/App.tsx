import { Suspense, lazy } from "react";
import { Route, Routes } from "react-router-dom";
import Navbar from "@components/Navbar";
import "@/App.scss";
import LoginPage from "@pages/LoginPage";
import AlarmSet from "@pages/myPage/AlarmSet";
import ProfileEdit from "@pages/myPage/ProfileEdit";

const MovieListPage = lazy(() => import("@pages/MovieListPage"));
const FundingListPage = lazy(() => import("@pages/FundingListPage"));
const HomePage = lazy(() => import("@pages/HomePage"));
const SubscribePage = lazy(() => import("@pages/SubscribePage"));
const MyPage = lazy(() => import("@pages/myPage/MyPage"));

function App() {
  return (
    <Suspense fallback={<div className="h-[100vh] bg-black">로딩중....</div>}>
      <Routes>
        <Route path="/" element={<Navbar />}>
          <Route index element={<HomePage />} />

          <Route path="movie">
            <Route path="list" element={<MovieListPage />} />
          </Route>

          <Route path="fund">
            <Route path="list" element={<FundingListPage />} />
          </Route>

          <Route path="subscribe">
            <Route index element={<SubscribePage />} />
          </Route>

          <Route path="user">
            <Route path="mypage" element={<MyPage />} />
          </Route>
        </Route>

        <Route path="/login" element={<LoginPage />} />

        <Route path="user">
          <Route path="alarmset" element={<AlarmSet />} />
          <Route path="edit" element={<ProfileEdit />} />
        </Route>
      </Routes>
    </Suspense>
  );
}

export default App;
