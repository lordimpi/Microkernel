package co.unicauca.microkernel.plugins.peru;

import co.unicauca.microkernel.common.entities.Delivery;
import co.unicauca.microkernel.common.entities.Product;
import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;
/**
 * Plugin para envios a Mexico
 * @author Libardo, Julio
 */
public class PeruDeliveryPlugin implements IDeliveryPlugin {

    /**
     * Calcular el costo de envío de un producto de la tienda enviado dentro de
     * México.
     *
     */
    public double calculateCost(Delivery delivery) {

        Product product = delivery.getProduct();

        double cost;

        //El peso del producto determina el costo.
        if (product.getWeight() <= 2) {

            cost = 5;

        } else {

            //El peso adicional después de 2 kg se cobra a 0.5 por kilo.
            cost = 5 + (product.getWeight() - 2) * 0.5;

        }

        return cost;
    }
}
