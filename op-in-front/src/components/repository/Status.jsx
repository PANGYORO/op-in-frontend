import DefaultImg from "@assets/basicprofile.png";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Tooltip } from "react-tooltip";
import ContributorsModal from "@components/modals/ContributorsModal";

const GithubSvg = () => {
  return (
    <svg
      version="1.0"
      xmlns="http://www.w3.org/2000/svg"
      width="512.000000pt"
      height="512.000000pt"
      viewBox="0 0 512.000000 512.000000"
      className="w-6 h-6"
      preserveAspectRatio="xMidYMid meet"
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

const Status = ({ repoDetail }) => {
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
            <Link
              id={"cont" + list[i].id}
              to={`/userdetail`}
              state={list[i].nickname}
              key={i}
            >
              <img
                className="inline-block h-10 w-10 rounded-full object-cover ring-2 ring-white"
                src={DefaultImg}
                alt={list[i].nickname}
              />
            </Link>
            <Tooltip
              anchorId={"cont" + list[i].id}
              content={list[i].nickname}
            />
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
    if (
      repoDetail?.contributors != null &&
      repoDetail?.contributors.length > 4
    ) {
      return repoDetail?.contributors.length - 4 + " more Contributors...";
    } else return "";
  };
  return (
    <div className="relative max-w-xs p-4 bg-white shadow-lg rounded-xl  ">
      <div className="my-2">
        <span className="font-bold">Contributors</span>
        <div className="relative max-w-xs  my-2 h-full">
          <div className="block w-full h-full">
            <div className="w-full">
              <div className="flex items-center">
                <div className="flex -space-x-2">
                  {contributerRender(repoDetail?.contributors)}
                </div>
              </div>
              <div
                className="mt-2 text-blue-500 "
                onClick={toggleModal}
              >
                {moreContributors()}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="my-2">{repoDetail?.content}</div>
      <div className="my-2 text-gray-400 ">
        {repoDetail?.updateDate == null ? "no data" : repoDetail?.updateDate}
      </div>
      <div className="my-2 font-bold">About</div>
      <div className="grid grid-cols-3 my-2">
        {tagRender(repoDetail?.techLangs)}
      </div>
      <div className="my-2 font-bold">Last Update</div>
      <div>{new Date(repoDetail.date).toLocaleString()}</div>
      <div className="m-4">
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
        <div>
          <a
            id="visitsite"
            className="inline-flex"
            href={repoDetail.html}
            target="_blank"
            rel="noreferrer"
          >
            <GithubSvg />
            <Tooltip anchorId="visitsite" content="go to Github page" />
            click to visit
          </a>
        </div>
      </div>
      <ContributorsModal
        open={open}
        setOpen={setOpen}
        contributors={repoDetail?.contributors}
      />
    </div>
  );
};
export default Status;
