import MovieCarousel from "@components/movieList/MovieCarousel";
import "@assets/styles/ModingOffice.scss";

const ModingOffice = () => {
  return (
    <div className="movielist-bg">
      <div className="neon text-center text-3xl">
        무<span>딩</span>오피스
      </div>
      <MovieCarousel />
    </div>
  );
};

export default ModingOffice;
