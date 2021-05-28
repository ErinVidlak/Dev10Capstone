import { useState, useEffect, useContext } from 'react'; 
import { useHistory } from 'react-router';
import { findAll } from '../../services/materialAPI';
import MessageContext from '../../context/MessageContext';
import "materialize-css";
import { Select } from "react-materialize";
import {capitalizeEach } from "../../utils/helpers"

function AddMaterialPurchase() {
    const history = useHistory();
    const [materialPurchase, setMaterialPurchase] = useState({});
    const [materials, setMaterials] = useState([]);
    const { setMessages } = useContext(MessageContext);

    useEffect(() => {
        findAll().then((result) => {setMaterials(result)});
    }, []);

    const submit = (evt) => {
        evt.preventDefault()  
        fetch("http://localhost:8080/api/materialPurchase", {
            method: "POST", 
            headers: { "Content-Type": "application/json"}, 
            body: JSON.stringify(materialPurchase),
        }) 
            .then((response) => {
                if (response.ok) { 
                    setMessages([`Your ${materialPurchase.materialName} purchase was successfully added.`]);
                    history.push("/purchases");
                } else {
                    response.json().then(json => {
                        if (Array.isArray(json)) {
                            setMessages(json);
                        } else {
                            setMessages([json.message])
                        }
                    });
                }
            })  
    } 

    const cancel = () => {
        history.push("/purchases");
    }

    const onSelectChange = (event) => {
        const name = materials.find((material) => material.materialId == +event.target.value).materialName;
        setMaterialPurchase({
            ...materialPurchase,
            materialName: name,
            materialId: +event.target.value,
        })
    }

    return (
      <div className="container">
        <h4>Add a Purchase</h4>
        <form onSubmit={submit}>
          <div>
            <label htmlFor="datePurchased">Purchase Date</label>
            <input
              type="date"
              id="datePurchased"
              name="materialPurchase[datePurchased]"
              onChange={(evt) =>
                setMaterialPurchase({
                  ...materialPurchase,
                  datePurchased: evt.target.value,
                })
              }
            />
          </div>

         
          <div className="input-field col s12">
            <Select
              id="selectMaterial"
              multiple={false}
              onChange={onSelectChange}
              className="black-text"
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
                Choose Material Purchased
              </option>
              {materials.map((material) => (
                <option key={material.materialId} value={material.materialId}>
                  {capitalizeEach(material.materialName)}
                </option>
              ))}
            </Select>
          </div>

        

          <div>
            <label htmlFor="purchasePrice">Cost</label>
            <input
              type="number"
              step="0.01"
              id="purchasePrice"
              name="materialPurchase[purchasePrice]"
              onChange={(evt) =>
                setMaterialPurchase({
                  ...materialPurchase,
                  purchasePrice: evt.target.value,
                })
              }
            />
          </div>
          <div>
            <label htmlFor="quantityPurchased">Quantity</label>
            <input
              type="number"
              id="quantityPurchased"
              name="materialPurchase[quantityPurchased]"
              value={materialPurchase.quantityPurchased}
              onChange={(evt) =>
                setMaterialPurchase({
                  ...materialPurchase,
                  quantityPurchased: evt.target.value,
                })
              }
            />
          </div>
          <div>
            <label htmlFor="units">Units</label>
            <input
              type="text"
              id="units"
              name="materialPurchase[units]"
              value={materialPurchase.units || ""}
              onChange={(evt) =>
                setMaterialPurchase({
                  ...materialPurchase,
                  units: evt.target.value,
                })
              }
            />
          </div>
          <div>
            <label htmlFor="description">Description</label>
            <input
              type="text"
              id="description"
              name="materialPurchase[description]"
              value={materialPurchase.description}
              onChange={(evt) =>
                setMaterialPurchase({
                  ...materialPurchase,
                  description: evt.target.value,
                })
              }
            />
          </div>
          <div>
            <button
              className="btn waves-effect waves-light btn-flat deep-purple lighten-3"
              type="submit">
              Add Purchase
            </button>{" "}
            <button
              className="btn waves-effect waves-light btn-flat deep-purple lighten-3"
              type="button"
              onClick={cancel}>
              Cancel
            </button>
          </div>
        </form>
      </div>
    );
} 

export default AddMaterialPurchase;

//  <Select
//           id="selectMaterial"
//           multiple={false}
//           onChange={onSelectChange}
//           options={{
//             classes: "",
//             dropdownOptions: {
//               alignment: "left",
//               autoTrigger: true,
//               closeOnClick: true,
//               constrainWidth: true,
//               coverTrigger: true,
//               hover: false,
//               inDuration: 150,
//               onCloseEnd: null,
//               onCloseStart: null,
//               onOpenEnd: null,
//               onOpenStart: null,
//               outDuration: 250,
//             },
//           }}
//           value="">
//           <option disabled value="">
//             Choose Material Purchased
//           </option>
//           {materials.map((material) => (
//             <option key={material.materialId} value={material.materialId}>
//               {capitalizeEach(material.materialName)}
//             </option>
//           ))}
//         </Select>


//   <label>Material Name</label>
//           <div className="input-field col s12">
//             <select
//               className="browser-default"
//               onChange={onSelectChange}
//               required>
//               {materials.map((material) => (
//                 <option key={material.materialId} value={material.materialId}>
//                   {material.materialName}
//                 </option>
//               ))}
//             </select>
//           </div>
