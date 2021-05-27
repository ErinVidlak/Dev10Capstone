import { findAll } from "../../services/userAPI";
import { useState, useEffect, useContext } from "react";
import { Link, useHistory, useParams, Redirect } from "react-router-dom";
import AuthContext from "../../context/AuthContext";


export default function AdminUserView() {
	const [userList, setUserList] = useState([]);
	  const auth = useContext(AuthContext);
    const currentUser = JSON.parse(localStorage.getItem("user"));


	useEffect(() => {
    findAll().then((data) => {
      setUserList(data);
    });
  }, []);

  return (
    <>
      {auth.user.roles.includes("ADMIN") && (
        <div className="container">
          <div className="row center">
            <div className="card purple lighten-4">
              <div className="card-content black-text">
                <span className="card-title center">
                  Registered Application Users
                </span>
              </div>
            </div>
          </div>

          <div className="row">
            <table className="striped centered">
              <thead className="deep-purple lighten-3">
                <tr>
                  <th>Username</th>
                </tr>
              </thead>

              <tbody className="deep-purple lighten-4">
                {userList.map((user) => (
                  <tr>
                    <td>{user.userId}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
	    {!(auth.user.roles.includes("ADMIN")) && (<Redirect to="/" />)}
     
    </>
  );


}
