import { Link } from "react-router-dom";
import { useEffect, useState } from 'react';

export default function ProductSummary({
    productId,
    productName,
    totalMaterialsCost,
    timeToMake,
}) {

    const [product, setProduct] = useState({productId: ""});

    useEffect(() => {

        fetch(`http://localhost:8080/api/product/${productId}`)  
            .then(response => {
                if (response.status !== 200) {
                    return Promise.reject("Product fetch failed")
                } 
                return response.json();
            }) 
            .then(json => setProduct(json));

    }, []);
    
    return (
        <tr>
            <td>{productName}</td>
            <td>${totalMaterialsCost}</td>
            <td>{timeToMake}</td>
            <td>
                <Link to={`/products/${productId}`}>
                <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3">
                    View
                </button>
                </Link>
            </td>
        </tr>
    );
}