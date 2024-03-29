import { Link } from "react-router-dom";

const ModingOffice = () => {
  return (
    <div className="movielist-container">
      <div>무딩오피스페이지 입니다.</div>
      <Link to={"/movie/list"} state={{ category: "드라마" }}>
        <div>무딩리스트로 아동하기</div>
      </Link>
    </div>
  );
};

export default ModingOffice;
