

export default function ListedProductTableSummary({listingName, listedPrice, dateListed, dateSold}) {

    return (
        <tr>
            <td>{listingName}</td>
            <td>${listedPrice}</td>
            <td>{dateListed}</td>
            <td>{dateSold}</td>
        </tr>
    );
} 
