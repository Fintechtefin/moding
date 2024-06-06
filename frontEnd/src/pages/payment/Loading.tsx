import "@assets/styles/payment/Loading.scss";

const Loading = () => {
  return (
    <div className="fixed inset-0 z-20 flex items-center justify-center m-auto bg-black bg-opacity-30 Loading">
      <div className="spinner text-[3vh] font-bold tracking-[1.5vh] text-textGray flex items-center justify-center">
        <span>M</span>
        <span>O</span>
        <span>D</span>
        <span>I</span>
        <span>N</span>
        <span>G</span>
      </div>
    </div>
  );
};

export default Loading;
