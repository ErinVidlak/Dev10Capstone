import { useState, useContext } from "react";
import { Link, useHistory, useLocation } from "react-router-dom";
import AuthContext from "../../context/AuthContext";

import Errors from "../Errors";

function Login() {
  const auth = useContext(AuthContext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState("");

  const history = useHistory();
  const location = useLocation();

  const { state: { from } = { from: "/" } } = location;

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await auth.authenticate(username, password);
      history.push(from);
    } catch (err) {
      console.log(err);
      setErrors([err.message]);
    }
  };

  return (
    <div className="container">
      <h2>Login</h2>
      <Errors errors={errors} />
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            onChange={(event) => setUsername(event.target.value)}
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="text"
            onChange={(event) => setPassword(event.target.value)}
          />
        </div>
        <div>
          <button type="submit" className="btn waves-effect waves-light">
            Login
          </button>
          <Link className="btn waves-effect waves-light" to="/register">
            I don't have an account
          </Link>
        </div>
      </form>
    </div>
  );
}

export default Login;
