import { useState } from "react";
import { useNavigate } from "react-router-dom";
import NoneNavHeader from "@components/NoneNavHeader";
import axios from "axios";
import profile from "@assets/images/char.webp";
import { IoCameraOutline } from "react-icons/io5";
// import "@assets/styles/myPage/MyPageEdit.scss";

const ProfileEdit = () => {
  const BASE_URL = import.meta.env.VITE_BASE_URL;
  const user = JSON.parse(localStorage.getItem("user"));
  const [nick, setNick] = useState(user?.nickname);
  const [openDelete, setOpenDelete] = useState(false);
  const [imgUrl, setImgUrl] = useState(user?.imageUrl);
  const [preView, setPreView] = useState(user?.imageUrl);
  const navigate = useNavigate();

  const btnClass =
    "cursor-pointer bg-transparent text-[2.5vh] border-none text-white";

  const clearStorage = () => {
    localStorage.removeItem("jwt");
    localStorage.removeItem("user");
    navigate("/login", { replace: true });
  };

  // 로그아웃
  const userLogOut = async () => {
    try {
      await axios.delete(`${BASE_URL}/api/log-out`, {
        headers: {
          Authorization: localStorage.getItem("jwt"),
          "Content-Type": "application/json",
        },
      });
      clearStorage();
    } catch (error) {
      console.log(error);
    }
  };

  // 회원탈퇴
  const userDelete = async () => {
    try {
      await axios.delete(`${BASE_URL}/api/users`, {
        headers: {
          Authorization: localStorage.getItem("jwt"),
        },
      });
      clearStorage();
    } catch (err) {
      console.log(err);
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNick(e.target.value);
  };

  const openDeleteModal = () => {
    setOpenDelete(true);
  };

  const handleChangeFile = (e: React.ChangeEvent<HTMLInputElement>) => {
    // const file = e.target.files[0];
    // const reader = new FileReader();
    // reader.readAsDataURL(file);
    // reader.onloadend = () => {
    //   setPreView(reader.result);
    //   setImgUrl(file);
    // };
  };

  const changeUserInfo = async () => {
    try {
      let res;

      if (user.imageUrl !== imgUrl) {
        const formData = new FormData();
        formData.append("images", imgUrl);
        formData.append("type", "PROFILE");

        res = await axios.post(`${BASE_URL}/api/images`, formData, {
          headers: {
            Authorization: localStorage.getItem("jwt"),
          },
        });
      }

      const res1 = await axios.put(
        `${BASE_URL}/api/users`,
        { imageUrl: res ? res.data.imageNames[0] : imgUrl, nickname: nick },
        {
          headers: {
            Authorization: localStorage.getItem("jwt"),
            "Content-Type": "application/json",
          },
        }
      );

      localStorage.setItem("user", res1.config.data);

      navigate("/user/mypage");
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="relative flex flex-col gap-[4vh] h-[100vh]">
      <NoneNavHeader />
      <div
        className=" absolute cursor-pointer font-bold text-[2.5vh] right-[2.3vh] top-[1.7vh]"
        onClick={changeUserInfo}
      >
        완료
      </div>
      <div className="flex justify-center">
        <label htmlFor="profile-img" className="relative cursor-pointer">
          <img
            src={preView || profile}
            alt=""
            className="w-[12vh] h-[12vh] rounded-[35%]"
          />
          <input
            type="file"
            accept="image/*"
            id="profile-img"
            className="hidden "
            onChange={handleChangeFile}
          />
          <div className="absolute bottom-[-0.5vh] right-[-0.5vh] bg-[#232121] w-[4vh] h-[4vh] flex items-center justify-center rounded-[50%] ">
            <IoCameraOutline className="w-[3vh] h-[3vh] text-white" />
          </div>
        </label>
      </div>
      <div className="flex flex-col gap-[1vh] mx-[2vh]">
        <div className="font-bold text-[2.5vh]">닉네임</div>
        <input
          className="px-[2vh] w-[100%] h-[7vh] text-[2vh] rounded-[2vh] text-black"
          type="text"
          value={nick}
          onChange={handleChange}
        />
      </div>
      <div className="absolute bottom-[25%] left-[50%] flex gap-[3vh] translate-x-[-50%]">
        <button className={btnClass} onClick={userLogOut}>
          로그아웃
        </button>
        <button className={btnClass} onClick={openDeleteModal}>
          회원탈퇴
        </button>
      </div>
      {openDelete && (
        <>
          <div
            className="fixed inset-0 mx-auto filter-open bg-black/70"
            onClick={() => setOpenDelete(false)}
          ></div>
          <div className="a z-1 absolute flex flex-col gap-[2vh] p-[2vh] w-[30vh] top-[50%] left-[50%] bg-white rounded-[2vh] translate-x-[-50%] translate-y-[-50%]">
            <h2 className="a py-[1vh] text-[2.5vh] text-black font-bold">
              회원 탈퇴
            </h2>
            <div className="flex flex-col gap-[1vh] text-[1.5vh] ">
              <div className="text-gray-600">
                회원 탈퇴 시 계정 정보가 삭제되어 복구가 불가해요
              </div>
              <div className="text-gray-600">정말로 탈퇴하시겠어요?</div>
            </div>
            <div className="px-[1vh] flex gap-[2vh]">
              <button
                className="cancel cursor-pointer border-none w-[100%] flex justify-center px-[1.5vh] text-[1.5vh] font-bold rounded-[1vh] bg-gray-400"
                onClick={() => setOpenDelete(false)}
              >
                더 써볼래요
              </button>
              <button
                className="cancel cursor-pointer border-none w-[100%] flex justify-center px-[1.5vh] text-[1.5vh] font-bold rounded-[1vh] bg-red-500"
                onClick={userDelete}
              >
                떠날래요
              </button>
            </div>
          </div>
        </>
      )}
    </div>
  );
};

export default ProfileEdit;
