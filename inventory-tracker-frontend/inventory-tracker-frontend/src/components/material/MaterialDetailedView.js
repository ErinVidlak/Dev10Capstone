import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { findById } from "../../services/materialAPI";
import { capitalizeEach, formatPricePerUnit } from "../../utils/helpers";
import DeleteMaterialForm from "./forms/DeleteMaterialForm";
import MaterialInventory from "./MaterialInventory";
import MaterialProductListView from "./MaterialProductListView";
import MaterialWithPurchaseListView from "./MaterialWithPurchaseListView";

export default function MaterialDetailedView() {
  const { materialId } = useParams();

  const [material, setMaterial] = useState({
    materialId: 0,
    materialName: "",
    pricePerUnit: 0.0,
    userId: "",
    inventory: {},
    purchases: [],
    products: [],
  });

  //GET material
  useEffect(() => {
    findById(materialId).then((data) => {
      setMaterial(data);
    });
  }, [materialId]);

  return (
    <div className="container">
      <div className="row center">
        <div className="col s12">
          <div className="card light-blue lighten-4">
            <div className="card-content black-text">
              <span className="card-title center">
                {capitalizeEach(material.materialName)}
              </span>
            </div>
          </div>
        </div>

        <div className="col s6 ">
          <div className="card indigo lighten-3">
            <div className="card-content black-text">
              <span className="card-title">
                {" "}
                ${formatPricePerUnit(material.pricePerUnit)} /unit
              </span>
              <p></p>
            </div>
          </div>
        </div>

        <div className="col s6">
          <MaterialInventory inventory={material.inventory} />
        </div>
      </div>
      <div className="row center">
        <MaterialWithPurchaseListView purchases={material.purchases} />
      </div>
      <div className="row center">
        <MaterialProductListView products={material.products} />
      </div>
      <div className="row">
        <div className="col 1">
          <Link to="/materials">
            <button className=" waves-effect waves-light btn ">Back </button>
          </Link>
        </div>
        <div className="col 1">
          <Link to={"/materials/edit/" + materialId}>
            <button className="waves-effect waves-light btn  blue darken-3">
              Update Material
            </button>
          </Link>
        </div>
        <div className="col 1">
          <DeleteMaterialForm initialMaterial={material} />
        </div>
      </div>
    </div>
  );
}
//   "/materials/edit/:materialId"
