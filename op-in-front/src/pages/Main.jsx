import React from "react";
import { Route, Routes, Outlet } from "react-router-dom";
import Header from "../components/Header";
import SignIn from "./user/SignIn";
import SignUp from "./user/SignUp";
import UserFind from "./user/UserFind";
import Detail from "./user/Detail";
import SelectTag from "./user/SelectTag";

import Search from "./Search";
import NotFound from "./NotFound";
import Sidebar from "../components/SideBar";
import DashBoard from "./DashBoard";
import Education from "./Education";
import Events from "./Events";
import RepoSelection from "./repository/main/RepoSelection";

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
  return (
    <div className="Main">
      <Header />
      <main className="relative h-screen overflow-hidden bg-gray-100 dark:bg-gray-800">
        <Routes>
          <Route path="search" element={<Search />} />
          <Route path="signin" element={<SignIn />} />
          <Route path="signup" element={<SignUp />} />
          <Route path="userfind" element={<UserFind />} />
          <Route path="detail" element={<Detail />} />
          <Route path="selecttag" element={<SelectTag />} />
          <Route exact path="/" element={<MainTemplate />}>
            <Route exact index element={<DashBoard />} />
            <Route path="repo" element={<RepoTemplate />}>
              <Route index element={<RepoSelection />} />
              <Route exact path="recommand" element={<div>123</div>} />
              <Route exact path=":repoNum" element={<div>125125</div>} />
            </Route>
            <Route path="edu" element={<Education />} />
            <Route path="events" element={<Events />} />
          </Route>
          <Route path="*" element={<NotFound />} />
        </Routes>
      </main>
    </div>
  );
}
