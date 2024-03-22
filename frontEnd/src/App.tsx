import { lazy, useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import MainLayout from "@components/MainLayout";
import "@/firebase-messaging-sw";
import "@/App.scss";

const ModingOffice = lazy(() => import("@pages/ModingOffice"));
const MovieCategory = lazy(() => import("@pages/MovieCategory"));
const MovieDetail = lazy(() => import("@pages/MovieDetail"));
const FundingListPage = lazy(() => import("@pages/FundingListPage"));
const HomePage = lazy(() => import("@pages/HomePage"));
const SubscribePage = lazy(() => import("@pages/SubscribePage"));
const MyPage = lazy(() => import("@pages/myPage/MyPage"));
const LoginPage = lazy(() => import("@pages/LoginPage"));
const AlarmSet = lazy(() => import("@pages/myPage/AlarmSet"));
const ProfileEdit = lazy(() => import("@pages/myPage/ProfileEdit"));
const ReservePage = lazy(() => import("@pages/ReservePage"));
const AuthPage = lazy(() => import("@pages/AuthPage"));
const PaymentPage = lazy(() => import("@pages/payment/PaymentPage"));
const PaymentSuccessPage = lazy(
  () => import("@pages/payment/PaymentSuccessPage")
);
const PaymentFailPage = lazy(() => import("@pages/payment/PaymentFailPage"));

function App() {
  // useEffect(() => {
  //   if ("serviceWorker" in navigator) {
  //     navigator.serviceWorker
  //       .register("../public/firebase-messaging-sw.js")
  //       .then((registration) => {
  //         console.log("Service Worker 등록 성공:", registration);
  //       })
  //       .catch((err) => {
  //         console.log("Service Worker 등록 실패:", err);
  //       });
  //   }
  // }, []);

  return (
    <Routes>
      <Route path="/" element={<MainLayout type="nav" />}>
        <Route index element={<HomePage />} />

        <Route path="movie">
          <Route index element={<ModingOffice />} />
          <Route path="list" element={<MovieCategory />} />
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
        <Route path="fund">
          <Route path="reserve" element={<ReservePage />} />
          <Route path="payment">
            <Route index element={<PaymentPage />} />
            <Route path="success" element={<PaymentSuccessPage />} />
            <Route path="fail" element={<PaymentFailPage />} />
          </Route>
          <Route path="list">
            <Route path=":id" element={<MovieDetail />} />
          </Route>
        </Route>
      </Route>
    </Routes>
  );
}

export default App;
