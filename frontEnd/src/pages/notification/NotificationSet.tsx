import NoneNavHeader from "@components/NoneNavHeader";
import "@assets/styles/notification/NotificationSet.scss";
import { useState } from "react";

const NotificationSet = () => {
  const [alarm, setAlarm] = useState(true);

  const handleChange = () => setAlarm((prev) => !prev);

  return (
    <div className="NotificationSet h-[100vh]">
      <NoneNavHeader centerText="알림 설정" />
      <div className="flex flex-col gap-[5vh] p-[3vh]">
        <div className="flex flex-col gap-[1vh]">
          <div className="text-[2.5vh] font-bold">정보 알림</div>
          <div className="text-[1.7vh] text-textGray">
            내 활동에 대한 알림을 보내드려요
          </div>
        </div>
        <div className="flex items-center justify-between">
          <div className="text-[2.3vh]">서비스 알림</div>
          <label className="switch">
            <input
              type="checkbox"
              className="checkbox"
              checked={alarm}
              onChange={handleChange}
            />
            <div className="slider"></div>
          </label>
        </div>
      </div>
    </div>
  );
};

export default NotificationSet;
