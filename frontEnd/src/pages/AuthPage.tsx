import { useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getCode, getUser } from "@api/login";

const AuthPage = () => {
  const { social } = useParams<{ social: string }>();
  const params = new URL(document.URL).searchParams;
  const code = params.get("code");
  const navigate = useNavigate();

  const tokenGet = async () => {
    if (social && code) {
      await getCode(social, code).then((res) => {
        const data = res.data.accessToken;
        localStorage.setItem("jwt", data);
      });
      await getUser().then((res) => {
        const user = res;
        localStorage.setItem("user", user);
      });
      navigate("/", { replace: true });
    } else {
      console.error();
    }
  };

  useEffect(() => {
    tokenGet();
    navigate("/");
  }, []);

  // useEffect(() => {
  //   navigate("/");
  // }, []);

  return <div>{code}</div>;
};

export default AuthPage;
