import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import { useState } from "react";
import AuthContext from "../context/AuthContext";
import jwt_decode from "jwt-decode";
import Login from "./user/Login";
import NotFound from "./NotFound";
import Register from "./user/Register";
import MaterialPurchaseListView from "./materialPurchase/MaterialPurchaseListView";
import MaterialPurchaseDetailedView from "./materialPurchase/MaterialPurchaseDetailedView";
import MessageContext from "../context/MessageContext";
import AddMaterialPurchase from "../components/materialPurchase/AddMaterialPurchase";
import MaterialListView from "./material/MaterialListView";
import MaterialDetailedView from "./material/MaterialDetailedView";
import AddMaterialForm from "./material/forms/AddMaterialForm";
import UpdateMaterialForm from "./material/forms/UpdateMaterialForm";
import { addAppUser, findAll, findById } from "../services/userAPI";
import Home from "./Home";
import AppHeader from "./AppHeader";
import ProductDetailedView from "./product/ProductDetailedView";
import ProductListView from "./product/ProductListView";
import ListedProductDetailedView from "./product/ListedProductDetailedView";
import AdminUserView from "./user/AdminUserView";
import AddProductForm from "./product/forms/AddProductForm";
import Messages from "./Messages";
import AddProductMaterialForm from "./product/forms/AddProductMaterialForm";

function InventoryManager() {
  const storage = localStorage.getItem("user");
  const userFromStorage = JSON.parse(storage);

  const [user, setUser] = useState(userFromStorage);
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
    localStorage.setItem("user", JSON.stringify(user));

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
    localStorage.clear();
  };

  const auth = {
    user,
    authenticate,
    logout,
    //our User class API service call functions
    addAppUser,
    findById,
    findAll,
  };
  // {user ? <Home /> : <Redirect to="/login" />}
  return (
    <AuthContext.Provider value={auth}>
      <MessageContext.Provider value={{ messages, setMessages }}>
        <Router>
          <AppHeader />
          <Switch>
            <Route exact path="/" component={Home} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            {user ? (
              <Route exact path="/users" component={AdminUserView} />
            ) : (
              <Redirect to="/" />
            )}

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
              exact
              path="/purchases/:purchaseId"
              component={MaterialPurchaseDetailedView}
            />
            <Route exact path="/products" component={ProductListView} />
            <Route exact path="/products/add" component={AddProductForm} />

            <Route
              exact
              path="/products/:productId"
              component={ProductDetailedView}
            />

            <Route
              exact
              path="/products/:productId/listing/:listedProductId"
              component={ListedProductDetailedView}
            />
            <Route path="*" component={NotFound} />
          </Switch>
          <div className="container">
            {messages.length > 0 && <Messages messages={messages}/>}
          </div>
        </Router>
      </MessageContext.Provider>
    </AuthContext.Provider>
  );
}

export default InventoryManager;
