import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import { useState } from "react";
import AuthContext from "../context/AuthContext";
import jwt_decode from "jwt-decode";
import Login from "./Login";
import NotFound from "./NotFound";
import Register from "./Register";
import MaterialListView from "./material/MaterialListView";
import MaterialDetailedView from "./material/MaterialDetailedView";
import AddMaterialForm from "./material/forms/AddMaterialForm";

function InventoryManager() {
  const [user, setUser] = useState(null);
  // const [messages, setMessages] = useState([]);

  const login = (token) => {
    const { id, sub: username, roles: rolesString } = jwt_decode(token);
    const roles = rolesString.split(",");

    const user = {
      id,
      username,
      roles,
      token,
      hasRole(role) {
        return this.roles.includes(role);
      },
    };

    setUser(user);
  };

  const authenticate = async (username, password) => {
    const response = await fetch("http://localhost:5000/authenticate", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify({
        username,
        password,
      }),
    });

    if (response.status === 200) {
      const { jwt_token } = await response.json();
      login(jwt_token);
    } else if (response.status === 403) {
      throw new Error("Bad username or password");
    } else {
      throw new Error("There was an issue logging in");
    }
  };

  const logout = () => {
    setUser(null);
  };

  const auth = {
    user,
    authenticate,
    logout,
  };

  return (
    <AuthContext.Provider value={auth}>
      <Router>
        <Switch>
          <Route path="/login" component={Login} />
          <Route path="/register" component={Register} />
          <Route exact path="/materials" component={MaterialListView} />
          <Route exact path="/materials/add" component={AddMaterialForm} />
          <Route
            exact path="/materials/:materialId"
            component={MaterialDetailedView}
          />
          <Route path="*" component={NotFound} />
        </Switch>
      </Router>
    </AuthContext.Provider>
  );
}

export default InventoryManager;
