import encryption.*;
import encryption.algorithms.*;
import encryption.algorithms.BasicAlgorithm;
import encryption.design.observer.EventTypesEnum;

import java.io.*;
import java.time.Clock;
import java.util.*;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption and decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 5.0
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

    /**
     * Enum of different modes of operation
     * @since 5.0
     */
    private enum ModeEnum {
        SINGLE,
        SYNC,
        ASYNC
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

    private static char SetKey(Queue<Key> keys) {
        /* SetKey pseudo code
            key <- Random()
            output(key)
            return key
         */
        Random rnd = new Random();
        char key = 0;

        key = (char)rnd.nextInt(EncryptionFunction.BYTE_MAX_VALUE+1);
        System.out.println("key: " + (int)key);
        keys.add(new Key(key));
        return key;
    }

    /**
     * Sets the key for the encryption/decryption
     *
     * If not a number the user will be requested to input the file name again
     *
     * @since 2.3
     */
    private static char GetKey(Queue<Key> keys) {
        /*
        GetKey pseudo code
            while(true)
                print ("Enter Key:")
                key <- input()
            if (key can be converted to byte)
                break
            else
                print("Key is not a byte")
                print("Please try again")

            return key
         */

        /*char key = 0;

        Scanner scanner;
        while (true) {
            System.out.println("Enter key:");
            try {
                scanner = new Scanner(System.in);
                key = (char) scanner.nextInt();
                if (key > EncryptionFunction.BYTE_MAX_VALUE)
                    throw new IllegalArgumentException("key must be between 0 and " + EncryptionFunction.BYTE_MAX_VALUE);

                break; //good key found, exit while loop
            } catch (IllegalArgumentException | InputMismatchException exp) {
                System.out.println("key must be a number in range 0-" + EncryptionFunction.BYTE_MAX_VALUE + ", please try again:");
            }
        }

        return key;*/

        char key = keys.remove().getKey();
        if (key > EncryptionFunction.BYTE_MAX_VALUE)
            throw new IllegalArgumentException("key must be between 0 and " + EncryptionFunction.BYTE_MAX_VALUE);
        return key;
    }

    private static ObservableEncryptionAlgorithmDecorator GetAlgorithm(ChoiceEnum function, Queue<Key> keys) {
        return GetAlgorithm(function, keys, false);
    }

    /**
     * Sets the algorithm for the encryption/decryption
     *
     * @since 2.3
     */
    private static ObservableEncryptionAlgorithmDecorator GetAlgorithm(ChoiceEnum function, Queue<Key> keys, boolean reversed) {
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
                if (function == ChoiceEnum.ENCRYPT)
                    key = SetKey(keys);
                else
                    key = GetKey(keys);
            } else {
                if(function == ChoiceEnum.ENCRYPT)
                    key = GetKey(keys);
                else
                    key = SetKey(keys);
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
                        algo1 = GetAlgorithm(function, keys);
                        algo2 = GetAlgorithm(function, keys);
                        return new DoubleAlgorithmDecorator(algo1, algo2);
                    case '5':
                    case 'R':
                    case 'r':
                        System.out.println("please choose algorithm to use in reverse:");
                        return GetAlgorithm(ChoiceEnum.DECRYPT, keys, true);
                    case '6':
                    case 'S':
                    case 's':
                        System.out.println("please choose two algorithms to use in the split:");
                        algo1 = GetAlgorithm(function, keys);
                        algo2 = GetAlgorithm(function, keys);
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
                        algo1 = GetAlgorithm(function, keys);
                        algo2 = GetAlgorithm(function, keys);
                        return new DoubleAlgorithmDecorator(algo2, algo1);
                    case '5':
                    case 'R':
                    case 'r':
                        System.out.println("please choose algorithm to use in reverse:");
                        return GetAlgorithm(ChoiceEnum.ENCRYPT, keys, true);
                    case '6':
                    case 'S':
                    case 's':
                        System.out.println("please choose two algorithms to use in the split:");
                        algo1 = GetAlgorithm(function, keys);
                        algo2 = GetAlgorithm(function, keys);
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

            mode <- SelectMode()
            choice <- SelectionMenu()
            switch (mode)
                case SINGLE:
                    DoSingleFile(choice)
                    break;
                case SYNC:
                    DoSync(choice)
                    break;
                case ASYNC:
                    DoAsync(choice)
                    break;


        */
        PrintCredits();

        ChoiceEnum choice = SelectionMenu();
        DoSingleFile(choice);

        pauseProg();
    }

    private static void DoSingleFile(ChoiceEnum choice) {
        /*
        DoSingleFile pseudo code

            switch (choice)
                case ENCRYPT:
                    encryptionFunction <- new encryption.Encryptor()
                break
                case DECRYPT:
                    encryptionFunction <- new encryption.Decryptor()
                break

            SetFilePath(encryptionFunction)
            keys = PrepareKeysQueue(choice, filePath);
            encryptionFunction.setAlgorithm(GetAlgorithm(choice, keys));
            FinishKeysQueue(choice, filePath, keys);

            add event listeners
            try decoratedEncryptionFunction.run(),
                and in case of exception print exception info
         */
        EncryptionFunction encryptionFunction = null; // object for encryption function to preform

        switch (choice) {
            case ENCRYPT:
                encryptionFunction = new Encryptor();
                break;
            case DECRYPT:
                encryptionFunction = new Decryptor();
                break;
        }

        SetFilePath(encryptionFunction);
        try {
            Queue<Key> keys = PrepareKeysQueue(choice, encryptionFunction.getFilePath());
            encryptionFunction.setAlgorithm(GetAlgorithm(choice, keys));
            FinishKeysQueue(choice, encryptionFunction.getFilePath(), keys);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        EncryptionEventListener encryptionEventListener = new EncryptionEventListener(Clock.systemUTC());
        encryptionFunction.getAlgorithm().register(encryptionEventListener, EventTypesEnum.FUNCTION_START);
        encryptionFunction.getAlgorithm().register(encryptionEventListener, EventTypesEnum.FUNCTION_END);

        encryptionFunction.run();
    }

    private static void DoSync(ChoiceEnum choice) {
        /* DoSync pseudo code
            directory <- setDirectory()

            keys = PrepareKeysQueue(choice, filePath);
            encryptionFunction.setAlgorithm(GetAlgorithm(choice, keys));
            FinishKeysQueue(choice, filePath, keys);

            for each file in directory and not sub directories
                encryptionFunction.setFilePath(file.name)

                add event listeners
                try decoratedEncryptionFunction.run(),
                    and in case of exception print exception info
         */

    }

    private static void DoAsync(ChoiceEnum choice) {
        /* DoSync pseudo code
            directory <- setDirectory()

            keys = PrepareKeysQueue(choice, filePath);
            encryptionFunction.setAlgorithm(GetAlgorithm(choice, keys));
            FinishKeysQueue(choice, filePath, keys);

            for each file in directory and not sub directories
                encryptionFunction.setFilePath(file.name)

                add event listeners
                try run new thread using decoratedEncryptionFunction.run(),
                    and in case of exception print exception info
         */
    }

    private static ModeEnum SelectMode() {
        /* ModeEnum pseudo code
            do
                print("Please select mode:
                    [1] Single [F]ile
                    [2] Directory - [S]ync
                    [3] Directory - [A]sync
                ")

                mode <- input ()
            while (mode != 1..3, 'F', 'f', 'S', 's', 'A', 'a')

            return mode
         */
    }

    private static Queue<Key> PrepareKeysQueue(ChoiceEnum choice, String filePath) throws IOException, ClassNotFoundException {
        File keyFile = new File(filePath.substring(0, filePath.lastIndexOf('\\'))+ "\\key.bin");
        switch (choice) {
            case ENCRYPT:
                return new ArrayDeque<>();
            case DECRYPT:
                ObjectInputStream keyReader = new ObjectInputStream(new FileInputStream(keyFile));
                Queue<Key> keys = (Queue<Key>) keyReader.readObject();
                keyReader.close();
                return keys;
        }
        return null;
    }

    private static void FinishKeysQueue(ChoiceEnum choice, String filePath, Queue<Key> keys) throws IOException {
        File keyFile = new File(filePath.substring(0, filePath.lastIndexOf('\\'))+ "\\key.bin");
        switch (choice) {
            case ENCRYPT:
                if (!keyFile.exists())
                    //noinspection ResultOfMethodCallIgnored
                    keyFile.createNewFile();
                ObjectOutputStream keyWriter = new ObjectOutputStream(new FileOutputStream(keyFile));
                keyWriter.writeObject(keys);
                keyWriter.close();
                break;
            case DECRYPT:
                break;
        }
    }
}
