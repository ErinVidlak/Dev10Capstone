import { Link } from "react-router-dom";

export default function ProductSummary({
    productId,
    productName,
    totalMaterialsCost,
    timeToMake,
}) {
    
    return (
        <tr>
            <td>{productName}</td>
            <td>${totalMaterialsCost}</td>
            <td>{timeToMake}</td>
            <td>
                <Link to={`/products/${productId}`}>
                <button className="btn waves-effect waves-light btn teal accent-1 black-text">
                    View
                </button>
                </Link>
            </td>
        </tr>
    );
}