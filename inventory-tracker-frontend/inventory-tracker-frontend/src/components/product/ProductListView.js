import { useContext, useState, useEffect } from "react";
import { findAll } from "../../services/productAPI";
import ProductSummary from "./ProductSummary";
import Messages from "../Messages";
import MessageContext from "../../context/MessageContext";
import { useHistory } from 'react-router';

export default function ProductListView() {
    const [productList, setProductList] = useState([]);
    const {messages} = useContext(MessageContext);
    const history = useHistory();

    const addProduct = () => {
        history.push("/products/add");
    } 

    useEffect(() => {
        findAll().then((data) => {
            setProductList(data);
        });
    }, []);

    return (
    <div className="container px-3 py-3">
        
        
        <div className="row center">
            <div className="card purple lighten-4">
                <div className="card-content black-text">
                    <span className="card-title center">Products</span>
                </div>
            </div>
        </div>
        
        <div className="row">
            <table className="striped centered">
                <thead className="deep-purple lighten-3">
                    <tr>
                    <th>Product Name</th>
                    <th>Total Materials Cost</th>
                    <th>Time to Make (hr)</th>
                    <th>View</th>
                    </tr>
                </thead>

                <tbody className="deep-purple lighten-4">
                    {productList.sort( (a, b) => {
                        return (b.productName) - (a.productName)
                    })
                    .map((product) => (
                    <ProductSummary
                        key={product.productId}
                        productName={product.productName}
                        totalMaterialsCost={product.totalMaterialsCost}
                        timeToMake={product.timeToMake}
                        productId={product.productId}
                    />
                    ))}
                </tbody>
            </table>
        </div>
        <button className="btn waves-effect waves-light btn teal accent-1 black-text" onClick={addProduct}>Add New Product</button>
        {messages.length > 0 && <Messages messages={messages}/>}
    </div>
    );

}