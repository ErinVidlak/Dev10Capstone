import dateFormat from 'dateformat';

export default function ListedProductTableSummary({listingName, listedPrice, dateListed}) {

    return (
        <tr>
            <td>{listingName}</td>
            <td>${listedPrice}</td>
            <td>{dateFormat(new Date(dateListed), "paddedShortDate")}</td>
        </tr>
    );
} 
