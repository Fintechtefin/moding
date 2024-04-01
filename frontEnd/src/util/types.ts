export interface Subscribe {
  id: number;
  name: string;
  state: string;
  count: number;
  url: string;
}

export interface ISeat {
  line: string;
  col: number;
}

export interface ISeats {
  seat: ISeat[];
}

export interface ITicket {
  seats: ISeats;
  poster: string;
  age: string;
  title: string;
  date: string;
  startTime: string;
  runningTime: number;
  count: number;
  name: string;
  number: number;
}

export interface ProgressMovie {
  id: number;
  movieId: number;
  endAt: string;
  moviePoster: string;
  movieTitle: string;
  orderUuid: string;
  participantCount: number;
  recruitedCount: number;
}

export interface FundingCompleted {
  title: string;
  poster: string;
  movieId: number;
  fundingFinalResult: number;
  goalCnt: number;
  attendCnt: number;
  reservationId: number;
  date: string;
}
