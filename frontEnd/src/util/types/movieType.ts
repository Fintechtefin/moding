export interface Movie {
  movieId: number;
  poster: string;
  status: string;
}

export interface MovieRanking extends Movie {
  searchCnt: number;
  refreshTime: number;
}
