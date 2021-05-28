import { useState, useEffect, useContext } from 'react'; 
import { useHistory, useParams } from 'react-router';
import MessageContext from '../../context/MessageContext';
import { findAll } from '../../services/materialAPI';

function UpdateMaterialPurchase({ materialName, materialPurchase, setShowUpdateForm }) {
    const history = useHistory();
    const { purchaseId } = useParams();
    const [updatedMaterialPurchase, setUpdatedMaterialPurchase] = useState(materialPurchase); 
    const { setMessages } = useContext(MessageContext);
    const [materials, setMaterials] = useState([]);

    useEffect(() => {
        findAll().then((result) => {setMaterials(result)});
    }, []);

    const submit = (evt) => {
        evt.preventDefault()
        fetch(`http://localhost:8080/api/materialPurchase/${purchaseId}`, {
            method: "PUT", 
            headers: { "Content-Type": "application/json"}, 
            body: JSON.stringify(updatedMaterialPurchase),
        }) 
            .then((response) => { 
                if (response.ok) {
                    setMessages([`Your ${materialName} purchase was successfully updated.`]);
                    setShowUpdateForm(false);
                } else {
                    response.json().then(json => {
                        if (Array.isArray(json)) {
                            setMessages(json);
                        } else {
                            setMessages([json.message])
                        }
                    });
                } 
            }) 
    }

    const cancel = () => {
        setShowUpdateForm(false);
    }

    const onSelectChange = (event) => {
        setUpdatedMaterialPurchase({
            ...updatedMaterialPurchase,
            materialId: +event.target.value,
        })
    }

    return (
        <>
        {updatedMaterialPurchase && (
            <>
            <h4>Update a Purchase</h4>
            <form onSubmit={submit}>
                <div>
                <label htmlFor="datePurchased">Purchase Date</label>
                    <input type="date" id="datePurchased" name="updatedMaterialPurchase[datePurchased]" value={updatedMaterialPurchase.datePurchased} onChange={evt => setUpdatedMaterialPurchase({ ... updatedMaterialPurchase, datePurchased: evt.target.value})}/>
                </div>
                <label>Material Name</label>
                <div className="input-field col s12">
                <select className="browser-default" value={updatedMaterialPurchase.materialId} onChange={onSelectChange}>
                    {materials.map((material) => (
                        <option key={material.materialId} value={material.materialId}>{material.materialName}</option>
                    ))}
                </select>
                </div>
                <div>
                <label htmlFor="purchasePrice">Cost</label>    
                    <input type="number" step="0.01" id="purchasePrice" name="updatedMaterialPurchase[purchasePrice]" value={updatedMaterialPurchase.purchasePrice} onChange={evt => setUpdatedMaterialPurchase({ ... updatedMaterialPurchase, purchasePrice: evt.target.value})}/>
                </div>
                <div>
                <label htmlFor="quantityPurchased">Quantity</label>    
                    <input type="number" id="quantityPurchased" name="updatedMaterialPurchase[quantityPurchased]" value={updatedMaterialPurchase.quantityPurchased} onChange={evt => setUpdatedMaterialPurchase({ ... updatedMaterialPurchase, quantityPurchased: evt.target.value})}/>
                </div>
                <div>
                <label htmlFor="units">Units</label>    
                    <input type="text" id="units" name="updatedMaterialPurchase[units]" value={updatedMaterialPurchase.units || ""} onChange={evt => setUpdatedMaterialPurchase({ ... updatedMaterialPurchase, units: evt.target.value})}/>
                </div>
                <div>
                <label htmlFor="description">Description</label>    
                    <input type="text" id="description" name="updatedMaterialPurchase[description]" value={updatedMaterialPurchase.description} onChange={evt => setUpdatedMaterialPurchase({ ... updatedMaterialPurchase, description: evt.target.value})}/>
                </div>
                <div>
                <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" type="submit">Update Purchase</button>  <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" type="button" onClick={cancel}>Cancel</button>
                </div>
            </form>
            </>
        )}
        </>
    );
} 

export default UpdateMaterialPurchase;