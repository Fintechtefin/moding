import FundingSuccessItem from "./FundingSuccessItem";
import type { FundingCompleted } from "@util/types";
import NoneData from "@components/common/NoneData";

interface Props {
  successData: FundingCompleted[];
}

const FundingSuccess = ({ successData }: Props) => {
  if (!successData.length) {
    return <NoneData content="성공한 펀딩이 없습니다." />;
  }

  return (
    <>
      {successData.map((item) => {
        return <FundingSuccessItem key={item.reservationId} item={item} />;
      })}
    </>
  );
};

export default FundingSuccess;
