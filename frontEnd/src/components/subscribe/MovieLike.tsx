import { useEffect, useState } from "react";
import { IoHeart } from "react-icons/io5";
import post1 from "@assets/images/영화포스터.jpg";

interface Like {
  id: number;
  name: string;
  state: string;
  count: number;
  url: string;
}

const MovieLike = () => {
  const [likeList, setLikeList] = useState<Like[]>([]);

  useEffect(() => {
    setLikeList([
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
  }, []);

  return (
    <div className="none-scroll px-[3vh] grid grid-cols-2 gap-[3vh] overflow-auto h-[80vh]">
      {likeList.map((like) => {
        return (
          <div className="w-[100%] relative " key={like.id}>
            <div className="text-white absolute z-[1] flex justify-between w-[100%] p-[1vh]">
              <IoHeart className="text-[#C00202] text-[3vh]" />
              <div>
                <div className="bg-[#C00202] blur-sm w-[100%] h-[100%] border-2 border-[#C00202]"></div>
                <div className="text-[1.5vh] relative top-[-100%] right-0 my-[0.5vh] mx-[1vh]">
                  {like.state}
                </div>
              </div>
            </div>
            <img
              className="w-[100%] h-[100%] object-cover rounded-t-[1.5vh] brightness-[90%]"
              src={like.url}
              alt=""
            />
            <div className="p-[1.5vh] flex flex-col gap-[1vh] absolute bottom-0 bg-black rounded-b-[1.5vh] w-[100%] bg-opacity-50">
              <div className="text-white text-[2vh]">{like.name}</div>
              <div className="text-[#C00202] font-bold text-[2vh]">
                {like.count}명 좋아해요
              </div>
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default MovieLike;
