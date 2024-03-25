import { getDate } from "@util/commonFunction";
import { notification } from "./Notification";

interface Props extends notification {}

const NotificationItem = ({ type, content, postUrl, date }: Props) => {
  return (
    <div className="flex gap-[2vh] justify-between">
      <div className="flex flex-col gap-[2vh]">
        <div className="flex gap-[1vh] text-[1.7vh] ">
          <div className="font-bold text-red-600">{type}</div>
          <div className="text-textGray">{getDate(date)}</div>
        </div>
        <div className="text-[2vh]">{content}</div>
      </div>
      <img className="h-[13vh] rounded-[0.5vh]" src={postUrl} alt="" />
    </div>
  );
};

export default NotificationItem;
