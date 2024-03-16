import NavHeader from "@components/NavHeader";
import { ChangeEvent, useState } from "react";
import "@assets/styles/Subscribe/SubscribePage.scss";

const SubscribePage = () => {
  const [check, setCheck] = useState("like");

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setCheck(e.target.value);
  };

  return (
    <div className="subscribe-container">
      <NavHeader leftWord="관심 있는 소식만 모았어요" />
      <div className="tabs">
        <input
          checked={check === "like" ? true : false}
          id="like"
          value="like"
          name="subscribe"
          type="radio"
          className="input"
          onChange={handleChange}
        />
        <label htmlFor="like" className="label">
          좋아요
        </label>
        <input
          checked={check === "req" ? true : false}
          id="req"
          value="req"
          name="subscribe"
          type="radio"
          className="input"
          onChange={handleChange}
        />
        <label htmlFor="req" className="label">
          펀딩 요청
        </label>
      </div>
    </div>
  );
};

export default SubscribePage;
