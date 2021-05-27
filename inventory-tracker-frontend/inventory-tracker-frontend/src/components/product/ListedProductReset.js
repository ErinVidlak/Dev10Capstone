import {
  findById,
  updateProduct as updateListing,
} from "../../services/listedProductAPI";
import { useState, useEffect } from "react";
import { Link, useHistory, useParams } from "react-router-dom";

export default function ListedProductReset({
  initialListing,
  setShowClearForm,
}) {
	const {listedProductId} = useParams();
	const {productId } = useParams();
  const [currentListing, setCurrentListing] = useState(initialListing);

  const history = useHistory();

  useEffect(() => {
    setCurrentListing(initialListing);
  }, [initialListing]);

  function handleClick(evt) {
    let nextListing = { ...currentListing };
	nextListing.listedProductId = listedProductId;
    nextListing.listingName = "LISTING NOT FOUND";
    nextListing.listedPrice = 0.0;
    nextListing.dateListed = "2000-01-01";
    nextListing.dateSold = null;
    nextListing.feeAmount = 0.0;
    nextListing.sold = false;
	nextListing.productId = productId;

    setCurrentListing(nextListing);

    updateListing(nextListing).then(() => {
      history.push("/products/" + productId);
    });
  }

  const cancel = async () => {
    await setShowClearForm(false);
    history.push(
      `/products/${productId}/listing/${listedProductId}`
    );
  };

  return (
    <div className="card">
      <div className="card-content">
        <span className="card-title">
          Are you sure you want to reset this Listing?
        </span>
        <button
          className="btn waves-effect waves-light btn-flat deep-purple lighten-3"
          onClick={cancel}>
          Nevermind
        </button>
        <button
          className="waves-effect waves-light btn  red lighten-1"
          onClick={handleClick}>
          Reset
        </button>
      </div>
    </div>
  );
}
