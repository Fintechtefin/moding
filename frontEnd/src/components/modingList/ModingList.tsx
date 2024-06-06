import { useState, useEffect } from "react";
import { useQuery } from "@tanstack/react-query";
import { Link } from "react-router-dom";
import MovieListItem from "@components/movieList/MovieListItem";
import ProgressArea from "@components/movieDetail/ProgressArea";
import StatusBadge from "@components/movieDetail/StatusBadge";
import type { ModingList } from "@util/types/movieType";
import { getTopTen } from "@api/funding";

interface Props {
  status: string;
  getModingBack: (url: string) => void;
}

// status 내려받기
const ModingList = ({ status, getModingBack }: Props) => {
  const [posterList, setPosterList] = useState<ModingList[]>();

  const { data: modingList } = useQuery<ModingList[], Error>({
    queryKey: ["modingListResult", status], // 쿼리 키를 지정합니다.
    queryFn: () => getTopTen(status),
  });

  useEffect(() => {
    if (modingList) {
      setPosterList(modingList);
      console.log(posterList);
      if (posterList) {
        getModingBack(posterList[0].poster);
      }
    }
  }, [modingList, posterList]);

  return (
    <>
      {posterList && (
        <div className="relative">
          <div className="flex flex-col mt-3 px-[1.6vh] relative">
            <Link
              to={`/fund/list/${posterList[0].movieId}`}
              state={{ type: "list" }}
            >
              <img
                className="w-[100%] aspect-square object-cover brightness-[90%]"
                src={posterList[0].poster}
                alt=""
              />
            </Link>
            <div className="absolute w-[100%] top-0 right-3">
              <StatusBadge status={posterList[0].status} textSize="3vh" />
            </div>
            <div className="mt-2">
              {posterList[0].status == "무딩중" && (
                <>
                  <ProgressArea
                    crowd={posterList[0].crowdCnt}
                    joinCnt={posterList[0].peopleCnt}
                    height="4vh"
                    size="big"
                  />
                </>
              )}
              {posterList[0].status == "무딩 준비 중" && (
                <>
                  <div className="absolute right-5 bottom-3 text-white text-2xl">
                    {posterList[0].requestCnt}/200
                  </div>
                </>
              )}
            </div>
            <div className="absolute text-8xl top-0">1</div>
          </div>
          <div className="movie-list relative none-scroller px-[1.6vh] mt-10 grid grid-cols-3 gap-[1.2vh] overflow-auto pb-20">
            {posterList.map((poster, index) => {
              if (index !== 0) {
                return (
                  <div key={index} className="relative">
                    <Link
                      to={`/fund/list/${poster.movieId}`}
                      state={{ type: "list" }}
                    >
                      <MovieListItem
                        state={poster.status}
                        url={poster.poster}
                        heigth="23vh"
                      ></MovieListItem>
                    </Link>
                    <div className="absolute top-0 left-1 text-[4vh]">
                      {index + 1}
                    </div>
                    <div className="mt-1 mb-3">
                      {poster.status == "무딩중" && (
                        <>
                          <ProgressArea
                            crowd={poster.crowdCnt}
                            joinCnt={poster.peopleCnt}
                            height="1vh"
                            size="small"
                          />
                        </>
                      )}
                      {poster.status == "무딩 준비 중" && (
                        <>
                          <div className="absolute bottom-[2vh] right-1">
                            {poster.requestCnt}/200
                          </div>
                        </>
                      )}
                    </div>
                  </div>
                );
              } else {
                return null;
              }
            })}
          </div>
        </div>
      )}
    </>
  );
};

export default ModingList;
