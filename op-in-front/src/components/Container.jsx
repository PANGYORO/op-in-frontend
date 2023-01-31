import cx from "classnames";
function Container({ className, children }) {
  return <div className={cx("container", className)}>{children}</div>;
}

export default Container;
