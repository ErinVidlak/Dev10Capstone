//name, price, quantity
import {formatPricePerUnit} from '../../utils/helpers';


export default function MaterialWithPurchaseTableSummary({purchaseId, purchasePrice, quantityPurchased}) {

	return (
    <tr>
      <td>{purchaseId}</td>
      <td>${formatPricePerUnit(purchasePrice)}</td>
      <td>{quantityPurchased}</td>
      <td><button className="btn "> view </button>   </td>
    </tr>
  );
}
