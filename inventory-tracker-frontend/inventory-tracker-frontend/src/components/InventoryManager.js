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
import MaterialPurchaseListView from "./materialPurchase/MaterialPurchaseListView";
import MaterialPurchaseDetailedView from "./materialPurchase/MaterialPurchaseDetailedView";
import MessageContext from "../context/MessageContext";
import AddMaterialPurchase from "../components/materialPurchase/AddMaterialPurchase";

import MaterialListView from "./material/MaterialListView";
import MaterialDetailedView from "./material/MaterialDetailedView";
import AddMaterialForm from "./material/forms/AddMaterialForm";
import UpdateMaterialForm from "./material/forms/UpdateMaterialForm";

function InventoryManager() {
  const [user, setUser] = useState(null);
  const [messages, setMessages] = useState([]);
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
      <MessageContext.Provider value={{ messages, setMessages }}>
        <Router>
          <Switch>
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/materials" component={MaterialListView} />
            <Route exact path="/materials/add" component={AddMaterialForm} />
            <Route
              exact
              path="/materials/edit/:materialId"
              component={UpdateMaterialForm}
            />
            <Route
              exact
              path="/materials/:materialId"
              component={MaterialDetailedView}
            />
            <Route
              exact
              path="/purchases"
              component={MaterialPurchaseListView}
            />
            <Route path="/purchases/add" component={AddMaterialPurchase} />
            <Route
              path="/purchases/:purchaseId"
              component={MaterialPurchaseDetailedView}
            />
            <Route path="/login" component={Login} />
            <Route path="/register" component={Register} />
            <Route path="*" component={NotFound} />
          </Switch>
        </Router>
      </MessageContext.Provider>
    </AuthContext.Provider>
  );
}

export default InventoryManager;
