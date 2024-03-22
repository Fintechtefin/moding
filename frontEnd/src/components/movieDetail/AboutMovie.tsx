interface Props {
  actors: string;
  plot: string;
}

const AboutMovie = ({ actors, plot }: Props) => {
  return (
    <div className="">
      <div>출연배우</div>
      <div>{actors}</div>
      <div>줄거리</div>
      <div>{plot}</div>
    </div>
  );
};

export default AboutMovie;
