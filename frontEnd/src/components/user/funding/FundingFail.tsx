import FundingFailItem from "./FundingFailItem";
import { FundingCompleted } from "@util/types";
import NoneData from "@components/common/NoneData";

interface Props {
  failData: FundingCompleted[];
}

const FundingFail = ({ failData }: Props) => {
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
