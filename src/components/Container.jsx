import cx from "classnames";
import React from "react";

function Container({ className, children }) {
  return <div className={cx("container", className)}>{children}</div>;
}

export default Container;

