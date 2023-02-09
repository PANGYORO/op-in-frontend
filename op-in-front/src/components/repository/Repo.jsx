import React from "react";
import { useNavigate } from "react-router-dom";

// import axios from "axios";

// const Reopitem = ({ repos }) => {
//     const { img, name, contributor, star } = repos;
//     return (

//     )
//   }

export default function Repo({ _id, _title, _content, _techLangs, _repoDetails }) {
  const navigate = useNavigate();
  const RepoTagList = () => {
    const result = [];
    const length = _techLangs == null ? 0 : _techLangs.length;
    for (let i = 0; i < length; i++) {
      result.push(
        <span key={i} className="px-4 py-1  text-base rounded-full text-red-600  bg-red-200 ">
          {_techLangs[i]}
        </span>
      );
    }
    return result;
  };
  return (
    <>
      <div className="w-full inset-x-0 bottom-0 bg-white dark:bg-gray-800 shadow-lg rounded-xl p-4 flex flex-col">
        <div
          onClick={() =>
            navigate(`/repo/${_id}`, {
              state: _repoDetails,
            })
          }
        >
          <div className="truncate">
            <span className="text-lg font-bold">{_title}</span>
          </div>
          <div className="w-full h-24 text-clip overflow-hidden">
            <p className="text-gray-600 dark:text-white my-5 pl-2">{_content}</p>
          </div>
        </div>

        {/* 여기 언어 공간 */}
        <div>
          <div className="inline-flex items-center bg-white leading-none ${props.textColor} rounded-lg p-2 shadow text-teal text-sm">
            <div className="flex flex-wrap items-center gap-4">{RepoTagList()}</div>
          </div>
        </div>
      </div>
    </>
  );
}
