import { useEffect, useState } from "react";
import toast, { Toaster } from "react-hot-toast";
import { Subscribe } from "@util/types";
import post1 from "@assets/images/영화포스터.jpg";
import SubscribeItem from "./SubscribeItem";

const Subscribe = ({ type }: { type: string }) => {
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
  }, []);

  const handleClick = (id: number) => {
    setSubscribeItems((prev) => prev.filter((item) => item.id !== id));
    toast(isType ? "좋아요를 취소했어요." : "요청을 취소했어요.", {
      duration: 1500,
    });
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
      <Toaster
        containerStyle={{
          margin: "0 auto",
        }}
        toastOptions={{
          // Define default options
          style: {
            background: "#363636",
            color: "#fff",
            fontSize: "2vh",
          },
        }}
      />
    </>
  );
};

export default Subscribe;
