import { useState, useContext } from "react";
import { useHistory, useParams } from "react-router-dom";
import MessageContext from '../../../context/MessageContext';

export default function UpdateProduct({ productName, product, setShowUpdateProduct }) {
    const history = useHistory();
    const { productId } = useParams();
    const { setMessages } = useContext(MessageContext);
    const [updatedProduct, setUpdatedProduct] = useState(product)

    const submit = (evt) => {
        evt.preventDefault()
        fetch(`http://localhost:8080/api/product/${productId}`, {
            method: "PUT", 
            headers: { "Content-Type": "application/json"}, 
            body: JSON.stringify(updatedProduct),
        }) 
            .then((response) => { 
                if (response.ok) {
                    setMessages([`Your ${productName} product was successfully updated.`]);
                } else {
                    response.json().then(json => {
                        if (Array.isArray(json)) {
                            setMessages(json);
                        } else {
                            setMessages([json.message])
                        }
                    });
                } 
                setShowUpdateProduct(false);
            }) 
    }

    const cancel = () => {
        setShowUpdateProduct(false);
    }

    return (
        <div>
        {updatedProduct && (
            <div className="col s6">
            <h4>Update a Product</h4>
            <form onSubmit={submit}>
                <div>
                <label htmlFor="productName">Product Name</label>    
                    <input type="text" id="productName" name="updatedProductName[productName]" value={updatedProduct.productName} onChange={evt => setUpdatedProduct({ ... updatedProduct, productName: evt.target.value})}/>
                </div>
                <div>
                <label htmlFor="timeToMake">Time to Make in Hours</label>    
                    <input type="number" id="timeToMake" name="updatedProductTimeToMake[timeToMake]" value={updatedProduct.timeToMake} onChange={evt => setUpdatedProduct({ ... updatedProduct, timeToMake: evt.target.value})}/>
                </div>
                <div>
                <label htmlFor="totalMaterialsCost">Total Cost of Materials Used ($)</label>    
                    <input type="number" step="0.01" id="totalMaterialsCost" name="updatedProductTotalMaterialsCost[totalMaterialsCost]" value={updatedProduct.totalMaterialsCost} onChange={evt => setUpdatedProduct({ ... updatedProduct, totalMaterialsCost: evt.target.value})}/>
                </div>
                <div>
                <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" type="submit">Update Product</button>  <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" type="button" onClick={cancel}>Cancel</button>
                </div>
            </form>
            </div>
        )}
        </div>
    );
}