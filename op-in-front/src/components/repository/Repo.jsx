import React from "react";
import { useNavigate } from "react-router-dom";

const Repo = ({
  id = 1,
  title = "test",
  content = "testrepo",
  techLangs = [{ title: "react" }, { title: "HTML" }],
  repoDetails = [
    {
      id: 2,
      title: "test title",
      content: "test content",
      techLangs: [],
      contributors: [
        {
          nickname: "testmj",
          id: "1",
          profileImg: null,
        },
      ],
      gitContributors: null,
      star: "1233455565",
      forkNum: "123214141",
      topicList: [],
      updateDate: null,
    },
  ],
}) => {
  const navigate = useNavigate();

  return (
    <>
      <div className="w-full inset-x-0 bottom-0 bg-white dark:bg-gray-800 shadow-lg rounded-xl p-4 flex flex-col">
        <div
          onClick={() =>
            navigate(`/repo/${id}`, {
              state: repoDetails,
            })
          }
        >
          <div className="truncate">
            <span className="text-lg font-bold">{title}</span>
          </div>
          <div className="w-full h-24 text-clip overflow-hidden">
            <p className="text-gray-600 dark:text-white my-5 pl-2">{content}</p>
          </div>
        </div>

        {/* 여기 언어 공간 */}
        <div>
          <div
            className={`inline-flex items-center bg-white leading-none rounded-lg p-2 shadow text-teal text-sm w-full`}
          >
            <div className="overflow-hidden flex flex-wrap items-center gap-4">
              {techLangs.map((techLang, index) => {
                return (
                  <span
                    key={index}
                    className="px-4 py-1  text-base rounded-full text-red-600  bg-red-200 "
                  >
                    {techLang.title}
                  </span>
                );
              })}
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default Repo;
