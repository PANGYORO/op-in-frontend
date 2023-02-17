import React, { useEffect, useState, useRef } from "react";
import Post from "@components/Post";
import PostModal from "@components/modals/PostModal";
import http from "@api/http";
import ToLoginModal from "@components/modals/ToLoginModal";
import { useRecoilValue } from "recoil";
import { userInfo } from "@recoil/user/atoms";

const PostList = ({ posts = [] }) => {
  return (
    <div className="grid grid-cols-2 max-2xl:grid-cols-1 gap-4 w-full">
      {posts.map((post) => {
        return (
          <Post
            key={post.id}
            id={post.id}
            date={post.date}
            title={post.title}
            post_content={post.post_content}
            likeCount={post.likeCount}
            commentCount={post.commentCount}
            authorMemberAvatar={post.authorMemberAvatar}
            authorMemberName={post.authorMemberName}
          />
        );
      })}
    </div>
  );
};

function FollowingPosts({ repoId }) {
  const [open, setOpen] = useState(false);
  const [toLoginOpen, setToLoginOpen] = useState(false);
  const [posts, setPosts] = useState([]);
  const inputRef = useRef();
  const user = useRecoilValue(userInfo);

  useEffect(() => {
    searchData({ page: 0, size: 100, query: "" });
  }, []);

  const searchData = async ({ page = 0, size = 10, query = "" }) => {
    await http
      .get(`/search/repos/${repoId}/posts`, {
        params: {
          page,
          size,
          query,
        },
      })
      .then(({ data }) => {
        setPosts(data);
      })
      .catch((err) => {
        console.error(err);
      });
  };
  function toggleModal() {
    setOpen((prev) => !prev);
  }
  function toLoginToggleModal() {
    setToLoginOpen((prev) => !prev);
  }
  const keyUpEvent = (e) => {
    if (e.keyCode == 13) {
      searchData({ page: 0, size: 100, query: inputRef.current.value });
    }
  };

  const highFunction = (data) => {
    setPosts((prev) => [
      ...prev,
      {
        id: data.id,
        title: data.title,
        date: data.date,
        post_content: data.content,
        likeCount: data.likeCount,
        commentCount: data.commentCount,
        authorMemberAvatar: data.authorMemberAvatar,
        authorMemberName: data.authorMemberName,
      },
    ]);
  };
  return (
    <>
      <header className="z-20 items-center w-full h-16 bg-white shadow-lg  rounded-2xl mb-4 ">
        <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
          <div className="relative grid grid-cols-2 items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0">
            <div className="container relative left-0 z-20 flex w-3/4 h-auto h-full">
              <div className="relative flex items-center w-full h-full lg:w-64 group">
                {/* 사진 부분 */}
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
                  className="block w-full py-1.5 pl-10 pr-4 leading-normal rounded-2xl focus:border-transparent focus:outline-none focus:ring-2 focus:ring-blue-500 ring-opacity-90 bg-gray-100  text-gray-400 aa-input"
                  placeholder="Post Search"
                  ref={inputRef}
                  onKeyUp={keyUpEvent}
                />
              </div>
            </div>

            {/* 모달창  연결 부분 */}
            <div className="w-auto h-auto h-full justify-self-end">
              <button
                type="button"
                onClick={() => {
                  if (user.logined) toggleModal();
                  else toLoginToggleModal();
                }}
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
      <div className="minwidth: 600px">
        <PostList posts={posts} />
      </div>
      <PostModal
        open={open}
        setOpen={setOpen}
        propFunction={highFunction}
        repositoryId={repoId}
      />
      <ToLoginModal open={toLoginOpen} setOpen={setToLoginOpen} />
    </>
  );
}
export default FollowingPosts;
