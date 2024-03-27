import { useEffect, useState } from "react";
import { FaHeart } from "react-icons/fa";
import post1 from "@assets/images/영화포스터.jpg";
import toast, { Toaster } from "react-hot-toast";

interface Req {
  id: number;
  name: string;
  state: string;
  count: number;
  url: string;
}

const FundingRequest = () => {
  const [reqList, setReqList] = useState<Req[]>([]);

  useEffect(() => {
    setReqList([
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

  const handleClick = (id: number) => {
    setReqList((prev) => prev.filter((item) => item.id !== id));
    toast("요청을 취소했어요.", { duration: 1500 });
  };

  return (
    <>
      {reqList.map((req) => {
        return (
          <div
            className="w-[100%] relative shadow-bgTTT rounded-[1.5vh]"
            key={req.id}
          >
            <img
              className="w-[100%] h-[100%] object-cover rounded-[1.5vh] brightness-[90%]"
              src={req.url}
              alt=""
            />
            <div className="absolute inset-0 flex flex-col justify-between">
              <div className="flex justify-between items-center p-[1vh]">
                <div className=" relative">
                  <div className="absolute inset-0 bg-[#C00202] blur-sm"></div>
                  <div className="relative text-[1.5vh] px-[1vh] py-[0.5vh]">
                    {req.state}
                  </div>
                </div>
                <FaHeart
                  className="text-[#C00202] text-[3vh]"
                  onClick={() => handleClick(req.id)}
                />
              </div>
              <div className="p-[1.5vh] flex flex-col gap-[1vh] absolute bottom-0 bg-black rounded-b-[1.5vh] w-[100%] bg-opacity-80">
                <div className="text-white text-[2vh]">{req.name}</div>
                <div className="text-[#C00202] font-bold text-[2vh]">
                  {req.count}명 요청했어요
                </div>
              </div>
            </div>
          </div>
        );
      })}
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

export default FundingRequest;
