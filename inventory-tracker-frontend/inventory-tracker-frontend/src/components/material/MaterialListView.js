import { useState, useEffect, useContext } from "react";
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
    <div className="container px-3 py-3">
      <table>
        <thead>
          <tr>
            <th>Material ID</th>
            <th>Material</th>
            <th>Price Per Unit</th>
            <th>Details</th>
          </tr>
        </thead>

        <tbody>
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
  );
}
