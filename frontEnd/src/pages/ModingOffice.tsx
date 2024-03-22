import { NavLink } from "react-router-dom";

const ModingOffice = () => {
  return (
    <div className="movielist-container">
      <div>무딩오피스페이지 입니다.</div>
      <NavLink to={"/movie/list"}>
        <div>무딩리스트로 아동하기</div>
      </NavLink>
    </div>
  );
};

export default ModingOffice;
