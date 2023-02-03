import React from "react";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import Header from "../components/Header";
import SignIn from "./user/SignIn";
import SignUp from "./user/SignUp";
import UserFind from "./user/UserFind";
import Menus from "./Menus";
import Detail from "./user/Detail";
import SelectTag from './user/SelectTag';

import { useRecoilState } from "recoil";
import { userStored } from "../recoil/user/atoms";
import { useEffect } from "react";

// import DashBoard from "./DashBoard";
// import Repository from "./Repository";
// import Education from "./Education";
// import Events from "./Events";
import Search from "./Search";
import NotFound from "./NotFound";

export default function Main() {
  // const [subDomain] = useLocation();
  const [user, setUser] = useRecoilState(userStored);
  useEffect(() => {
    setUser([
      {
        id: 100,
        name: "Keane Sykes",
        address: "Ap #765-5550 A, Av.",
        phone: "010-1152-3825",
        memo: "luctus, ipsum leo elementum sem, vitae aliquam eros turpis non",
      },
    ]);
  }, []);

  return (
    <BrowserRouter>
      <div className="Main">
        <Header />
        <main className="relative h-screen overflow-hidden bg-gray-100 dark:bg-gray-800">
          <Routes>
            <Route path="/search" element={<Search />} />
            <Route path="/signin" element={<SignIn />} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="/userfind" element={<UserFind />} />
            <Route path="/detail" element={<Detail />} />
            <Route path="/selecttag" element={<SelectTag/>} />
            <Route exact path="/" element={<Navigate to="/main" />} />
            <Route exact path="/main/*" element={<Menus />}></Route>
            <Route path="*" element={<NotFound />} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  );
}
