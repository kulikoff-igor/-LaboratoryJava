package client;
import cobraApp.ServerCobra;
import cobraApp.ServerCobraHelper;
import jobXML.AddXML;
import jobXML.DeleteXML;
import jobXML.ViewXML;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.lang.*;
import java.util.Scanner;

/**
 * Created by admin on 19.04.2017.
 */
public class MainClient {

    private static ServerCobra testRef;

    public static void main(String args[])
    {
        try
        {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            NamingContext ncRef =
                    NamingContextHelper.narrow(objRef);
            NameComponent nc = new NameComponent("ServerCobra", "");
            NameComponent path[] = {nc};
            testRef = ServerCobraHelper.narrow(ncRef.resolve(path));

            int input = 0;
            do {
                System.out.println("Действие программы:\n" + "1. Чтение файла\n" + "2. Добавление информации в файл\n" + "3. Удаление информации из файла\n" + "0. Завершение программы");
                Scanner in = new Scanner(System.in);
                System.out.println("Enter some number: ");
                input = in.nextInt();
                executionWork(input);

            } while (input != 0);
           testRef.viewFile();


        }
        catch (Exception e)
        {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace();
        }
    }
    private static void executionWork(int input) {
        switch (input) {
            case 1: {
                try {
                    JSONArray days = new JSONArray(testRef.viewFile());
                    for (int i = 0; i < days.length(); i++) {
                        JSONObject day = days.getJSONObject(i);
                        System.out.println("Index :"+ i );
                        System.out.print(" Date     :" + day.getString("Date")+"\n");
                        System.out.print(" Temp     :" + day.getString("Temp")+"\n");
                        System.out.print(" Pressure :" + day.getString("Pressure")+"\n");
                        System.out.print(" Wind     :" + day.getString("Wind")+"\n");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            }
            case 2: {

                addingInformation();
                break;
            }
            case 3: {

                deleteItem();
                break;
            }
        }
    }

    private static void deleteItem() {
        System.out.println("Введите дату для удаления :");
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        testRef.deleteData(date);
    }

    private static void addingInformation() {
        JSONObject object = new JSONObject();
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите дату : \n");
            object.put("Date", sc.nextLine());

            System.out.print("Введите температуру : \n");
            object.put("Temp", sc.nextLine());

            System.out.print("Введите давление : \n");
            object.put("Pressure", sc.nextLine());

            System.out.print("Введите скорость ветра : \n");
            object.put("Wind", sc.nextLine());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        testRef.addFile(object.toString());
        System.out.println("\n");
    }
}
