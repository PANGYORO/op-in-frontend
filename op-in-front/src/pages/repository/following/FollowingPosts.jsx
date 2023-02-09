import React, { useState } from "react";
import RepoPost from "@components/repository/RepoPost";
import PostModal from "@components/modals/PostModal";
// import { userInfo } from "@recoil/user/atoms";
// import { useRecoilValue } from "recoil";

const PostDummy = [
  {
    postId: "1",
    createTime: "2023-02-08T02:18:39",
    post_content: "A",
    likeCount: 0,
    commentCount: 0,
  },
  {
    postId: "2",
    createTime: "2023-02-08T02:18:39",
    post_content: "B",
    likeCount: 0,
    commentCount: 0,
  },
  {
    postId: "3",
    createTime: "2023-02-08T02:18:39",
    post_content: "C",
    likeCount: 2,
    commentCount: 0,
  },
  {
    postId: "4",
    createTime: "2023-02-08T02:18:39",
    post_content: "D",
    likeCount: 0,
    commentCount: 0,
  },
];

export default function FollowingPosts() {
  const [open, setOpen] = useState(false);
  // const user = useRecoilValue(userInfo);

  function toggleModal() {
    setOpen((prev) => !prev);
  }

  const rendering = (list) => {
    const result = [];
    for (let i = list.length == null ? -1 : list.length - 1; i >= 0; i--) {
      result.push(
        <RepoPost
          key={i}
          _postId={list[i].postId}
          _createTime={list[i].createTime}
          post_content={list[i].post_content}
          _likeCount={list[i].likeCount}
          _commentCount={list[i].commentCount}
        />
      );
    }
    return result;
  };

  const highFunction = (data) => {
    PostDummy.push({
      postId: data.title,
      createTime: "today",
      post_content: data.content,
      likeCount: 0,
      commentCount: 0,
    });
  };
  return (
    <>
      <header className="z-20 items-center w-full h-16 bg-white shadow-lg dark:bg-gray-700 rounded-2xl ml-4 mb-4 mr-4">
        <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
          <div className="relative grid grid-cols-2 items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0">
            <div className="container relative left-0 z-20 flex w-3/4 h-auto h-full">
              <div className="relative flex items-center w-full h-full lg:w-64 group">
                <div className="absolute z-20 flex items-center justify-center block w-auto h-10 p-3 pr-2 text-sm text-gray-500 uppercase cursor-pointer sm:hidden">
                  <svg
                    fill="none"
                    className="relative w-5 h-5"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                  >
                    <path d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                  </svg>
                </div>
                <svg
                  className="absolute left-0 z-20 hidden w-4 h-4 ml-4 text-gray-500 pointer-events-none fill-current group-hover:text-gray-400 sm:block"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                >
                  <path d="M12.9 14.32a8 8 0 1 1 1.41-1.41l5.35 5.33-1.42 1.42-5.33-5.34zM8 14A6 6 0 1 0 8 2a6 6 0 0 0 0 12z"></path>
                </svg>
                <input
                  type="text"
                  className="block w-full py-1.5 pl-10 pr-4 leading-normal rounded-2xl focus:border-transparent focus:outline-none focus:ring-2 focus:ring-blue-500 ring-opacity-90 bg-gray-100 dark:bg-gray-800 text-gray-400 aa-input"
                  placeholder="Post Search"
                />
              </div>
            </div>
            <div className="w-auto h-auto h-full justify-self-end">
              <button
                type="button"
                onClick={toggleModal}
                disabled=""
                className="py-2 px-4 bg-blue-600 hover:bg-blue-700 focus:ring-blue-500 focus:ring-offset-blue-200 text-white 
                w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none 
                focus:ring-2 focus:ring-offset-2  opacity-70 rounded-lg flex justify-center items-center"
              >
                <svg
                  width="20"
                  height="20"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="w-6 h-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10"
                  />
                </svg>
                Post Write
              </button>
            </div>
          </div>
        </div>
      </header>
      <div className="flex">
        <div className="grid grid-cols-2 gap-4 w-full ml-4 h-screen overflow-auto">
          {rendering(PostDummy)}
        </div>
      </div>
      <PostModal open={open} setOpen={setOpen} propFunction={highFunction} />
    </>
  );
}
