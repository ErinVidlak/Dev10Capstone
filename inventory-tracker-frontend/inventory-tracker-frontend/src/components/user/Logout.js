import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import {
  Link,
  Router,
  Route,
  useLocation,
  useParams,
  useHistory,
} from "react-router-dom";

export default function Logout() {
  const auth = useContext(AuthContext);
  const history = useHistory();

  async function handleClick(evt) {
    evt.preventDefault();
    evt.stopPropagation();

    await auth.logout();
    history.push("/login");
  }

  return (
    <div>
      <button className="btn" onClick={handleClick}>
        Logout
      </button>
    </div>
  );
}
