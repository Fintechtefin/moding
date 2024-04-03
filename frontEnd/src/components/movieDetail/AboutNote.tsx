const AboutNote = () => {
  return (
    <div>
      <div className="text-3xl mb-3 mt-5">유의사항</div>
      <div className="mb-2">
        펀딩 기간이 끝난 후에 취소는{" "}
        <span className=" text-red-600">불가합니다.</span>
      </div>
      <div className="mb-2">
        요청하기, 참여하기는{" "}
        <span className=" text-red-600">고지된 연령 이상</span>인 경우에만
        가능합니다.
      </div>
      <div className="mb-2">
        상영장소와 상영시간은 요청 시{" "}
        <span className=" text-red-600">투표</span>를 통해 결정됩니다.
      </div>
      <div className="mb-2">
        상영관 정보는 펀딩이 종료된 후{" "}
        <span className=" text-red-600">발급된 티켓</span>에 표기됩니다.
      </div>
      <div className="mb-2">
        상영관 위치는 영화관 측에 의해 변경될 수 있으며
        <br /> 변경으로 인한 환불을 원하는 경우{" "}
        <span className=" text-red-600">100%</span> 환불됩니다.
      </div>
    </div>
  );
};

export default AboutNote;
