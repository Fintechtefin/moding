import type { MovieFund } from "@util/types";
import SubscribeItem from "./SubscribeItem";
import NoneData from "@components/common/NoneData";

interface Props {
  data: MovieFund[];
}

const Like = ({ data }: Props) => {
  if (!data.length) {
    return <NoneData content="찜한 펀딩이 없습니다" />;
  }

  return (
    <>
      {data.map((item) => (
        <SubscribeItem key={item.movieId} {...item} type="like" />
      ))}
    </>
  );
};

export default Like;
