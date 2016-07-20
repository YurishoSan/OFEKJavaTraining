/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption and decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 0.1
 */
public class Encryptor {
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
    public static char SelectionMenu() {
        /*
        SelectionMenu pseudo code
            do
                Output ("Please choose functionality:
                    1) [E]ncrypt a file
                    2) [D]ecrypt a file
                 ")
                 choice <- input()
            while (choice != 1,2,E or D)

            convert choice to choiceEnum of type ChoiceEnum as such:
                ENCRYPT if choice is '1' or 'E'
                DECRYPT if choice is '2' or 'D'

            return choice
         */
    }

    /**
     * Gets the path of the file to encrypt/decrypt
     *
     * If the file name is illegal or no file exists the user will be requested to input the file name again
     *
     * @return path of file to encrypt/decrypt
     */
    public static String GetFileName() {
        /*
        GetFileName pseudo code
            do
                print ("Enter File Path")
                filePath <- input()

                if (filePath is illegal path)
                    error <- "Illegal file path"

                elseif (file at filePath does not exist)
                    error <- "file at path does not exist"

                if (error != "")
                    print (error)
                    print ("Please Try again")
                    filePath <- ""

            while (filePath != "")

            return filePath
         */
    }

    /**
     * mock encryption of file.
     * prints that the file is being encrypted.
     * @param FilePath path of file to encrypt
     */
    public static void Encrypt(String FilePath) {
        /*
        Encrypt pseudo code
            print("encryption simulation of file " + FileName")
         */
    }

    /**
     * mock decryption of file.
     * prints that the file is being decrypted.
     * @param FilePath path of file to encrypt
     */
    public static void Decrypt(String FilePath) {
        /*
        Encrypt pseudo code
            print("decryption simulation of file " + FileName")
         */
    }

    // Main ------------------------------------------------------------------------------------------------------------

    public static void main(String args[]) {
        /*
        main pseudo code
            PrintCredits()

            choice <- SelectionMenu()

            filePath <- GetFileName()

            switch (choice)
                case ENCRYPT:
                    Encrypt(fileName)
                break
                case DECRYPT:
                    Decrypt(fileName)
                break
        */
    }
}
