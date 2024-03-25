import { useState, useEffect } from "react";
import post1 from "@assets/images/영화포스터.jpg";
import { NavLink } from "react-router-dom";
import MovieListItem from "@components/movieList/MovieListItem";

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

const ModingList = ({ status, getModingBack }: Props) => {
  useEffect(() => {
    getModingBack(PosterList[0].url);
  }, []);

  const [PosterList, setPosterList] = useState<ModingInfo[]>([
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
      state: "무딩중",
      hopeCnt: 150,
      alarmCnt: 220,
      crowd: 150,
      joinCnt: 110,
    },
  ]);

  return (
    <div>
      <div className="flex flex-col items-center mt-3">
        <img className="w-[100%] h-[45vh] px-[1.6vh] object-cover brightness-[90%]" src={PosterList[0].url} alt="" />
      </div>
      <div className="movie-list none-scroller px-[1.6vh] mt-5 grid grid-cols-3 gap-[1.2vh] overflow-auto pb-20">
        {PosterList.map((poster) => {
          if (poster.id !== 1) {
            return (
              <NavLink to={`/fund/list/${poster.id}`} key={poster.id}>
                <MovieListItem poster={poster}></MovieListItem>
              </NavLink>
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
