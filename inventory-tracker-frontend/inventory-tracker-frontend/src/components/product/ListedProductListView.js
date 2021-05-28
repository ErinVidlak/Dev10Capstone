import ListedProductTableSummary from "./ListedProductTableSummary";
import dateFormat from "dateformat";

export default function ListedProductListView({ listedProduct }) {
  const displayDateSold = () => {
    if (listedProduct.dateSold && listedProduct.dateSold != "2000-01-01") {
      return dateFormat(new Date(listedProduct.dateSold), "paddedShortDate");
   
    } else {
      return "Unsold";
    }
  };

  const displayDateListed = () => {
    if (listedProduct.dateListed == "2000-01-01") {
      return "Unlisted";
    } else {
      return dateFormat(listedProduct.dateListed, "paddedShortDate");
    }
  };

  return (
    <table className="striped centered">
      <thead className="deep-purple lighten-3">
        <tr>
          <th>Listing Name</th>
          <th>Listing Price</th>
          <th>Date Listed</th>
          <th>Date Sold</th>
          <th>View</th>
        </tr>
      </thead>
      <tbody className="deep-purple lighten-4">
        <ListedProductTableSummary
          listingName={
            listedProduct.listingName == ""
              ? "Not Listed"
              : listedProduct.listingName
          }
          listedPrice={listedProduct.listedPrice}
          dateListed={displayDateListed()}
          dateSold={displayDateSold()}
          productId={listedProduct.productId}
          listedProductId={listedProduct.listedProductId}
        />
      </tbody>
    </table>
  );
}
