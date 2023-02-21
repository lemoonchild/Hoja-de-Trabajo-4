package Controller;
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 

/**
 * @author 
 * Madeline Castro 22743 
 * Hoja de Trabjo #4 
 * Clase tomada de la Hoja de Trabajo #2 - Grupo 7
 * Propósito: Leer el archivo txt 
 */

public class readFile {

/**
 * Metodo que lee el documento con las operaciones Infix y guarda los datos dentro de ArrayList 
 * @param fpath Mensaje de lugar donde se encuentra el archivo datos.txt 
 * @return data String
 */
public String _readfile(String fpath) {

  String data = ""; 

    try {

        File myObj = new File(fpath);
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNextLine()) {

          data = myReader.nextLine();

        }
        myReader.close();

      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    return data; 
  }
}