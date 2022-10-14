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
            System.out.println("2 | Mostrar Alumnos");
            System.out.println("2 | Salir");
            System.out.print("Ingrese una opcion: ");
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        showStudents();
                        break;
                    case 3:
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

    private static void showStudents() {
        
    }

    public static void addStudent() {
        String name = "";
        String lastName = "";
        int id = 0;
        int note = 0;

        do {
            System.out.print("Ingrese el nombre del alumno: ");
            name = scanner.next();
        } while (!fileManager.checkText(name));

        do {
            System.out.print("Ingrese el apellido del alumno: ");
            lastName = scanner.next();
        } while (!fileManager.checkText(lastName));

        do {
            System.out.print("Ingrese la nota del alumno: ");
            note = scanner.nextInt();
        } while (!fileManager.checkNumber(String.valueOf(note)));

        do {
            System.out.print("Ingrese el id del alumno: ");
            id = scanner.nextInt();
        } while (!fileManager.checkNumber(String.valueOf(id)));

        ManagerXml managerXml = new ManagerXml();

        managerXml.WriterXML(NameFile, name, lastName, note, id);
    }
}