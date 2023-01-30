import { createBrowserRouter } from 'react-router-dom';

import Main from "./Main";
import Repository from "./Repository";
import Education from "./Education";
import Search from "./Search";
import SignIn from './user/SignIn';
import SignUp from './user/SignUp';
import Detail from './user/Detail';
import UserFind from './user/UserFind';


const router = createBrowserRouter([
  {
    path: "/",
    element: <Main />,
  },
  {
    path: "/repo",
    element: <Repository />,
  },
  {
    path: "/edu",
    element: <Education />,
  },
  {
    path: "/search",
    element: <Search />,
  },
  {
    path: "/detail",
    element: <Detail />,
  },
  {
    path: "/signin",
    element: <SignIn />,
  },
  {
    path: "/signup",
    element: <SignUp />,
  },
  {
    path: "/userfind",
    element: <UserFind />,
  },

]);

export default router;