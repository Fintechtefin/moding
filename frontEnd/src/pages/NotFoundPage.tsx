import { Link, useRouteError } from "react-router-dom";

const NotFoundPage = () => {
  const err = useRouteError();

  console.log(err);

  return (
    <div className="h-[100vh]">
      <div>NotFoundPage</div>
      <Link to="/">무딩 홈으로</Link>
    </div>
  );
};

export default NotFoundPage;
