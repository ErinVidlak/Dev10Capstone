import { useState, useEffect } from 'react';

function MaterialPurchase({ materialPurchase }) {
    const [material, setMaterial] = useState({materialName: ""});

    useEffect(() => {
         
        fetch(`http://localhost:8080/api/material/${materialPurchase.materialId}`)  
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
            <td>{materialPurchase.datePurchased}</td>
            <td>{material.materialName}</td>
            <td>{materialPurchase.quantityPurchased}</td>
            <td>{materialPurchase.units}</td>
            <td>{materialPurchase.purchasePrice}</td>
            <td>{materialPurchase.description}</td>
        </tr>
    );

} 

export default MaterialPurchase;