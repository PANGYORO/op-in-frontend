import React from "react";
import { useNavigate } from "react-router-dom";
// import axios from "axios";

// const Reopitem = ({ repos }) => {
//     const { img, name, contributor, star } = repos;
//     return (

//     )
//   }
export default function Repo() {
  const navigate = useNavigate();

  return (
    <>
      <div className="w-full inset-x-0 bottom-0 bg-white dark:bg-gray-800 shadow-lg rounded-xl p-4 flex flex-col">
        <div onClick={() => navigate(`/repo/123123`, { state: "123123" })}>
          <div className="truncate">
            <span className="text-lg font-bold">
              참여레포 이 말이 지워지는지 확인하고 싶은데 어떻게
              생각하세요dydydydydydy
            </span>
          </div>
          <div className="w-full h-24 text-clip overflow-hidden">
            <p className="text-gray-600 dark:text-white my-5 pl-2">
              내용칸 입니다. 내용칸 입니다. 이 말이 지워지는지 확인하고 싶은데
              어떻게 생각하세요 이 말이 지워지는지 확인하고 싶은데 어떻게
              생각하세요 이 말이 지워지는지 확인하고 싶은데 어떻게 생각하세요 이
              말이 지워지는지 확인하고 싶은데 어떻게 생각하세요 이 말이
              지워지는지 확인하고 싶은데 어떻게 생각하세요 이 말이 지워지는지
              확인하고 싶은데 어떻게 생각하세요 이 말이 지워지는지 확인하고
              싶은데 어떻게 생각하세요 이 말이 지워지는지 확인하고 싶은데 어떻게
              생각하세요
            </p>
          </div>
        </div>
        {/* 여기 언어 공간 */}
        <div>
          <div className="inline-flex items-center bg-white leading-none ${props.textColor} rounded-lg p-2 shadow text-teal text-sm">
            <div className="flex flex-wrap items-center gap-4">
              <span className="px-4 py-1  text-base rounded-full text-red-600  bg-red-200 ">
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
              </span>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
