import React from "react";
import { Tooltip } from "react-tooltip";

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


const Package = ({ title, svg, link, tooltipId, relatedGitLink }) => {
  return (
    <div className="flex flex-col items-center max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-slate-200">
      <h5 className=" text-3xl font-bold tracking-tight text-gray-900 ">{title}</h5>
      <div className="p-2">{svg}</div>
      <div className="flex flex-row">
        <a
          href={link}
          target="_blank"
          rel="noreferrer"
          className="inline-flex items-center px-4 py-1.5 text-md font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 "
        >
          visit
          <svg
            aria-hidden="true"
            className="w-4 h-4 ml-2 -mr-1"
            fill="currentColor"
            viewBox="0 0 20 20"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fillRule="evenodd"
              d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z"
              clipRule="evenodd"
            ></path>
          </svg>
        </a>
        <a
          href={relatedGitLink}
          id={tooltipId}
          target="_blank"
          rel="noreferrer"
          className="p-2"
        >
          <GithubSvg />
        </a>
        <Tooltip anchorId={tooltipId} content="related github repos" />
      </div>
    </div>
  );
};
export default Package;
