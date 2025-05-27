import { useState, useEffect, useRef } from "react";
import MovieListItem from "./MovieListItem";
import "@/assets/styles/movieList/MovieList.scss";
import { Link } from "react-router-dom";
import type { MovieCategory } from "@util/types/movieType";
import { useInfiniteQuery } from "@tanstack/react-query";
import { getGenreList } from "@api/movie";
import { useInView } from "react-intersection-observer";
import { FixedSizeGrid as Grid } from "react-window";

interface Props {
  category: number;
  cateTitle: string;
  sort: string;
}

const COLUMN_COUNT = 3;
const ITEM_HEIGHT = 220; // 아이템 높이(px), 필요에 맞게 조정
const ITEM_WIDTH = 150; // 아이템 너비(px), 필요에 맞게 조정

const MovieList = ({ category, cateTitle, sort }: Props) => {
  const nickname = localStorage.getItem("nickname");

  const [ref, inView] = useInView({
    threshold: 0.5,
  });

  const [hasFetchedOnce, setHasFetchedOnce] = useState(false);

  const { data, fetchNextPage, hasNextPage, isFetchingNextPage } =
    useInfiniteQuery<MovieCategory>({
      queryKey: ["GenreList", category, sort], // 쿼리 키를 지정합니다.
      queryFn: ({ pageParam }) =>
        getGenreList(category, pageParam as number, sort),
      initialPageParam: 1,
      getNextPageParam: (lastPage, allPages) => {
        const totalLoaded = allPages.reduce(
          (acc, page) => acc + page.movieList?.length,
          0
        );

        if (!lastPage.movieList || lastPage.movieList.length === 0)
          return undefined;

        return totalLoaded < lastPage.totalCnt
          ? allPages.length + 1
          : undefined;
      },
      // getNowRanking 함수를 호출합니다.
      staleTime: 1000 * 60 * 5,
      gcTime: 1000 * 60 * 10,
    });

  // useEffect(() => {
  //   if (!inView) {
  //     setHasFetchedOnce(false);
  //   }
  //   if (inView && hasNextPage && !isFetchingNextPage && !hasFetchedOnce) {
  //     fetchNextPage();
  //     setHasFetchedOnce(true);
  //   }
  // }, [inView, hasNextPage, isFetchingNextPage]);

  const PosterList = data?.pages.flatMap((page) => page.movieList) || [];
  const totalCnt = data?.pages?.[0]?.totalCnt || 0;

  const rowCount = Math.ceil(PosterList.length / COLUMN_COUNT);

  const Cell = ({
    columnIndex,
    rowIndex,
    style,
  }: {
    columnIndex: number;
    rowIndex: number;
    style: React.CSSProperties;
  }) => {
    const index = rowIndex * COLUMN_COUNT + columnIndex;
    console.log("ddddddddd");
    console.log(index);
    if (index >= PosterList.length) {
      return <div style={style} />;
    }

    const poster = PosterList[index];

    return (
      <div style={style}>
        <Link to={`/fund/list/${poster.movieId}`} state={{ type: "list" }}>
          <MovieListItem
            state={poster.status}
            url={poster.poster}
            heigth="22vh"
          />
        </Link>
      </div>
    );
  };

  return (
    <div className="movielist-container">
      <div className="flex flex-col items-end pt-4 pr-3 text-gray-400">
        <div className="text-[3vh]">웨이드님을 위해</div>
        <div className="text-[3vh]">무딩이 준비한 {totalCnt}편의</div>
        <div className="text-[3vh]">{cateTitle}</div>
      </div>
      {/* <div className="movie-list none-scroller translate-y-[-40px] px-[1.5vh] grid grid-cols-3 gap-[1.2vh] overflow-auto pb-56">
        {PosterList && (
          <>
            {PosterList.map((poster) => {
              return (
                <Link
                  to={`/fund/list/${poster.movieId}`}
                  key={poster.movieId}
                  state={{ type: "list" }}
                >
                  <MovieListItem
                    state={poster.status}
                    url={poster.poster}
                    heigth="22vh"
                  ></MovieListItem>
                </Link>
              );
            })}
          </>
        )}
        <div ref={ref} className="w-full h-[100px]"></div>
      </div> */}
      <Grid
        className="movie-list none-scroller "
        columnCount={COLUMN_COUNT}
        columnWidth={ITEM_WIDTH}
        height={window.innerHeight * 0.7}
        rowCount={rowCount}
        rowHeight={ITEM_HEIGHT}
        width={window.innerWidth * 0.9}
        onItemsRendered={({
          visibleRowStopIndex,
          overscanRowStopIndex,
        }: {
          visibleRowStopIndex: number;
          overscanRowStopIndex: number;
        }) => {
          // 마지막 행이 거의 보일 때 다음 페이지 로딩
          if (
            hasNextPage &&
            !isFetchingNextPage &&
            (visibleRowStopIndex >= rowCount - 1 ||
              overscanRowStopIndex >= rowCount - 1)
          ) {
            fetchNextPage();
          }
        }}
      >
        {Cell}
      </Grid>
    </div>
  );
};

export default MovieList;
