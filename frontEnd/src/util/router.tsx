import { lazy } from "react";
import { createBrowserRouter } from "react-router-dom";
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
const NotificationSet = lazy(
  () => import("@pages/notification/NotificationSet")
);
const ProfileEdit = lazy(() => import("@pages/myPage/ProfileEdit"));
const ReservePage = lazy(() => import("@pages/ReservePage"));
const AuthPage = lazy(() => import("@pages/AuthPage"));
const PaymentPage = lazy(() => import("@pages/payment/PaymentPage"));
const PaymentSuccessPage = lazy(
  () => import("@pages/payment/PaymentSuccessPage")
);
const PaymentFailPage = lazy(() => import("@pages/payment/PaymentFailPage"));
const NotFoundPage = lazy(() => import("@pages/NotFoundPage"));
const Notification = lazy(() => import("@pages/notification/Notification"));

export const router = createBrowserRouter([
  {
    path: "/",
    element: <MainLayout type="nav" />,
    children: [
      { index: true, element: <HomePage /> },
      {
        path: "movie",
        children: [
          {
            index: true,
            element: <ModingOffice />,
          },
          { path: "list", element: <MovieCategory /> },
        ],
      },
      {
        path: "fund",
        children: [{ path: "list", element: <FundingListPage /> }],
      },
      {
        path: "subscribe",
        children: [{ index: true, element: <SubscribePage /> }],
      },
      { path: "user", children: [{ path: "mypage", element: <MyPage /> }] },
    ],
  },
  {
    path: "/",
    element: <MainLayout type="noneNav" />,
    children: [
      {
        path: "login",
        children: [
          { index: true, element: <LoginPage /> },
          { path: ":social", element: <AuthPage /> },
        ],
      },
      {
        path: "user",
        children: [
          {
            path: "notification",
            children: [
              { index: true, element: <Notification /> },
              { path: "setting", element: <NotificationSet /> },
            ],
          },
          { path: "edit", element: <ProfileEdit /> },
        ],
      },
      {
        path: "fund",
        children: [
          { path: "reserve", element: <ReservePage /> },
          {
            path: "payment",
            children: [
              {
                index: true,
                element: <PaymentPage />,
              },
              { path: "success", element: <PaymentSuccessPage /> },
              { path: "fail", element: <PaymentFailPage /> },
            ],
          },
          {
            path: "list",
            children: [{ path: ":id", element: <MovieDetail /> }],
          },
        ],
      },
      { path: "*", element: <NotFoundPage /> },
    ],
  },
]);
