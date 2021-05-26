import { useState, useContext } from 'react'; 
import { useParams } from 'react-router';
import MessageContext from '../../../context/MessageContext';

function UpdateProductMaterial({
    materialId,
    materialName,
    materialQuantity,
    setShowPMUpdateForm,
}) {
    const { productId } = useParams();
    const { setMessages } = useContext(MessageContext);
    const [updatedQuantity, setUpdatedQuantity] = useState(materialQuantity);

    const submit = (evt) => {
        evt.preventDefault()
        fetch(`http://localhost:8080/api/productMaterial/${productId}/${materialId}`, {
            method: "PUT", 
            headers: { "Content-Type": "application/json"}, 
            body: JSON.stringify(
                {
                    "materialQuantity": updatedQuantity,
                    "productId": productId,
                    "material" : {
                        "materialId": materialId
                    }
                } 
            ),
        }) 
            .then((response) => { 
                if (response.ok) {
                    setMessages([`Your ${materialName} quantity was successfully updated.`]);
                } else {
                    response.json().then(json => {
                        if (Array.isArray(json)) {
                            setMessages(json);
                        } else {
                            setMessages([json.message])
                        }
                    });
                } 
                setShowPMUpdateForm(false);
            }) 
    }

    const cancel = () => {
        setShowPMUpdateForm(false);
    }

    return(
        <div className="col s6">
        <h4>Update Material Quantity</h4>
        <form onSubmit={submit}>
            <div>
            <label htmlFor="materialName">Material Name</label>   
                <input type="text" id="materialName" name="updatedProductMaterial[materialName]" value={materialName} readOnly/>
            </div>
            <div>
            <label htmlFor="materialQuantity">Quantity</label> 
                <input type="number" id="materialQuantity" name="updatedProductMaterial[materialQuantity]" value={updatedQuantity} onChange={evt => setUpdatedQuantity(evt.target.value)}/>
            </div> 
            <div>
                <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" type="submit">Update Quantity</button>  <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" type="button" onClick={cancel}>Cancel</button>
            </div>
        </form>
        </div>
    );

} 

export default UpdateProductMaterial;