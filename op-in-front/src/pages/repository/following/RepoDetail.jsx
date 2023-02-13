import React, { useState, useCallback, useEffect } from "react";
import FollowingPosts from "./FollowingPosts";
import FollowingQnas from "./FollowingQnas";
import Status from "@components/repository/Status";
import { useLocation } from "react-router-dom";
import http from "@api/http";
import useAuth from "@hooks/useAuth";
import { useToast } from "@hooks/useToast";

const POSTS_TAB = "posts";
const QNAS_TAB = "qnas";

const RepoDetail = () => {
  const [tab, setTab] = useState(POSTS_TAB);
  const location = useLocation();
  const repoId = location.state;
  const [repoDetail, setRepoDetail] = useState(null);
  const followClassState =
    "py-1 px-3 bg-blue-600 hover:bg-blue-700 focus:ring-blue-500 focus:ring-offset-red-200 text-white  transition ease-in duration-200 text-center font-semibold shadow-md focus:outline-none focus:ring-2  focus:ring-offset-2  opacity-70 rounded-lg ";
  const followingClassState =
    "py-1 px-3 bg-orange-600 hover:bg-orange-700 focus:ring-orange-500  focus:ring-offset-red-200 text-white  transition ease-in duration-200 text-center font-semibold shadow-md focus:outline-none focus:ring-2   focus:ring-offset-2  opacity-70 rounded-lg ";
  const [followState, setFollowState] = useState({});
  const { hasAuth, auth } = useAuth();
  const { setToast } = useToast();

  const FollowButton = () => {
    return (
      <button type="button" onClick={() => followStateChange()} className={followState.classValue}>
        {followState.value}
      </button>
    );
  };

  useEffect(() => {
    getRepoDetail(repoId);
    if (hasAuth) checkFollowState();
  }, []);

  const checkFollowState = async () => {
    await http
      .get(`member/follow/repo/${repoId}`)
      .then(({ data }) => {
        if (data) {
          setFollowState({
            state: true,
            classValue: followingClassState,
            value: "following",
          });
        } else {
          setFollowState({
            state: false,
            classValue: followClassState,
            value: "follow",
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const followStateChange = async () => {
    // 팔로우 상태라면 언팔로우
    if (followState.state) {
      await http
        .delete(`member/follow/repo/${repoId}`)
        .then(() => {
          setFollowState({
            state: false,
            classValue: followClassState,
            value: "follow",
          });
          setToast({
            message: repoDetail.title + "레포 팔로우가 취소되었습니다.",
          });
        })
        .catch((error) => {
          console.log(error);
        });
    }
    // 언팔로우 상태라면 팔로우
    else {
      await http
        .post(`member/follow/repo/${repoId}`)
        .then(() => {
          setFollowState({
            state: true,
            classValue: followingClassState,
            value: "following",
          });
          setToast({
            message: repoDetail.title + " 레포 팔로우가 추가되었습니다.",
          });
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  const getRepoDetail = async (id) => {
    await http
      .get(`repo/${id}`)
      .then(({ data }) => {
        setRepoDetail({ ...data });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const onClick = useCallback((item) => {
    setTab(item);
  }, []);

  const selected =
    "inline-block p-4 text-blue-600 bg-gray-100 rounded-t-lg active dark:bg-gray-800 dark:text-blue-500";
  const deselected =
    "inline-block p-4 rounded-t-lg hover:text-gray-600 hover:bg-gray-50 dark:hover:bg-gray-800 dark:hover:text-gray-300";

  return (
    repoDetail && (
      <div className="flex flex-auto w-full mt-4">
        <div className="w-3/4">
          <div className="ml-4 mb-4">
            <header className="z-40 items-center w-full h-15 pb-3 bg-white shadow-lg dark:bg-gray-700 rounded-t-2xl">
              <div className="flex justify-between pt-3 pl-3 text-2xl">
                <span>
                  <span className="font-semibold"> Current Repository :&nbsp;</span>
                  <span className="font-bold"> {repoDetail?.title}</span>
                </span>
                {hasAuth && auth.id !== repoDetail.ownerId && (
                  <span className="mr-3">
                    <FollowButton />
                  </span>
                )}
              </div>
              <ul className="pt-3 pl-3 flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:border-gray-700 dark:text-gray-400">
                <li className="mr-2">
                  <div
                    className={tab == POSTS_TAB ? selected : deselected}
                    onClick={() => onClick(POSTS_TAB)}
                  >
                    Post
                  </div>
                </li>
                <li className="mr-2">
                  <div
                    className={tab == QNAS_TAB ? selected : deselected}
                    onClick={() => onClick(QNAS_TAB)}
                  >
                    Qna
                  </div>
                </li>
              </ul>
            </header>
          </div>
          <div className="ml-4">
            {tab == POSTS_TAB ? (
              <FollowingPosts repoId={repoId} />
            ) : (
              <FollowingQnas repoId={repoId} />
            )}
          </div>
        </div>
        <div className="w-1/4 h-full max-xl:hidden">
          <Status repoDetail={repoDetail} />
        </div>
      </div>
    )
  );
};
export default RepoDetail;
