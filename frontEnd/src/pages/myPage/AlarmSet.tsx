import NoneNavHeader from "@components/NoneNavHeader";

const AlarmSet = () => {
  return (
    <div className="h-[100vh]">
      <NoneNavHeader centerText="알림 설정" />
      <div className="flex flex-col gap-[5vh] px-[3vh] pt-5">
        <div className="flex flex-col gap-[1vh]">
          <div className="text-[2.7vh] font-bold">정보 알림</div>
          <div className="text-[1.7vh] text-[#6E6E6D]">
            내 활동에 대한 알림을 보내드려요
          </div>
        </div>
        <div>
          <div className="flex items-center justify-between">
            <div className="text-[2.3vh]">서비스 알림</div>
            <label className="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" value="" className="sr-only peer" />
              <div className="group peer ring-0 bg-gradient-to-r from-zinc-300 to-zinc-500 rounded-full outline-none duration-700 after:duration-300 w-[7vh] h-[4vh]  shadow-md peer-checked:bg-gradient-to-r peer-checked:from-red-500 peer-checked:to-red-900 peer-focus:outline-none  after:content-[''] after:rounded-full after:absolute after:bg-gray-50 after:outline-none after:h-[3vh] after:w-[3vh] after:top-1 after:left-1  peer-checked:after:translate-x-5 "></div>
            </label>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AlarmSet;
