import ListedProductTableSummary from './ListedProductTableSummary';

export default function ListedProductListView({ listedProduct }) {
    return (
        <table className="striped centered">
            <thead className="deep-purple lighten-3">
                <tr>
                    <th>Listing Name</th>
                    <th>Listing Price</th>
                    <th>Date Listed</th>
                </tr>
            </thead>
            <tbody className="deep-purple lighten-4">
                {listedProduct.map((l) => (
                    <ListedProductTableSummary 
                        listingName={l.listingName}
                        listedPrice={l.listedPrice}
                        dateListed={l.dateListed}
                    />
                ))}
            </tbody>
        </table>
    );
}