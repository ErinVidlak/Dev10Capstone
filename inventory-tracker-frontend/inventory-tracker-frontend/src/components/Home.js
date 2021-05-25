import { useState, useContext } from "react";
import { Link, useHistory, useLocation } from "react-router-dom";
import AuthContext from "../context/AuthContext";
import Logout from "./user/Logout";

export default function Home() {
  const auth = useContext(AuthContext);

  return (
    <div className="container">

      {!auth.user && (
        <>
          <h2>Welcome Guest</h2>
          <Link to="/login">
            <button className="btn"> login </button>
          </Link>
          <Link to="/register">
            <button className="btn"> register </button>
          </Link>
        </>
      )}

      {auth.user && (
        <>
          <h2>Welcome Home {auth.user.username}</h2>
          <Link to="/materials">
            <button className="btn"> view materials </button>
          </Link>
          <Link to="/purchases">
            <button className="btn"> view purchases </button>
          </Link>
          <Logout />
        </>
      )}
    </div>
  );
}
