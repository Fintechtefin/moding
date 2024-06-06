interface Props {
  actors: string;
  plot: string;
}

const AboutMovie = ({ actors, plot }: Props) => {
  return (
    <div className="mt-5">
      <div className="text-3xl mb-3">출연배우</div>
      <div className="mb-9">{actors}</div>
      <div className="text-3xl mb-3">줄거리</div>
      <div>{plot}</div>
    </div>
  );
};

export default AboutMovie;
