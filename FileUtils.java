/*
File utilities for reading and writing

@author Pranav Avadhanam
@since August 25, 2022
*/
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;//substitutes "PrintWriter" with "java.io.PrintWriter"
public class FileUtils
{
        /*
        * opens a file to read using the Scanner class
        * @param fileName     name of the file to open
        * @return             the Scanner object to the file
        */

        public static java.util.Scanner openToRead(String FileName)
        {
                java.util.Scanner input = null;
                //error-handling
                try
                {
                        input = new java.util.Scanner(new java.io.File(FileName));
                }
                //refer to API's!
                catch(java.io.FileNotFoundException e)
                {
                        System.err.println("ERROR. Cannot open " + FileName + " for reading.");
                        System.exit(-1);//throw non-zero exit code - error
                }
                return input;
        }
        /*&
        * Opens a file to write using the PrintWriter class
        * @param fileName              name of file to open
        * @return                                              the PrintWriter object to the file
        * */
        public static PrintWriter openToWrite(String FileName)
        {
                PrintWriter output = null;
                try
                {
                        output = new PrintWriter(new File(FileName));
                }
                catch(FileNotFoundException e)
                {
                        System.err.println("ERROR: Cannot open " + FileName + " for writing.");
                        System.exit(-1);
                }
                return output;
        }
}