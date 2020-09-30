package co.unicauca.microkernel.app;

import co.unicauca.microkernel.plugin.manager.DeliveryPluginManager;
import co.unicauca.microkernel.presentation.Console;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    public static void main(String[] args) {

        //Inicializar el plugin manager con la ruta base de la aplicación.
        String basePath = getBaseFilePath();
        try {
            DeliveryPluginManager.init(basePath);

            Console presentationObject = new Console();
            presentationObject.start();

        } catch (Exception ex) {
            Logger.getLogger("Application").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }

    }

    /**
     * Obtiene la ruta base donde está corriendo la aplicación, sin importar que
     * sea desde un archivo .class o desde un paquete .jar.
     *
     */
    private static String getBaseFilePath() {
        String path = Application.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        File pathFile = new File(path);
        if (pathFile.isFile()) {
            path = pathFile.getParent();

            if (!path.endsWith(File.separator)) {
                path += File.separator;
            }

        }

        return path;
    }

}
