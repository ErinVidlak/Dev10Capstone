import { useContext, useState } from "react";
import AuthContext from "../../context/AuthContext";
import { addAppUser } from "../../services/userAPI";
import { Link, useHistory } from "react-router-dom";
import Errors from "../Errors";

function Register() {
  const auth = useContext(AuthContext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState([]);
  const history = useHistory();

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log(username);
    console.log(password);

    try {
      const response = await fetch("http://localhost:5000/create_account", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username,
          password,
        }),
      });

      if (response.status === 201) {
        try {
          await auth.authenticate(username, password);
          let user = { userId: username };
          await addAppUser(user);

          history.push("/");
        } catch (err) {
          throw new Error("Something went wrong here");
        }
      } else if (response.status === 400) {
        throw new Error("The account is already in use");
      }
    } catch (err) {
      setErrors([err.message]);
    }
  };

  return (
    <div className="container">
      <h2>Register</h2>
      <Errors errors={errors} />
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(event) => setUsername(event.target.value)}
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
          />
        </div>
        <div>
          <button type="submit" className="btn">
            Register
          </button>
          <Link className="btn" to="/login">
            I already have an account
          </Link>
        </div>
      </form>
    </div>
  );
}

export default Register;
