import FundingFailItem from "./FundingFailItem";
import type { FundingCompleted } from "@util/types";
import NoneData from "@components/common/NoneData";

interface Props {
  failData: FundingCompleted[];
}

const FundingFail = ({ failData }: Props) => {
  return (
    <>
      {failData.length ? (
        failData.map((item) => (
          <FundingFailItem key={item.movieId} item={item} />
        ))
      ) : (
        <NoneData content="실패한 펀딩이 없습니다." />
      )}
    </>
  );
};

export default FundingFail;
