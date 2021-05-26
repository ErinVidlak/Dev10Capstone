import { capitalizeEach } from '../../utils/helpers';
import { Link } from "react-router-dom";

export default function ListedProductTableSummary({listingName, listedPrice, dateListed, dateSold, productId, listedProductId}) {

    return (
        <tr>
            <td>{capitalizeEach(listingName)}</td>
            <td>${listedPrice}</td>
            <td>{dateListed}</td>
            <td>{dateSold}</td>
            <td>
                <Link to={`/products/${productId}/listing/${listedProductId}`}>
                <button className="btn waves-effect waves-light btn teal accent-1 black-text">
                    View
                </button>
                </Link>
            </td>
        </tr>
    );
} 
