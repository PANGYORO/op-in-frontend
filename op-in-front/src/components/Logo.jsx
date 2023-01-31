import cx from "classnames";
import React from "react";

import LightLogo from "../assets/logo-light.png";
import DarkLogo from "../assets/logo-dark.png";

function Logo({ darkMode = false, className }) {
  return <img className={cx(className)} src={darkMode ? DarkLogo : LightLogo} alt="Op-in Logo" />;
}

export default Logo;
