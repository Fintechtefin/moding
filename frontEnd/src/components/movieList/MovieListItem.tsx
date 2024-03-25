import { Poster } from "./MovieList";

type PosterProps = {
  poster: Poster;
};

const MovieListItem = ({ poster }: PosterProps) => {
  const { name, state, count, url } = poster;

  return (
    <div className="w-[100%] relative">
      <div className="text-white absolute z-[1] flex justify-between w-[100%] p-[1vh]">
        <div>
          <div className="bg-[#C00202] blur-sm w-[100%] h-[100%] border-2 border-[#C00202]"></div>
          <div className="text-[1.5vh] relative top-[-100%] right-0 my-[0.5vh] mx-[1vh]">{state}</div>
        </div>
      </div>
      <img className="w-[100%] h-[100%] object-cover brightness-[90%]" src={url} alt="" />
    </div>
  );
};

export default MovieListItem;
