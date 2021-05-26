import { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { findById } from '../../services/listedProductAPI';
import { findById as productFindById} from '../../services/productAPI';
import { capitalizeEach } from '../../utils/helpers';
import dateFormat from 'dateformat';

export default function ListedProductDetailedView() {
  const { listedProductId } = useParams();
  const { productId } = useParams();
  const [listing, setListing] = useState({
    listingName: "",
    listedPrice: 0.0,
    dateListed: null,
    dateSold: null,
    feeAmount: 0.0,
    isSold: false,
    productId: 0,
  });

  const [product, setProduct] = useState({
    productName: "",
    totalMaterialsCost: 0.0,
    timeToMake: 0,
    materials: [],
  });

  // GET listing
  useEffect(() => {
    findById(listedProductId).then((data) => {
      setListing(data);
    });
  }, [listedProductId]);

  // GET product
  useEffect(() => {
    productFindById(listing.productId).then((data) => {
      setProduct(data);
    });
  }, [listing.productId]);

  const displayDateSold = () => {
    if (listing.dateSold) {
      return dateFormat(new Date(listing.dateSold), "paddedShortDate");
    } else {
      return "Unsold";
    }
  };

  const displayIsSold = () => {
    if (listing.isSold) {
      return "Profit";
    } else {
      return "Profit Once Sold";
    }
  };

  //   const displayIsSold = () => {
  //     if (listing.isSold) {
  //       return "Yes!";
  //     } else {
  //       return "Not Yet!";
  //     }
  //   };

  const calculateProfit = () => {
    let profit =
      parseFloat(listing.listedPrice) -
      parseFloat(product.totalMaterialsCost) -
      parseFloat(listing.feeAmount);
    return profit.toFixed(2);
  };

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
                <div class="divider grey darken-4"></div>${listing.listedPrice}
              </span>
            </div>
          </div>
        </div>

        <div className="col s6">
          <div className="card indigo lighten-3">
            <div className="card-content black-text">
              <span className="card-title">
                {displayIsSold()} <div class="divider grey darken-4"></div>$
                {calculateProfit()}
              </span>
            </div>
          </div>
        </div>
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
                <button className="btn waves-effect waves-light btn teal accent-1 black-text">
                  Mark As Sold
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <Link to={`/products/${listing.productId}`}>
        <button className=" waves-effect waves-light btn ">Back </button>
      </Link>
      <button className="btn waves-effect waves-light btn teal accent-1 black-text">
        Edit Listing
      </button>
      <button className="btn waves-effect waves-light btn teal accent-1 black-text">
        Clear Listing
      </button>
    </div>
  );
}