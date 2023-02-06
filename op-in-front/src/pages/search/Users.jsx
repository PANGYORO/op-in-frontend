import React from "react";
import User from "@components/user/User";

export default function Users() {
  return (
    <>
      <div className="grid grid-cols-3 gap-4 ml-4">
        <User />
        <User />
        <User />
        <User />
        <User />
        <User />
      </div>
    </>
  );
}
