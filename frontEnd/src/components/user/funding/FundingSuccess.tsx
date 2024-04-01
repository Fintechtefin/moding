import { useState } from "react";
import FundingSuccessItem from "./FundingSuccessItem";
import { FundingCompleted } from "@util/types";
import NoneData from "@components/common/NoneData";

interface Props {
  successData: FundingCompleted[];
}

const FundingSuccess = ({ successData }: Props) => {
  const [data, setData] = useState([
    {
      title: "엘리멘탈",
      poster:
        "https://thumb.mtstarnews.com/06/2023/08/2023080115114051612_1.jpg/dims/optimize",
      movieId: 1,
      fundingFinalResult: 1,
      goalCnt: 200,
      attendCnt: 76,
      reservationId: 6,
      date: "2024-04-01",
    },
    {
      title: "엘리멘탈",
      poster:
        "https://thumb.mtstarnews.com/06/2023/08/2023080115114051612_1.jpg/dims/optimize",
      movieId: 2,
      fundingFinalResult: 1,
      goalCnt: 200,
      attendCnt: 96,
      reservationId: 7,
      date: "2024-06-24",
    },
  ]);

  const removeFund = (reservationId: number) => {
    setData((prev) =>
      prev.filter((item) => item.reservationId !== reservationId)
    );
  };

  return (
    <>
      {successData.length ? (
        successData.map((item) => {
          return (
            <FundingSuccessItem
              key={item.reservationId}
              item={item}
              removeFund={removeFund}
            />
          );
        })
      ) : (
        <NoneData content="성공한 펀딩이 없습니다." />
      )}
    </>
  );
};

export default FundingSuccess;
