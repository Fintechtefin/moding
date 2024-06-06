import kakao_logo from "@assets/images/kakao_logo.webp";

const SocialKakao = () => {
  const Rest_api_key = "d05009f41c13bdd89ac1bad897d45a01"; //REST API KEY
  const redirect_uri = `${import.meta.env.VITE_REDIRECT_URL}/login/KAKAO`; //Redirect URI
  // oauth 요청 URL
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`;
  const handleLogin = () => {
    window.location.href = kakaoURL;
  };
  return (
    <>
      <img
        className="w-[50px]"
        src={kakao_logo}
        alt="카카오로그인"
        onClick={handleLogin}
      />
    </>
  );
};
export default SocialKakao;
