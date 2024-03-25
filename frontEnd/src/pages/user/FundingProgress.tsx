import NoneNavHeader from "@components/NoneNavHeader";
import { useState } from "react";
import "@assets/styles/FundingProgress.scss";
import FundingProgressItem from "@components/FundingProgressItem";

const FundingProgress = () => {
  const [data] = useState([
    {
      id: 0,
      title: "눈부신 하루 - 옴니버스영화 : 보물섬, 엄마 찾아 삼만리, 공항남녀",
      url: "https://blog.kakaocdn.net/dn/ze2jB/btqBSNSSM9q/ZfRVZvWMDlpJUvSa6SEHC0/img.jpg",
      per: 75,
      date: "2024.03.26",
    },
    {
      id: 1,
      title: "더 문",
      url: "https://thumb.mtstarnews.com/06/2023/08/2023080115114051612_1.jpg/dims/optimize",
      per: 20,
      date: "2024.04.01",
    },
    {
      id: 2,
      title: "파묘",
      url: "https://spnimage.edaily.co.kr/images/photo/files/NP/S/2024/03/PS24030200047.jpg",
      per: 40,
      date: "2024.05.01",
    },
    {
      id: 3,
      title: "폼페이",
      url: "https://cdn.huffingtonpost.kr/news/photo/201506/6399_10649.jpeg",
      per: 98,
      date: "2024.03.30",
    },
    {
      id: 4,
      title: "기생충",
      url: "https://blog.kakaocdn.net/dn/ze2jB/btqBSNSSM9q/ZfRVZvWMDlpJUvSa6SEHC0/img.jpg",
      per: 75,
      date: "2024.05.01",
    },
    {
      id: 5,
      title: "더 문",
      url: "https://thumb.mtstarnews.com/06/2023/08/2023080115114051612_1.jpg/dims/optimize",
      per: 20,
      date: "2024.05.01",
    },
    {
      id: 6,
      title: "파묘",
      url: "https://spnimage.edaily.co.kr/images/photo/files/NP/S/2024/03/PS24030200047.jpg",
      per: 40,
      date: "2024.05.01",
    },
    {
      id: 7,
      title: "폼페이",
      url: "https://cdn.huffingtonpost.kr/news/photo/201506/6399_10649.jpeg",
      per: 98,
      date: "2024.05.01",
    },
  ]);

  return (
    <div className="FundingProgress">
      <NoneNavHeader centerText="무딩중" />
      <div className="none-scroll h-[93vh] overflow-auto p-[3vh] flex flex-col gap-[3vh] ">
        {data.map((item) => {
          return <FundingProgressItem key={item.id} item={item} />;
        })}
      </div>
    </div>
  );
};

export default FundingProgress;
