import { useState, useContext } from "react";
import { Link, useHistory, useLocation } from "react-router-dom";
import AuthContext from "../context/AuthContext";

export default function Home() {
	  const auth = useContext(AuthContext);

  return (
    <div className="container">
      <h2>Welcome Home</h2>
      <Link to="/materials">
        <button> view materials </button>
      </Link>
      <Link to="/purchases">
        <button> view purchases </button>
      </Link>
      <Link to="/login">
        <button> login </button>
      </Link>
      <Link to="/register">
        <button> register </button>
      </Link>
    </div>
  );
}
