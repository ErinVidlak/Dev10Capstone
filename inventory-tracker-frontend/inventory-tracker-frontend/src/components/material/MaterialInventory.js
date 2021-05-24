
export default function MaterialInventory( { inventory } ){

	return (
    <div class="card indigo lighten-3">
      <div class="card-content black-text">
        <span class="card-title">
          Current Inventory: {inventory.totalQuantity}
        </span>
        <p></p>
      </div>
    </div>
  );

}