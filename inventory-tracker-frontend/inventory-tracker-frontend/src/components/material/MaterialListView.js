import { useState, useEffect, useContext } from "react";
import { Link } from "react-router-dom";
import { findAll } from "../../services/materialAPI";
import AuthContext from "../../context/AuthContext";
import MaterialSummary from "./MaterialSummary";
import Messages from "../Messages";
import MessageContext from "../../context/MessageContext";

export default function MaterialListView() {
  const [materialList, setMaterialList] = useState([]);
  const auth = useContext(AuthContext);
  const {messages} = useContext(MessageContext);

  useEffect(() => {
    findAll().then((data) => {
      setMaterialList(data);
    });
  }, []);

  //testing new find all materials for a given user
  // useEffect(() => {
  //   console.log(auth.user.username);
  //   findAllUserMaterials(auth.user.username).then((data) => {
  //     setMaterialList(data);
  //   });
  // }, []);

  return (
    <div className="container">
      <div className="row center">
        <div className="card purple lighten-4">
          <div className="card-content black-text">
            <span className="card-title center">Materials</span>
          </div>
        </div>
      </div>

      <div className="row">
        <table className="striped centered">
          <thead className="deep-purple lighten-3">
            <tr>
              <th>Material ID</th>
              <th>Material</th>
              <th>Price Per Unit</th>
              <th>Details</th>
            </tr>
          </thead>

          <tbody className="deep-purple lighten-4">
            {materialList.map((material) => (
              <MaterialSummary
                key={material.materialId}
                materialId={material.materialId}
                materialName={material.materialName}
                pricePerUnit={material.pricePerUnit}
              />
            ))}
          </tbody>
        </table>
      </div>
      <div className="row">
        <Link to="/materials/add">
          <button className="btn waves-effect waves-light btn teal accent-1 black-text">
            Add New Material
          </button>
        </Link>
      </div>
      {messages.length > 0 && <Messages messages={messages}/>}
    </div>
  );
}
