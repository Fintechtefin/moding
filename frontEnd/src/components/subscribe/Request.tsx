import type { MovieFund } from "@util/types";
import SubscribeItem from "./SubscribeItem";
import NoneData from "@components/common/NoneData";

interface Props {
  data: MovieFund[];
}

const Request = ({ data }: Props) => {
  if (!data.length) {
    return <NoneData content="요청한 펀딩이 없습니다" />;
  }

  return (
    <>
      {data.map((item) => (
        <SubscribeItem key={item.movieId} {...item} type="req" />
      ))}
    </>
  );
};

export default Request;
