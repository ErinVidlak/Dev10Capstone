import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import { Link, useLocation } from "react-router-dom";
import Logout from "./user/Logout";

export default function AppHeader() {
  const auth = useContext(AuthContext);
  const currentUser = JSON.parse(localStorage.getItem("user"));

  //const location = useLocation();

  return (
    <nav>
      {auth.user ? (
        <div className="nav-wrapper indigo lighten-4">
          <a href="/" className="brand-logo center black-text">
            Inventory Tracker
          </a>

          <ul className="right hide-on-med-and-down  black-text">
            <li>
              <a href="#" className="black-text">
                {auth.user.username}
              </a>
            </li>

            <li>
              <Logout />
            </li>
          </ul>
          <ul className="left hide-on-med-and-down  black-text">
            <li>
              <Link to="/materials">
                <span className="black-text">Materials</span>
              </Link>
            </li>
            <li>
              <Link to="/purchases">
                <span className="black-text">Purchases</span>
              </Link>
            </li>
            <li>
              <Link to="/products">
                <span className="black-text">Products</span>
              </Link>
            </li>
          </ul>
        </div>
      ) : (
        <div className="nav-wrapper indigo lighten-4">
          <a href="/" className="brand-logo center black-text">
            Inventory Tracker
          </a>
          <ul className="left hide-on-med-and-down ">
            <li>
              <Link to="/login">
                <span className="black-text">Login</span>
              </Link>
            </li>
            <li>
              <Link to="/register">
                <span className="black-text">Register</span>
              </Link>
            </li>
            <li></li>
          </ul>
        </div>
      )}
    </nav>
  );
}
