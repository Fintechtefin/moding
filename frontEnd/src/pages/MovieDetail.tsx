import { useEffect, useState } from "react";
import { useLocation, useParams, useNavigate } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
import "@/assets/styles/movieDetail/MovieDetail.scss";
import NoneNavHeader from "@components/NoneNavHeader";
import AboutMovie from "@components/movieDetail/AboutMovie";
import AboutFunding from "@components/movieDetail/AboutFunding";
import AboutNote from "@components/movieDetail/AboutNote";
import MovieDetailButton from "@components/movieDetail/MovieDetailButton";
import HopeSurvey from "@components/movieDetail/HopeSurvey";
import InfoArea from "@components/movieDetail/InfoArea";
import type { MovieInfo, FundingInfo } from "@util/types/movieType";
import { getSearchDetail, getMovieDetail } from "@api/movie";
import { getFundingInfo } from "@api/funding";

const MovieDetail = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const way = location.state.type;
  const { id } = useParams() as { id: string };
  const [log, setLog] = useState("");
  const [movieId, setMovieId] = useState("");
  const [fundMovieId, setFundMovieId] = useState("");

  const { data: searchDetail } = useQuery<MovieInfo, Error>({
    queryKey: ["logResult", log], // 쿼리 키를 지정합니다.
    queryFn: () => {
      if (log.length === 0) {
        return Promise.resolve([]);
      } else {
        return getSearchDetail(log);
      }
    },
  });

  const { data: movieDetail } = useQuery<MovieInfo, Error>({
    queryKey: ["movieResult", movieId], // 쿼리 키를 지정합니다.
    queryFn: () => {
      if (movieId.length === 0) {
        return Promise.resolve([]);
      } else {
        return getMovieDetail(movieId);
      }
    },
  });

  const { data: fundinfo } = useQuery<FundingInfo, Error>({
    queryKey: ["logResult", fundMovieId], // 쿼리 키를 지정합니다.
    queryFn: () => {
      if (fundMovieId.length === 0) {
        return Promise.resolve([]);
      } else {
        return getFundingInfo(fundMovieId);
      }
    },
  });

  useEffect(() => {
    console.log(id);
    setFundMovieId(id);
    if (way === "search") {
      setLog(id);
      if (searchDetail) {
        setMovieInfo(searchDetail);
        console.log(searchDetail);
      }
    } else if (way === "list") {
      setMovieId(id);
      if (movieDetail) {
        setMovieInfo(movieDetail);
        console.log(movieDetail);
      }
    }
  }, [searchDetail, movieDetail]);

  const [movieInfo, setMovieInfo] = useState<MovieInfo>();

  const [infoCategory, setInfoCategory] = useState(0);
  const [modalShow, setModalShow] = useState(false);
  // 무딩 준비 중이거나 무딩 완료 시에 false로 바꾸기
  const [fundingInfo, setFundingInfo] = useState(true);

  const sendFundingInfo = (type: string) => {
    const fundinfos = {
      fundinfo: fundinfo,
      poster: movieInfo?.poster,
    };
    if (type === "join") {
      navigate(`/fund/payment`, { state: fundinfos });
    } else if (type === "book") {
      navigate(`/fund/reserve`, { state: fundinfos });
    }
  };

  useEffect(() => {
    if (movieInfo) {
      console.log(movieInfo);
      if (
        movieInfo.status === "무딩 준비 중" ||
        movieInfo.status === "무딩종료"
      ) {
        setFundingInfo(false);
      }
    }
  }, [movieInfo, fundingInfo]);

  const modalDown = (state: boolean) => {
    setModalShow(state);
    console.log(modalShow);
  };

  return (
    <>
      {movieInfo && (
        <div
          className="movie-detail w-[100%]"
          style={{
            backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.9)), url(${movieInfo.poster})`,
            backgroundSize: "auto 800px, contain",
            backgroundRepeat: "no-repeat",
          }}
        >
          <NoneNavHeader type="share" />
          {/* info영역 */}
          <div className="flex flex-col items-center mt-72">
            <div className="text-[4vh] px-6 text-center">{movieInfo.title}</div>
            <div className="flex flex-row gap-2 mt-4 text-[2vh]">
              <div>{movieInfo.releaseAt} 개봉</div>
              <span>|</span>
              <div>{movieInfo.runningTime}분</div>
              <span>|</span>
              <div>{movieInfo.age}</div>
            </div>
            <div className="w-[90%] mt-8">
              {fundingInfo && (
                <InfoArea status={movieInfo.status} fundInfo={fundinfo} />
              )}
            </div>
          </div>
          {/* 상세정보영역 */}
          <div>
            <div className="info-detail flex flex-row justify-around mt-10 text-[2.3vh] border-red-700">
              <div
                className={`border-red-700 ${
                  infoCategory == 0 ? "select" : ""
                }`}
                onClick={() => setInfoCategory(0)}
              >
                영화 정보
              </div>
              <div
                className={`border-red-700 ${
                  infoCategory == 1 ? "select" : ""
                }`}
                onClick={() => setInfoCategory(1)}
              >
                펀딩 정보
              </div>
              <div
                className={`border-red-700 ${
                  infoCategory == 2 ? "select" : ""
                }`}
                onClick={() => setInfoCategory(2)}
              >
                유의 사항
              </div>
            </div>
            <div className="px-4 pt-4 pb-20">
              {infoCategory == 0 && (
                <AboutMovie actors={movieInfo.actors} plot={movieInfo.plot} />
              )}
              {infoCategory == 1 && <AboutFunding />}
              {infoCategory == 2 && <AboutNote />}
            </div>
          </div>
          {/* 버튼 */}
          <div className="z-[2] button-area cursor-pointer sticky bottom-0 w-[100%] h-[8vh]">
            <MovieDetailButton
              id={movieInfo.movieId}
              status={movieInfo.status}
              likeCnt={movieInfo.likeCnt}
              hopeCnt={movieInfo.hopeCnt}
              modalDown={modalDown}
              sendFundingInfo={sendFundingInfo}
              fundingId={fundinfo?.fundingId}
            />
          </div>
          {modalShow && <HopeSurvey modalDown={modalDown} id={movieId} />}
        </div>
      )}
    </>
  );
};

export default MovieDetail;
