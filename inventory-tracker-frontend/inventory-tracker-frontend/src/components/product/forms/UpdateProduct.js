import { updateMaterial, findById } from "../../../services/materialAPI";
import { useState, useEffect } from "react";
import { Link, useHistory, useParams } from "react-router-dom";

export default function UpdateProduct() {
    const { productId } = useParams();

    const [product, setProduct] = useState({
        productName: "",
        totalMaterialsCost: 0.0,
        timeToMake: 0,
        materials: [],
        userId: "", 
    });

    const history = useHistory();
}