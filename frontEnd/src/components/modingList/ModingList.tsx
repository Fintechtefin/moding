import { useState, useEffect } from "react";
import post1 from "@assets/images/영화포스터.jpg";
import { Link } from "react-router-dom";
import MovieListItem from "@components/movieList/MovieListItem";
import ProgressArea from "@components/movieDetail/ProgressArea";
import StatusBadge from "@components/movieDetail/StatusBadge";

interface Props {
  status: number;
  getModingBack: (url: string) => void;
}

interface ModingInfo {
  id: number;
  state: string;
  url: string;
  hopeCnt: number;
  alarmCnt: number;
  crowd: number;
  joinCnt: number;
}

// status 내려받기
const ModingList = ({ getModingBack }: Props) => {
  useEffect(() => {
    getModingBack(PosterList[0].url);
  }, []);

  const [PosterList] = useState<ModingInfo[]>([
    {
      id: 1,
      url: post1,
      state: "무딩중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
    {
      id: 2,
      url: post1,
      state: "무딩중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
    {
      id: 3,
      url: post1,
      state: "무딩중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
    {
      id: 4,
      url: post1,
      state: "무딩중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
    {
      id: 5,
      url: post1,
      state: "무딩중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
    {
      id: 6,
      url: post1,
      state: "무딩중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
    {
      id: 7,
      url: post1,
      state: "무딩중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
    {
      id: 8,
      url: post1,
      state: "무딩중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
    {
      id: 9,
      url: post1,
      state: "무딩중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
    {
      id: 10,
      url: post1,
      state: "무딩 중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
  ]);

  return (
    <div className="relative">
      <div className="flex flex-col mt-3  px-[1.6vh]">
        <Link to={`/fund/list/${PosterList[0].id}`}>
          <img
            className="w-[100%] aspect-square object-cover brightness-[90%]"
            src={PosterList[0].url}
            alt=""
          />
        </Link>
        <div className="absolute w-[100%] top-0 right-3">
          <StatusBadge status={PosterList[0].state} textSize="2.5vh" />
        </div>
        <div className="mt-2">
          <ProgressArea
            crowd={PosterList[0].crowd}
            joinCnt={PosterList[0].joinCnt}
          />
        </div>
      </div>
      <div className="movie-list none-scroller px-[1.6vh] mt-5 grid grid-cols-3 gap-[1.2vh] overflow-auto pb-20">
        {PosterList.map((poster) => {
          if (poster.id !== 1) {
            return (
              <Link to={`/fund/list/${poster.id}`} key={poster.id}>
                <MovieListItem
                  state={poster.state}
                  url={poster.url}
                  heigth="23vh"
                ></MovieListItem>
              </Link>
            );
          } else {
            return null;
          }
        })}
      </div>
    </div>
  );
};

export default ModingList;
