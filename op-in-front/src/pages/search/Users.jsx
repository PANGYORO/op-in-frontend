import React, { useEffect, useState } from "react";
import User from "@components/user/User";
import http from "@api/http";

const UserList = ({ users }) => {
  console.log(users);
  return (
    <div className="grid grid-cols-3 gap-4 ml-4">
      {users.map((user) => {
        return (
          <User
            key={user.id}
            profileimg={user.user_img}
            nickname={user.nickname}
            isfollow={user.follow}
          />
        );
      })}
    </div>
  );
};

const Users = ({ value }) => {
  const [results, setResults] = useState([]);
  useEffect(() => {
    // console.log(`search/users?query=${value}`);
    http
      .get(`search/users?query=${value}`)
      .then(({ data }) => {
        setResults([...data]);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <>
      <UserList users={results} />
    </>
  );
};
export default Users;
