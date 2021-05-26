import { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { findById } from '../../services/listedProductAPI';
import { capitalizeEach } from '../../utils/helpers';
import dateFormat from 'dateformat';

export default function ListedProductDetailedView() {
    const { listedProductId } = useParams();
    const [listing, setListing] = useState({
        listingName: "", 
        listedPrice: 0.0, 
        dateListed: null, 
        dateSold: null,
        feeAmount: 0.0,
        isSold: false,
        productId: 0
    });

    const displayDateSold = () => {
        if (listing.dateSold) {
            return dateFormat(new Date(listing.dateSold), "paddedShortDate");
        } else {
            return "Unsold";
        }
    }

    const displayIsSold = () => {
        if (listing.isSold) {
            return "Yes!"
        } else {
            return "Not Yet!";
        }
    } 

    // GET listing
    useEffect(() => {
        findById(listedProductId).then((data) => {
        setListing(data);
        });
    }, [listedProductId]);

    return (
        <div className="container">
            <div className="row center">

                <div className="col s12">
                    <div className="card light-blue lighten-4">
                        <div className="card-content black-text">
                            <span className="card-title center">
                                Listed As:<br/> {capitalizeEach(listing.listingName)}
                            </span>
                        </div>
                    </div>
                </div> 

                <div className="col s6">
                    <div className="card indigo lighten-3">
                        <div className="card-content black-text">
                            <span className="card-title">
                            Listed Price:<br/> ${listing.listedPrice}
                            </span>
                        </div>
                    </div>
                </div>

                <div className="col s6">
                    <div className="card indigo lighten-3">
                        <div className="card-content black-text">
                            <span className="card-title">
                            Sold?<br/> {displayIsSold()}
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
                            <td>{dateFormat(new Date(listing.dateListed), "paddedShortDate")}</td>
                            <td>{displayDateSold()}</td>
                            <td>${listing.feeAmount}</td>
                            <td>
                                <button className="btn waves-effect waves-light btn teal accent-1 black-text">Update</button>
                                <button className="btn waves-effect waves-light btn teal accent-1 black-text">Clear All</button>
                            </td>
                        </tr>
                    </tbody>
                 </table>   
                   
            </div>
            <Link to={`/products/${listing.productId}`}>
            <button className=" waves-effect waves-light btn ">Back </button>
            </Link>
        </div>
    );
}