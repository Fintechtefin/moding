import MovieList from "@components/movieList/MovieList";

const MovieCategory = () => {
  return (
    <div className="movielist-container">
      <div>영화리스트</div>
      <div>
        <MovieList />
      </div>
    </div>
  );
};

export default MovieCategory;
