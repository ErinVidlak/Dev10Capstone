import { useState, useEffect } from 'react';
import { useParams } from 'react-router';
import { findById } from '../../services/productAPI';
import { capitalizeEach } from '../../utils/helpers';
import ListedProductListView from './ListedProductListView';
import ProductMaterialListView from './ProductMaterialListView';

export default function ProductDetailedView() {
    const { productId } = useParams();
    const [product, setProduct] = useState({
        productName: "",
        totalMaterialsCost: 0.0,
        timeToMake: 0,
        materials: [] 
    });

    // GET product
    useEffect(() => {
        console.log(productId);
        findById(productId).then((data) => {
        setProduct(data);
        });
    }, [productId]);

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
                {product.materials && <ProductMaterialListView materials={product.materials} />}
            </div>

        </div> 
    );
}