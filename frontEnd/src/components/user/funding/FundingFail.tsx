import { useState } from "react";
import FundingFailItem from "./FundingFailItem";
import { FundingCompleted } from "@util/types";
import NoneData from "@components/common/NoneData";

interface Props {
  failData: FundingCompleted[];
}

const FundingFail = ({ failData }: Props) => {
  // const [data] = useState([
  //   {
  //     title: "엘리멘탈",
  //     poster:
  //       "https://thumb.mtstarnews.com/06/2023/08/2023080115114051612_1.jpg/dims/optimize",
  //     movieId: 1,
  //     fundingFinalResult: 1,
  //     goalCnt: 200,
  //     attendCnt: 76,
  //     reservationId: 6,
  //     date: "2024-04-01",
  //   },
  //   {
  //     title: "엘리멘탈",
  //     poster:
  //       "https://thumb.mtstarnews.com/06/2023/08/2023080115114051612_1.jpg/dims/optimize",
  //     movieId: 2,
  //     fundingFinalResult: 1,
  //     goalCnt: 200,
  //     attendCnt: 96,
  //     reservationId: 7,
  //     date: "2024-06-24",
  //   },
  // ]);

  return (
    <>
      {failData.length ? (
        failData.map((item) => {
          return <FundingFailItem key={item.reservationId} item={item} />;
        })
      ) : (
        <NoneData content="실패한 펀딩이 없습니다." />
      )}
    </>
  );
};

export default FundingFail;
