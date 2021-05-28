import { addProductMaterial } from "../../../services/productMaterialAPI";
import { useState, useContext, useEffect } from "react";
import { useParams } from 'react-router';
import { Link, useHistory } from "react-router-dom";
import { findAll } from '../../../services/materialAPI';
import MessageContext from '../../../context/MessageContext';

export default function AddProductMaterialForm() {  
  const { productId } = useParams();
  const { setMessages } = useContext(MessageContext);
  const [materials, setMaterials] = useState([]);

  useEffect(() => {
    findAll().then((result) => {setMaterials(result)});
  }, []);


const onSelectChange = (event) => {
  const mat = materials.find((m) => m.materialId == +event.target.value);
  setProductMaterial({
      ...productMaterial,
      material: mat
  })
}

  const [productMaterial, setProductMaterial] = useState({
    productId: productId,
    materialQuantity: 0,
    material: materials[0]
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
    nextProductMaterial.materialQuantity = parseInt(productMaterial.materialQuantity);
    setProductMaterial(nextProductMaterial);

    evt.preventDefault();
    evt.stopPropagation();
    const response = await addProductMaterial(nextProductMaterial);
    if (response.ok) { 
      setMessages([`Your material was successfully added.`]);
      history.push(`/products/${productId}`);
    } else {
      response.json().then(json => {
          if (Array.isArray(json)) {
              setMessages(json);
          } else {
              setMessages([json.message])
          }
      });
    }
  }

  const cancel = () => {
    history.push(`/products/${productId}`);
  }

  return (
    <div className="container">
      
      <div class="row">
        <form class="col s12" id="addProductMaterialForm" onSubmit={handleSubmit}>

        <label>Material Name</label>
            <div className="input-field col s12">
                <select className="browser-default" onChange={onSelectChange} required>
                    {materials.map((m) => (
                        <option key={m.materialId} defaultValue={materials[0]} value={m.materialId}>{m.materialName}</option>
                    ))}
                </select>
            </div>

          <div class="row">
            <div class="input-field col s12">
              <input
                name="materialQuantity"
                type="number"
                id="material_quantity"
                onChange={handleChange}
                DefaultValue="0"
              />
              <label for="material_quantity">Material Quantity </label>
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
                  <button class="btn waves-effect waves-light red lighten-1" onClick={cancel}>
                    Cancel
                  </button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}