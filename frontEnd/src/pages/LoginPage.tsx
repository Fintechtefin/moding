import NoneNavHeader from "@components/NoneNavHeader";
import SocialKakao from "@components/login/SocialKakao";
import SocialNaver from "@components/login/SocialNaver";

const LoginPage = () => {
  return (
    <div className="h-[100vh]">
      <NoneNavHeader centerText="로그인" />
      <div>로그인페이지 입니다</div>
      <div>
        <SocialNaver />
        <SocialKakao />
      </div>
    </div>
  );
};

export default LoginPage;
