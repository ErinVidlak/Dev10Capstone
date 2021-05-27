import { useState, useEffect, useContext } from "react";
import { useParams, useHistory, Link } from "react-router-dom";
import {
  findById,
  updateProduct as updateListing,
} from "../../services/listedProductAPI";
import { capitalizeEach } from "../../utils/helpers";
import dateFormat from "dateformat";
import MessageContext from "../../context/MessageContext";

export default function UpdateListedProduct({
  currentListing,
  isUpdatingAll,
  relisting,
  setShowDateSoldForm,
  setShowUpdateAllForm,
  setShowRelistForm,
}) {
  const history = useHistory();
  const { listedProductId } = useParams();
  const { setMessages } = useContext(MessageContext);
  const [relistingForm, setRelistingForm] = useState(relisting);
  const [isUpdatingAllForm, setIsUpdatingAllForm] = useState(isUpdatingAll);

  const [updatedListing, setUpdatedListing] = useState({
    listingName: "",
    listedPrice: 0.0,
    dateListed: null,
    dateSold: "2000-01-01",
    feeAmount: 0.0,
    isSold: false,
    productId: 0,
  });

  // GET listing
  useEffect(() => {
    findById(listedProductId).then((data) => {
      if (!data.dateSold) {
        data.dateSold = "2000-01-01";
      }
      setUpdatedListing(data);
    });
  }, [listedProductId]);

  function handleChange(evt) {
    let nextListing = { ...updatedListing };
    nextListing[evt.target.name] = evt.target.value;
    setUpdatedListing(nextListing);
    console.log(nextListing);
  }

  async function handleSubmit(evt) {
  
    let nextListing = { ...updatedListing };
    nextListing[evt.target.name] = evt.target.value;

    if (relistingForm == true && isUpdatingAllForm == false) {
      nextListing.sold = false;
      nextListing.dateSold = null;
    } else if (!isUpdatingAllForm && !relistingForm) {
      nextListing.sold = true;
    } else if (isUpdatingAllForm && !relistingForm) {
      if (!nextListing.sold) {
        nextListing.dateSold = null;
      }
    }

    nextListing.feeAmount = parseFloat(nextListing.feeAmount).toFixed(2);
    nextListing.listedPrice = parseFloat(nextListing.listedPrice).toFixed(2);

    setUpdatedListing(nextListing);

    await updateListing(nextListing);
    history.push(
      `/products/${updatedListing.productId}/listing/${nextListing.listingId}`
    );
  }

  const cancel = async () => {
    await setShowDateSoldForm(false);
    await setShowUpdateAllForm(false);
    await setShowRelistForm(false);

    history.push(
      `/products/${updatedListing.productId}/listing/${listedProductId}`
    );
  };
  //either entering the date sold because a product sold (has sold goes from false to true), (relisting == false, isUpdatingAll == false)
  //relisting a previously sold product (hasSold goes from true to false), (relisting = true, isUpdating all = false)
  //or its just a generic update on the fields
  return (
    <div className="container">
      <form onSubmit={handleSubmit}>
        {relisting ? (
          <>
            <div className="row">
              <label htmlFor="listingName">Listing Name</label>
              <div className="input-field col 6">
                <input
                  type="text"
                  data-length="50"
                  id="listingName"
                  name="listingName"
                  value={updatedListing.listingName}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>

            <div className="row">
              <label htmlFor="listedPrice">Listing Price</label>
              <div className="input-field col 4">
                <input
                  className="decimal"
                  step="0.01"
                  min="0.01"
                  value={updatedListing.listedPrice}
                  presicion={2}
                  name="listedPrice"
                  type="number"
                  id="listedPrice"
                  onChange={handleChange}
                  required
                />
              </div>
            </div>

            <div className="row">
              <label htmlFor="feeAmount">Total Fee Amount</label>
              <div className="input-field col s4">
                <input
                  className="decimal"
                  step="0.01"
                  value={updatedListing.feeAmount}
                  presicion={2}
                  name="feeAmount"
                  type="number"
                  id="feeAmount"
                  onChange={handleChange}
                />
              </div>
            </div>

            <div className="row">
              <label htmlFor="dateListed">Date Listed</label>
              <div className="input-field col 5">
                <input
                  min="2000-01-01"
                  value={updatedListing.dateListed}
                  name="dateListed"
                  type="date"
                  id="dateListed"
                  onChange={handleChange}
                  required
                />
              </div>
            </div>
            <div>
              <button
                className="btn waves-effect waves-light btn-flat deep-purple lighten-3"
                type="submit">
                Relist Product
              </button>
              <button
                className="btn waves-effect waves-light red lighten-1 "
                onClick={cancel}>
                Cancel
              </button>
            </div>
          </>
        ) : (
          <>
            {!isUpdatingAll ? (
              <>
                <div>
                  <label htmlFor="dateSold">Date Sold</label>
                  <input
                    min="2000-01-01"
                    value={updatedListing.dateSold}
                    name="dateSold"
                    type="date"
                    onChange={handleChange}
                  />
                </div>
                <div>
                  <button
                    className="btn waves-effect waves-light btn-flat deep-purple lighten-3"
                    type="submit">
                    Mark As Sold
                  </button>
                  <button
                    className="btn waves-effect waves-light red lighten-1 "
                    onClick={cancel}>
                    Cancel
                  </button>
                </div>
              </>
            ) : (
              <>
                <div className="row">
                  <label htmlFor="listingName">Listing Name</label>
                  <div className="input-field col 6">
                    <input
                      type="text"
                      data-length="50"
                      name="listingName"
                      value={updatedListing.listingName}
                      onChange={handleChange}
                      required
                    />
                  </div>
                </div>

                <div className="row">
                  <label htmlFor="listedPrice">Listing Price</label>
                  <div className="input-field col 4">
                    <input
                      className="decimal"
                      step="0.01"
                      min="0.01"
                      value={updatedListing.listedPrice}
                      presicion={2}
                      name="listedPrice"
                      type="number"
                      onChange={handleChange}
                    />
                  </div>
                </div>

                <div className="row">
                  <label htmlFor="feeAmount">Total Fee Amount</label>
                  <div className="input-field col s4">
                    <input
                      className="decimal"
                      step="0.01"
                      value={updatedListing.feeAmount}
                      presicion={2}
                      name="feeAmount"
                      type="number"
                      onChange={handleChange}
                    />
                  </div>
                </div>

                <div className="row">
                  <label htmlFor="dateListed">Date Listed</label>
                  <div className="input-field col 5">
                    <input
                      min="2000-01-01"
                      value={updatedListing.dateListed}
                      name="dateListed"
                      type="date"
                      onChange={handleChange}
                      required
                    />
                  </div>
                </div>

                {updatedListing.sold && (
                  <div>
                    <label htmlFor="dateSold">Date Sold</label>
                    <input
                      min="2000-01-01"
                      value={updatedListing.dateSold}
                      name="dateSold"
                      type="date"
                      onChange={handleChange}
                    />
                  </div>
                )}

                <div>
                  <button
                    className="btn waves-effect waves-light btn-flat deep-purple lighten-3"
                    type="submit">
                    Update Listing
                  </button>
                  <button
                    className="btn waves-effect waves-light red lighten-1 "
                    onClick={cancel}>
                    Cancel
                  </button>
                </div>
              </>
            )}
          </>
        )}
      </form>
    </div>
  );
}
