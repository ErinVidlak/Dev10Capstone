import { Link } from "react-router-dom";
import { useState, useEffect } from 'react';
import dateFormat from 'dateformat';

export default function MaterialPurchaseSummary({
    materialId,
    datePurchased,
    purchasePrice,
    materialPurchaseId,
}) {
    const [material, setMaterial] = useState({materialName: ""});

    useEffect(() => {
         
        fetch(`http://localhost:8080/api/material/${materialId}`)  
            .then(response => {
                if (response.status !== 200) {
                    return Promise.reject("Material fetch failed")
                } 
                return response.json();
            }) 
            .then(json => setMaterial(json));

    }, []);
    
    return (
        <tr>
            <td>{dateFormat(new Date(datePurchased), "paddedShortDate")}</td>
            <td>{material.materialName}</td>
            <td>${purchasePrice}</td>
            <td>
                <Link to={`/purchases/${materialPurchaseId}`}>
                <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3">
                    View
                </button>
                </Link>
            </td>
        </tr>
    );
}