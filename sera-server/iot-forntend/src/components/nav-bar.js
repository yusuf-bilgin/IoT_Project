import { Link, NavLink } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../app/hooks";
import { logout } from "../features/auth/authenticationSlice";
import { useEffect } from "react";

function Navbar() {
  const isLogin = useAppSelector((state) => state.auth.isLogin);

  return (
    // Fixed navbar
    <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <div className="container-fluid">
        <a className="navbar-brand" href="/">
          IoT Greenhouse
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarCollapse"
          aria-controls="navbarCollapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarCollapse">
          <ul className="navbar-nav me-auto mb-2 mb-md-0">
            {isLogin && (
              <>
                <li className="nav-item" aria-current="page">
                  <NavLink
                    to="/reports"
                    className={`nav-link ({ isActive, isPending }) =>
                                    isPending ? "pending" : isActive ? "active" : ""`}
                  >
                    Reports
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink
                    to="/ai"
                    className={`nav-link ({ isActive, isPending }) =>
                                    isPending ? "pending" : isActive ? "active" : ""`}
                  >
                    AI
                  </NavLink>
                </li>
              </>
            )}
          </ul>
          <LoginLogoutButton />
        </div>
      </div>
    </nav>
  );
}

const LoginLogoutButton = () => {
  const isLogin = useAppSelector((state) => state.auth.isLogin);
  const dispatch = useAppDispatch();

  const handleLogout = () => {
    dispatch(logout());

    localStorage.removeItem("jwt");
  };

  // useEffect(() => {

  // }, isLogin)

  return isLogin ? (
    <Link to={"/login"}>
      <button onClick={handleLogout} className="btn btn-danger">
        Logout
      </button>
    </Link>
  ) : (
    <Link to={"/login"}>
      <button className="btn btn-success">Login</button>
    </Link>
  );
};

export default Navbar;
