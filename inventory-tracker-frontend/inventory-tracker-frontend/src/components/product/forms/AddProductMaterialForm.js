import { addProductMaterial } from "../../../services/productMaterialAPI";
import { useState, useContext, useEffect } from "react";
import { useParams } from "react-router";
import { Link, useHistory } from "react-router-dom";
import { findAll } from "../../../services/materialAPI";
import "materialize-css";
import { Select } from "react-materialize";
import { capitalizeEach } from "../../../utils/helpers";

export default function AddProductMaterialForm({ setShowAddMaterial }) {
  const { productId } = useParams();

  const [materials, setMaterials] = useState([]);

  useEffect(() => {
    findAll().then((result) => {
      setMaterials(result);
    });
  }, []);

  const cancel = () => {
    setShowAddMaterial(false);
  };

  const onSelectChange = (event) => {
    const mat = materials.find((m) => m.materialId == +event.target.value);
    setProductMaterial({
      ...productMaterial,
      material: mat,
    });
  };

  const [productMaterial, setProductMaterial] = useState({
    productId: productId,
    materialQuantity: 0,
    material: materials[0],
  });

  console.log(productMaterial);

  const history = useHistory();

  function handleChange(evt) {
    let nextProductMaterial = { ...productMaterial };
    nextProductMaterial[evt.target.name] = evt.target.value;

    setProductMaterial(nextProductMaterial);
  }

  async function handleSubmit(evt) {
    let nextProductMaterial = { ...productMaterial };
    nextProductMaterial.materialQuantity = parseInt(
      productMaterial.materialQuantity
    );
    setProductMaterial(nextProductMaterial);

    // evt.preventDefault();
    // evt.stopPropagation();
    await addProductMaterial(nextProductMaterial);
    history.push("/products/" + productId);
  }

  return (
    <div className="card left col s5">
      <div className="card-content">
        <form id="addProductMaterialForm" onSubmit={handleSubmit}>
          <div class="row">
            <div className="col s12">
              <div className="input-field ">
                <Select
                  id="selectMaterial"
                  multiple={false}
                  onChange={onSelectChange}
                  options={{
                    classes: "",
                    dropdownOptions: {
                      alignment: "left",
                      autoTrigger: true,
                      closeOnClick: true,
                      constrainWidth: true,
                      coverTrigger: true,
                      hover: false,
                      inDuration: 150,
                      onCloseEnd: null,
                      onCloseStart: null,
                      onOpenEnd: null,
                      onOpenStart: null,
                      outDuration: 250,
                    },
                  }}
                  value="">
                  <option disabled value="">
                    Choose a material
                  </option>
                  {materials.map((material) => (
                    <option
                      key={material.materialId}
                      value={material.materialId}
                      className="text-black">
                      {capitalizeEach(material.materialName)}
                    </option>
                  ))}
                </Select>
              </div>
            </div>
          </div>

          <div className="row">
            <div class="input-field col s5">
              <input
                name="materialQuantity"
                type="number"
                id="material_quantity"
                onChange={handleChange}
                DefaultValue="0"
              />
              <label for="material_quantity">Material Quantity </label>
            </div>
          </div>

          <button
            class="btn waves-effect waves-light"
            type="submit"
            name="action">
            Submit
          </button>

          <button
            class="btn waves-effect waves-light red lighten-1 "
            onClick={cancel}>
            Cancel
          </button>
        </form>
      </div>
    </div>
  );
}
