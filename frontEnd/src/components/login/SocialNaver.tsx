import naver_logo from "@assets/images/naver_logo.webp";

const SocialNaver = () => {
  const clientId = "i6wNtvL6tR5n9p0ERkNi";

  const stateString = "DNolvvC9x8";

  const reUrl = "http://localhost:5174/login/Naver";

  const naverUrl = `https://nid.naver.com/oauth2.0/authorize?client_id=${clientId}&response_type=code&redirect_uri=${reUrl}&state=${stateString}`;

  const handleLogin = () => {
    window.location.href = naverUrl;
  };
  return (
    <>
      <img
        className="w-[50px]"
        src={naver_logo}
        alt="네이버로그인"
        onClick={handleLogin}
      />
    </>
  );
};

export default SocialNaver;
