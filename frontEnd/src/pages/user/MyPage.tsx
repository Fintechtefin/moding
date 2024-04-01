import React, { useEffect, useState } from "react";
import NavHeader from "@components/NavHeader";
import OptionSection from "@components/myPage/OptionSection";
import profileImg from "@assets/images/profileImg.webp";

const SECTIONS = [
  {
    title: "펀딩",
    items: [
      { name: "무딩중", url: "/user/fund/progress" },
      { name: "애프터 무딩", url: "/user/fund/completed" },
    ],
  },
  {
    title: "설정",
    items: [{ name: "알림 설정", url: "/user/notification/setting" }],
  },
  {
    title: "기타",
    items: [
      { name: "AI 카메라", url: "a" },
      { name: "좌석 예매", url: "/fund/reserve" },
      { name: "결제", url: "/fund/payment" },
    ],
  },
];

const MyPage = () => {
  const [nickname, setNickname] = useState("");
  const [imageUrl, setImageUrl] = useState("");

  useEffect(() => {
    setNickname(localStorage.getItem("nickname") || "로그인 후 이용해 주세요.");
    setImageUrl(localStorage.getItem("imageUrl") || profileImg);
  }, []);

  return (
    <div>
      <NavHeader
        leftWord="마이무딩"
        rightWord={["IoSettingsOutline", "edit"]}
      />
      <div className="p-[3vh] flex flex-col gap-[5vh]">
        <div className="flex flex-col justify-center items-center gap-[1vh]">
          <img
            src={imageUrl}
            alt="프로필"
            onError={(e) => {
              e.currentTarget.src = profileImg;
            }}
            className="w-[12vh] h-[12vh] rounded-[40px]"
          />
          <div className="text-[2.5vh] font-bold">{nickname}</div>
        </div>
        <div className="flex items-center justify-center">
          <div
            className={`px-[3vh] py-[4vh] bg-bgGray w-full rounded-2xl shadow-test`}
          >
            {SECTIONS.map((section, index) => (
              <React.Fragment key={index}>
                <OptionSection {...section} />
                {index < SECTIONS.length - 1 && (
                  <hr
                    className={
                      "my-[3vh] mx-[-3vh] border-0 h-[1px] bg-textGray"
                    }
                  />
                )}
              </React.Fragment>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyPage;
