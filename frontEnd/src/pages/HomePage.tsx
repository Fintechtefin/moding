import TopNavbar from "@components/TopNavbar";

const HomePage = () => {
  return (
    <div className="home-container">
      <TopNavbar />
      <div className="overflow-auto h-[86vh]">
        <div>홈페이지 입니다.</div>
      </div>
    </div>
  );
};

export default HomePage;
