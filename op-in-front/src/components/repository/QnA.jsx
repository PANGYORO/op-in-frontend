import DefaultImg from "@assets/basicprofile.png";
import Comment from "@components/repository/Comment";
import React, { useEffect, useRef, useState } from "react";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";
import http from "@api/http";
import { useToast } from "@hooks/useToast";
import QnaModifyModal from "@components/modals/QnaModifyModal";
import QnaDeleteModal from "@components/modals/QnaDeleteModal";

const commentRender = (list) => {
  const result = [];
  if (list != null)
    for (let i = 0; i < list.length; i++) {
      result.push(<Comment key={i} comment={list[i].comment} member={list[i].member} />);
    }
  return result;
};

const QnA = ({
  repoId,
  qnaId,
  nickname,
  avatar,
  createTime,
  content,
  qnACommentList,
  propFunction,
}) => {
  const user = useRecoilValue(userInfo);
  const [qnaContent, setQnaContent] = useState("");
  const [text, setText] = useState("");
  const [modifyOpen, setModifyOpen] = useState(false);
  const [deleteOpen, setDeleteOpen] = useState(false);

  const [CommentList, setCommentList] = useState([...qnACommentList]);

  const { setToast } = useToast();

  useEffect(() => {
    setQnaContent(content);
  }, []);

  const modifyHighFunction = ({ postContent }) => {
    setQnaContent(postContent);
    setToast({ message: "Qna가 수정되었습니다." });
  };
  const deleteHighFunction = () => {
    propFunction(qnaId);
  };

  const createComment = async (data) => {
    await http
      .post(`qna/comment`, {
        comment: data,
        qnaId: qnaId,
      })
      .then(() => console.debug("댓글 추가 성공"))
      .catch((error) => {
        console.debug(error);
      });
    setCommentList([
      ...CommentList,
      {
        member: { nickname: user.nickname, user_img: user.img_url },
        comment: data,
      },
    ]);
    console.debug(CommentList);
    setToast({ message: "Comment가 추가되었습니다." });
  };

  const modifyQnA = () => {
    setModifyOpen(true);
  };

  const removeQnA = () => {
    setDeleteOpen(true);
  };

  return (
    <>
      <div className="w-full p-4 mb-6 bg-white rounded-lg shadow  sm:inline-block">
        <div className="flex items-start text-left">
          <div className="flex-shrink-0">
            <div className="relative inline-block">
              <a href="#" className="relative block">
                <img
                  alt="profile"
                  src={avatar || DefaultImg}
                  className="mx-auto object-cover rounded-full h-16 w-16 "
                />
              </a>
            </div>
          </div>

          <div className="ml-6 w-full">
            <div className="flex flex-cols justify-between">
              <div>
                <p className="flex items-baseline">
                  <span className="font-bold text-gray-600 ">{nickname}</span>
                  <span className="ml-2 text-sm text-gray-500 ">
                    {new Date(createTime).toLocaleString()}
                  </span>
                </p>
                <div className="mt-4 whitespace-pre">{qnaContent}</div>
              </div>
              {nickname == user.nickname ? (
                <div className="">
                  <button
                    type="button"
                    onClick={modifyQnA}
                    className="text-white bg-blue-400 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-3 py-1 mr-2 mb-2  focus:outline-none "
                  >
                    Modify
                  </button>
                  <button
                    type="button"
                    onClick={removeQnA}
                    className="focus:outline-none text-white bg-red-500 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-3 py-1 mr-2 mb-2 "
                  >
                    Delete
                  </button>
                </div>
              ) : (
                <div></div>
              )}
            </div>

            <div className="mt-3">
              <label htmlFor="chat" className="sr-only">
                Leave a Comment...
              </label>
              <div className="flex items-center px-3 py-2 rounded-lg bg-gray-50 ">
                <textarea
                  id="chat"
                  rows="1"
                  value={text}
                  onChange={(e) => {
                    setText(e.target.value);
                  }}
                  className="block mx-4 p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 "
                  placeholder="Leave a Comment..."
                ></textarea>
                <button
                  // type="submit"
                  onClick={() => {
                    createComment(text);
                    setText("");
                  }}
                  className="inline-flex justify-center p-2 text-blue-600 rounded-full cursor-pointer hover:bg-blue-100 "
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

            <hr className="my-4" />
            {/* 댓글 공간 */}
            <div className="grid grid-rows-1 gap-2">{commentRender(CommentList)}</div>
          </div>
        </div>
      </div>
      <QnaModifyModal
        qnaId={qnaId}
        previousValue={qnaContent}
        open={modifyOpen}
        setOpen={setModifyOpen}
        propFunction={modifyHighFunction}
      />
      <QnaDeleteModal
        open={deleteOpen}
        setOpen={setDeleteOpen}
        qnaId={qnaId}
        propFunction={deleteHighFunction}
      />
    </>
  );
};
export default QnA;
