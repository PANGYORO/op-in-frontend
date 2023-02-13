import React, { useEffect, useState } from "react";
import User from "@components/user/User";
import http from "@api/http";
import { useToast } from "@hooks/useToast";


const UserList = ({ users }) => {
  console.log(users);
  return (
    <div className="grid grid-cols-3 gap-4 ml-4" >
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
  const [page, setPage] = useState(0);
  const { setToast } = useToast();
  const size = 10;
  useEffect(() => {
    http
      .get(`search/users?query=${value}&size=${size}&page=0`)
      .then(({ data }) => {
        setResults([...data]);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [value]);
  const getPreviousData = (pageNum) => {
    http
      .get(`search/users?query=${value}&size=${size}&page=${pageNum}`)
      .then(({ data }) => {
        setResults([...data]);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  const getNextData = (pageNum) => {
    http
      .get(`search/users?query=${value}&size=${size}&page=${pageNum}`)
      .then(({ data }) => {
        console.log(data);
        if (data?.length > 0) {
          setResults([...data]);
          setPage(page + 1);
        }
        else {
          if (page != 0) setToast({ message: "마지막 페이지입니다." });
        }
        console.log(page);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <>
      <div className="grid justify-center">
        <div className="pb-3">
          <div onClick={() => {
            if (page != 0) {
              getPreviousData(page - 1);
              setPage(page - 1);
            } else {
              setToast({ message: "첫 페이지입니다." });
            }
          }} className="inline-flex items-center px-4 py-2 text-xl font-medium text-gray-500 bg-white border border-gray-300 rounded-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
            &lt; Previous
          </div>
          <div onClick={() => {
            getNextData(page + 1);
          }} className="inline-flex items-center px-4 py-2 ml-3 text-xl font-medium text-gray-500 bg-white border border-gray-300 rounded-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
            Next	&gt;
          </div>
        </div>
      </div>
      <UserList users={results} />
    </>
  );
};
export default Users;

