//name, price, quantity
import {formatPricePerUnit} from '../../utils/helpers';


export default function MaterialPurchaseTableSummary({purchaseId, purchasePrice, quantityPurchased}) {

	return (
    <tr>
      <td>{purchaseId}</td>
      <td>${formatPricePerUnit(purchasePrice)}</td>
      <td>{quantityPurchased}</td>
    </tr>
  );
}
