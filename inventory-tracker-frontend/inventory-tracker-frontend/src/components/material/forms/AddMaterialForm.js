import { addMaterial } from "../../../services/materialAPI";
import { useState } from "react";
import { Link, useHistory } from "react-router-dom";

export default function AddMaterialForm() {
  const [material, setMaterial] = useState({
    materialId: 0,
    materialName: "",
    pricePerUnit: 0.00,
    userId: "",
    inventory: {},
    purchases: [],
    products: [],
  });

  const history = useHistory();

  function handleChange(evt) {
    let nextMaterial = { ...material };
    nextMaterial[evt.target.name] = evt.target.value;
    setMaterial(nextMaterial);
    console.log(nextMaterial);
  }

  async function handleSubmit(evt) {
    let nextMaterial = { ...material };
    setMaterial(nextMaterial);

    evt.preventDefault();
    evt.stopPropagation();
    await addMaterial(nextMaterial);
    history.push("/materials");
  }

  return (
    <div className="container">
      <h2> Add New Material </h2>
      <div class="row">
        <form class="col s12" id="addMaterialForm" onSubmit={handleSubmit}>
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
              <label htmlFor="material_name">Material Name</label>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input
                class="decimal"
                name="pricePerUnit"
                type="text"
                id="price_per_unit"
                class="materialize-textarea"></input>
              <label for="price_per_unit">Price Per Unit</label>
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
