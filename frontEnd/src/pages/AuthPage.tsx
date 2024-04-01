import { useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getCode, getUser } from "@api/login";
import Loading from "@pages/payment/Loading";

const AuthPage = () => {
  const { social } = useParams<{ social: string }>();
  const params = new URL(document.URL).searchParams;
  const code = params.get("code");
  const navigate = useNavigate();

  const tokenGet = async () => {
    if (social && code) {
      await getCode(social, code).then((res) => {
        const data = res.accessToken;
        localStorage.setItem("jwt", data);
      });
      await getUser().then((res) => {
        localStorage.setItem("imageUrl", res.imageUrl);
        localStorage.setItem("nickname", res.nickname);
      });
      navigate("/", { replace: true });
    } else {
      console.error();
    }
  };

  useEffect(() => {
    tokenGet();
  }, []);

  return <Loading />;
};

export default AuthPage;
