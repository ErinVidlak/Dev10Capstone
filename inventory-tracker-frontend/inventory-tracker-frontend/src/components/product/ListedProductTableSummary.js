import { capitalizeEach } from '../../utils/helpers';

export default function ListedProductTableSummary({listingName, listedPrice, dateListed, dateSold}) {

    return (
        <tr>
            <td>{capitalizeEach(listingName)}</td>
            <td>${listedPrice}</td>
            <td>{dateListed}</td>
            <td>{dateSold}</td>
            <td>
                <button className="btn waves-effect waves-light btn teal accent-1 black-text">
                    View
                </button>
            </td>
        </tr>
    );
} 
