//name, price, quantity
import { formatPricePerUnit } from "../../utils/helpers";
import { Link } from "react-router-dom";

export default function MaterialWithPurchaseTableSummary({
  purchaseId,
  purchasePrice,
  quantityPurchased,
}) {
  return (
    <tr>
      <td>{purchaseId}</td>
      <td>${formatPricePerUnit(purchasePrice)}</td>
      <td>{quantityPurchased}</td>
      <td>
        <Link to={"/purchases/" + purchaseId}>
          <button className="btn "> view </button>
        </Link>
      </td>
    </tr>
  );
}
