import React from "react";
import PackageDocuments from "./education/documents/PackageDocuments";
import { Tooltip } from "react-tooltip";
import OpenSourceContribute from "@assets/opensourceContribute.png";
import { useNavigate } from "react-router-dom";

const TutorialContent = () => {
  const navigate = useNavigate();
  return (
    <div className="p-3">
      <div className="grid grid-cols-5 gap-4 pb-4">
        <img
          src={OpenSourceContribute}
          alt="OpenSource Contribute"
          className="col-span-2"
        />
        <div className="col-span-3">
          <Tooltip
            anchorId="firstcontribute"
            content="go to First Contribute Github page"
          />
          <p>
            Op-in에서는{" "}
            <a
              id="firstcontribute"
              className="inline-flex text-blue-400"
              href="https://github.com/firstcontributions/first-contributions"
              target="_blank"
              rel="noreferrer"
            >
              &ldquo;firstcontributions&rdquo; github
            </a>
            에서 readme.md 파일을 수정하여 오픈소스에 기여해보는 방식으로
            튜토리얼을 제공합니다.
            <br />
            <br />
          </p>
          <div className="grid justify-items-center">
            <p>아래 버튼을 클릭하여 튜토리얼을 진행해 보세요.</p>

            <button
              type="button"
              onClick={() => {
                navigate(`/tutorial/pr`);
              }}
              className="text-white bg-gradient-to-r from-blue-500 via-blue-600 
            to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none 
            focus:ring-blue-300 dark:focus:ring-blue-800 shadow-lg shadow-blue-500/50 
            dark:shadow-lg dark:shadow-blue-800/80 font-medium rounded-lg text-3xl px-10 py-2.5 text-center mt-1 mb-4 "
            >
              Try Contribute -&gt;
            </button>
          </div>

          <p>
            오픈소스 기여에 대한 더 많은 정보는 아래 링크를 참조하세요.
            <br />
            <a
              id="firstcontribute"
              className="inline-flex text-blue-400"
              href="https://opensource.guide/ko/how-to-contribute/"
              target="_blank"
              rel="noreferrer"
            >
              오픈소스에 기여하는 방법
            </a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default function Education() {
  return (
    <>
      <div className="flex flex-col w-full pl-0 md:p-4 md:space-y-4">
        <header className="z-40 items-center w-full h-16 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
          <div className="relative flex items-center w-full h-full group ml-3 text-2xl">
            Educations
          </div>
        </header>
        <header className="z-10 items-center w-full bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
          <div className="p-5">
            <h3 className="text-3xl font-bold">Tutorials</h3>
          </div>
          <div className="relative z-10 flex flex-col justify-center h-full px-3 mx-auto flex-center">
            <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0 h-full">
              <div className="container relative left-0 z-10 flex w-full">
                <TutorialContent />
              </div>
            </div>
          </div>
        </header>
        <header className="items-center w-full bg-white shadow-lg dark:bg-gray-700 rounded-2xl ">
          <div className="p-5">
            <h3 className="text-3xl font-bold">Documentations</h3>
          </div>
          <div className="relative z-10 flex flex-col justify-center w-full h-full px-3 mx-auto flex-center">
            <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0 h-full">
              <div className="container w-full">
                <PackageDocuments />
              </div>
            </div>
          </div>
        </header>
      </div>
    </>
  );
}
