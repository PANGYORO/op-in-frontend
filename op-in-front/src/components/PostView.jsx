import React, { useState, useEffect, useRef } from "react";
import { useLocation, Link } from "react-router-dom";
import { Viewer } from "@toast-ui/react-editor";
import { useToast } from "@hooks/useToast";

import PostComment from "./repository/PostComment";

import http from "@api/http";
import DefaultImg from "@assets/basicprofile.png";
import ToLoginModal from "@components/modals/ToLoginModal";
import { useRecoilValue } from "recoil";
import { userInfo } from "@recoil/user/atoms";

// 여기 css를 수정해서 코드 하이라이팅 커스텀 가능
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css";

const HeartUnpressed = () => {
  return (
    <svg
      version="1.0"
      xmlns="http://www.w3.org/2000/svg"
      width="512.000000pt"
      height="512.000000pt"
      viewBox="0 0 512.000000 512.000000"
      preserveAspectRatio="xMidYMid meet"
      className="w-6 h-6"
    >
      <g
        transform="translate(0.000000,512.000000) scale(0.100000,-0.100000)"
        fill="#000000"
        stroke="none"
      >
        <path
          d="M1262 4830 c-319 -40 -586 -171 -812 -399 -203 -204 -325 -420 -395 -701 -124 -487 -34 -967 264 -1418 191 -289 438 -554 891 -958 288 -257 1167
-1007 1210 -1032 40 -24 55 -27 140 -27 85 0 100 3 140 27 43 25 924 776 1210 1032 455 406 700 670 891 958 298 451 388 931 264 1418 -70 281 -192 497 -395
701 -202 203 -418 320 -701 380 -142 30 -404 33 -528 5 -346 -75 -611 -248 -853 -556 l-28 -35 -27 35 c-239 302 -500 475 -833 551 -99 23 -327 33 -438
19z m334 -305 c284 -50 529 -214 723 -485 33 -47 74 -103 90 -126 74 -104 228 -104 302 0 16 23 57 79 90 126 265 370 634 544 1036 489 446 -61 794 -373 927
-832 105 -363 59 -744 -132 -1087 -160 -287 -427 -588 -892 -1005 -225 -201 -1171 -1015 -1180 -1015 -10 0 -952 811 -1180 1015 -715 641 -997 1041 -1065
1510 -44 303 19 629 172 886 230 387 678 599 1109 524z"
        />
      </g>
    </svg>
  );
};
const HeartPressed = () => {
  return (
    <svg
      version="1.0"
      xmlns="http://www.w3.org/2000/svg"
      width="512.000000pt"
      height="512.000000pt"
      viewBox="0 0 512.000000 512.000000"
      preserveAspectRatio="xMidYMid meet"
      className="w-6 h-6"
    >
      <g
        transform="translate(0.000000,512.000000) scale(0.100000,-0.100000)"
        fill="#000000"
        stroke="none"
      >
        <path
          d="M1180 4635 c-373 -64 -690 -257 -907 -551 -118 -161 -214 -386 -248
-584 -22 -125 -22 -328 -1 -449 71 -396 330 -816 785 -1272 417 -417 904 -793
1516 -1171 238 -147 228 -142 267 -122 152 79 620 382 865 560 921 671 1486
1341 1622 1922 98 419 -19 878 -307 1205 -417 472 -1073 608 -1646 343 -181
-83 -396 -259 -511 -416 -26 -36 -50 -65 -55 -65 -4 0 -34 36 -68 81 -122 162
-281 292 -477 389 -210 104 -392 146 -630 144 -71 -1 -164 -7 -205 -14z"
        />
      </g>
    </svg>
  );
};

