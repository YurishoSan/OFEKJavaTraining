import java.util.Scanner;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption and decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 1.1
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
            print ("Encryptor program made by Yitzhak Goldstein @ OFEK for Atuda prep. training 2016.
                This program encrypts and decrypts files.
            ")
         */

        System.out.println("Encryptor program made by Yitzhak Goldstein @ OFEK for Atuda prep. training 2016.");
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
        GetFilePath pseudo code
            do
                print ("Enter File Path:")
                filePath <- input()

                encryptionFunction.SetFilePath(filePath)

                if (encryptionFunction.GetFilePath() == "")
                    print ("Illegal file path or file at path does not exist or is a directory")
                    print ("Please Try again.")
            while (encryptionFunction.GetFilePath() == "")

            return filePath
         */

        String filePath; //path of file to get from user
        String error = ""; //error string

        Scanner reader = new Scanner(System.in); // input reader

        do {
            System.out.println("Enter File Path:");
            filePath = reader.nextLine();

            encryptionFunction.SetFilePath(filePath);

            if (encryptionFunction.GetFilePath().equals("")) { //error
                System.out.println("Illegal file path or file at path does not exist or is a directory");
                System.out.println("Please Try again.");
            }
        } while (encryptionFunction.GetFilePath().equals(""));
    }

    /**
     * Pause execution until ENTER is pressed
     *
     * see: http://codewithdesign.com/2010/01/17/create-a-press-enter-to-continue-with-java/
     *
     * @since 1.0
     */
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
                    encryptionFunction <- new Encryptor()
                break
                case DECRYPT:
                    encryptionFunction <- new Decryptor()
                break

            filePath <- GetFilePath(encryptionFunction)

            encryptionFunction.preformFunction();
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

        encryptionFunction.RunFunction();

        pauseProg();
    }
}
