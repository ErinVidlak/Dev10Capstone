import { useState, useEffect, useContext } from "react";
import { Link } from "react-router-dom";
import { findAll } from "../../services/materialAPI";
import AuthContext from "../../context/AuthContext";
import MaterialSummary from "./MaterialSummary";

export default function MaterialListView() {
  const [materialList, setMaterialList] = useState([]);
  //const auth = useContext(AuthContext);

  useEffect(() => {
    findAll().then((data) => {
      setMaterialList(data);
    });
  }, []);

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
          <button className="waves-effect waves-light btn green accent-1 black-text">
            Add Material
          </button>
        </Link>
      </div>
    </div>
  );
}
