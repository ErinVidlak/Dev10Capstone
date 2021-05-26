import { capitalizeEach } from '../../utils/helpers';

export default function ListedProductTableSummary({listingName, listedPrice, dateListed, dateSold}) {

    return (
        <tr>
            <td>{capitalizeEach(listingName)}</td>
            <td>${listedPrice}</td>
            <td>{dateListed}</td>
            <td>{dateSold}</td>
        </tr>
    );
} 
