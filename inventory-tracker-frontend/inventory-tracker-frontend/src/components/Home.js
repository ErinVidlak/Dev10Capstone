import { useState, useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../context/AuthContext";
import Logout from "./user/Logout";

export default function Home() {
  const auth = useContext(AuthContext);

  return (
    <div className="container center">
      {!auth.user && (
        <>
          <br />
          <div class="divider"></div>
          <div class="section">
            <h2>Welcome Guest</h2>
          </div>
          <div class="divider"></div>
          <div class="section">
            <Link to="/login">
              <button className="btn"> login </button>
            </Link>
            <Link to="/register">
              <button className="btn"> register </button>
            </Link>
          </div>
          <div class="divider"></div>
        </>
      )}

      {auth.user && (
        <>
          <br />
          <div class="divider"></div>
          <div class="section">
            <h2>Welcome Home {auth.user.username}</h2>
          </div>
          <div class="divider"></div>

          <div class="section">
            <Link to="/materials">
              <button className="btn"> view materials </button>
            </Link>
            <Link to="/purchases">
              <button className="btn"> view purchases </button>
            </Link>
            <Link to="/products">
              <button className="btn"> view products </button>
            </Link>
          </div>
          <div class="divider"></div>
        </>
      )}
    </div>
  );
}
