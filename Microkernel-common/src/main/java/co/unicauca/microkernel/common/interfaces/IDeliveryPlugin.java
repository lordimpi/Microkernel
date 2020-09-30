package co.unicauca.microkernel.common.interfaces;

import co.unicauca.microkernel.common.entities.Delivery;

public interface IDeliveryPlugin {

    /**
     * Establece el costo de envío en dólares.
     *
     * @param delivery envío
     * @return costo del envío
     */
    double calculateCost(Delivery delivery);

}
