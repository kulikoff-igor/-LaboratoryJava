import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by admin on 10.04.2017.
 */
public class HelloServiceClient {

    private static RemoteHelloService service;

    public static void main(String... args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 2099);
        service = (RemoteHelloService) registry.lookup("sample/HelloService");
        int input = 0;
        do {
            System.out.println("Действие программы:\n" + "1. Чтение файла\n" + "2. Добавление информации в файл\n" + "0. Завершение программы");
            Scanner in = new Scanner(System.in);
            System.out.println("Enter some number: ");
            input = in.nextInt();
            executionWork(input);

        } while (input != 0);
    }

    private static void executionWork(int input) throws RemoteException {
        switch (input) {
            case 1: {
                System.out.println("Полученные данные :\n");
                System.out.println(service.viewFile());
                break;
            }
            case 2: {

                addingInformation();
                break;
            }
        }
    }

    private static void addingInformation() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        String text;
        System.out.println("Ввод данных для добавления : \n");
        text = sc.nextLine();
        System.out.println("Результат записи : \n");
        System.out.println(service.addingInformation(text));
    }

}