import char1 from "@assets/images/char1.webp";

const NoneNotification = () => {
  return (
    <div className="h-[93vh] flex flex-col justify-center items-center gap-[5vh]">
      <img className="w-[25vh] h-[25vh] " src={char1} alt="" />
      <div className="flex flex-col gap-[2vh] items-center">
        <div className="font-bold text-[3vh]">아직 도착한 알림이 없어요</div>
        <div className="font-bold text-[2vh] text-textGray">
          소식이 도착하면 알려드릴게요
        </div>
      </div>
      <div className="h-[10vh]"></div>
    </div>
  );
};

export default NoneNotification;
