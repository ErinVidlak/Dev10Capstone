import { useState, useEffect } from 'react';

function MaterialPurchaseList() {

return (
<>
<h3>View All Raw Material Purchases</h3>
<table className="striped">
    <thead>
        <tr>
            <th>Purchase Date</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Units</th>
            <th>Cost</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        {}
    </tbody>
</table>
</>
);

} 

export default MaterialPurchaseList;