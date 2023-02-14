import React from "react";
import { useNavigate } from "react-router-dom";

const Repo = ({
  id = 423494644,
  title = "JavaProject_PizzaOrder",
  content = "2학년 2학기 '윈도우프로그래밍프로젝트' 과목 중 진행했던 팀 프로젝트 입니다.",
  techLangs = [],
}) => {
  const navigate = useNavigate();

  return (
    <>
      <div className="w-full inset-x-0 bottom-0 bg-white  shadow-lg rounded-xl p-4 flex flex-col">
        <div
          onClick={() =>
            navigate(`/repo/${id}`, {
              state: id,
            })
          }
        >
          <div className="truncate">
            <span className="text-lg font-bold">{title}</span>
          </div>
          <div className="w-full h-24 text-clip overflow-hidden">
            <p className="text-gray-600  my-5">{content}</p>
          </div>
        </div>

        {/* 여기 언어 공간 */}
        {techLangs?.length > 0 && (
          <div>
            <div
              className={`inline-flex items-center bg-white leading-none rounded-lg py-2 text-teal text-sm w-full`}
            >
              <div className="overflow-hidden flex flex-wrap items-center gap-2">
                {techLangs.map((techLang, index) => {
                  return (
                    <span
                      key={index}
                      className="px-4 py-1  text-xs rounded-full text-red-600  bg-red-200 "
                    >
                      {techLang.title}
                    </span>
                  );
                })}
              </div>
            </div>
          </div>
        )}
      </div>
    </>
  );
};
export default Repo;
