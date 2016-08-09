import encryption.*;
import encryption.algorithms.*;
import encryption.algorithms.BasicAlgorithm;
import encryption.design.observer.EventTypesEnum;

import java.time.Clock;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption and decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 4.0
 */
public class Main {
    // Enums -----------------------------------------------------------------------------------------------------------

    /**
     * Enum of choices a user can make in the selection menu
     * @since 1.0
     */
        private enum ChoiceEnum {
        ENCRYPT,    //encrypt the file
        DECRYPT     //decrypt the file
    }
    // Static Functions ------------------------------------------------------------------------------------------------

    /**
     * Prints the credits for the project
     * @since 1.0
     */
    private static void PrintCredits() {
        /*
        PrintCredits pseudo code
            print ("encryption.Encryptor program made by Yitzhak Goldstein @ OFEK for Atuda prep. training 2016.
                This program encrypts and decrypts files.
            ")
         */

        System.out.println("encryption.Encryptor program made by Yitzhak Goldstein @ OFEK for Atuda prep. training 2016.");
        System.out.println("This program encrypts and decrypts files.");
    }

    /**
     * Prints a menu of possible actions and return the action chosen by the user.
     *
     * Actions can be chosen ether by typing the option's number or the letter marked between square brackets ('[]')
     *
     * If an unrecognized action is requested, the user will be requested to choose again.
     *
     * @since 1.0
     * @return User's chosen action from the menu
     */
    public static ChoiceEnum SelectionMenu() {
        /*
        SelectionMenu pseudo code
            do
                Output ("Please choose functionality:
                    1) [E]ncrypt a file
                    2) [D]ecrypt a file
                 ")
                 choice <- input()
            while (choice != 1,2,E or D)

            if (choice = '1' or 'E' or 'e')
                return ChoiceEnum.ENCRYPT
            if (choice = '2' or 'D' 'd')
                return ChoiceEnum.DECRYPT
         */

        char choice; // the character the user chose.

        Scanner reader; // input reader

        do {
            System.out.println("Please choose functionality:");
            System.out.println("\t1) [E]ncrypt a file");
            System.out.println("\t2) [D]ecrypt a file");

            reader = new Scanner(System.in);
            choice = reader.next().charAt(0);
        } while (choice != '1' && choice != '2'&&
                choice != 'E' && choice != 'D' &&
                choice != 'e' && choice != 'd');

        if (choice == '1' || choice == 'E' || choice == 'e')
            return ChoiceEnum.ENCRYPT;
        else // choice == '2' || choice == 'D' || choice == 'd'
            return ChoiceEnum.DECRYPT;

    }

    /**
     * Sets the path of the file to encrypt/decrypt
     *
     * If the file name is illegal or no file exists the user will be requested to input the file name again
     *
     * @since 1.1
     */
    public static void SetFilePath(EncryptionFunction encryptionFunction) {
        /*
        SetFilePath pseudo code
            do
                print ("Enter File Path:")
                filePath <- input()

                encryptionFunction.SetFilePath(filePath)

                if (encryptionFunction.GetFilePath() == "")
                    print ("Illegal file path or file at path does not exist or is a directory")
                    print ("Please try again.")
            while (encryptionFunction.GetFilePath() == "")
         */

        String filePath; //path of file to get from user

        Scanner reader = new Scanner(System.in); // input reader

        do {
            System.out.println("Enter File Path:");
            filePath = reader.nextLine();

            encryptionFunction.setFilePath(filePath);

            if (encryptionFunction.getFilePath().equals("")) { //error
                System.out.println("Illegal file path or file at path does not exist or is a directory");
                System.out.println("Please try again.");
            }
        } while (encryptionFunction.getFilePath().equals(""));
    }

