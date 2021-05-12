package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SystemInput {

   public static String readString(){
        String data = new String();
        Scanner input = new Scanner(System.in);

        data = input.nextLine();

        return data;
   }

   public static int readInt(){
       int data = -1;
       Scanner input = new Scanner(System.in);

        try {
            data = input.nextInt();

            } catch (NumberFormatException e) {
                System.out.println("Você digitou um caracter. Por favor, digite um número "
                + e.getMessage());

            } catch (InputMismatchException e){
                System.out.println("Você digitou um caracter. Por favor, digite um número "
                + e.getMessage());
            } 

       return data;
   }

   public static double readDouble(){
        double data = -1;
        Scanner input = new Scanner(System.in);

        try {
            data = input.nextDouble();
            
        } catch (NumberFormatException e) {
            System.out.println("Você digitou um caracter. Por favor, digite um número "
            + e.getMessage());

        } catch (InputMismatchException e){
            System.out.println("Você digitou um caracter. Por favor, digite um número "
            + e.getMessage());
        } 

    return data;
    }

    public static boolean readBoolean() {
        boolean data = false;
        Scanner input = new Scanner(System.in);

        try {
            data = input.nextBoolean();
            
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite 'false' ou 'true' "
            + e.getMessage());

        } catch (InputMismatchException e){
            System.out.println("Por favor, digite 'false' ou 'true' "
            + e.getMessage());
        } 
              
        return data;
    }
}
