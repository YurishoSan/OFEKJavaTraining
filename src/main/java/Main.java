import java.io.File;
import java.util.Scanner;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption and decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class Main {
    // Enums -----------------------------------------------------------------------------------------------------------

    /**
     * Enum of choices a user can make in the selection menu
     */
        private enum ChoiceEnum {
        ENCRYPT,    //encrypt the file
        DECRYPT     //decrypt the file
    }
    // Static Functions ------------------------------------------------------------------------------------------------

    /**
     * Prints the credits for the project
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
     * Gets the path of the file to encrypt/decrypt
     *
     * If the file name is illegal or no file exists the user will be requested to input the file name again
     *
     * @return path of file to encrypt/decrypt
     */
    public static String GetFilePath() {
        /*
        GetFilePath pseudo code
            do
                print ("Enter File Path:")
                filePath <- input()

                if (filePath is illegal path)
                    error <- "Illegal file path"

                elseif (file at filePath does not exist or is directory)
                    error <- "file at path does not exist or is a directory"

                if (error != "")
                    print (error)
                    print ("Please Try again.")
                    filePath <- ""

            while (filePath == "")

            return filePath
         */

        String filePath; //path of file to get from user
        String error = ""; //error string

        Scanner reader = new Scanner(System.in); // input reader

        do {
            File file;

            System.out.println("Enter File Path:");
            filePath = reader.nextLine();
            file = new File(filePath);

            //check if filePath is an illegal path
            if (!FileUtils.isFilenameValid(filePath))
                error = "Illegal file path";


            //check if file doesn't exist or is a directory
            else if (!file.exists() || file.isDirectory())
                error = "file at path does not exist or is a directory";

            if (error != "") {
                System.out.println(error);
                System.out.println("Please Try again.");
                filePath = "";
                error = "";
            }
        } while (filePath == "");

        return filePath;
    }

    /**
     * Pause execution until ENTER is pressed
     *
     * see: http://codewithdesign.com/2010/01/17/create-a-press-enter-to-continue-with-java/
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

            filePath <- GetFilePath()

            switch (choice)
                case ENCRYPT:
                    encryptor <- new Encryptor(filePath)
                    encryptor.Encrypt()
                break
                case DECRYPT:
                    decryptor <- new Decryptor(filePath)
                    decryptor.Decrypt()
                break
        */

        PrintCredits();

        ChoiceEnum choice = SelectionMenu();
        String filePath = GetFilePath();

        switch (choice) {
            case ENCRYPT:
                Encryptor encryptor = new Encryptor(filePath);
                encryptor.Encrypt();
                break;
            case DECRYPT:
                Decryptor decryptor = new Decryptor(filePath);
                decryptor.Decrypt();
                break;
            default:
                System.out.println("unsupported choice");
        }

        pauseProg();
    }
}
