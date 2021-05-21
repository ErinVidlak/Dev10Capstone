package learn.inventory.validation;

import learn.inventory.models.ListedProduct;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ListedProductDateValidator implements ConstraintValidator<ListedProductDateValidation, ListedProduct> {
    @Override
    public void initialize(ListedProductDateValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(ListedProduct listedProduct, ConstraintValidatorContext constraintValidatorContext) {

        if(listedProduct.getDateSold() == null){
            return true;
        }


         if (listedProduct.getDateListed().isBefore(listedProduct.getDateSold()) ){
             return true;
         }else return listedProduct.getDateListed().isEqual(listedProduct.getDateSold());
    }
}
