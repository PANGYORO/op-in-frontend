import React from "react";
import Sidebar from "../components/SideBar";
import { Route, Routes } from "react-router-dom";
import DashBoard from "./DashBoard";
import Repository from "./Repository";
import Education from "./Education";
import Search from "./Search";
import Events from "./Events";
import NotFound from "./NotFound";
import Question from './repository/main/Question';

export default function Menus() {
  return (
    <>
      <div className="flex items-start justify-between mx-44">
        <Sidebar />
        <Routes>
          <Route exact path="/" element={<DashBoard />} />
          <Route path="/repo" element={<Repository />} />
          <Route path="/edu" element={<Education />} />
          <Route path="/search" element={<Search />} />
          <Route path="/events" element={<Events />} />
          <Route path="/repo/qna" element={<Question />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </>
  );
}
