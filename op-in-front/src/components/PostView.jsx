import React, { useState, useEffect } from "react";
import { useLocation, Link } from "react-router-dom";
import { Viewer } from "@toast-ui/react-editor";
import { useToast } from "@hooks/useToast";

import PostComment from "./repository/PostComment";

import http from "@api/http";
import DefaultImg from "@assets/basicprofile.png";

// 여기 css를 수정해서 코드 하이라이팅 커스텀 가능
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css";

const PostView = () => {
  const location = useLocation();
  const postId = location.state;
  const [detail, setDetail] = useState({});
  const { setToast } = useToast();
  const [commentList, setCommentList] = useState([]);

  useEffect(() => {
    getPostData();
  }, []);

  const getPostData = async () => {
    await http
      .get(`/post/${postId}`)
      .then(({ data }) => {
        setDetail(data);
        setCommentList(data.commentList);
      })
      .catch((error) => console.log(error));
  };

  const PostCommentList = ({ comments }) => {
    return (
      <div className="grid grid-rows-1 gap-2 ml-4">
        {comments.map((comment) => {
          return (
            <PostComment
              key={comment.id}
              memberName={comment.memberName}
              memberAvatarUrl={comment.memberAvatarUrl}
              commentContent={comment.commentContent}
              date={comment.date}
            />
          );
        })}
      </div>
    );
  };

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
          ...commentList,
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

  const PostDetail = ({ details }) => {
    // console.log(details);
    const [text, setText] = useState("");

    return (
      <>
        <div className="lg:flex lg:items-center lg:justify-between w-full mx-auto py-12 px-4 sm:px-6 lg:py-5 lg:px-8 z-20">
          <h1 className="text-3xl font-extrabold text-black dark:text-white sm:text-4xl">
            <span className="block flex felx-col place-items-center">
              {details.title}
            </span>
          </h1>
        </div>

        {/* 내용칸 */}
        <div className="m-4 p-4 mb-6 rounded-lg shadow from-blue-500 to-blue-600">
          <div className="flex items-start text-left">
            <div className="flex-shrink-0">
              <div className="relative inline-block">
                <Link
                  to={`/userdetail`}
                  state={details.authorMemberName}
                  className="relative block"
                >
                  <img
                    alt="profile"
                    src={
                      details.authorMemberAvatar == null
                        ? DefaultImg
                        : details.authorMemberAvatar
                    }
                    className="mx-auto object-cover rounded-full h-16 w-16 "
                  />
                </Link>
              </div>
            </div>
            <div className="ml-6 mt-4 grid grid-col">
              <span className="ml-2 font-bold text-gray-600 dark:text-gray-200">
                <h2>{details.authorMemberName}</h2>
              </span>
              <span className="ml-2 text-sm text-gray-500 dark:text-gray-300">
                {details.createTime}
              </span>
            </div>
          </div>

          <div className="mt-3">
            <p className="mt-1 mx-4 font-light">
              <Viewer initialValue={details.content} />
            </p>
          </div>
          <div className="flex items-start mt-6 ">
            {/* 하트 */}
            <button className="mx-3">
              <svg
                width="25"
                height="25"
                fill="currentColor"
                viewBox="0 0 1792 1792"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M1664 596q0-81-21.5-143t-55-98.5-81.5-59.5-94-31-98-8-112 25.5-110.5 64-86.5 72-60 61.5q-18 22-49 22t-49-22q-24-28-60-61.5t-86.5-72-110.5-64-112-25.5-98 8-94 31-81.5 59.5-55 98.5-21.5 143q0 168 187 355l581 560 580-559q188-188 188-356zm128 0q0 221-229 450l-623 600q-18 18-44 18t-44-18l-624-602q-10-8-27.5-26t-55.5-65.5-68-97.5-53.5-121-23.5-138q0-220 127-344t351-124q62 0 126.5 21.5t120 58 95.5 68.5 76 68q36-36 76-68t95.5-68.5 120-58 126.5-21.5q224 0 351 124t127 344z"></path>
              </svg>
            </button>
            {details.likeCount}
            {/* 포크 */}
            <button className="mx-3">
              <svg
                width="25"
                height="25"
                fill="currentColor"
                viewBox="0 0 1792 1792"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M1344 1024q133 0 226.5 93.5t93.5 226.5-93.5 226.5-226.5 93.5-226.5-93.5-93.5-226.5q0-12 2-34l-360-180q-92 86-218 86-133 0-226.5-93.5t-93.5-226.5 93.5-226.5 226.5-93.5q126 0 218 86l360-180q-2-22-2-34 0-133 93.5-226.5t226.5-93.5 226.5 93.5 93.5 226.5-93.5 226.5-226.5 93.5q-126 0-218-86l-360 180q2 22 2 34t-2 34l360 180q92-86 218-86z"></path>
              </svg>
            </button>
            {details.commentCount}
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
              value={text}
              onChange={(e) => {
                setText(e.target.value);
              }}
              className="block mx-4 p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Leave a Comment..."
            ></textarea>
            <button
              // type="submit"
              onClick={() => {
                createComment(text);
                setText("");
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
        <PostCommentList comments={commentList} />
      </>
    );
  };
  return (
    <div className="w-full m-4 p-6 bg-white h-screen shadow-lg rounded-2xl dark:bg-gray-700 ">
      <PostDetail details={detail} />
    </div>
  );
};
export default PostView;

