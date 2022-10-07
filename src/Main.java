import java.util.Scanner;

public class Main {

    public static String NameFile = "src\\db\\Escuela.xml";
    public static String NameFolder = "src\\db";

    static FileManager fileManager = new FileManager();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        boolean InMenu = true;

        fileManager.checkFolder(NameFolder);
        fileManager.checkFile(NameFile);

        while(InMenu == true) {
            System.out.println("1 | Agregar Alumno");
            System.out.println("2 | Salir");
            System.out.print("Ingrese una opcion: ");
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        InMenu = false;
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                        break;
                }
            } catch (Exception e) {
                InMenu = false;
                System.out.println("Que hace no pongas cosas que no son numeros");
            }
        }
    }

    public static void addStudent() {
        
    }
}