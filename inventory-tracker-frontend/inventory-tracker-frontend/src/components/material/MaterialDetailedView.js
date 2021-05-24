import { useState, useEffect } from "react";
import { useParams } from "react-router";
import { findById } from "../../services/materialAPI";

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

   useEffect(() => {
     findById(materialId).then((data) => {
       setMaterial(data);
     });
   }, [materialId]);







}
