import ListedProductTableSummary from './ListedProductTableSummary';

export default function ListedProductListView({ listedProduct }) {
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
                        dateListed={listedProduct.dateListed}
                        dateSold={listedProduct.dateSold}
                    />
            </tbody>
        </table>
    );
}