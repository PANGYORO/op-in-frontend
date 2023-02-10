import React, { useEffect, useState } from "react";
import Repo from "@components/repository/Repo";
// import RepoPost from "@components/repository/RepoPost";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";
import { useNavigate } from "react-router-dom";
import http from "@api/http";
import axios from "axios";

// axios 사용하기
// function Tests() {
//   const [users, setUsers] = useState(null);
//   const [loading, setLoading] = useState(false);
//   const [error, setError] = useState(null);

//   useEffect(() => {
//     const fetchUsers = async () => {
//       try {
//         // 요청이 시작 할 때에는 error 와 users 를 초기화하고
//         setError(null);
//         setUsers(null);
//         // loading 상태를 true 로 바꿉니다.
//         setLoading(true);
//         const response = await axios.get(
//           // 주소만 새로 설정하기
//           "https://jsonplaceholder.typicode.com/users"
//         );
//         setUsers(response.data); // 데이터는 response.data 안에 들어있습니다.
//       } catch (e) {
//         setError(e);
//       }
//       setLoading(false);
//     };

//     fetchUsers();
//   }, []);

//   if (loading) return <div>로딩중..</div>;
//   if (error) return <div>에러가 발생했습니다</div>;
//   if (!users) return null;
//   return (
//     <ul>
//       {users.map((user) => (
//         <li key={user.id}>
//           {user.username}({user.id})
//         </li>
//       ))}
//     </ul>
//   );
// }

// function ApiTest() {
//   // axios.get('http://i8c211.p.ssafy.io:5001/post')
//   axios.get('https://my-json-server.typicode.com/typicode/demo/posts')
//     .then(function (response) {
//       console.log(response.data[0].id);
//       // test = response.data[0]
//     }).catch((error => {
//       console.log(error);
//     }))

// }

const repoData = {
  id: 2,
  title: "test title",
  content: "test content",
  techLangs: ["java", "C++", "HTML", "a", "b"],
  contributors: [
    {
      nickname: "박소망",
      id: "1",
      profileImg: null,
    },
    {
      nickname: "swany0509",
      id: "2",
      profileImg: null,
    },
    {
      nickname: "swany0509",
      id: "3",
      profileImg: null,
    },
  ],
  gitContributors: null,
  star: "12334",
  forkNum: "1232",
  topicList: ["JPA", "Security", "Django"],
  updateDate: null,
};
const RepoSelection = () => {
  const user = useRecoilValue(userInfo);
  const navigate = useNavigate();
  const [repoDatas, setRepoData] = useState([]);

  useEffect(() => {
    if (!user.logined) navigate(`/post`);
    else getData();
  }, []);

  const getData = async () => {
    await http
      .post(`/post`, {
        email: user.email,
      })
      .then(({ data }) => {
        setRepoData(data);
        console.log("success");
      })
      .catch((error) => {
        console.log("fail");
        console.log(error);
      });
  };

  return (
    <div className="flex flex-col w-full pl-0 md:p-4 h-screen">
      <div className="flex items-top w-full  pt-2 pb-24 pl-2 pr-2 overflow-auto md:pt-0 md:pr-0 md:pl-0">
        <div className="grid grid-cols-2 gap-4 w-full">
          <Repo
            id={repoData.id}
            title={repoData.title}
            content={repoData.content}
            techLangs={repoData.techLangs}
            repoDetails={repoData}
          />
          {repoDatas.map((repoData) => {
            return (
              <Repo
                key={repoData.id}
                id={repoData.id}
                title={repoData.title}
                content={repoData.content}
                techLangs={repoData.techLangs}
                repoDetails={repoData}
              />
            );
          })}
        </div>
      </div>
    </div>
  );
}
export default RepoSelection;