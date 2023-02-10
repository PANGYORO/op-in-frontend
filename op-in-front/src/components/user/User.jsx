import React, { useEffect, useState } from "react";
import BasicProfile from "@assets/basicprofile.png";
import { useNavigate } from "react-router-dom";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";
import http from "@api/http";

const User = ({ profileimg = BasicProfile, nickname = "test", isfollow = false }) => {
  const followClassState =
    "py-1 px-3 bg-blue-600 hover:bg-blue-700 focus:ring-blue-500 focus:ring-offset-red-200 text-white  transition ease-in duration-200 text-center font-semibold shadow-md focus:outline-none focus:ring-2  focus:ring-offset-2  opacity-70 rounded-lg ";

  const followingClassState =
    "py-1 px-3 bg-orange-600 hover:bg-orange-700 focus:ring-orange-500  focus:ring-offset-red-200 text-white  transition ease-in duration-200 text-center font-semibold shadow-md focus:outline-none focus:ring-2   focus:ring-offset-2  opacity-70 rounded-lg ";

  const [followState, setFollowState] = useState({});

  useEffect(() => {
    setFollowState(
      isfollow
        ? {
            state: true,
            classValue: followingClassState,
            value: "following",
          }
        : {
            state: false,
            classValue: followClassState,
            value: "follow",
          }
    );
  }, []);

  const FollowButton = () => {
    return (
      <button type="button" onClick={() => followStateChange()} className={followState.classValue}>
        {followState.value}
      </button>
    );
  };

  const followStateChange = async () => {
    // 팔로우 상태라면 언팔로우
    if (followState.state) {
      await http
        .post(`member/follow/delete`, {
          nickname: nickname,
        })
        .then(() => {
          setFollowState({
            state: false,
            classValue: followClassState,
            value: "follow",
          });
          console.log("no follow set");
        })
        .catch((error) => {
          console.log(error);
        });
    }
    // 언팔로우 상태라면 팔로우
    else {
      await http
        .post(`member/follow`, {
          nickname: nickname,
        })
        .then(() => {
          setFollowState({
            state: true,
            classValue: followingClassState,
            value: "following",
          });
          console.log("following set");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };
  const navigate = useNavigate();
  const user = useRecoilValue(userInfo);

  return (
    <div className="w-full bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
      <div className="flex justify-end px-4 pt-6"></div>
      <div className="flex flex-col items-center pb-6">
        <div className="grid grid-cols-2 gap-2 items-center px-4">
          <img
            className="w-24 h-24 mb-3 rounded-full shadow-lg justify-self-center"
            src={profileimg == null ? BasicProfile : profileimg}
            alt="Profile image"
          />
          <div>
            <h5 className="mb-1 text-xl font-medium text-gray-900 dark:text-white">{nickname}</h5>
            {/* <span className="text-sm text-gray-500 dark:text-gray-400">Visual Designer</span> */}
          </div>
        </div>
        <div className="flex mt-4 space-x-3 md:mt-6">
          {user.nickname == nickname ? <></> : <FollowButton />}
          <div
            onClick={() => {
              navigate(`/userdetail`, { state: nickname });
            }}
            className="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-gray-900 bg-white border border-gray-300 rounded-lg hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-200 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-700 dark:focus:ring-gray-700"
          >
            Visit
          </div>
        </div>
      </div>
    </div>
  );
};

export default User;
