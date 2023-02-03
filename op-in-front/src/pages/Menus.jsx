import React from "react";
import Sidebar from "../components/SideBar";
import { Route, Routes } from "react-router-dom";
import DashBoard from "./DashBoard";
import Repository from "./repository/Repository";
import Education from "./Education";
import Events from "./Events";
import NotFound from "./NotFound";

export default function Menus() {
  return (
    <>
      <div className="flex items-start justify-between mx-44">
        <Sidebar />
        <Routes>
          <Route exact index element={<DashBoard />} />
          <Route path="repo" element={<Repository />} />
          <Route path="edu" element={<Education />} />
          <Route path="events" element={<Events />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </>
  );
}
