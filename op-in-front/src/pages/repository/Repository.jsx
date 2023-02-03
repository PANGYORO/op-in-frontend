import React from "react";
import { Routes, Route } from "react-router-dom";
import RepoSelection from "./main/RepoSelection";
import Recommend from "../../components/Recommend";
import RepoDetail from "./following/RepoDetail";

// import Recommend from "../../components/Recommend";

export default function Repository() {
  return (
    <Routes>
      <Route path="/" element={<RepoSelection />} />
      <Route path="/repdetail" element={<RepoDetail />} />
      <Route path="/recommand" element={<Recommend />} />
    </Routes>
  );
}
