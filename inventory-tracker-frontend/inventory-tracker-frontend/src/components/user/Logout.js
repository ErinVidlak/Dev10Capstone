import { useContext } from "react";
import AuthContext from "../../context/AuthContext";
import { useHistory } from "react-router-dom";

export default function Logout() {
  const auth = useContext(AuthContext);
  const history = useHistory();

  async function handleClick(evt) {
    evt.preventDefault();
    evt.stopPropagation();

    await auth.logout();
    //  history.push("/login");   set once views are login dependent
    history.push("/");
  }

  return (
    <>
      <button className="btn-small red accent-2" onClick={handleClick}>
        Logout
      </button>
    </>
  );
}
