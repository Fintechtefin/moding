import "@/assets/styles/HomePage.scss";
import ThreeScene from "@components/homePage/ThreeScene";

const HomePage = () => {
  console.log(import.meta.env);
  return (
    <>
      <ThreeScene />
      <div className="sections">
        <section className="section relative">
          <div className="absolute top-[55vh] left-[50%] translate-x-[-50%] text-7xl text-black">
            Moding
            <div className="mt-5 text-sm text-black">
              다시보고 싶은 영화를 영화관에서
            </div>
          </div>
        </section>
        <section className="section">
          <h2></h2>
        </section>
        <section className="section"></section>
        <section className="section"></section>
        <section className="section relative">
          <div className="absolute top-[65vh] left-[50%] translate-x-[-50%] text-5xl text-black">
            Moding
          </div>
        </section>
      </div>
    </>
    // <div className="home-container">
    //   <HomeHeader />
    //   <div className="overflow-auto h-[86vh]">

    //   </div>
    //   {/* {isOpen && <HomeModal />} */}
    // </div>
  );
};

export default HomePage;
