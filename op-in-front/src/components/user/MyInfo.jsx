import React from "react";
import { Tooltip } from "react-tooltip";
import "react-tooltip/dist/react-tooltip.css";
import TagInfo from "./TagInfo";
import { useState } from "react";
import DeleteModal from "@components/modals/DeleteModal";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";
import { useNavigate } from "react-router-dom";
import useAuth from "@hooks/useAuth";

const GithubSvg = () => {
  return (
    <svg
      version="1.0"
      xmlns="http://www.w3.org/2000/svg"
      width="512.000000pt"
      height="512.000000pt"
      viewBox="0 0 512.000000 512.000000"
      preserveAspectRatio="xMidYMid meet"
      className="w-6 h-6"
    >
      <g
        transform="translate(0.000000,512.000000) scale(0.100000,-0.100000)"
        fill="#000000"
        stroke="none"
      >
        <path
          d="M2247 4995 c-1043 -126 -1913 -881 -2167 -1880 -59 -234 -74 -356
-74 -610 -1 -129 5 -259 13 -315 108 -783 536 -1431 1212 -1841 206 -124 479
-239 570 -239 45 0 55 4 85 34 22 22 34 44 35 63 2 40 -3 448 -4 449 -1 1 -42
-5 -92 -13 -108 -17 -273 -12 -360 12 -115 30 -202 79 -279 158 -64 63 -81 90
-144 217 -83 169 -138 244 -230 308 -142 100 -165 144 -87 171 31 10 54 11
103 3 136 -22 259 -109 355 -251 120 -178 219 -247 389 -271 78 -11 185 1 285
31 71 22 71 22 98 131 18 70 67 163 111 209 l25 26 -63 7 c-103 12 -293 55
-386 88 -168 59 -276 123 -395 237 -222 212 -326 520 -314 931 4 141 9 178 31
255 36 121 88 223 164 323 48 64 60 86 53 100 -21 40 -41 168 -41 261 0 107
18 219 50 317 25 73 38 78 145 63 136 -19 330 -99 506 -209 71 -45 80 -48 111
-39 84 24 309 59 450 69 228 17 538 -10 737 -64 l54 -15 91 56 c175 108 358
183 495 203 108 15 117 10 145 -79 13 -42 29 -111 36 -154 17 -103 8 -293 -17
-372 l-19 -60 46 -56 c150 -187 212 -376 211 -645 -1 -671 -304 -1054 -930
-1179 -64 -12 -142 -26 -174 -30 l-58 -7 35 -38 c43 -48 88 -135 112 -216 15
-51 18 -122 23 -504 5 -334 9 -451 19 -467 17 -31 63 -53 108 -53 93 0 453
163 659 297 165 109 299 219 435 359 385 395 615 869 691 1424 20 144 17 507
-5 655 -172 1140 -1081 2015 -2234 2150 -154 18 -464 18 -615 0z"
        />
      </g>
    </svg>
  );
};

const renderRepos = (list = [], flag) => {
  const navigate = useNavigate();
  return list.map((item, index) => (
    <li
      key={item.id}
      className="flex items-center justify-between py-3 text-gray-600 border-b-2 border-gray-100 "
    >
      <div
        className="flex items-center justify-start text-sm hover:text-blue-400"
        onClick={() =>
          navigate(`/repo/${item.id}`, {
            state: item.id,
          })
        }
      >
        <span className="mx-4">{index + 1}</span>
        <span>{item.title}</span>
      </div>
      <span className="mr-4">
        {flag && (
          <a
            id={index + item.title}
            className="inline-flex"
            href={item.html}
            target="_blank"
            rel="noreferrer"
          >
            <GithubSvg />
            <Tooltip
              anchorId={index + item.title}
              content="go to Github page"
            />
          </a>
        )}
      </span>
    </li>
  ));
};

const MyInfo = ({ currentUser, addTag, deleteTag }) => {
  const [open, setOpen] = useState(false);
  const user = useRecoilValue(userInfo);
  const isMine = user.nickname == currentUser.nickname;
  const { auth } = useAuth();
  function toggleModal() {
    setOpen((prev) => !prev);
  }

  return (
    <div className="mx-0 mb-4 h-full ">
      <div className="w-full h-full bg-white shadow-lg rounded-2xl ">
        <hr />
        <div className="mr-3">
          <TagInfo
            title="Language"
            taglist={currentUser?.techLanguages}
            ismine={isMine}
            addTag={addTag}
            deleteTag={deleteTag}
          />
        </div>
        <hr />
        <div className="mr-3">
          <TagInfo
            title="Topic"
            taglist={currentUser?.topicResponses}
            ismine={isMine}
            addTag={addTag}
            deleteTag={deleteTag}
          />
        </div>
        <hr />
        <p className="p-4 font-bold text-black text-md ">
          Completed Contributes
          <span className="ml-2 text-sm text-gray-500 ">
            ({currentUser.contributeRepo?.length || 0})
          </span>
        </p>
        <ul>{renderRepos(currentUser.contributeRepo, true)}</ul>
        <hr />
        <p className="p-4 font-bold text-black text-md ">
          following Respsitories
          <span className="ml-2 text-sm text-gray-500 ">
            ({currentUser.followRepos?.length || 0})
          </span>
        </p>
        <ul>{renderRepos(currentUser.followRepos, false)}</ul>
        {isMine && auth?.githubSync == false && (
          <div className="grid grid-cols-1 justify-items-center py-4">
            <button
              type="button"
              onClick={toggleModal}
              className={`py-3 px-4 bg-red-600 hover:bg-red-700 focus:ring-red-500
        focus:ring-offset-red-200 text-white transition ease-in duration-200
        text-center font-semibold shadow-md focus:outline-none focus:ring-2
        focus:ring-offset-2 opacity-70 rounded-lg`}
            >
              Delete Member
            </button>
          </div>
        )}
      </div>
      <DeleteModal open={open} setOpen={setOpen} />
    </div>
  );
};
export default MyInfo;
