 
function MaterialPurchase({ materialPurchase, material }) {

    return (
        <tr>
            <td>
                <input type="checkbox" checked="checked" />
            </td>
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