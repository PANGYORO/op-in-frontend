import React from "react";
// import { Router,useLocation } from 'react-router-dom';
import Header from "../components/Header";
import Sidebar from "../components/SideBar";


export default function Main() {
  // const [subDomain] = useLocation();
  return (
    <div>
      <div>
        <Header />
        <main className="relative h-screen overflow-hidden bg-gray-100 dark:bg-gray-800 rounded-2xl">
          <Sidebar />
          {/* <Router subDomain ? component TK>
            
          </Router> */}
        </main>
      </div>
    </div>
  );
}
