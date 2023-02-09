import React from "react";

export default function RepoPostDetail() {
  return (
    <>
      {/* 제목 */}
      <div className="bg-gray-200 dark:bg-gray-800 border-4">
        <div className="lg:flex lg:items-center lg:justify-between w-full mx-auto py-12 px-4 sm:px-6 lg:py-5 lg:px-8 z-20">
          <h2 className="text-3xl font-extrabold text-black dark:text-white sm:text-4xl">
            <span className="block flex felx-col place-items-center">
              <div>
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    strokeWidth={1.5}
                    stroke="currentColor"
                    className="w-16 h-16 px-3"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      d="M17.982 18.725A7.488 7.488 0 0012 15.75a7.488 7.488 0 00-5.982 2.975m11.963 0a9 9 0 10-11.963 0m11.963 0A8.966 8.966 0 0112 21a8.966 8.966 0 01-5.982-2.275M15 9.75a3 3 0 11-6 0 3 3 0 016 0z"
                    />
                  </svg>
                
              </div>
              Repo Name
            </span>
          </h2>
          <div> 여기 오른쪽 끝에 붙여주는 칸</div>
        </div>
        {/* 내용칸 */}
        <p className="text-xs pl-4">2hour ago</p>
        <p className="text-xl px-4 my-4 text-gray-600">
          레포의 내용을 적는 칸입니다. 레포의 내용을 적는 칸입니다. 레포의 내용을 적는 칸입니다.
          레포의 내용을 적는 칸입니다. 레포의 내용을 적는 칸입니다.
        </p>
      </div>
    </>
  );
}
