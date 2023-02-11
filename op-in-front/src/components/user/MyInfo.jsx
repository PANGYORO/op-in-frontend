import React from "react";
import BasicBadge from "@assets/basicbadge.png";
import { Tooltip } from "react-tooltip";
import "react-tooltip/dist/react-tooltip.css";
import TagInfo from "./TagInfo";
import { useState } from "react";
import DeleteModal from "@components/modals/DeleteModal";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";

const renderRepos = (list = []) => {
  return list.map((item, index) => (
    <li
      key={item.id}
      className="flex items-center justify-between py-3 text-gray-600 border-b-2 border-gray-100 dark:text-gray-200 dark:border-gray-800"
    >
      <div
        className="flex items-center justify-start text-sm"
        // onClick={() =>
        //   visitRepo(item.id,item,title);
        // }
      >
        <span className="mx-4">{index + 1}</span>
        <span>{item.title}</span>
      </div>
    </li>
  ));
};

// Language , Topic
const MyInfo = ({ currentuser }) => {
  const [open, setOpen] = useState(false);
  const user = useRecoilValue(userInfo);

  function toggleModal() {
    setOpen((prev) => !prev);
  }

  return (
    <div className="mx-0 mb-4 h-full ">
      <div className="w-full h-full bg-white shadow-lg rounded-2xl dark:bg-gray-700">
        <p className="p-4 font-bold text-black text-md dark:text-white">
          Badges
          <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
            ({currentuser.badges == null ? 0 : currentuser.badges.length})
          </span>
        </p>
        <div className="grid grid-cols-8 gap-4 px-3 mb-3">
          <img id="badge-1" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-1" content="normal badge" />
          <img id="badge-2" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-2" content="normal badge" />
          <img id="badge-3" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-3" content="normal badge" />
          <img id="badge-4" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-4" content="normal badge" />
          <img id="badge-5" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-5" content="normal badge" />
          <img id="badge-6" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-6" content="normal badge" />
          <img id="badge-7" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-7" content="normal badge" />
        </div>
        <hr />
        <div className="mr-3">
          <TagInfo
            title="Language"
            taglist={currentuser?.techLanguages}
            ismine={user.nickname == currentuser.nickname ? true : false}
          />
        </div>
        <hr />
        <div className="mr-3">
          <TagInfo
            title="Topic"
            taglist={currentuser?.topicResponses}
            ismine={user.nickname == currentuser.nickname ? true : false}
          />
        </div>
        <hr />
        <p className="p-4 font-bold text-black text-md dark:text-white">
          Completed Contributes
          <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
            ({currentuser.contributeRepo == null ? 0 : currentuser.contributeRepo.length})
          </span>
        </p>
        <ul>{renderRepos(currentuser.contributeRepo)}</ul>
        <hr />
        <p className="p-4 font-bold text-black text-md dark:text-white">
          following Respsitories
          <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
            ({currentuser.followRepo == null ? 0 : currentuser.followRepo.length})
          </span>
        </p>
        <ul>{renderRepos(currentuser.followRepo)}</ul>
        {currentuser.nickname == user.nickname ? (
          <div className="grid grid-cols-1 justify-items-center py-4">
            <button
              type="button"
              disabled=""
              onClick={() => {
                toggleModal();
              }}
              className={`py-3 px-4 bg-red-600 hover:bg-red-700 focus:ring-red-500
        focus:ring-offset-red-200 text-white transition ease-in duration-200
        text-center font-semibold shadow-md focus:outline-none focus:ring-2
        focus:ring-offset-2 opacity-70 rounded-lg`}
            >
              Delete Member
            </button>
          </div>
        ) : (
          <div></div>
        )}
      </div>
      <DeleteModal open={open} setOpen={setOpen} />
    </div>
  );
};
export default MyInfo;
