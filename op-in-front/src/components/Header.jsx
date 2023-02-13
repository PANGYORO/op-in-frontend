import React, { Fragment, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import cx from "classnames";

import { Disclosure, Menu, Transition } from "@headlessui/react";
import { Bars3Icon, XMarkIcon } from "@heroicons/react/24/outline";

import { useSetRecoilState } from "recoil";
import { menuState, repoMenuState } from "@recoil/sidebar/atoms";

import Logo from "@components/Logo";
import useAuth from "@hooks/useAuth";
import { useToast } from "@hooks/useToast";

import DefaultImg from "@assets/basicprofile.png";

const navigation = [
  //메뉴 목록
  { name: "SignIn", href: "/signin", current: true },
  { name: "SignUp", href: "/signup", current: true },
];

const Search = ({ search }) => {
  const [value, setValue] = useState("");

  const onKeyDown = (e) => {
    if (e.key == "Enter") {
      search(value);
      setValue("");
    }
  };

  const onChange = (e) => {
    setValue(e.target.value);
  };

  return (
    <div className="relative flex items-center w-full h-full lg:w-64 group">
      <div className="absolute z-50 flex items-center justify-center block w-auto h-10 p-3 pr-2 text-sm text-gray-500 uppercase cursor-pointer sm:hidden">
        <svg
          fill="none"
          className="relative w-5 h-5"
          strokeLinecap="round"
          strokeLinejoin="round"
          strokeWidth="2"
          stroke="currentColor"
          viewBox="0 0 24 24"
        >
          <path d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
        </svg>
      </div>
      <svg
        className="absolute left-0 z-20 hidden w-4 h-4 ml-4 text-gray-500 pointer-events-none fill-current group-hover:text-gray-400 sm:block"
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 20 20"
      >
        <path d="M12.9 14.32a8 8 0 1 1 1.41-1.41l5.35 5.33-1.42 1.42-5.33-5.34zM8 14A6 6 0 1 0 8 2a6 6 0 0 0 0 12z"></path>
      </svg>
      <input
        value={value}
        type="text"
        className="block w-full py-1.5 pl-10 pr-4 leading-normal rounded-2xl focus:border-transparent focus:outline-none focus:ring-2 focus:ring-blue-500 ring-opacity-90 bg-gray-100 dark:bg-gray-800 text-gray-400 aa-input"
        placeholder="Search"
        onKeyUp={onKeyDown}
        onChange={onChange}
      />
    </div>
  );
};

const Header = () => {
  const setCurrentMenu = useSetRecoilState(menuState);
  const setRepoCurrentMenu = useSetRecoilState(repoMenuState);
  const navigate = useNavigate();
  const { logout, auth } = useAuth();
  const { setToast } = useToast();

  const selectMenu = (id) => {
    setCurrentMenu(id);
    setRepoCurrentMenu("myrepo");
  };

  const search = (value) => {
    if (value != "") {
      navigate(`/search`, {
        state: value,
      });
    } else {
      setToast({ message: "검색 키워드를 입력해주세요" });
    }
  };

  return (
    <Disclosure as="nav" className="bg-gray-800 sticky top-0 z-50">
      {({ open }) => (
        <>
          <div className="mx-44 px-2 sm:px-6 lg:px-8" style={{ minWidth: '720px' }} >
            <div className="relative flex h-16 items-center justify-between">
              {/* 사인 자리 가져와바 */}
              
              <div className="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
                <div className="flex flex-shrink-0 items-center">
                  {/* 로고자리 */}
                  <Link
                    to="/"
                    onClick={() => {
                      selectMenu("dashboard");
                    }}
                  >
                    <Logo className="h-10 w-auto lg:block" />
                  </Link>
                </div>

                <div className="hidden sm:ml-6 sm:block w-1/3">
                  <div className="flex space-x-4">
                    <Search search={search} />
                  </div>
                </div>
              </div>
              <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                <span className="text-white">{auth.nickname}</span>

                {auth.logined && (
                  <Menu as="div" className="relative ml-3">
                    <div>
                      <Menu.Button className="flex rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                        <img
                          className="h-8 w-8 rounded-full"
                          src={auth.img_url || DefaultImg}
                          alt=""
                        />
                      </Menu.Button>
                    </div>
                    <Transition
                      as={Fragment}
                      enter="transition ease-out duration-100"
                      enterFrom="transform opacity-0 scale-95"
                      enterTo="transform opacity-100 scale-100"
                      leave="transition ease-in duration-75"
                      leaveFrom="transform opacity-100 scale-100"
                      leaveTo="transform opacity-0 scale-95"
                    >
                      <Menu.Items className="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
                        <Menu.Item>
                          {({ active }) => (
                            <Link
                              to="/userdetail"
                              state={auth.nickname}
                              className={cx(
                                active &&
                                  "flex flex-col bg-gray-100 items-center",
                                "flex flex-col items-center block px-4 py-2 text-sm text-gray-700"
                              )}
                            >
                              Your Profile
                            </Link>
                          )}
                        </Menu.Item>
                        <Menu.Item>
                          {({ active }) => (
                            <button
                              className={cx(
                                active && "w-full bg-gray-100",
                                "w-full block px-4 py-2 text-sm text-gray-700"
                              )}
                              onClick={logout}
                            >
                              Sign out
                            </button>
                          )}
                        </Menu.Item>
                      </Menu.Items>
                    </Transition>
                  </Menu>
                )}

                {!auth.logined &&
                  navigation.map((item) => (
                    <Link
                      key={item.name}
                      to={item.href}
                      className={cx(
                        item.current
                          ? "bg-[#ffffff] text-[#000000] mx-2"
                          : "text-gray-300 hover:bg-gray-700 hover:text-white",
                        "px-3 py-2 rounded-md text-sm font-medium"
                      )}
                      aria-current={item.current ? "page" : undefined}
                    >
                      {item.name}
                    </Link>
                  ))}
                <Menu as="div" className="relative ml-3">
                  <Transition
                    as={Fragment}
                    enter="transition ease-out duration-100"
                    enterFrom="transform opacity-0 scale-95"
                    enterTo="transform opacity-100 scale-100"
                    leave="transition ease-in duration-75"
                    leaveFrom="transform opacity-100 scale-100"
                    leaveTo="transform opacity-0 scale-95"
                  ></Transition>
                </Menu>
              </div>
            </div>
          </div>
        </>
      )}
    </Disclosure>
  );
};
export default Header;
