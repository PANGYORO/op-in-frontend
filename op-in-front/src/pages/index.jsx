import { createBrowserRouter } from 'react-router-dom';

import Main from "./Main";
import Repository from "./Repository";
import Education from "./Education";
import Search from "./Search";
import User from "./User";

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
    path: "/user",
    element: <User />,
  },

]);

export default router;