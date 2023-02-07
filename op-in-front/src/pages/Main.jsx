import React, { useEffect } from "react";
import { Route, Routes, Outlet } from "react-router-dom";
import jwt_decode from "jwt-decode";
import { Cookies } from "react-cookie";
import Header from "@components/Header";
import SignIn from "@pages/user/SignIn";
import SignUp from "@pages/user/SignUp";
import UserFind from "@pages/user/UserFind";
import UserDetail from "@pages/user/UserDetail";
import SelectTag from "@pages/user/SelectTag";

import Search from "@pages/Search";
import NotFound from "@pages/NotFound";
import Sidebar from "@components/SideBar";
import DashBoard from "@pages/DashBoard";
import Education from "@pages/Education";
import Events from "@pages/Events";
import RepoSelection from "@pages/repository/main/RepoSelection";
import RecommandIndex from "@pages/repository/Recommand";
import RepoDetail from "./repository/following/RepoDetail";
import PostView from "@components/PostView";
import useToken from "@hooks/useToken";
import { useSetRecoilState } from "recoil";
import { userInfo } from "@recoil/user/atoms";

function MainTemplate() {
  return (
    <div className="flex items-start justify-between mx-44">
      <Sidebar />
      <Outlet />
    </div>
  );
}

function RepoTemplate() {
  return <Outlet />;
}

export default function Main() {
  const { token } = useToken();
  const cookies = new Cookies();
  const setUser = useSetRecoilState(userInfo);

  useEffect(() => {
    const accessToken = cookies.get("accessToken");
    if (accessToken) {
      const decodedUserInfo = jwt_decode(accessToken);
      setUser((before) => ({
        ...before,
        ...decodedUserInfo,
        logined: true,
      }));
    } else {
      //async refreshToken 보내서 accessToken 갱신해서 recoil 저장
      // refreshToken 시간 만료시 다시 로그인하라는 알림 띄우기
    }
  }, [token]);

  return (
    <div className="Main h-screen overflow-auto">
      <Header />
      <main className="relative h-screen overflow-hidden bg-gray-100 dark:bg-gray-800">
        <Routes>
          <Route path="search" element={<Search />} />
          <Route path="signin" element={<SignIn />} />
          <Route path="signup" element={<SignUp />} />
          <Route path="userfind" element={<UserFind />} />
          <Route path="userdetail" element={<UserDetail />} />
          <Route path="selecttag" element={<SelectTag />} />
          <Route exact path="/" element={<MainTemplate />}>
            <Route exact index element={<DashBoard />} />
            <Route path="repo" element={<RepoTemplate />}>
              <Route index element={<RepoSelection />} />
              <Route exact path="recommand" element={<RecommandIndex />} />
              <Route exact path=":repoNum" element={<RepoDetail />} />
              <Route path="postview" element={<PostView />} />
            </Route>
            <Route path="postview" element={<PostView />} />
            <Route path="edu" element={<Education />} />
            <Route path="events" element={<Events />} />
          </Route>
          <Route path="*" element={<NotFound />} />
        </Routes>
      </main>
    </div>
  );
}
