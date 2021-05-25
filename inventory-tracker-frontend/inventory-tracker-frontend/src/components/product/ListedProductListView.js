import ListedProductTableSummary from './ListedProductTableSummary';
import dateFormat from 'dateformat';

export default function ListedProductListView({ listedProduct }) {
    const displayDateSold = () => {
        console.log(listedProduct);
        if (listedProduct.dateSold) {
            return dateFormat(new Date(listedProduct.dateSold), "paddedShortDate");
        } else {
            return "Unsold";
        }
    }
    console.log(listedProduct);
    return (
        <table className="striped centered">
            <thead className="deep-purple lighten-3">
                <tr>
                    <th>Listing Name</th>
                    <th>Listing Price</th>
                    <th>Date Listed</th>
                    <th>Date Sold</th>
                </tr>
            </thead>
            <tbody className="deep-purple lighten-4">
                    <ListedProductTableSummary 
                        listingName={listedProduct.listingName}
                        listedPrice={listedProduct.listedPrice}
                        dateListed={dateFormat(new Date(listedProduct.dateListed), "paddedShortDate")}
                        dateSold={displayDateSold()}
                    />
            </tbody>
        </table>
    );
}