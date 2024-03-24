import NoneNavHeader from "@components/NoneNavHeader";
import NoneNotification from "@components/notification/NoneNotification";
import { useState } from "react";
import NotificationItem from "./NotificationItem";

export interface notification {
  id: number;
  type: string;
  content: string;
  postUrl: string;
  date: string;
}

const Notification = () => {
  const [notificationData] = useState<notification[]>([
    {
      id: 0,
      type: "상영공지",
      content: "회원님이 예매하신 [ooooo] 이 3일 뒤에 상영합니다.",
      postUrl:
        "https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/8809529012721.jpg",
      date: "2024.03.24 15:00:00",
    },
    {
      id: 1,
      type: "펀딩성공",
      content: "회원님이 펀딩하신 [ooooo] 이 성공 하였습니다.",
      postUrl:
        "https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/8809529012721.jpg",
      date: "2024.03.20 15:00:00",
    },
    {
      id: 2,
      type: "펀딩시작",
      content: "회원님이 신청하신 [ooooo] 이 시작 되었습니다.",
      postUrl:
        "https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/8809529012721.jpg",
      date: "2024.03.10 15:00:00",
    },
    {
      id: 3,
      type: "상영공지",
      content: "회원님이 예매하신 [ooooo] 이 3일 뒤에 상영합니다.",
      postUrl: "https://cdn.stupress.co.kr/news/photo/202105/110_112_155.jpg",
      date: "2024.03.24 15:00:00",
    },
    {
      id: 4,
      type: "펀딩성공",
      content: "회원님이 펀딩하신 [ooooo] 이 성공 하였습니다.",
      postUrl: "https://cdn.stupress.co.kr/news/photo/202105/110_112_155.jpg",
      date: "2024.03.20 15:00:00",
    },
    {
      id: 5,
      type: "펀딩시작",
      content: "회원님이 신청하신 [ooooo] 이 시작 되었습니다.",
      postUrl: "https://cdn.stupress.co.kr/news/photo/202105/110_112_155.jpg",
      date: "2024.03.10 15:00:00",
    },
  ]);

  return (
    <div>
      <NoneNavHeader centerText="알림" type="notification" />
      {notificationData.length ? (
        <div className="none-scroll h-[93vh] overflow-auto p-[2vh] flex flex-col gap-[4vh]">
          {notificationData.map((data) => {
            return <NotificationItem key={data.id} {...data} />;
          })}
        </div>
      ) : (
        <NoneNotification />
      )}
    </div>
  );
};

export default Notification;
