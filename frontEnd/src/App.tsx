import { lazy } from "react";
import { Route, Routes } from "react-router-dom";
import MainLayout from "@components/MainLayout";
import "@/App.scss";

const MovieListPage = lazy(() => import("@pages/MovieListPage"));
const FundingListPage = lazy(() => import("@pages/FundingListPage"));
const HomePage = lazy(() => import("@pages/HomePage"));
const SubscribePage = lazy(() => import("@pages/SubscribePage"));
const MyPage = lazy(() => import("@pages/myPage/MyPage"));
const LoginPage = lazy(() => import("@pages/LoginPage"));
const AlarmSet = lazy(() => import("@pages/myPage/AlarmSet"));
const ProfileEdit = lazy(() => import("@pages/myPage/ProfileEdit"));
const Reserve = lazy(() => import("@pages/Reserve"));
const AuthPage = lazy(() => import("@pages/AuthPage"));

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainLayout type="nav" />}>
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

      <Route path="/" element={<MainLayout type="noneNav" />}>
        <Route path="login">
          <Route index element={<LoginPage />} />
          <Route path=":social" element={<AuthPage />} />
        </Route>

        <Route path="user">
          <Route path="alarmset" element={<AlarmSet />} />
          <Route path="edit" element={<ProfileEdit />} />
        </Route>

        <Route path="fund/reserve" element={<Reserve />} />
      </Route>
    </Routes>
  );
}

export default App;
