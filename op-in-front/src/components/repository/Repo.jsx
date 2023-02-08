import React from "react";
import { useNavigate } from "react-router-dom";

// import axios from "axios";

// const Reopitem = ({ repos }) => {
//     const { img, name, contributor, star } = repos;
//     return (

//     )
//   }

export default function Repo(props) {
  // const repos = [
  //   {
  //     id: 1,
  //     title: "title_1",
  //     content: "test content1",
  //     techLangs: [],
  //     contributors: [
  //       {
  //         nickname: "testmj",
  //         id: "1",
  //         profileImg: null,
  //       },
  //     ],
  //     gitContributors: null,
  //     star: "125",
  //     forkNum: "123214141",
  //     topicList: [],
  //     updateDate: null,
  //   },
  //   {
  //     id: 2,
  //     title: "test title",
  //     content: "test content",
  //     techLangs: [],
  //     contributors: [
  //       {
  //         nickname: "testck",
  //         id: "1",
  //         profileImg: null,
  //       },
  //       {
  //         nickname: "testmj",
  //         id: "2",
  //         profileImg: null,
  //       },
  //     ],
  //     gitContributors: null,
  //     star: "1233455565",
  //     forkNum: "123214141",
  //     topicList: [],
  //     updateDate: null,
  //   },
  //   {
  //     id: 3,
  //     title: "title3",
  //     content: "test content_3",
  //     techLangs: [],
  //     contributors: [],
  //     gitContributors: null,
  //     star: "1235",
  //     forkNum: "123214141",
  //     topicList: [],
  //     updateDate: null,
  //   },
  // ];
  // const repoId = props.id;
  const navigate = useNavigate();
  const RepoTagList = () => {
    const result = [];
    const length = props._techLangs == null ? 0 : props._techLangs.length;
    for (let i = 0; i < length; i++) {
      result.push(
        <span key={i} className="px-4 py-1  text-base rounded-full text-red-600  bg-red-200 ">
          {props._techLangs[i]}
        </span>
      );
    }
    return result;
  };
  return (
    <>
      <div className="w-full inset-x-0 bottom-0 bg-white dark:bg-gray-800 shadow-lg rounded-xl p-4 flex flex-col">
        <div onClick={() => navigate(`/repo/123123`, { state: "123123" })}>
          <div className="truncate">
            <span className="text-lg font-bold">{props._title}</span>
          </div>
          <div className="w-full h-24 text-clip overflow-hidden">
            <p className="text-gray-600 dark:text-white my-5 pl-2">{props._content}</p>
          </div>
        </div>
        {/* 여기 언어 공간 */}
        <div>
          <div className="inline-flex items-center bg-white leading-none ${props.textColor} rounded-lg p-2 shadow text-teal text-sm">
            <div className="flex flex-wrap items-center gap-4">
              {RepoTagList()}
              {/* <span className="px-4 py-1  text-base rounded-full text-red-600  bg-red-200 ">
                java
              </span>
              <span className="px- py-1  text-base rounded-full text-yellow-600  bg-yellow-200 ">
                jupiter notebook
              </span>
              <span className="px-4 py-1  text-base rounded-full text-green-600  bg-green-200 ">
                python
              </span>
              <span className="px-4 py-1  text-base rounded-full text-green-600  bg-green-200 ">
                python
              </span>
              <span className="px-4 py-1  text-base rounded-full text-green-600  bg-green-200 ">
                python
              </span> */}
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
