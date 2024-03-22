const AboutFunding = () => {
  const fundInfo = "flex flex-col text-center gap-10";

  return (
    <div className="flex flex-col item-center gap-28 mt-32">
      <div className={fundInfo}>
        <div className="text-2xl">1. 무딩 요청</div>
        <div>
          <div>원하는 영화 무딩 요청하기</div>
          <div></div>
        </div>
        <div>
          <div>희망 지역, 희망 시간 체크하기</div>
          <div></div>
        </div>
        <div>
          <div>마구마구 공유하기</div>
          <div></div>
        </div>
        <div>
          <div>요청 200 달성 시 심사 후 무딩 오픈!!</div>
          <div></div>
        </div>
      </div>
      <div className={fundInfo}>
        <div className="text-2xl">2. 무딩 참여</div>
        <div>
          <div>장소, 시간, 가격 확인하기</div>
          <div></div>
        </div>
        <div>
          <div>장소 OK, 시간 OK, 가격 OK -&gt; 참여하기</div>
          <div></div>
        </div>
        <div>
          <div>인원 선택 후 결제하기</div>
          <div></div>
        </div>
        <div>
          <div>마구마구 공유하기</div>
          <div></div>
        </div>
        <div>
          <div>90% 이상 참여하면 상영확정!!</div>
          <div></div>
        </div>
      </div>
      <div className={fundInfo}>
        <div className="text-2xl">3. 좌석 예매</div>
        <div>
          <div>펀딩 종료 루 다음날 8시 오픈</div>
          <div></div>
        </div>
        <div>
          <div>예매 좌석 수 만큼 좌석 예매하기</div>
          <div></div>
        </div>
        <div>
          <div>예매 기간이 지나면 랜덤좌석 배정</div>
          <div></div>
        </div>
      </div>
      <div className={fundInfo}>
        <div className="text-2xl">4. 관람하기</div>
        <div>
          <div>즐겁게 관람하기</div>
          <div></div>
        </div>
      </div>
    </div>
  );
};

export default AboutFunding;
