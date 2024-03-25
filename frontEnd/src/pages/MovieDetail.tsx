import { useState } from "react";
import "@/assets/styles/movieDetail/MovieDetail.scss";
import NoneNavHeader from "@components/NoneNavHeader";
import AboutMovie from "@components/movieDetail/AboutMovie";
import AboutFunding from "@components/movieDetail/AboutFunding";
import AboutNote from "@components/movieDetail/AboutNote";
import MovieDetailButton from "@components/movieDetail/MovieDetailButton";
import HopeSurvey from "@components/movieDetail/HopeSurvey";
import InfoArea from "@components/movieDetail/InfoArea";

interface MovieInfo {
  id: number;
  title: string;
  status: string;
  release: string;
  runningTime: number;
  age: string;
  actors: string;
  plot: string;
  poster: string;
  likeCnt: number;
  hopeCnt: number;
  alarmCnt: number;
}

const MovieDetail = () => {
  const [movieInfo, setMovieInfo] = useState<MovieInfo>({
    id: 1,
    title: "밀정",
    status: "무딩 준비 중",
    release: "2016.09.07",
    runningTime: 140,
    age: "15세관람가",
    actors: "공유, 송강호, 한지민, 엄태구, 신성록",
    plot: "1920년대 일제강점기.조선인 출신 일본경찰 이정출(송강호)은 무장독립운동 단체 의열단의 뒤를 캐라는 특명으로 의열단의 리더 김우진(공유)에게 접근하고, 한 시대의 양 극단에 서 있는 두 사람은 서로의 정체와 의도를 알면서도 속내를 감춘 채 가까워진다. 출처를 알 수 없는 정보가 쌍방간에 새어나가고 누가 밀정인지 알 수 없는 가운데, 의열단은 일제의 주요 시설을 파괴할 폭탄을 경성으로 들여오기 위해, 그리고 일본 경찰은 그들을 쫓아 모두 상해에 모인다.잡아야만 하는 자들과 잡힐 수 없는 자들 사이, 자신의 목표를 위해 서로를 이용하려는 암투와 회유, 교란 작전이 숨가쁘게 펼쳐지는 긴장감 속에서 폭탄을 실은 열차는 국경을 넘어 경성으로 향하는데…",
    poster: "http://file.koreafilm.or.kr/thm/02/00/04/28/tn_DPK011707.jpg",
    likeCnt: 100,
    hopeCnt: 160,
    alarmCnt: 123,
  });

  const [infoCategory, setInfoCategory] = useState(0);
  const [modalShow, setModalShow] = useState(true);
  // 무딩 준비 중이거나 무딩 완료 시에 false로 바꾸기
  const [fundingInfo, setFundingInfo] = useState(true);

  return (
    <div
      className="movie-detail w-[100%]"
      style={{
        backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.9)), url(${movieInfo.poster})`,
        backgroundSize: "auto 800px, contain",
        backgroundRepeat: "no-repeat",
      }}
    >
      <NoneNavHeader type="share" />
      {/* info영역 */}
      <div className="flex flex-col items-center mt-72">
        <div className="text-[4vh] px-6 text-center">{movieInfo.title}</div>
        <div className="flex flex-row gap-2 mt-4 text-[2vh]">
          <div>{movieInfo.release} 개봉</div>
          <span>|</span>
          <div>{movieInfo.runningTime}분</div>
          <span>|</span>
          <div>{movieInfo.age}</div>
        </div>
      </div>
      {/* 상세정보영역 */}
      <div>
        <div className="info-detail flex flex-row justify-around mt-10 text-[2.3vh] border-red-700">
          <div
            className={`border-red-700 ${infoCategory == 0 ? "select" : ""}`}
            onClick={() => setInfoCategory(0)}
          >
            영화 정보
          </div>
          <div
            className={`border-red-700 ${infoCategory == 1 ? "select" : ""}`}
            onClick={() => setInfoCategory(1)}
          >
            펀딩 정보
          </div>
          <div
            className={`border-red-700 ${infoCategory == 2 ? "select" : ""}`}
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
          id={movieInfo.id}
          status={movieInfo.status}
          likeCnt={movieInfo.likeCnt}
          hopeCnt={movieInfo.hopeCnt}
          alarmCnt={movieInfo.alarmCnt}
        />
      </div>
      {modalShow && <HopeSurvey />}
    </div>
  );
};

export default MovieDetail;
