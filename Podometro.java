/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @Aitor Amigot    
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborales;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0.0;
        sexo = 'M';
        longitudZancada = 0.0;
        totalPasosLaborales = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0.0;
        totalDistanciaFinSemana = 0.0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;        
        if(sexo == HOMBRE){
            longitudZancada = Math.ceil(ZANCADA_HOMBRE * altura);
        }
        
        if(sexo == MUJER){
            longitudZancada = Math.floor(ZANCADA_MUJER * altura);
        }
    }

     /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFin � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio, int horaFin) {
        int horasDeInicio = horaInicio / 100;
        int horasDeFin = horaFin / 100;
        int minutosDeInicio = horaInicio % 100;
        int minutosDeFin = horaFin % 100;
        int tiempoEnMins = (horasDeFin - horasDeInicio) * 60;
        
        tiempoEnMins += minutosDeFin - minutosDeInicio;
        tiempo += tiempoEnMins;
        
        switch(dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                totalPasosLaborales += pasos;
                break;
            case 6:
                totalPasosSabado += pasos;
                break;
            case 7:
                totalPasosDomingo += pasos;
                break;
        }
        
        if(dia <= 5){
            totalDistanciaSemana += pasos * longitudZancada / 100 / 1000;
        } else {
            totalDistanciaFinSemana += pasos * longitudZancada / 100 / 1000; 
        }
        
        if(horaInicio >= 2100){
            caminatasNoche++;
        }
        
    }
    
     /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        if(sexo == HOMBRE){
            System.out.println("Configuraci�n del pod�metro");
            System.out.println("***************************");
            System.out.println("Alutura: " + altura / 100 + " mtos");
            System.out.println("Sexo: HOMBRE");
            System.out.println("Longitud zancada: " + longitudZancada / 100 + " mtos"); 
        }
        
        if(sexo == MUJER){
            System.out.println("Configuraci�n del pod�metro");
            System.out.println("***************************");
            System.out.println("Alutura: " + altura / 100 + " mtos");
            System.out.println("Sexo: MUJER");
            System.out.println("Longitud zancada: " + longitudZancada / 100 + " mtos"); 
        }
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        double totalDistancias = totalDistanciaSemana + totalDistanciaFinSemana;
        System.out.println("Estad�ticas");
        System.out.println("*********************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistancias + " Km");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km");
        System.out.println("\n");
        System.out.println("N� pasos d�as laborales: " + totalPasosLaborales);
        System.out.println("N� pasos S�BADO: " + totalPasosSabado);
        System.out.println("N� pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("\n");
        System.out.println("N� caminatas realizadas a partir de las 21h: " + caminatasNoche);
        System.out.println("\n");
        System.out.println("Tiempo total caminado en la semana: " + (tiempo / 60) + "h" + " y " + (tiempo % 60) + "m.");
        System.out.println("D�a/s con m�s pasos caminados: " + diaMayorNumeroPasos());
    }

   

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        if(totalPasosDomingo > totalPasosSabado && totalPasosDomingo > totalPasosLaborales){
          return "DOMINGO";
        } else if(totalPasosSabado > totalPasosDomingo && totalPasosSabado > totalPasosLaborales ){
            return "S�BADO";
        } else if(totalPasosLaborales > totalPasosDomingo && totalPasosLaborales > totalPasosSabado){
            return "LABORALES";
        } else if (totalPasosDomingo == totalPasosSabado && totalPasosDomingo ==totalPasosLaborales){
            return "DOMINGO, S�BADO y LABORALES";
        } else if (totalPasosDomingo == totalPasosSabado){
            return "DOMINGO y S�BADO";
        } else if (totalPasosDomingo == totalPasosLaborales){
            return "DOMINGO Y LABORALES";
        } else{
            return "S�BADO Y LABORALES";
        }
    }
    
    
    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {
        altura = 0.0;
        sexo = 'M';
        longitudZancada = 0.0;
        totalPasosLaborales = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0.0;
        totalDistanciaFinSemana = 0.0;
        tiempo = 0;
        caminatasNoche = 0;
    }

}
