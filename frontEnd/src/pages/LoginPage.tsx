import SocialKakao from "@components/login/SocialKakao";
import SocialNaver from "@components/login/SocialNaver";

const LoginPage = () => {
  return (
    <div className="h-[100vh]">
      <div>로그인페이지 입니다</div>
      <div>
        <SocialNaver />
        <SocialKakao />
      </div>
    </div>
  );
};

export default LoginPage;
