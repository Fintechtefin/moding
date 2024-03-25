import { useState } from "react";
import FundingFailItem from "./FundingFailItem";

const FundingFail = () => {
  const [data] = useState([
    {
      id: 0,
      title: "눈부신 하루 - 옴니버스영화 : 보물섬, 엄마 찾아 삼만리, 공항남녀",
      url: "https://blog.kakaocdn.net/dn/ze2jB/btqBSNSSM9q/ZfRVZvWMDlpJUvSa6SEHC0/img.jpg",
      per: 80,
      date: "2024.03.24",
    },
    {
      id: 1,
      title: "더 문",
      url: "https://thumb.mtstarnews.com/06/2023/08/2023080115114051612_1.jpg/dims/optimize",
      per: 72,
      date: "2024.03.11",
    },
    {
      id: 2,
      title: "파묘",
      url: "https://spnimage.edaily.co.kr/images/photo/files/NP/S/2024/03/PS24030200047.jpg",
      per: 11,
      date: "2024.02.27",
    },
    {
      id: 3,
      title: "폼페이",
      url: "https://cdn.huffingtonpost.kr/news/photo/201506/6399_10649.jpeg",
      per: 28,
      date: "2024.02.22",
    },
    {
      id: 4,
      title: "기생충",
      url: "https://blog.kakaocdn.net/dn/ze2jB/btqBSNSSM9q/ZfRVZvWMDlpJUvSa6SEHC0/img.jpg",
      per: 75,
      date: "2024.02.01",
    },
    {
      id: 5,
      title: "더 문",
      url: "https://thumb.mtstarnews.com/06/2023/08/2023080115114051612_1.jpg/dims/optimize",
      per: 30,
      date: "2024.01.19",
    },
    {
      id: 6,
      title: "파묘",
      url: "https://spnimage.edaily.co.kr/images/photo/files/NP/S/2024/03/PS24030200047.jpg",
      per: 20,
      date: "2024.01.10",
    },
    {
      id: 7,
      title: "폼페이",
      url: "https://cdn.huffingtonpost.kr/news/photo/201506/6399_10649.jpeg",
      per: 88,
      date: "2024.01.08",
    },
  ]);

  return (
    <>
      {data.map((item) => {
        return <FundingFailItem key={item.id} item={item} />;
      })}
    </>
  );
};

export default FundingFail;
