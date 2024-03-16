import HomeHeader from "@components/HomeHeader";

const HomePage = () => {
  return (
    <div className="home-container">
      <HomeHeader />
      <div className="overflow-auto h-[86vh]">
        <div>홈페이지 입니다.</div>
      </div>
    </div>
  );
};

export default HomePage;
