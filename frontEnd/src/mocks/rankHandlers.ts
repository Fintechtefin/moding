import { http, HttpResponse } from "msw";

export const rankHandlers = [
  http.get("/api/fundings", ({ request }) => {
    const url = new URL(request.url);
    const status = url.searchParams.get("status");

    if (status == "progress") {
      return HttpResponse.json([
        {
          movieId: 1,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/85/tn_DPK014285.jpg",
          status: "무딩중",
          crowdCnt: 200,
          peopleCnt: 175,
          requestCnt: 0,
        },
        {
          movieId: 2,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/43/tn_DPK012446.jpg",
          status: "무딩중",
          crowdCnt: 200,
          peopleCnt: 150,
          requestCnt: 0,
        },
        {
          movieId: 3,
          poster: "http://file.koreafilm.or.kr/thm/02/99/18/07/tn_DPK020635.jpg",
          status: "무딩중",
          crowdCnt: 200,
          peopleCnt: 130,
          requestCnt: 0,
        },
        {
          movieId: 4,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/57/tn_DPK013042.jpg",
          status: "무딩중",
          crowdCnt: 200,
          peopleCnt: 120,
          requestCnt: 0,
        },
        {
          movieId: 5,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/53/tn_DPK012845.jpg",
          status: "무딩중",
          crowdCnt: 200,
          peopleCnt: 90,
          requestCnt: 0,
        },
        {
          movieId: 6,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/27/tn_DPK011651.jpg",
          status: "무딩중",
          crowdCnt: 200,
          peopleCnt: 80,
          requestCnt: 0,
        },
        {
          movieId: 7,
          poster: "http://file.koreafilm.or.kr/thm/02/99/17/68/tn_DPK019003.jpg",
          status: "무딩중",
          crowdCnt: 200,
          peopleCnt: 70,
          requestCnt: 0,
        },
        {
          movieId: 8,
          poster: "http://file.koreafilm.or.kr/thm/02/00/03/82/tn_DPK07435A.jpg",
          status: "무딩중",
          crowdCnt: 200,
          peopleCnt: 50,
          requestCnt: 0,
        },
        {
          movieId: 9,
          poster: "http://file.koreafilm.or.kr/thm/02/00/05/15/tn_DPK014533.jpg",
          status: "무딩중",
          crowdCnt: 200,
          peopleCnt: 40,
          requestCnt: 0,
        },
        {
          movieId: 10,
          poster: "http://file.koreafilm.or.kr/thm/02/00/01/39/tn_DPK004235.JPG",
          status: "무딩중",
          crowdCnt: 200,
          peopleCnt: 20,
          requestCnt: 0,
        },
      ]);
    }
    if (status == "request") {
      return HttpResponse.json([
        {
          movieId: 11,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/08/tn_DPK010872.jpg",
          status: "무딩 준비 중",
          crowdCnt: 10,
          peopleCnt: 184,
          requestCnt: 200,
        },
        {
          movieId: 2,
          poster: "http://file.koreafilm.or.kr/thm/02/99/18/04/tn_DPK020523.jpg",
          status: "무딩 준비 중",
          crowdCnt: 10,
          peopleCnt: 150,
          requestCnt: 200,
        },
        {
          movieId: 3,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/55/tn_DPK012925.jpg",
          status: "무딩 준비 중",
          crowdCnt: 10,
          peopleCnt: 123,
          requestCnt: 200,
        },
        {
          movieId: 4,
          poster: "http://file.koreafilm.or.kr/thm/02/00/02/93/tn_DPK010000.JPG",
          status: "무딩 준비 중",
          crowdCnt: 10,
          peopleCnt: 110,
          requestCnt: 200,
        },
        {
          movieId: 5,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/56/tn_DPK013000.jpg",
          status: "무딩 준비 중",
          crowdCnt: 10,
          peopleCnt: 106,
          requestCnt: 200,
        },
        {
          movieId: 6,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/28/tn_DPK011707.jpg",
          status: "무딩 준비 중",
          crowdCnt: 10,
          peopleCnt: 100,
          requestCnt: 200,
        },
        {
          movieId: 7,
          poster: "http://file.koreafilm.or.kr/thm/02/00/01/39/tn_DPK004235.JPG",
          status: "무딩 준비 중",
          crowdCnt: 0,
          peopleCnt: 90,
          requestCnt: 200,
        },
        {
          movieId: 8,
          poster: "http://file.koreafilm.or.kr/thm/02/00/01/92/tn_DPK005793.JPG",
          status: "무딩 준비 중",
          crowdCnt: 10,
          peopleCnt: 85,
          requestCnt: 200,
        },
        {
          movieId: 9,
          poster: "http://file.koreafilm.or.kr/thm/02/00/05/34/tn_DPK015079.jpg",
          status: "무딩 준비 중",
          crowdCnt: 10,
          peopleCnt: 80,
          requestCnt: 200,
        },
        {
          movieId: 10,
          poster: "http://file.koreafilm.or.kr/thm/02/00/04/23/tn_DPK011412.jpg",
          status: "무딩 준비 중",
          crowdCnt: 10,
          peopleCnt: 20,
          requestCnt: 200,
        },
      ]);
    }
  }),

  http.get("/api/fundings/movies/:id", ({ request, params }) => {
    const { id } = params;
    if (id == "1") {
      return HttpResponse.json({
        movieId: 1,
        poster: "http://file.koreafilm.or.kr/thm/02/00/04/85/tn_DPK014285.jpg",
        // status: "예매예정",
        status: "예매 진행",
        title: "극한직업",
        releaseAt: "2019.01.23",
        runningTime: 111,
        age: "15세관람가",
        actors: "류승룡, 이하늬, 진선규, 이동휘, 공명",
        plot: "낮에는 치킨장사! 밤에는 잠복근무!지금까지 이런 수사는 없었다!불철주야 달리고 구르지만 실적은 바닥, 급기야 해체 위기를 맞는 마약반!더 이상 물러설 곳이 없는 팀의 맏형 고반장은 국제 범죄조직의 국내 마약 밀반입 정황을 포착하고 장형사, 마형사, 영호, 재훈까지 4명의 팀원들과 함께 잠복 수사에 나선다.마약반은 24시간 감시를 위해 범죄조직의 아지트 앞 치킨집을 인수해 위장 창업을 하게 되고, 뜻밖의 절대미각을 지닌 마형사의 숨은 재능으로 치킨집은 일약 맛집으로 입소문이 나기 시작한다.수사는 뒷전, 치킨장사로 눈코 뜰 새 없이 바빠진 마약반에게 어느 날 절호의 기회가 찾아오는데…범인을 잡을 것인가, 닭을 잡을 것인가!2019년 새해, 출동!",
        like: true,
        likeCnt: 80,
        request: false,
        hopeCnt: 100,
        total: 200,
        genere: ["코미디"],
        success: 100,
      });
    }
    if (id == "3") {
      return HttpResponse.json({
        movieId: 3,
        poster: "http://file.koreafilm.or.kr/thm/02/99/18/07/tn_DPK020635.jpg",
        status: "무딩중",
        title: "헤어질 결심",
        releaseAt: "2022.06.29",
        runningTime: 138,
        age: "15세관람가",
        actors: "탕웨이, 박해일, 이정현, 박용우, 고경표",
        plot: "산 정상에서 추락한 한 남자의 변사 사건.담당 형사 '해준'(박해일)은 사망자의 아내 '서래'(탕웨이)와 마주하게 된다. 산에 가서 안 오면 걱정했어요, 마침내 죽을까 봐. 남편의 죽음 앞에서 특별한 동요를 보이지 않는 '서래'.경찰은 보통의 유가족과는 다른 '서래'를 용의선상에 올린다.'해준'은 사건 당일의 알리바이 탐문과 신문, 잠복수사를 통해 '서래'를 알아가면서 그녀에 대한 관심이 점점 커져가는 것을 느낀다.한편, 좀처럼 속을 짐작하기 어려운 '서래'는 상대가 자신을 의심한다는 것을 알면서도 조금의 망설임도 없이 '해준'을 대하는데….진심을 숨기는 용의자용의자에게 의심과 관심을 동시에 느끼는 형사그들의  헤어질 결심",
        like: true,
        likeCnt: 119,
        request: false,
        hopeCnt: 111,
        total: 200,
        genere: ["로맨스", "드라마", "미스터리"],
        success: 100,
      });
    }
    if (id == "11") {
      return HttpResponse.json({
        movieId: 11,
        poster: "http://file.koreafilm.or.kr/thm/02/00/04/08/tn_DPK010872.jpg",
        status: "무딩 예정",
        title: "베테랑",
        releaseAt: "2015.08.05",
        runningTime: 123,
        age: "15세관람가",
        actors: "황정민, 유아인, 유해진, 오달수, 장윤주",
        plot: "한 번 꽂힌 것은 무조건 끝을 보는 행동파 ‘서도철’(황정민), 20년 경력의 승부사 ‘오팀장’(오달수), 위장 전문 홍일점 ‘미스봉’(장윤주), 육체파 ‘왕형사’(오대환), 막내 ‘윤형사’(김시후)까지 없고, 못 잡는 것 없고, 봐주는 것 없는 특수 강력사건 담당 광역수사대.오랫동안 쫓던 대형 범죄를 해결한 후 숨을 돌리려는 찰나, 서도철은 재벌 3세 ‘조태오’(유아인)를 만나게 된다. 세상 무서울 것 없는 안하무인의 조태오와 언제나 그의 곁을 지키는 오른팔 ‘최상무’(유해진). 서도철은 의문의 사건을 쫓던 중 그들이 사건의 배후에 있음을 직감한다. 건들면 다친다는 충고에도 불구하고 포기하지 않는 서도철의 집념에 판은 걷잡을 수 없이 커져가고 조태오는 이를 비웃기라도 하듯 유유히 포위망을 빠져 나가는데… 베테랑 광역수사대 VS 유아독존 재벌 3세2015년 여름, 자존심을 건 한판 대결이 시작된다!",
        like: false,
        likeCnt: 80,
        request: false,
        hopeCnt: 184,
        total: 200,
        genere: ["드라마", "액션"],
        success: 100,
      });
    }
  }),

  http.get("/api/fundings/open/:id", ({ request, params }) => {
    const { id } = params;
    if (id == "1") {
      return HttpResponse.json({
        cinemaName: "롯데시네마 건대입구점",
        crowdCnt: 200,
        date: "2024-04-21T18:30:00.00",
        fundingId: 1,
        peopleCnt: 175,
        price: 16000,
        time: "18:30",
      });
    }
    if (id == "3") {
      return HttpResponse.json({
        cinemaName: "롯데시네마 건대입구점",
        crowdCnt: 200,
        date: "2024-04-23T18:30:00.000Z",
        fundingId: 1,
        peopleCnt: 123,
        price: 16000,
        time: "18:30",
      });
    }
  }),
];
