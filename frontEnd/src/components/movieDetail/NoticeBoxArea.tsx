import { useEffect, useState } from "react";
import { IoSettingsOutline } from "react-icons/io5";
import Select from "react-select";

interface Props {
  status: string;
}

interface Cinema {
  value: number;
  label: string;
}

const NoticeBoxArea = ({ status }: Props) => {
  const [cinemaList] = useState<Cinema[]>([
    {
      value: 1,
      label: "CGV 상무",
    },
    {
      value: 2,
      label: "CGV 하남",
    },
    {
      value: 3,
      label: "CGV 첨단",
    },
    {
      value: 4,
      label: "CGV 충장로",
    },
    {
      value: 5,
      label: "CGV 전대",
    },
    {
      value: 6,
      label: "CGV 수완",
    },
  ]);

  const [modingClose] = useState(true);
  const [adminOn, setAdminOn] = useState(false);
  const [selectCinema, setSelectCinema] = useState("영화관 선택");

  useEffect(() => {
    //status가 무딩예정인데 아직 데이터가 없으면 노란색적용
    // setModingClose(true);yes
    // 무딩예정
  }, []);

  const openFunding = () => {
    setAdminOn(false);
    console.log(selectCinema);
  };

  const getSelectCinema = (e: any) => {
    setSelectCinema(e.target.value);
  };

  return (
    <div className={`relative w-[100%] mb-4 p-6 border-solid ${modingClose ? "border-yellow-600" : "border-red-600"}`}>
      {status === "무딩 예정" && (
        <>
          {modingClose ? (
            <div className="flex justify-center">
              <div className="text-[2vh]">내부 심사중</div>
              <div className="absolute top-2 right-2 w-[30px] h-[30px]">
                <IoSettingsOutline className="w-[100%] h-[100%]" onClick={() => setAdminOn(true)} />
              </div>
            </div>
          ) : (
            <div className="text-center">오픈 예정</div>
          )}
        </>
      )}
      {adminOn && (
        <div className="absolute flex flex-col gap-3 w-[90%] p-4 bg-black text-white">
          <div>날짜선택</div>
          <input className="text-black h-[4vh]" type="datetime-local" name="" id="" />
          <div className="">영화관선택</div>
          <Select className="text-black h-[4vh]" options={cinemaList} onChange={getSelectCinema} defaultValue={cinemaList[0]} />
          <div>금액</div>
          <input className="h-[4vh]" type="text" />
          <div className="p-2 border-solid text-center" onClick={() => openFunding()}>
            확인
          </div>
        </div>
      )}
    </div>
  );
};

export default NoticeBoxArea;
