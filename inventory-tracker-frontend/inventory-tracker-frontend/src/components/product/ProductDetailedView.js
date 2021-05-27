import { useState, useEffect, useContext } from 'react';
import { useParams, Link } from 'react-router-dom';
import { findById } from '../../services/productAPI';
import { capitalizeEach } from '../../utils/helpers';
import ListedProductListView from './ListedProductListView';
import ProductMaterialListView from './ProductMaterialListView';
import UpdateProductMaterial from "./forms/UpdateProductMaterial";
import MessageContext from '../../context/MessageContext';
import Messages from '../Messages';
import DeleteProductMaterial from './forms/DeleteProductMaterial';
import DeleteProductCard from './forms/DeleteProductCard';
import UpdateProduct from './forms/UpdateProduct';

export default function ProductDetailedView() {
    const {messages} = useContext(MessageContext);
    const { productId } = useParams();
    const [product, setProduct] = useState({
        productName: "",
        totalMaterialsCost: 0.0,
        timeToMake: 0,
        materials: [] 
    });
    const [showPMUpdateForm, setShowPMUpdateForm] = useState(false);
    const [showPMDeleteCard, setShowPMDeleteCard] = useState(false);
    const [showDeleteProductCard, setShowDeleteProductCard] = useState(false);
    const [showUpdateProduct, setShowUpdateProduct] = useState(false);

    // GET product
    useEffect(() => {
        findById(productId).then((data) => {
        setProduct(data);
        });
    }, [productId, showPMUpdateForm, showPMDeleteCard, showUpdateProduct]);

    return (
        <div className="container">
            <div className="row center">
                    
                    <div className="col s12">
                        <div className="card light-blue lighten-4">
                            <div className="card-content black-text">
                                <span className="card-title center">
                                    {capitalizeEach(product.productName)}
                                </span>
                            </div>
                        </div>
                    </div>
                

                <div className="col s6">
                    <div className="card indigo lighten-3">
                        <div className="card-content black-text">
                            <span className="card-title">
                            Total Cost of Materials Used: ${product.totalMaterialsCost}
                            </span>
                        </div>
                    </div>
                </div>

                <div className="col s6">
                    <div className="card indigo lighten-3">
                        <div className="card-content black-text">
                            <span className="card-title">
                            Time to Make in Hours: {product.timeToMake}
                            </span>
                        </div>
                    </div>
                </div>
                
            </div> 

            <div className="row center">
                {product.listedProduct && <ListedProductListView listedProduct={product.listedProduct}/>}
            </div>
            
            <div className="row center">
                {product.materials && <ProductMaterialListView materials={product.materials} setShowPMUpdateForm={setShowPMUpdateForm} setShowPMDeleteCard={setShowPMDeleteCard}/>}
            </div>
            <Link to="/products">
            <button className=" waves-effect waves-light btn ">Back </button>
            </Link>
            <button className="waves-effect waves-light btn  blue darken-3" onClick={() => setShowUpdateProduct(true)}>Update Product</button>
            <button className="waves-effect waves-light btn  red lighten-1" onClick={() => setShowDeleteProductCard(true)}>Delete Product</button>



            <div className="row">
            {showPMUpdateForm && (
                <UpdateProductMaterial 
                    materialId={showPMUpdateForm.materialId} 
                    materialName={showPMUpdateForm.materialName} 
                    materialQuantity={showPMUpdateForm.materialQuantity} 
                    setShowPMUpdateForm={setShowPMUpdateForm}
                />
            )}
            </div> 

            <div>
            {showPMDeleteCard && (
                <DeleteProductMaterial 
                    materialId={showPMDeleteCard.materialId}
                    materialName={showPMDeleteCard.materialName}
                    setShowPMDeleteCard={setShowPMDeleteCard}
                />
            )}
            </div>   

            <div>
            {showDeleteProductCard && (
                <DeleteProductCard 
                    productName={product.productName}
                    setShowDeleteProductCard={setShowDeleteProductCard}
                />
            )}
            </div>
            {showUpdateProduct && (
                <UpdateProduct product={product} />
            )}    

            {messages.length > 0 && <Messages messages={messages}/>}
        </div> 
    );
}