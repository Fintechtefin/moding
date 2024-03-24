import { notification } from "./Notification";

interface Props extends notification {}

const NotificationItem = ({ type, content, postUrl, date }: Props) => {
  const getDate = (date: string) => {
    const now = new Date();
    const specificDate = new Date(date);

    const diffInHours =
      (specificDate.getTime() - now.getTime()) / (1000 * 60 * 60);

    const rtf = new Intl.RelativeTimeFormat("ko-KR");

    let relativeTime;

    if (Math.abs(diffInHours) < 24) {
      // 시간 단위로 변환
      relativeTime = rtf.format(Math.round(diffInHours), "hour");
    } else {
      // 일 단위로 변환
      relativeTime = rtf.format(Math.round(diffInHours / 24), "day");
    }

    return relativeTime;
  };

  return (
    <div className="flex gap-[1vh] justify-between">
      <div className="flex flex-col gap-[2vh]">
        <div className="flex gap-[1vh] text-[1.7vh] ">
          <div className="font-bold text-red-600">{type}</div>
          <div className="text-textGray">{getDate(date)}</div>
        </div>
        <div className="text-[2vh]">{content}</div>
      </div>
      <img className="h-[13vh]" src={postUrl} alt="" />
    </div>
  );
};

export default NotificationItem;
