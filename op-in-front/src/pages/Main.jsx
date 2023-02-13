import React, { useEffect } from "react";
import { Route, Routes, Outlet } from "react-router-dom";
import Header from "@components/Header";
import SignIn from "@pages/user/SignIn";
import SignUp from "@pages/user/SignUp";
import UserFind from "@pages/user/UserFind";
import UserDetail from "@pages/user/UserDetail";
import SelectTag from "@pages/user/SelectTag";
import PRTutorial from "@pages/education/tutorial/PRTutorial";
import TutorialComplete from '@pages/education/tutorial/tutorialComplete';
import ChooseTutorial from '@pages/education/tutorial/ChooseTutorial';

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
import "@assets/css/editor.css";

import useAuth from "@hooks/useAuth";
import { ScrollToTop } from "@components/ScrollToTop";

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
    <div className="Main bg-gray-100">
      <Header />
      <main className="relative bg-gray-100 dark:bg-gray-800">
        <Routes>
          <Route path="search" element={<Search />} />
          <Route path="signin" element={<SignIn />} />
          <Route path="signup" element={<SignUp />} />
          <Route path="userfind" element={<UserFind />} />
          <Route path="userdetail" element={<UserDetail />} />
          <Route path="selecttag" element={<SelectTag />} />
          <Route path="/tutorial/pr" element={<PRTutorial />} />
          <Route path="/tutorial/complete" element={<TutorialComplete />} />
          <Route path="/tutorial/choose" element={<ChooseTutorial />} />
          
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
        <ScrollToTop />
      </main>
    </div>
  );
}
