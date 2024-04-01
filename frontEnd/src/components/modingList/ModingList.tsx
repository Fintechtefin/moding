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
          <div className="flex flex-col mt-3  px-[1.6vh]">
            <Link to={`/fund/list/${posterList[0].movieId}`} state={{ type: "list" }}>
              <img className="w-[100%] aspect-square object-cover brightness-[90%]" src={posterList[0].poster} alt="" />
            </Link>
            <div className="absolute w-[100%] top-0 right-3">
              <StatusBadge status={posterList[0].status} textSize="2.5vh" />
            </div>
            <div className="mt-2">
              <ProgressArea crowd={posterList[0].crowdCnt} joinCnt={posterList[0].peopleCnt} />
            </div>
          </div>
          <div className="movie-list none-scroller px-[1.6vh] mt-5 grid grid-cols-3 gap-[1.2vh] overflow-auto pb-20">
            {posterList.map((poster, index) => {
              if (index !== 0) {
                return (
                  <Link to={`/fund/list/${poster.movieId}`} key={index} state={{ type: "list" }}>
                    <MovieListItem state={poster.status} url={poster.poster} heigth="23vh"></MovieListItem>
                  </Link>
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
