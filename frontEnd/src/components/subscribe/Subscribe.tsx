import { useEffect, useState } from "react";
import type { Subscribe } from "@util/types";
import SubscribeItem from "./SubscribeItem";
import post1 from "@assets/images/영화포스터.jpg";
import { toastMsg } from "@util/commonFunction";
import { ToasterMsg } from "@components/Common";

interface Props {
  type: string;
}

const Subscribe = ({ type }: Props) => {
  const [subscribeItems, setSubscribeItems] = useState<Subscribe[]>([]);
  const isType = type === "좋아요";

  useEffect(() => {
    if (isType) {
      setSubscribeItems([
        {
          id: 1,
          name: "엘리멘탈",
          state: "무딩중",
          count: 3663,
          url: post1,
        },
        {
          id: 2,
          name: "불한당",
          state: "무딩예정",
          count: 1763,
          url: post1,
        },
        {
          id: 3,
          name: "엘리멘탈",
          state: "무딩중",
          count: 3663,
          url: post1,
        },
        {
          id: 4,
          name: "불한당",
          state: "무딩예정",
          count: 1763,
          url: post1,
        },
        {
          id: 5,
          name: "엘리멘탈",
          state: "무딩중",
          count: 3663,
          url: post1,
        },
        {
          id: 6,
          name: "불한당",
          state: "무딩예정",
          count: 1763,
          url: post1,
        },
      ]);
    } else {
      setSubscribeItems([
        {
          id: 1,
          name: "엘리멘탈",
          state: "무딩중",
          count: 3663,
          url: post1,
        },
        {
          id: 2,
          name: "불한당",
          state: "무딩예정",
          count: 1763,
          url: post1,
        },
        {
          id: 3,
          name: "엘리멘탈",
          state: "무딩중",
          count: 3663,
          url: post1,
        },
        {
          id: 4,
          name: "불한당",
          state: "무딩예정",
          count: 1763,
          url: post1,
        },
        {
          id: 5,
          name: "엘리멘탈",
          state: "무딩중",
          count: 3663,
          url: post1,
        },
        {
          id: 6,
          name: "불한당",
          state: "무딩예정",
          count: 1763,
          url: post1,
        },
      ]);
    }
  }, [isType]);

  const handleClick = (id: number) => {
    setSubscribeItems((prev) => prev.filter((item) => item.id !== id));
    toastMsg(isType ? "좋아요를 취소했어요." : "요청을 취소했어요.");
  };

  return (
    <>
      {subscribeItems.map((item) => (
        <SubscribeItem
          key={item.id}
          {...item}
          handleClick={handleClick}
          isType={isType}
        />
      ))}
      <ToasterMsg />
    </>
  );
};

export default Subscribe;
