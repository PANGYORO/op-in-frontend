import React, { useEffect, useState } from "react";
import User from "@components/user/User";
import http from "@api/http";
import { useToast } from "@hooks/useToast";


const UserList = ({ users }) => {
  console.debug(users);
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
        if (!(data?.length)) setToast({ message: "User 검색 결과가 존재하지 않습니다." });
        setResults([...data]);
      })
      .catch((error) => {
        console.debug(error);
      });
  }, [value]);
  const getPreviousData = (pageNum) => {
    http
      .get(`search/users?query=${value}&size=${size}&page=${pageNum}`)
      .then(({ data }) => {
        setResults([...data]);
      })
      .catch((error) => {
        console.debug(error);
      });
  }
  const getNextData = (pageNum) => {
    http
      .get(`search/users?query=${value}&size=${size}&page=${pageNum}`)
      .then(({ data }) => {
        console.debug(data);
        if (data?.length > 0) {
          setResults([...data]);
          setPage(page + 1);
        }
        else {
          if (page != 0) setToast({ message: "마지막 페이지입니다." });
        }
        console.debug(page);
      })
      .catch((error) => {
        console.debug(error);
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
          }} className="inline-flex items-center px-4 py-2 text-xl font-medium text-gray-500 bg-white border border-gray-300 rounded-lg hover:bg-gray-100 hover:text-gray-700     ">
            &lt; Previous
          </div>
          <div onClick={() => {
            getNextData(page + 1);
          }} className="inline-flex items-center px-4 py-2 ml-3 text-xl font-medium text-gray-500 bg-white border border-gray-300 rounded-lg hover:bg-gray-100 hover:text-gray-700     ">
            Next	&gt;
          </div>
        </div>
      </div>
      <UserList users={results} />
    </>
  );
};
export default Users;

