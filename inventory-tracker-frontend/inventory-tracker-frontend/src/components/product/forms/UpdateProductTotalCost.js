import { useState, useEffect, useContext } from "react";
import { useHistory, useParams } from "react-router";
import { findAll, updateMaterial } from "../../../services/materialAPI";
import { findAll as findAllPurchases } from "../../../services/materialPurchaseAPI";
import { findById, updateProduct } from "../../../services/productAPI";
import { findByProductId } from "../../../services/productMaterialAPI";

export default function UpdateProductTotalCost() {
  const [materialList, setMaterialList] = useState([]);
  const [purchaseList, setPurchaseList] = useState([]);
  const [materialProductList, setMaterialProductList] = useState([]);
  const { productId } = useParams();
  const history = useHistory();

  const [product, setProduct] = useState({});

  useEffect(() => {
    findAll().then((data) => {
      setMaterialList(data);
    });
  }, []);

  useEffect(() => {
    findAllPurchases().then((data) => {
      setPurchaseList(data);
    });
  }, []);

  useEffect(() => {
    findById(productId).then((data) => {
      setProduct(data);
    });
  }, []);

  useEffect(() => {
    findByProductId(productId).then((data) => {
      setMaterialProductList(data);
    });
  }, []);

  async function recalculateTotalCost(evt) {
    let totalCostOfMaterials = 0.0;

    let currentMaterial = null;
    materialList.forEach((m) => {
      currentMaterial = { ...m };
      let pricePer = 0.0;
      purchaseList.forEach((p) => {
        if (p.materialId == currentMaterial.materialId) {
          pricePer += p.purchasePrice / parseFloat(p.quantityPurchased);
        }
      });
      m.pricePerUnit = pricePer;

      updateMaterial(m);
    });

    materialProductList.forEach((pm) => {
      console.log(pm);

      totalCostOfMaterials +=
        parseFloat(pm.material.pricePerUnit).toFixed(2) *
        parseFloat(pm.materialQuantity).toFixed(2);
    });

    let nextProduct = { ...product };
    nextProduct.totalMaterialsCost =
      parseFloat(totalCostOfMaterials).toFixed(2);
    console.log(nextProduct);

	window.setTimeout(function () {
    window.location.reload();
  }, 1000);

    updateProduct(nextProduct);
	window.setTimeout(function () {
    window.location.reload();
  }, 2000);

    history.push("/products/" + productId);
	
    console.log(totalCostOfMaterials);
  }

  return (
    <>
      <button
        className="btn  green lighten-1 black-text"
        onClick={recalculateTotalCost}
        type="submit">
        Recalculate Total Cost
      </button>
    </>
  );
}
