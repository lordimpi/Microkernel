package co.unicauca.microkernel.plugin.manager;

import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase es una fábrica que utiliza reflexión para crear dinámicamente los
 * plugins.
 *
 */
public class DeliveryPluginManager {

    private static final String FILE_NAME = "plugin.properties";
    private static DeliveryPluginManager instance;

    private Properties pluginProperties;

    private DeliveryPluginManager() {
        pluginProperties = new Properties();
    }

    public static DeliveryPluginManager getInstance() {
        return instance;
    }

    public static void init(String basePath) throws Exception {

        instance = new DeliveryPluginManager();
        instance.loadProperties(basePath);
        if (instance.pluginProperties.isEmpty()) {
            throw new Exception("Could not initialize plugins");
        }

    }

    public IDeliveryPlugin getDeliveryPlugin(String countryCode) {

        //Verificar si existe una entrada en el archivo para el país indicado.
        String propertyName = "delivery." + countryCode.toLowerCase();
        if (!pluginProperties.containsKey(propertyName)) {
            return null;
        }

        IDeliveryPlugin plugin = null;
        //Obtener el nombre de la clase del plugin.
        String pluginClassName = pluginProperties.getProperty(propertyName);

        try {

            //Obtener una referencia al tipo de la clase del plugin.
            Class<?> pluginClass = Class.forName(pluginClassName);
            if (pluginClass != null) {

                //Crear un nuevo objeto del plugin.
                Object pluginObject = pluginClass.getDeclaredConstructor().newInstance();

                if (pluginObject instanceof IDeliveryPlugin) {
                    plugin = (IDeliveryPlugin) pluginObject;
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            Logger.getLogger("DeliveryPluginManager").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }

        return plugin;

    }

    private void loadProperties(String basePath) {

        String filePath = basePath + FILE_NAME;
        //String filePath = "C:\\plugin.properties";
        //System.out.println("filepath "+filePath);
        try (FileInputStream stream = new FileInputStream(filePath)) {

            pluginProperties.load(stream);

        } catch (IOException ex) {
            Logger.getLogger("DeliveryPluginManager").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }

    }

}
