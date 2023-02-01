import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "../components/Header";
import DashBoard from "./DashBoard";
import Repository from "./Repository";
import Education from "./Education";
import Search from "./Search";
import SignIn from "./user/SignIn";
import SignUp from "./user/SignUp";
import Detail from "./user/Detail";
import UserFind from "./user/UserFind";
import NotFound from "./NotFound";

export default function Main() {
  // const [subDomain] = useLocation();
  return (
    <BrowserRouter>
      <div className="Main">
        <Header />
        <main className="relative h-screen overflow-hidden bg-gray-100 dark:bg-gray-800 rounded-2xl">
          <div className="flex items-start justify-between mx-44">
            <Routes>
              <Route exact path="/" element={<DashBoard />} />
              <Route path="/repo" element={<Repository />} />
              <Route path="/edu" element={<Education />} />
              <Route path="/search" element={<Search />} />
              <Route path="/signin" element={<SignIn />} />
              <Route path="/signup" element={<SignUp />} />
              <Route path="/detail" element={<Detail />} />
              <Route path="/userfind" element={<UserFind />} />
              <Route path="*" element={<NotFound />} />
            </Routes>
          </div>
        </main>
      </div>
    </BrowserRouter>
  );
}
