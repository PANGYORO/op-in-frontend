import DefaultImg from "@assets/basicprofile.png";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Tooltip } from "react-tooltip";
import ContributorsModal from "@components/modals/ContributorsModal";

export default function Status({ repoDetail }) {
  const [open, setOpen] = useState(false);
  const toggleModal = () => {
    setOpen(true);
  };
  const contributerRender = (list) => {
    const result = [];
    if (list != null)
      for (let i = 0; i < (list.length < 5 ? list.length : 5); i++) {
        result.push(
          <span key={i}>
            <Link id={"cont" + list[i].id} to={`/userdetail`} state={list[i].nickname} key={i}>
              <img
                className="inline-block h-10 w-10 rounded-full object-cover ring-2 ring-white"
                src={DefaultImg}
                alt={list[i].nickname}
              />
            </Link>
            <Tooltip anchorId={"cont" + list[i].id} content={list[i].nickname} />
          </span>
        );
      }
    return result;
  };
  const tagRender = (list) => {
    const result = [];
    if (list != null)
      for (let i = 0; i < list.length; i++) {
        result.push(
          <button
            key={i}
            type="button"
            className="fpx-3 py-1 mb-3 text-base text-blue-600 bg-blue-200 rounded-full mx-1"
          >
            {list[i]?.title}
          </button>
        );
      }
    return result;
  };
  const moreContributors = () => {
    if (repoDetail?.contributors != null && repoDetail?.contributors.length > 4) {
      return repoDetail?.contributors.length - 4 + " more Contributors...";
    } else return "";
  };
  return (
    <div className="overflow-hidden relative max-w-xs p-4 h-screen bg-white shadow-lg rounded-xl dark:bg-gray-800 ml-3 ">
      <div className="my-2">
        <span className="font-bold">Contributors</span>
        <div className="relative max-w-xs  my-2 h-full">
          <div className="block w-full h-full">
            <div className="w-full">
              <div className="flex items-center">
                <div className="flex -space-x-2">{contributerRender(repoDetail?.contributors)}</div>
              </div>
              <div className="mt-2 text-blue-500 dark:text-gray-300" onClick={toggleModal}>
                {moreContributors()}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="my-2">{repoDetail?.content}</div>
      <div className="my-2 text-gray-400 dark:text-white-300">
        {repoDetail?.updateDate == null ? "no data" : repoDetail?.updateDate}
      </div>
      <div className="my-2 font-bold">About</div>
      <div className="grid grid-cols-3 my-2">{tagRender(repoDetail?.techLangs)}</div>
      <div className="m-4">
        {/* <div>
          <div className="inline-flex">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="w-6 h-6"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M12 6.042A8.967 8.967 0 006 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 016 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 016-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0018 18a8.967 8.967 0 00-6 2.292m0-14.25v14.25"
              />
            </svg>
            Readme
          </div>
        </div> */}

        <div className="inline-flex">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={1.5}
            stroke="currentColor"
            className="w-6 h-6"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M11.48 3.499a.562.562 0 011.04 0l2.125 5.111a.563.563 0 00.475.345l5.518.442c.499.04.701.663.321.988l-4.204 3.602a.563.563 0 00-.182.557l1.285 5.385a.562.562 0 01-.84.61l-4.725-2.885a.563.563 0 00-.586 0L6.982 20.54a.562.562 0 01-.84-.61l1.285-5.386a.562.562 0 00-.182-.557l-4.204-3.602a.563.563 0 01.321-.988l5.518-.442a.563.563 0 00.475-.345L11.48 3.5z"
            />
          </svg>
          stars : {repoDetail?.star}
        </div>
        {/* 
        <div>
          <div className="inline-flex">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="w-6 h-6"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M2.036 12.322a1.012 1.012 0 010-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178z"
              />
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
              />
            </svg>
            31watching
          </div>
        </div> */}
        <div>
          <div className="inline-flex">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="w-6 h-6"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M7.217 10.907a2.25 2.25 0 100 2.186m0-2.186c.18.324.283.696.283 1.093s-.103.77-.283 1.093m0-2.186l9.566-5.314m-9.566 7.5l9.566 5.314m0 0a2.25 2.25 0 103.935 2.186 2.25 2.25 0 00-3.935-2.186zm0-12.814a2.25 2.25 0 103.933-2.185 2.25 2.25 0 00-3.933 2.185z"
              />
            </svg>
            forks : {repoDetail?.forkNum}
          </div>
        </div>
      </div>
      <ContributorsModal open={open} setOpen={setOpen} contributors={repoDetail?.contributors} />
    </div>
  );
}
