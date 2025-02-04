import { http, HttpResponse } from "msw";

export const movieHandlers = [
  http.get("/api/fundings/movies", ({ request }) => {
    const url = new URL(request.url);
    const id = url.searchParams.get("genre");
    const sort = url.searchParams.get("sort");
    const page = url.searchParams.get("page");
    console.log(id);

    if (id == "1") {
      return HttpResponse.json({
        movieList: [
          {
            movieId: 1,
            poster: "http://file.koreafilm.or.kr/thm/02/00/04/31/tn_DPK011800.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 2,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/53/tn_DPK004651.JPG",
            status: "무딩 준비 중",
          },
          {
            movieId: 3,
            poster: "http://file.koreafilm.or.kr/thm/02/00/03/57/tn_DPK05568A.JPG",
            status: "무딩 준비 중",
          },
          {
            movieId: 4,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/39/tn_DPK004235.JPG",
            status: "무딩중",
          },
          {
            movieId: 5,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/95/tn_DPK005855.JPG",
            status: "무딩 준비 중",
          },
          {
            movieId: 6,
            poster: "http://file.koreafilm.or.kr/thm/02/00/04/23/tn_DPK011408.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 7,
            poster: "http://file.koreafilm.or.kr/thm/02/00/03/82/tn_DPK06336A.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 8,
            poster: "http://file.koreafilm.or.kr/thm/02/00/03/90/tn_DPK10119A.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 9,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/10/tn_DPK003209.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 10,
            poster: "http://file.koreafilm.or.kr/thm/02/00/03/32/tn_DPK06678A.jpg",
            status: "무딩 준비 중",
          },
        ],
        totalCnt: 357,
      });
    }

    if (id == "3" && sort == "titleAsc") {
      return HttpResponse.json({
        movieList: [
          {
            movieId: 1,
            poster: "http://file.koreafilm.or.kr/thm/02/00/05/57/tn_DPK015779.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 2,
            poster: "http://file.koreafilm.or.kr/thm/02/00/03/81/tn_DPK10093A.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 3,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/92/tn_DPK005793.JPG",
            status: "무딩 준비 중",
          },
          {
            movieId: 4,
            poster: "http://file.koreafilm.or.kr/thm/02/00/05/34/tn_DPK015079.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 5,
            poster: "http://file.koreafilm.or.kr/thm/02/00/04/31/tn_DPK011800.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 6,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/53/tn_DPK004650.JPG",
            status: "무딩 준비 중",
          },
          {
            movieId: 7,
            poster: "http://file.koreafilm.or.kr/thm/02/00/02/00/tn_DPK006044.JPG",
            status: "무딩 준비 중",
          },
          {
            movieId: 8,
            poster: "http://file.koreafilm.or.kr/thm/02/00/04/54/tn_DPK012877.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 9,
            poster: "http://file.koreafilm.or.kr/thm/02/00/05/61/tn_DPK016016.jpg",
            status: "무딩 준비 중",
          },
        ],
        totalCnt: 1287,
      });
    }

    if (id == "3" && sort == "titleDesc") {
      return HttpResponse.json({
        movieList: [
          {
            movieId: 1,
            poster: "http://file.koreafilm.or.kr/thm/02/00/04/65/tn_DPK013358.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 2,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/67/tn_DPK005503.JPG",
            status: "무딩 준비 중",
          },
          {
            movieId: 3,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/45/tn_DPK004390.JPG",
            status: "무딩 준비 중",
          },
          {
            movieId: 4,
            poster: "http://file.koreafilm.or.kr/thm/02/00/03/87/tn_DPK08353A.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 5,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/39/tn_DPK004241.JPG",
            status: "무딩 준비 중",
          },
          {
            movieId: 6,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/07/tn_DPK020635.jpg",
            status: "무딩중",
          },
          {
            movieId: 7,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/76/tn_DPK019304.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 8,
            poster: "http://file.koreafilm.or.kr/thm/02/00/03/31/tn_DPK08154A.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 9,
            poster: "http://file.koreafilm.or.kr/thm/02/00/04/88/tn_DPK014347.jpg",
            status: "무딩 준비 중",
          },
        ],
        totalCnt: 1287,
      });
    }

    if (id == "3" && sort == "likeAsc") {
      return HttpResponse.json({
        movieList: [
          {
            movieId: 1,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/07/tn_DPK020635.jpg",
            status: "무딩중",
          },
          {
            movieId: 2,
            poster: "http://file.koreafilm.or.kr/thm/02/00/03/82/tn_DPK07435A.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 3,
            poster: "http://file.koreafilm.or.kr/thm/02/00/04/55/tn_DPK012925.jpg",
            status: "무딩예정",
          },
          {
            movieId: 4,
            poster: "http://file.koreafilm.or.kr/thm/02/00/05/34/tn_DPK015079.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 5,
            poster: "http://file.koreafilm.or.kr/thm/02/00/04/43/tn_DPK012446.jpg",
            status: "무딩중",
          },
          {
            movieId: 6,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/52/tn_DPK004608.JPG",
            status: "무딩 준비 중",
          },
          {
            movieId: 7,
            poster: "http://file.koreafilm.or.kr/thm/02/00/01/66/tn_DPK005480.JPG",
            status: "무딩종료",
          },
          {
            movieId: 8,
            poster: "http://file.koreafilm.or.kr/thm/02/00/04/54/tn_DPK012877.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 9,
            poster: "http://file.koreafilm.or.kr/thm/02/00/05/61/tn_DPK016016.jpg",
            status: "무딩 준비 중",
          },
        ],
        totalCnt: 1287,
      });
    }

    if (id == "3" && sort == "likeDesc" && page == "1") {
      return HttpResponse.json({
        movieList: [
          {
            movieId: 1,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021524.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 2,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/27/tn_DPK021510.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 3,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/24/tn_DPK021396.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 4,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/21/tn_DPK021283.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 5,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/19/tn_DPK021205.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 6,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/17/tn_DPK021106.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 7,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/13/tn_DPK020963.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 8,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/12/tn_DPK020910.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 9,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/10/tn_DPK020798.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 10,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/03/tn_DPK020503.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 11,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/03/tn_DPF027206.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 12,
            poster: "http://file.koreafilm.or.kr/thm/02/99/18/01/tn_DPK020414.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 13,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/98/tn_DPK020299.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 14,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/97/tn_DPK020277.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 15,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/95/tn_DPK020222.jpg",
            status: "무딩 준비 중",
          },
        ],
        totalCnt: 1287,
      });
    }

    if (id == "3" && sort == "likeDesc" && page == "2") {
      return HttpResponse.json({
        movieList: [
          {
            movieId: 16,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/95/tn_DPK020221.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 17,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/97/tn_DPK020280.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 18,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/94/tn_DPK020168.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 19,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/91/tn_DPK020082.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 20,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/90/tn_DPK020050.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 21,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/91/tn_DPK020084.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 22,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/92/tn_DPK020104.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 23,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/88/tn_DPK019868.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 24,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/88/tn_DPK019853.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 25,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/84/tn_DPK019707.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 26,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/87/tn_DPK019802.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 27,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/82/tn_DPK019591.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 28,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/84/tn_DPK019700.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 29,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/83/tn_DPA001657.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 30,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/82/tn_DPK019588.jpg",
            status: "무딩 준비 중",
          },
        ],
        totalCnt: 1287,
      });
    }

    if (id == "3" && sort == "likeDesc" && page == "3") {
      return HttpResponse.json({
        movieList: [
          {
            movieId: 31,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/80/tn_DPK019475.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 32,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/82/tn_DPK019590.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 33,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/80/tn_DPK019468.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 34,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/81/tn_DPK019557.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 35,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/79/tn_DPK019464.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 36,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/76/tn_DPK019304.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 37,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/75/tn_DPK019270.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 38,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/75/tn_DPK019235.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 39,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/75/tn_DPK019258.jpg",
            status: "무딩 준비 중",
          },
          {
            movieId: 40,
            poster: "http://file.koreafilm.or.kr/thm/02/99/17/73/tn_DPK019167.jpg",
            status: "무딩 준비 중",
          },
        ],
        totalCnt: 1287,
      });
    }
  }),

  http.get("/api/fundings/movies/popular", ({ request }) => {
    return HttpResponse.json([
      {
        movieId: 1,
        poster: "http://file.koreafilm.or.kr/thm/02/00/04/43/tn_DPK012446.jpg",
        status: "무딩중",
        searchCnt: 1,
        refreshTime: 2,
      },
      {
        movieId: 2,
        poster: "http://file.koreafilm.or.kr/thm/02/99/18/07/tn_DPK020635.jpg",
        status: "무딩중",
        searchCnt: 1,
        refreshTime: 2,
      },
      {
        movieId: 3,
        poster: "http://file.koreafilm.or.kr/thm/02/99/17/75/tn_DPK019258.jpg",
        status: "무딩 준비 중",
        searchCnt: 1,
        refreshTime: 2,
      },
      {
        movieId: 4,
        poster: "http://file.koreafilm.or.kr/thm/02/00/04/57/tn_DPK013042.jpg",
        status: "무딩중",
        searchCnt: 1,
        refreshTime: 2,
      },
      {
        movieId: 5,
        poster: "http://file.koreafilm.or.kr/thm/02/00/04/55/tn_DPK012925.jpg",
        status: "무딩예정",
        searchCnt: 1,
        refreshTime: 2,
      },
      {
        movieId: 6,
        poster: "http://file.koreafilm.or.kr/thm/02/00/03/82/tn_DPK07435A.jpg",
        status: "무딩 중",
        searchCnt: 1,
        refreshTime: 2,
      },
      {
        movieId: 7,
        poster: "http://file.koreafilm.or.kr/thm/02/00/03/07/tn_DPK010215.JPG",
        status: "무딩 준비 중",
        searchCnt: 1,
        refreshTime: 2,
      },
      {
        movieId: 8,
        poster: "http://file.koreafilm.or.kr/thm/02/00/04/53/tn_DPK012845.jpg",
        status: "무딩중",
        searchCnt: 1,
        refreshTime: 2,
      },
      {
        movieId: 9,
        poster: "http://file.koreafilm.or.kr/thm/02/99/17/68/tn_DPK019003.jpg",
        status: "무딩 예정",
        searchCnt: 1,
        refreshTime: 2,
      },
      {
        movieId: 10,
        poster: "http://file.koreafilm.or.kr/thm/02/00/05/29/tn_DPK014922.jpg",
        status: "무딩 준비 중",
        searchCnt: 1,
        refreshTime: 2,
      },
    ]);
  }),

  http.get("/api/fundings/movies/search", ({ request }) => {
    const url = new URL(request.url);
    const word = url.searchParams.get("word");

    if (word == "부산행") {
      return HttpResponse.json([
        {
          movieId: 1,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/27/tn_DPK011651.jpg",
          status: "무딩중",
        },
      ]);
    }
    if (word == "공유") {
      return HttpResponse.json([
        {
          movieId: 1,
          poster: "http://file.koreafilm.or.kr/thm/02/00/05/34/tn_DPK015079.jpg",
          status: "무딩예정",
        },
        {
          movieId: 2,
          poster: "http://file.koreafilm.or.kr/thm/02/00/01/39/tn_DPK004235.JPG",
          status: "무딩중",
        },
        {
          movieId: 3,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/23/tn_DPK011408.jpg",
          status: "무딩 준비 중",
        },
        {
          movieId: 4,
          poster: "http://file.koreafilm.or.kr/thm/02/00/01/48/tn_DPK004495.JPG",
          status: "무딩 준비 중",
        },
        {
          movieId: 5,
          poster: "http://file.koreafilm.or.kr/thm/02/00/03/85/tn_DPK05896A.jpg",
          status: "무딩 준비 중",
        },
        {
          movieId: 6,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/28/tn_DPK011707.jpg",
          status: "무딩예정",
        },
        {
          movieId: 7,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/27/tn_DPK011651.jpg",
          status: "무딩중",
        },
        {
          movieId: 8,
          poster: "http://file.koreafilm.or.kr/thm/02/99/17/12/tn_DPK016544.jpg",
          status: "무딩 준비 중",
        },
      ]);
    }
  }),
];
