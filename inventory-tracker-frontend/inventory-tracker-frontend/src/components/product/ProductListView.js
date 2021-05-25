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
        <h3>View All Products</h3>
        <table className="striped">
        <thead>
            <tr>
            <th>Product Name</th>
            <th>Total Materials Cost</th>
            <th>Time to Make (hr)</th>
            <th>View</th>
            </tr>
        </thead>

        <tbody>
            {productList.sort( (a, b) => {
                return (b.productName) - (a.productName)
            })
            .map((product) => (
            <ProductSummary
                key={product.productId}
                productName={product.productName}
                totalMaterialsCost={product.totalMaterialsCost}
                timeToMake={product.timeToMake}
            />
            ))}
        </tbody>
        </table>
        <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" onClick={addProduct}>Add</button>
        {messages.length > 0 && <Messages messages={messages}/>}
    </div>
    );

}