    /**
     * Sets the key for the encryption/decryption
     *
     * If not a number the user will be requested to input the file name again
     *
     * @since 2.3
     */
    private static char GetKey(ChoiceEnum choice) {
        /*
        GetKey pseudo code
            switch(choice)
                case ENCRYPT:
                    key <- Random()
                    output(key)
                    break

                case DECRYPT:
                    while(true)
                        print ("Enter Key:")
                        key <- input()
                    if (key can be converted to byte)
                         break
                    else
                        print("Key is not a byte")
                        print("Please try again")
                    break

            return key
         */

        Random rnd = new Random();
        char key = 0;

        switch (choice) {
            case ENCRYPT:
                key = (char)rnd.nextInt(EncryptionFunction.BYTE_MAX_VALUE+1);
                System.out.println("key: " + (int)key);
                break;

            case DECRYPT:
                Scanner scanner;
                while(true) {
                    System.out.println("Enter key:");
                    try {
                        scanner = new Scanner(System.in);
                        key = (char)scanner.nextInt();
                        if (key > EncryptionFunction.BYTE_MAX_VALUE)
                            throw new IllegalArgumentException("key must be between 0 and " + EncryptionFunction.BYTE_MAX_VALUE);

                        break; //good key found, exit while loop
                    }
                    catch (IllegalArgumentException | InputMismatchException exp) {
                        System.out.println("key must be a number in range 0-" + EncryptionFunction.BYTE_MAX_VALUE + ", please try again:");
                    }
                }
                break;
        }

        return key;
    }

    private static ObservableEncryptionAlgorithmDecorator GetAlgorithm(ChoiceEnum function) {
        return GetAlgorithm(function, false);
    }

    /**
     * Sets the algorithm for the encryption/decryption
     *
     * @since 2.3
     */
    private static ObservableEncryptionAlgorithmDecorator GetAlgorithm(ChoiceEnum function, boolean reversed) {
         /*
        SetAlgorithmType pseudo code
            do
                Output ("Please choose algorithm:
                    0) [N]one
                    1) [C]aesar
                    2) [X]or
                    3) [M]ultiplication

                    4) [D]ouble
                    5) [R]everse
                    6) [S]plit
                 ")
                 choice <- input()
            while (choice != 0..6 or N,C,X,M,D,R,S or n,c,x,m,d,r,s)

            if chosen double or split
                algo1 <- SetAlgorithm(..)
                algo2 <- SetAlgorithm(..)

            if chosen reverse
                decorate the algorithm with the opposite decorator

            decorate the algorithm with the appropriate decorator
        */
        char choice;
        Scanner reader; // input reader

        ObservableEncryptionAlgorithmDecorator algo1;
        ObservableEncryptionAlgorithmDecorator algo2;
        do {
            System.out.println("Please choose algorithm:");
            System.out.println("\t0) [N]one");
            System.out.println("\t1) [C]aesar");
            System.out.println("\t2) [X]or");
            System.out.println("\t3) [M]ultiplication");
            System.out.println("\n");
            System.out.println("\t4) [D]ouble");
            System.out.println("\t5) [R]everse");
            System.out.println("\t6) [S]plit");

            reader = new Scanner(System.in);
            choice = reader.next().charAt(0);
        } while ((choice < '0' || choice > '6')&&
                choice != 'N' && choice != 'C' && choice != 'X' &&choice != 'M' &&
                choice != 'D' && choice != 'R' && choice != 'S' &&
                choice != 'n' && choice != 'c' && choice != 'x' && choice != 'm' &&
                choice != 'd' && choice != 'r' && choice != 's');

        char key=0;
        // only ask for key if a proper algorithm and not using other algorithm
        if ((choice < '4' || choice > '6')&&
                choice != 'D' && choice != 'R' && choice != 'S' &&
                choice != 'd' && choice != 'r' && choice != 's')
            if(!reversed) {
                key = GetKey(function);
            } else {
                if(function == ChoiceEnum.ENCRYPT)
                    key = GetKey(ChoiceEnum.DECRYPT);
                else
                    key = GetKey(ChoiceEnum.ENCRYPT);
            }

        switch (function) {
            case ENCRYPT:
                switch (choice) {
                    case '0':
                    case 'N':
                    case 'n':
                        return new NoneEncryptionAlgorithmDecorator(new BasicAlgorithm(), key);
                    case '1':
                    case 'C':
                    case 'c':
                        return new CaesarEncryptionAlgorithmDecorator(new BasicAlgorithm(), key);
                    case '2':
                    case 'X':
                    case 'x':
                        return new XorEncryptionAlgorithmDecorator(new BasicAlgorithm(), key);
                    case '3':
                    case 'M':
                    case 'm':
                        //fix key
                        if (key % 2 == 0) {
                            key += 1;
                            System.out.println("key fixed to: " + (int)key);
                        }
                        return new MultiplicationEncryptionAlgorithmDecorator(new BasicAlgorithm(), key);
                    case '4':
                    case 'D':
                    case 'd':
                        System.out.println("please choose two algorithms to use in the double:");
                        algo1 = GetAlgorithm(function);
                        algo2 = GetAlgorithm(function);
                        return new DoubleAlgorithmDecorator(algo1, algo2);
                    case '5':
                    case 'R':
                    case 'r':
                        System.out.println("please choose algorithm to use in reverse:");
                        return GetAlgorithm(ChoiceEnum.DECRYPT, true);
                    case '6':
                    case 'S':
                    case 's':
                        System.out.println("please choose two algorithms to use in the split:");
                        algo1 = GetAlgorithm(function);
                        algo2 = GetAlgorithm(function);
                        return new SplitAlgorithmDecorator(algo1, algo2);
                }
                break;
            case DECRYPT:
                switch (choice) {
                    case '0':
                    case 'N':
                    case 'n':
                        return new NoneDecryptionAlgorithmDecorator(new BasicAlgorithm(), key);
                    case '1':
                    case 'C':
                    case 'c':
                        return new CaesarDecryptionAlgorithmDecorator(new BasicAlgorithm(), key);
                    case '2':
                    case 'X':
                    case 'x':
                        return new XorDecryptionAlgorithmDecorator(new BasicAlgorithm(), key);
                    case '3':
                    case 'M':
                    case 'm':
                        return new MultiplicationDecryptionAlgorithmDecorator(new BasicAlgorithm(), key);
                    case '4':
                    case 'D':
                    case 'd':
                        System.out.println("please choose two algorithms to use in the double:");
                        algo1 = GetAlgorithm(function);
                        algo2 = GetAlgorithm(function);
                        return new DoubleAlgorithmDecorator(algo1, algo2);
                    case '5':
                    case 'R':
                    case 'r':
                        System.out.println("please choose algorithm to use in reverse:");
                        return GetAlgorithm(ChoiceEnum.ENCRYPT, true);
                    case '6':
                    case 'S':
                    case 's':
                        System.out.println("please choose two algorithms to use in the split:");
                        algo1 = GetAlgorithm(function);
                        algo2 = GetAlgorithm(function);
                        return new SplitAlgorithmDecorator(algo1, algo2);
                }
                break;
        }

        return null;
    }

