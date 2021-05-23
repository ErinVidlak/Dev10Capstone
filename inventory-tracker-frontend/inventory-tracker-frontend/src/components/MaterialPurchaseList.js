import { useState, useEffect } from 'react';
import MaterialPurchase from './MaterialPurchase';

function MaterialPurchaseList() {
    const [materialPurchases, setMaterialPurchases] = useState([]); 


    useEffect(() => {
         
        fetch("http://localhost:8080/api/materialPurchase")  
            .then(response => {
                if (response.status !== 200) {
                    return Promise.reject("Material Purchase fetch failed")
                } 
                return response.json();
            }) 
            .then(json => setMaterialPurchases(json));

    }, []);

    return (
        <>
        <h3>View All Raw Material Purchases</h3>
        <table className="striped">
            <thead>
                <tr>
                    <th></th>
                    <th>Purchase Date</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Units</th>
                    <th>Cost</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                {materialPurchases.map(materialPurchase => 
                    <MaterialPurchase key={materialPurchase.matherialPurchaseId} materialPurchase={materialPurchase} />
                )}
            </tbody>
        </table>
        </>
    );

} 

export default MaterialPurchaseList;