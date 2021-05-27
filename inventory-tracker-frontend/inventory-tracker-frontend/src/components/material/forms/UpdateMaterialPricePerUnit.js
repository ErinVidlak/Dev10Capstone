import { useState, useEffect, useContext } from "react";
import { useHistory } from "react-router";
import { findAll, updateMaterial } from "../../../services/materialAPI";
import { findAll as findAllPurchases } from "../../../services/materialPurchaseAPI";

export default function UpdateMaterialPricePerUnit() {
  const [materialList, setMaterialList] = useState([]);
  const [purchaseList, setPurchaseList] = useState([]);
  const [material, setMaterial] = useState({});

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

  async function recalculatePPU(evt) {
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
      setMaterial(m);

      //   evt.preventDefault();
      //   evt.stopPropagation();
      window.setTimeout(function () {
        window.location.reload();
      }, 1000);
      updateMaterial(m);
      window.setTimeout(function () {
        window.location.reload();
      }, 2000);
    });
  }

  return (
    <>
      <button
        className="btn  green lighten-1 black-text"
        onClick={recalculatePPU}>
        Calculate Price Per Unit Based On Purchases
      </button>
    </>
  );
}
