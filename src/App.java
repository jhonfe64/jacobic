import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class App {

    // declaramos la funcion que limita la cantidad de decimnales y redondea
    public static double cantidadCifrasYredondeo(double n) {
        BigDecimal nBd = new BigDecimal(n);
        double nRedondeado = nBd.setScale(4, RoundingMode.HALF_UP).doubleValue();
        return nRedondeado;
    };

    public static void main(String[] args) throws Exception {
        /*
         * 
         * Resolver por el método de jacovic
         * 
         * 6x + 2y + z = 22
         * x + 8y + 2z = 30
         * x - y + 6z = 23
         * 
         */

        // despejamos x, y y z con referencia a la diagonal de la matriz y obtenemos las
        // ecuaciones
        String xEcu = "(22-2y-z)/6";
        String yEcu = "(30+x-2z)/8";
        String zEcu = "(23+y-x)/6";

        // Mostramos las ecuaciones
        System.out.println("Ecuaciones:y e \n");
        System.out.println("x =".concat(xEcu));
        System.out.println("y =".concat(yEcu));
        System.out.println("z =".concat(zEcu));
        System.out.println("\n");

        // Establecemos el criterio de parada
        double criterioParada = 0.0010;
        // Establecemos la variable que deja seguir iterando o para las iteraciones
        boolean continuarIterando = true;
        // Declaramos el array que guarda los valores de la variable x
        ArrayList<Double> x = new ArrayList<Double>();
        // //Declaramos el array que guarda los valores de la variable y
        ArrayList<Double> y = new ArrayList<Double>();
        // Declaramos el array que guarda los valores de la variable z
        ArrayList<Double> z = new ArrayList<Double>();
        // Declaramos el array que guarda los valores de delta x el valor absoluto
        // actual de x menos el anterior
        ArrayList<Double> deltaX = new ArrayList<Double>();
        // Declaramos el array que guarda los valores de delta y el valor absoluto
        // actual de x menos el anterior
        ArrayList<Double> deltaY = new ArrayList<Double>();
        // Declaramos el array que guarda los valores de delta z el valor absoluto
        // actual de x menos el anterior
        ArrayList<Double> deltaZ = new ArrayList<Double>();
        // Declaramos el array que guarda el error total que es el valor mayor del delta
        // de x,y,z
        ArrayList<Double> error = new ArrayList<Double>();

        // Agregamos el valor incial de x,y,z
        x.add(0.0000);
        y.add(0.0000);
        z.add(0.0000);
        // Agregamos el valor incial de los deltas y el error
        deltaX.add(0.0000);
        deltaY.add(0.0000);
        deltaZ.add(0.0000);
        error.add(0.0000);

        // creaamos un for infinito
        for (int i = 0;; i++) {
            // por cada iteracion i calculamos el valor de x segun las ecuaciones de arriba
            // reemplazamos los valores con los valores de x,y,z
            double nuevoX = cantidadCifrasYredondeo((22 - (2 * (y.get(i))) - z.get(i)) / 6);
            // por cada iteracion i calculamos el valor de x segun las ecuaciones de arriba
            // reemplazamos los valores con los valores de x,y,z
            double nuevoY = cantidadCifrasYredondeo((30 + x.get(i) - 2 * z.get(i)) / 8);
            // por cada iteracion i calculamos el valor de x segun las ecuaciones de arriba
            // reemplazamos los valores con los valores de x,y,z
            double nuevoZ = cantidadCifrasYredondeo((23 + y.get(i) - x.get(i)) / 6);
            // por cada iteracion i calculamos el delta de x restando el nuevo valor de x
            // menos el x anterior (el del array x)
            double delX = cantidadCifrasYredondeo(Math.abs((nuevoX - x.get(i))));
            // por cada iteracion i calculamos el delta de y restando el nuevo valor de y
            // menos el y anterior (el del array y)
            double delY = cantidadCifrasYredondeo(Math.abs((nuevoY - y.get(i))));
            // por cada iteracion i calculamos el delta de z restando el nuevo valor de z
            // menos el z anterior (el del array z)
            double delZ = cantidadCifrasYredondeo(Math.abs((nuevoZ - z.get(i))));
            // Agregamos al array de los deltas deltaX, deltaY, deltaZ los valores
            // calculados delX, delY, delZ
            deltaX.add(delX);
            deltaY.add(delY);
            deltaZ.add(delZ);
            // Agregamos al array de los x,y,z los valores
            // calculados de x,y,z
            x.add(nuevoX);
            y.add(nuevoY);
            z.add(nuevoZ);
            // Calculamos el valor maximo de los deltas delX y delY y el resultado lo
            // comparamos con delZ ya que la funcion solo comparea dos valores a la vez
            double maxValorError = Math.max(delX, delY);
            double maxValorErrorFinal = Math.max(maxValorError, delZ);
            // Enviamos el error calculado al array error
            error.add(maxValorErrorFinal);
            // Si el error final calculado es menor que el criterio de parada y estamos en
            // la iteracion 1
            if (maxValorErrorFinal < criterioParada && i > 0) {
                // asignamos false a la variable que para las iteraciones
                continuarIterando = false;
            }
            // si la variable continuar iterando es false rompemos el for y dejamos de
            // iterar
            if (!continuarIterando) {
                break;
            }
        }

        System.out.println();
        System.out.println("Tamaño x " + x.size());
        System.out.println("Tamaño y " + y.size());
        System.out.println("Tamaño z " + z.size());
        System.out.println("Tamaño delta x " + deltaX.size());
        System.out.println("Tamaño delta y " + deltaY.size());
        System.out.println("Tamaño delta z " + deltaZ.size());
        System.out.println("Tamaño error " + error.size());

        System.out.println();
        System.out.println("x " + x);
        System.out.println("y " + y);
        System.out.println("z " + z);
        System.out.println("delta x " + deltaX);
        System.out.println("delta y " + deltaY);
        System.out.println("delta z " + deltaZ);
        System.out.println("error " + error + "\n");

        // tabulamos la informacion guardada en los arrays
        System.out.printf("%-20s %-15s %-12s %-12s %-12s %-12s %-12s %-12s%n", "K",
                "x^k", "y^k", "z^k", "Delta 1",
                "Delta 2", "Delta 3", "Error");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < x.size(); i++) {
            System.out.printf(
                    "%-20d %-15.4f %-12.4f %-12.4f %-12.4f %-12.4f %-12.4f %-12.4f%n",
                    i,
                    x.get(i),
                    y.get(i),
                    z.get(i),
                    deltaX.get(i),
                    deltaY.get(i),
                    deltaZ.get(i),
                    error.get(i));
        }
    }
}