const PostDetail = ({
  title,
  authorMemberName,
  authorMemberAvatar,
  createTime,
  content,
  likeCount,
  commentCount,
  commentList = [],
  postId,
}) => {
  const textAreaRef = useRef();
  const { setToast } = useToast();
  const [toLoginOpen, setToLoginOpen] = useState(false);
  const user = useRecoilValue(userInfo);

  const [comments, setCommentList] = useState(commentList);
  const [likeState, setLikeState] = useState(true);
  const [likesCount, setLikesCount] = useState(0);

  function toLoginToggleModal() {
    setToLoginOpen((prev) => !prev);
  }
  const createComment = async (comment) => {
    await http
      .post(`post/comment`, {
        postId: postId,
        commentContent: comment,
      })
      .then((response) => {
        const comment = response.data;
        setToast({ message: "Comment가 추가되었습니다." });
        setCommentList([
          ...comments,
          {
            id: comment.id,
            memberName: comment.memberName,
            memberAvatarUrl: comment.memberAvatarUrl,
            commentContent: comment.commentContent,
            date: new Date(comment.updateDate),
          },
        ]);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const HeartStateChange = () => {
    setLikeState((prev) => {
      if (prev) {
        setLikesCount((cur) => cur + 1);
      } else {
        setLikesCount((cur) => cur - 1);
      }
      return !prev;
    });
    console.log("heartStateChange");
  };

  return (
    <>
      <div className="lg:flex lg:items-center lg:justify-between w-full mx-auto py-12 px-4 sm:px-6 lg:py-5 lg:px-8 z-20">
        <h1 className="text-3xl font-extrabold text-black dark:text-white sm:text-4xl">
          <span className="block flex flex-col place-items-center">{title}</span>
        </h1>
      </div>

      {/* 내용칸 */}
      <div className="m-4 p-4 mb-6 rounded-lg shadow from-blue-500 to-blue-600">
        <div className="flex items-start text-left">
          <div className="flex-shrink-0">
            <div className="relative inline-block">
              <Link to={`/userdetail`} state={authorMemberName} className="relative block">
                <img
                  alt="profile"
                  src={authorMemberAvatar || DefaultImg}
                  className="mx-auto object-cover rounded-full h-16 w-16 "
                />
              </Link>
            </div>
          </div>
          <div className="ml-6 mt-4 grid grid-col">
            <span className="ml-2 font-bold text-gray-600 dark:text-gray-200">
              <h2>{authorMemberName}</h2>
            </span>
            <span className="ml-2 text-sm text-gray-500 dark:text-gray-300">{createTime}</span>
          </div>
        </div>

        <div className="mt-3">
          <p className="mt-1 mx-4 font-light">
            <Viewer initialValue={content} />
          </p>
        </div>
        <div className="flex items-start mt-6 ">
          {/* 하트 */}
          <button className="mx-3" onClick={HeartStateChange}>
            {/* 좋아요 눌린 상태에 따라 변경 */}
            {likeState ? <HeartUnpressed /> : <HeartPressed />}
          </button>
          {likesCount}
          {/* 포크 */}
          <div className="mx-3">
            <svg
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
                d="M7.5 8.25h9m-9 3H12m-9.75 1.51c0 1.6 1.123 2.994 2.707 3.227 1.129.166 2.27.293 3.423.379.35.026.67.21.865.501L12 21l2.755-4.133a1.14 1.14 0 01.865-.501 48.172 48.172 0 003.423-.379c1.584-.233 2.707-1.626 2.707-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z"
              />
            </svg>
          </div>
          {commentCount}
        </div>
      </div>
      {/* 댓글 적는칸 */}
      <div className="m-3">
        <label htmlFor="chat" className="sr-only">
          Leave a Comment...
        </label>
        <div className="flex items-center px-3 py-2 rounded-lg bg-gray-50 dark:bg-gray-700">
          <textarea
            id="chat"
            rows="1"
            ref={textAreaRef}
            className="block mx-4 p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="Leave a Comment..."
          ></textarea>
          <button
            // type="submit"
            onClick={() => {
              if (user.logined) {
                createComment(textAreaRef.current.value);
                textAreaRef.current.value = "";
              } else toLoginToggleModal();
            }}
            className="inline-flex justify-center p-2 text-blue-600 rounded-full cursor-pointer hover:bg-blue-100 dark:text-blue-500 dark:hover:bg-gray-600"
          >
            <svg
              aria-hidden="true"
              className="w-6 h-6 rotate-90"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path d="M10.894 2.553a1 1 0 00-1.788 0l-7 14a1 1 0 001.169 1.409l5-1.429A1 1 0 009 15.571V11a1 1 0 112 0v4.571a1 1 0 00.725.962l5 1.428a1 1 0 001.17-1.408l-7-14z"></path>
            </svg>
            <span className="sr-only">Send message</span>
          </button>
        </div>
      </div>
      {/* 댓글달리는 곳 */}
      {/* <div className="grid grid-rows-1 gap-2">{commentRender(details.commentList)}</div> */}
      <div className="grid grid-rows-1 gap-2 ml-4">
        {comments.map((comment, index) => {
          return (
            <PostComment
              key={index}
              memberName={comment.memberName}
              memberAvatarUrl={comment.memberAvatarUrl}
              commentContent={comment.commentContent}
              date={comment.date}
            />
          );
        })}
      </div>
      <ToLoginModal open={toLoginOpen} setOpen={setToLoginOpen} />
    </>
  );
};

const PostView = () => {
  const location = useLocation();
  const postId = location.state;
  const [detail, setDetail] = useState(null);

  useEffect(() => {
    getPostData();
  }, []);

  const getPostData = async () => {
    await http
      .get(`/post/${postId}`)
      .then(({ data }) => {
        setDetail(data);
        console.log(data);
      })
      .catch((error) => console.log(error));
  };

  return (
    <div className="w-full m-4 p-6 bg-white h-screen shadow-lg rounded-2xl dark:bg-gray-700 h-screen overflow-auto">
      {detail && <PostDetail {...detail} postId={postId} />}
    </div>
  );
};
export default PostView;
