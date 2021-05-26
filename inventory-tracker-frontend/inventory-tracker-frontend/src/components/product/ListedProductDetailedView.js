import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import {
  findById,
  updateProduct as updateListing,
} from "../../services/listedProductAPI";
import { findById as productFindById } from "../../services/productAPI";
import { capitalizeEach } from "../../utils/helpers";
import dateFormat from "dateformat";
import UpdateListedProduct from "./UpdateListedProduct";

export default function ListedProductDetailedView() {
  const { listedProductId } = useParams();
    const { productId } = useParams();

  const [listing, setListing] = useState({
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
        if(!data.dateSold){
            data.dateSold = "2000-01-01";
        }
      setListing(data);
    });
  }, [listedProductId]);

  const [product, setProduct] = useState({
    productName: "",
    totalMaterialsCost: 0.0,
    timeToMake: 0
  });

  const [showDateSoldForm, setShowDateSoldForm] = useState(false);
  const [showUpdateAllForm, setShowUpdateAllForm] = useState(false);
  const [showRelistForm, setShowRelistForm] = useState(false);

  // GET product
  useEffect(() => {
    productFindById(productId).then((data) => {
      setProduct(data);
    });
  }, [productId]);

  const displayDateSold = () => {
    if (!(listing.dateSold == "2000-01-01" || listing.dateSold == "1999-12-31") && listing.sold) {
      return dateFormat((listing.dateSold), "paddedShortDate");
    } else {
      return "Unsold";
    }
  };

  const displayIsSold = () => {
    if (listing.sold) {
      return "Profit";
    } else {
      return "Profit Once Sold";
    }
  };

  // const displayIsSold = () => {
  //   if (listing.isSold) {
  //     return "Yes!";
  //   } else {
  //     return "Not Yet!";
  //   }
  // };

  const displaySoldButton = () => {
    if (listing.sold) {
      return "relist product";
    } else {
      return "mark as sold";
    }
  };

  const calculateProfit = () => {
    let profit =
      parseFloat(listing.listedPrice) -
      parseFloat(product.totalMaterialsCost) -
      parseFloat(listing.feeAmount);
    return profit.toFixed(2);
  };

  async function handleHasSold(evt) {
    let soldListing = { ...listing };
    console.log(listing.sold);
    if (!listing.sold) {
         setShowDateSoldForm(true);
    } else {
         setShowRelistForm(true);
    }
  }


  return (
    <div className="container">
      <div className="row center">
        <div className="col s12">
          <div className="card light-blue lighten-4">
            <div className="card-content black-text">
              <span className="card-title center">
                Listed As:
                <br /> {capitalizeEach(listing.listingName)}
              </span>
            </div>
          </div>
        </div>

        <div className="col s6">
          <div className="card indigo lighten-3">
            <div className="card-content black-text">
              <span className="card-title">
                Listed Price:
                <div className="divider grey darken-4"></div>$
                {listing.listedPrice}
              </span>
            </div>
          </div>
        </div>
        {calculateProfit() < 0 ? (
          <div className="col s6">
            <div className="card red lighten-2">
              <div className="card-content black-text">
                <span className="card-title">
                  {displayIsSold()}{" "}
                  <div className="divider grey darken-4"></div>$
                  {calculateProfit()}
                </span>
              </div>
            </div>
          </div>
        ) : (
          <div className="col s6">
            <div className="card green lighten-3">
              <div className="card-content black-text">
                <span className="card-title">
                  {displayIsSold()}{" "}
                  <div className="divider grey darken-4"></div>$
                  {calculateProfit()}
                </span>
              </div>
            </div>
          </div>
        )}
      </div>

      <div className="row center">
        <table className="striped centered">
          <thead className="deep-purple lighten-3">
            <tr>
              <th>Date Listed</th>
              <th>Date Sold</th>
              <th>Total Shipping/Listing Fees</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody className="deep-purple lighten-4">
            <tr>
              <td>
                {dateFormat(new Date(listing.dateListed), "paddedShortDate")}
              </td>
              <td>{displayDateSold()}</td>
              <td>${listing.feeAmount}</td>
              <td>
                {listing.sold == true ? (
                  <button
                    className="btn waves-effect waves-light btn green accent-1 black-text"
                    id="isSoldButton"
                    onClick={handleHasSold}>
                    {displaySoldButton()}
                  </button>
                ) : (
                  <button
                    className="btn waves-effect waves-light btn pink lighten-4 black-text"
                    id="isSoldButton"
                    onClick={handleHasSold}>
                    Mark as Sold
                  </button>
                )}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div className="container left">
        {showRelistForm && (
          <UpdateListedProduct
            currentListing={listing}
            isUpdatingAll={showUpdateAllForm}
            relisting={showRelistForm}
            setShowDateSoldForm={setShowDateSoldForm}
            setShowUpdateAllForm={setShowUpdateAllForm}
            setShowRelistForm={setShowRelistForm}
          />
        )}
        {showDateSoldForm && (
          <UpdateListedProduct
            currentListing={listing}
            isUpdatingAll={showUpdateAllForm}
            relisting={showRelistForm}
            setShowDateSoldForm={setShowDateSoldForm}
            setShowUpdateAllForm={setShowUpdateAllForm}
            setShowRelistForm={setShowRelistForm}
          />
        )}
      </div>
      <Link to={`/products/${listing.productId}`}>
        <button className=" waves-effect waves-light btn ">Back </button>
      </Link>
      <button
        className="btn waves-effect waves-light btn teal accent-1 black-text"
        onClick={() => setShowUpdateAllForm(true)}>
        Edit Listing
      </button>
      <button className="btn waves-effect waves-light btn teal accent-1 black-text">
        Clear Listing
      </button>
    </div>
  );
}


//  {
//    showRelistForm && (
//      <UpdateListedProduct
//        isUpdatingAll={false}
//        relisting={true}
//        setShowDateSoldForm={setShowDateSoldForm}
//        setShowUpdateAllForm={setShowUpdateAllForm}
//        setShowRelistForm={setShowRelistForm}
//      />
//    );
//  }
//  {
//    showDateSoldForm && (
//      <UpdateListedProduct
//        isUpdatingAll={false}
//        relisting={false}
//        setShowDateSoldForm={setShowDateSoldForm}
//        setShowUpdateAllForm={setShowUpdateAllForm}
//        setShowRelistForm={setShowRelistForm}
//      />
//    );
//  }
//  {
//    showUpdateAllForm && (
//      <UpdateListedProduct
//        isUpdatingAll={true}
//        relisting={false}
//        setShowDateSoldForm={setShowDateSoldForm}
//        setShowUpdateAllForm={setShowUpdateAllForm}
//        setShowRelistForm={setShowRelistForm}
//      />
//    );
//  }