import { addProductMaterial } from "../../../services/productMaterialAPI";
import { useState, useContext } from "react";
import { Link, useHistory } from "react-router-dom";
import AuthContext from "../../../context/AuthContext";

export default function AddProductMaterialForm() {
  const auth = useContext(AuthContext);

  const [productMaterial, setProductMaterial] = useState({
    materialId: 0,
    materialName: "",
    pricePerUnit: 0.0,
    userId: "username",
  });

  const history = useHistory();

  function handleChange(evt) {
    let nextProductMaterial = { ...material };
    nextProductMaterial[evt.target.name] = evt.target.value;

    setProductMaterial(nextProductMaterial);
    console.log(nextProductMaterial);
  }

  async function handleSubmit(evt) {
    let nextProductMaterial = { ...material };
    nextProductMaterial.pricePerUnit = parseFloat(material.pricePerUnit).toFixed(2);
    setProductMaterial(nextProductMaterial);

    evt.preventDefault();
    evt.stopPropagation();
    await addProductMaterial(nextProductMaterial);
    history.push("/materials");
  }

  return (
    <div className="container">
      <div className="row">
        <div class="col s12">
          <div className="card light-blue lighten-4">
            <div className="card-content black-text">
              <span className="card-title ">Add New ProductMaterial</span>
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <form class="col s12" id="addProductMaterialForm" onSubmit={handleSubmit}>
          <div class="row">
            <div class="input-field col s12">
              <input
                type="text"
                data-length="50"
                id="material_name"
                name="materialName"
                onChange={handleChange}
                required
              />
              <label htmlFor="material_name">ProductMaterial Name</label>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input
                class="decimal"
                min="0.00"
                step="0.01"
                Defaultvalue={material.pricePerUnit}
                presicion={2}
                name="pricePerUnit"
                type="number"
                id="price_per_unit"
                onChange={handleChange}
              />
              <label for="price_per_unit">Price Per Unit{" (Optional)"}</label>
            </div>
            <div className="row">
              <div className="col">
                <button
                  class="btn waves-effect waves-light"
                  type="submit"
                  name="action">
                  Submit
                </button>
              </div>
              <div className="col">
                <Link to="/materials">
                  <button class="btn waves-effect waves-light red lighten-1 ">
                    Cancel
                  </button>
                </Link>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}
