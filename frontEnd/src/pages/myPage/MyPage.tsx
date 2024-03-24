import NavHeader from "@components/NavHeader";
import char from "@assets/images/char.webp";
import React from "react";
import OptionSection from "@components/myPage/OptionSection";

const SECTIONS = [
  {
    title: "펀딩",
    items: [
      { name: "무딩중", count: 0, url: "a" },
      { name: "애프터 무딩", count: 0, url: "a" },
    ],
  },
  {
    title: "설정",
    items: [
      { name: "알림 설정", count: null, url: "/user/notification/setting" },
    ],
  },
  {
    title: "기타",
    items: [
      { name: "AI 카메라", count: null, url: "a" },
      { name: "좌석 예매", count: null, url: "/fund/reserve" },
      { name: "결제", count: null, url: "/fund/payment" },
    ],
  },
];

const HR = "my-[3vh] mx-[-3vh] border-0 h-[1px] bg-[#A09FB2]";

const MyPage = () => {
  return (
    <div className="mypage-container flex flex-col gap-[5vh]">
      <NavHeader
        leftWord="마이무딩"
        rightWord={["IoSettingsOutline", "edit"]}
      />
      <div className="flex flex-col justify-center items-center gap-[1vh]">
        <img src={char} alt="" className="w-[12vh] h-[12vh] rounded-[35%]" />
        <div className="text-[2.5vh] font-bold">로그인 후 이용해 주세요</div>
      </div>
      <div className="flex justify-center items-center px-[3vh]">
        <div className={`px-[3vh] py-[4vh] bg-bgGray w-[100%]  rounded-[5%]`}>
          {SECTIONS.map((section, index) => (
            <React.Fragment key={index}>
              <OptionSection {...section} />
              {index < SECTIONS.length - 1 && <hr className={HR} />}
            </React.Fragment>
          ))}
        </div>
      </div>
    </div>
  );
};

export default MyPage;
