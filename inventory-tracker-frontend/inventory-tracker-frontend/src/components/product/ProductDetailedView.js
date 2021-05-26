import { useState, useEffect, useContext } from 'react';
import { useParams } from 'react-router';
import { findById } from '../../services/productAPI';
import { capitalizeEach } from '../../utils/helpers';
import ListedProductListView from './ListedProductListView';
import ProductMaterialListView from './ProductMaterialListView';
import UpdateProductMaterial from "./forms/UpdateProductMaterial";
import MessageContext from '../../context/MessageContext';
import Messages from '../Messages';

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

    // GET product
    useEffect(() => {
        findById(productId).then((data) => {
        setProduct(data);
        });
    }, [productId, showPMUpdateForm]);

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
                {product.materials && <ProductMaterialListView materials={product.materials} setShowPMUpdateForm={setShowPMUpdateForm}/>}
            </div>
            
            <div className="row">
            {showPMUpdateForm && (
                    <UpdateProductMaterial 
                        materialId={showPMUpdateForm.materialId} 
                        materialName={showPMUpdateForm.materialName} 
                        materialQuantity={showPMUpdateForm.materialQuantity} 
                        setShowPMUpdateForm={setShowPMUpdateForm}/>
            )}
            </div>
            {messages.length > 0 && <Messages messages={messages}/>}
        </div> 
    );
}