package co.unicauca.microkernel.plugins.ecuador;

import co.unicauca.microkernel.common.entities.Delivery;
import co.unicauca.microkernel.common.entities.Product;
import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;
/**
 * Plugin para envios a Mexico
 * @author Libardo, Julio
 */
public class EcuadorDeliveryPlugin implements IDeliveryPlugin {

    /**
     * Calcular el costo de envío de un producto de la tienda enviado dentro de
     * México.
     *
     */
    public double calculateCost(Delivery delivery) {
    Product product = delivery.getProduct();

        double cost;

        //El peso del producto determina el costo.
        if (product.getWeight() <= 3) {

            cost = 6;

        } else {

            //El peso adicional despuÃ©s de 3 kg se cobra a 0.6 por kilo.
            cost = 6 + (product.getWeight() - 3) * 0.6;

        }

        return cost;
        

    }
}
