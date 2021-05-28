import { updateMaterial, findById } from "../../../services/materialAPI";
import { useState, useEffect, useContext } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import MessageContext from "../../../context/MessageContext";

export default function UpdateMaterialForm() {
  const { setMessages } = useContext(MessageContext);
  const { materialId } = useParams();

  const [material, setMaterial] = useState({
    materialId: 0,
    materialName: "",
    pricePerUnit: 0.0,
    userId: "username",
  });

  const history = useHistory();

  //GET current material to update
  useEffect(() => {
    findById(materialId).then((data) => {
      setMaterial(data);
    });
  }, [materialId]);

  function handleChange(evt) {
    let nextMaterial = { ...material };
    nextMaterial[evt.target.name] = evt.target.value;
    setMaterial(nextMaterial);
    console.log(nextMaterial);
  }

  async function handleSubmit(evt) {
    let nextMaterial = { ...material };
    nextMaterial.pricePerUnit = parseFloat(material.pricePerUnit).toFixed(2);
    setMaterial(nextMaterial);

    evt.preventDefault();
    evt.stopPropagation();
    const response = await updateMaterial(nextMaterial);
    if (response.ok) { 
      setMessages([`Your ${nextMaterial.materialName} was successfully updated.`]);
    } else {
      response.json().then(json => {
          if (Array.isArray(json)) {
              setMessages(json);
          } else {
              setMessages([json.message])
          }
      });
    }
    history.push(`/materials/${materialId}`);
  }

  return (
    <div className="container">
      <div className="row">
        <div className="col s12">
          <div className="card light-blue lighten-4">
            <div className="card-content black-text">
              <span className="card-title ">Update Material {materialId}</span>
            </div>
          </div>
        </div>
      </div>

      <div className="row">
        <form
          className="col s12"
          id="updateMaterialForm"
          onSubmit={handleSubmit}>
          <div className="row">
            <label htmlFor="material_name">Material Name</label>
            <div className="input-field col s12">
              <input
                type="text"
                data-length="50"
                id="material_name"
                name="materialName"
                value={material.materialName}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="row">
            <label htmlFor="price_per_unit">
              Price Per Unit{" (Optional)"}
            </label>
            <div className="input-field col s12">
              <input
                className="decimal"
                min="0.00"
                step="0.01"
                value={material.pricePerUnit}
                presicion={2}
                name="pricePerUnit"
                type="number"
                id="price_per_unit"
                onChange={handleChange}
              />
            </div>
            <div className="row">
              <div className="col">
                <button
                  className="btn waves-effect waves-light"
                  type="submit"
                  name="action">
                  Submit
                </button>
              </div>
              <div className="col">
                <Link to={"/materials/" + materialId}>
                  <button className="btn waves-effect waves-light red lighten-1 ">
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
