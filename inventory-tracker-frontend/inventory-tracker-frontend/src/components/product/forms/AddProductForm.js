import { useState, useEffect, useContext } from "react";
import { useHistory, Link } from "react-router";
import { findAll, addProduct } from "../../../services/productAPI";
import { addProductMaterial } from "../../../services/productMaterialAPI";
import {
  findAll as findAllMaterials,
  findById as findByMaterialId,
} from "../../../services/materialAPI";
import { capitalizeEach } from "../../../utils/helpers";
import "materialize-css";
import { Select } from "react-materialize";
import AuthContext from "../../../context/AuthContext";
import MessageContext from "../../../context/MessageContext";

export default function AddProductForm() {
  const history = useHistory();
  const { setMessages } = useContext(MessageContext);
  const auth = useContext(AuthContext);
  const [materialList, setMaterialList] = useState([]);
  const [updatedProductList, setUpdatedProductList] = useState([]);

  useEffect(() => {
    findAllMaterials().then((result) => {
      setMaterialList(result);
    });
  }, []);

  useEffect(() => {
    findAll().then((result) => {
      setUpdatedProductList(result);
    });
  }, []);

  const [product, setProduct] = useState({
    productId: 0,
    productName: "",
    totalMaterialCost: 0.0,
    timeToMake: 0,
    userId: "username",
  });

  const [newProduct, setNewProduct] = useState({});
  const [currentMaterial, setCurrentMaterial] = useState({});

  const [productMaterial, setProductMaterial] = useState({
    productId: 0,
    materialQuantity: 1,
    materialId: 0,
  });

  const cancel = () => {
    history.push("/products");
  };

  let totalCost = parseFloat(0.0).toFixed(2);

  //from AddMaterialPurchase
  //   const onSelectChange = (event) => {
  //     const selectedMaterial = materialList.find(
  //       (material) => material.materialId == +event.target.value
  //     );
  //     setCurrentMaterial(selectedMaterial);

  //     setProductMaterial({
  //       ...productMaterial,
  //       materialId: +event.target.value,
  //     });

  //     let nextProduct = { ...product };
  //     nextProduct.totalMaterialCost =
  //       parseFloat(selectedMaterial.pricePerUnit).toFixed(2) *
  //       parseFloat(productMaterial.materialQuantity).toFixed(2);

  //     setProduct(nextProduct);
  //     totalCost = nextProduct.totalMaterialsCost;
  //   };

  //   function handleMaterialChange(evt) {
  //     let nextproductMaterial = { ...productMaterial };
  //     nextproductMaterial[evt.target.name] = evt.target.value;
  //     setProductMaterial(nextproductMaterial);

  //     let nextProduct = { ...product };
  //     nextProduct.totalMaterialCost =
  //       parseFloat(currentMaterial.pricePerUnit).toFixed(2) *
  //       parseFloat(nextproductMaterial.materialQuantity).toFixed(2);
  //     setProduct(nextProduct);
  //     totalCost = nextProduct.totalMaterialsCost;
  //     console.log(nextproductMaterial);
  //   }

  function handleChange(evt) {
    let nextProduct = { ...product };
    nextProduct[evt.target.name] = evt.target.value;
    setProduct(nextProduct);
    console.log(nextProduct);
  }

  async function handleSubmit(evt) {
    let nextProduct = { ...product };
    nextProduct[evt.target.name] = evt.target.value;
    nextProduct.totalMaterialCost = parseFloat(
      nextProduct.totalMaterialCost
    ).toFixed(2);

    setProduct(nextProduct);
    console.log(nextProduct);

    evt.preventDefault();
    evt.stopPropagation();

    const response = await addProduct(nextProduct);
    if (response.ok) {
      setMessages([`Your ${nextProduct.productName} product was successfully updated.`]);
      history.push("/products");
    } else {
      response.json().then(json => {
          if (Array.isArray(json)) {
              setMessages(json);
          } else {
              setMessages([json.message])
          }
      });
    } 
   

    // try {
    //   await addProduct(product);
    //   let nextproductMaterial = { ...productMaterial };
    //   let newProductId = 0;
    //   let isMatched = false;
    //   await findAll()
    //     .then((result) => {
    //       setUpdatedProductList(result);
    //       result.forEach((p) => {
    //         if (
    //           p.productName === nextProduct.productName &&
    //           p.timeToMake == nextProduct.timeToMake &&
    //           p.totalMaterialCost == nextProduct.totalMaterialCost
    //         ) {
    //           //match
    //           isMatched = true;
    //           newProductId = p.productId;
    //           nextproductMaterial.productId = newProductId;
    //           setProductMaterial(nextproductMaterial);
    //           console.log("match found in updated product list");
    //         }
    //       });
    //     })
    //     .then(addProductMaterial(productMaterial))
    //     .then(history.push("/products/" + newProductId));
    // } catch (err) {
    //   console.log(err);
    // }
    // try {
    // //   let nextproductMaterial = { ...productMaterial };
    // //   let newProductId = 0;
    // //   let isMatched = false;

    // //   updatedProductList.forEach((p) => {
    // //     if (
    // //       p.productName == nextProduct.productName &&
    // //       p.timeToMake == nextProduct.timeToMake &&
    // //       p.totalMaterialCost == nextProduct.totalMaterialCost
    // //     ) {
    // //       //match
    // //       isMatched = true;
    // //       newProductId = p.productId;
    // //       nextproductMaterial.productId = newProductId;
    // //       setProductMaterial(nextproductMaterial);
    // //       console.log("match found in updated product list");
    // //     }
    // //   });

    //   await addProductMaterial(nextproductMaterial);
    //   history.push("/products/" + newProductId);
    // } catch (err) {
    //   console.log(err);
    // }
  };

  //name, cost, time to make,
  return (
    <div className="container">
      <h4>Add a Product</h4>
      <form onSubmit={handleSubmit}>
        <div className="input-field col s12">
          <label htmlFor="productName">Product Name</label>
          <input
            type="text"
            id="productName"
            name="productName"
            defaultValue={product.name}
            onChange={handleChange}
          />
        </div>
        <label htmlFor="totalMaterialCost">Estimated Total Material Cost</label>
        <div class="input-field col s12">
          <input
            class="decimal"
            min="0.00"
            step="0.01"
            defaultValue={product.totalMaterialCost}
            presicion={2}
            name="totalMaterialCost"
            type="number"
            id="totalMaterialCost"
            onChange={handleChange}
          />
        </div>

        <label htmlFor="timeToMake">
          {"Total Time Taken to Make Product (hrs)"}
        </label>
        <div class="input-field col s12">
          <input
            type="number"
            min="0"
            id="timeToMake"
            name="timeToMake"
            defaultValue={product.timeToMake}
            onChange={handleChange}
          />
        </div>

        <div>
          <button
            className="btn waves-effect waves-light btn-flat deep-purple lighten-3"
            type="submit">
            Add Product
          </button>
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
//             Material Used {" (You will be able to add more later) "}
//           </option>
//           {materialList.map((material) => (
//             <option key={material.materialId} value={material.materialId}>
//               {capitalizeEach(material.materialName)}
//             </option>
//           ))}
//         </Select>

//         <div>
//           <label htmlFor="materialQuantity">
//             Quantity of Material Used In Product
//           </label>
//           <input
//             type="number"
//             min="1"
//             id="materialQuantity"
//             name="materialQuantity"
//             value={productMaterial.materialQuantity}
//             onChange={handleMaterialChange}
//           />
//         </div>