    /**
     * Pause execution until ENTER is pressed
     *
     * see: http://codewithdesign.com/2010/01/17/create-a-press-enter-to-continue-with-java/
     *
     * @since 1.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static void pauseProg(){
        System.out.println("Press enter to continue...");
        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();
    }

    // Main ------------------------------------------------------------------------------------------------------------

    public static void main(String args[]) {
        /*
        main pseudo code
            PrintCredits()

            choice <- SelectionMenu()

            switch (choice)
                case ENCRYPT:
                    encryptionFunction <- new encryption.Encryptor()
                break
                case DECRYPT:
                    encryptionFunction <- new encryption.Decryptor()
                break

            SetFilePath(encryptionFunction)
            SetKey(encryptionFunction, choice)
            SetAlgorithm(encryptionFunction, choice)

            try decoratedEncryptionFunction.run(),
                and in case of exception print exception info
        */

        EncryptionFunction encryptionFunction; // object for encryption function to preform
        PrintCredits();

        ChoiceEnum choice = SelectionMenu();

        switch (choice) {
            case ENCRYPT:
                encryptionFunction = new Encryptor();
                break;
            case DECRYPT:
                encryptionFunction = new Decryptor();
                break;
            default:
                System.out.println("unsupported choice");
                pauseProg();
                return;
        }

        SetFilePath(encryptionFunction);
        encryptionFunction.setAlgorithm(GetAlgorithm(choice));

        EncryptionEventListener encryptionEventListener = new EncryptionEventListener(Clock.systemUTC());
        encryptionFunction.getAlgorithm().register(encryptionEventListener, EventTypesEnum.FUNCTION_START);
        encryptionFunction.getAlgorithm().register(encryptionEventListener, EventTypesEnum.FUNCTION_END);

        encryptionFunction.run();

        pauseProg();
    }
}
