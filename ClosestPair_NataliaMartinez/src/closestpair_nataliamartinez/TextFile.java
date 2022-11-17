/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closestpair_nataliamartinez;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author natymartinez04
 */
public class TextFile {
    
    public void create (String nombre){
	// creates a file
		try{
			// defines the filename
			String fname = (nombre);
			// creates a new File object
			File f = new File (fname);

			String msg = "creating file `" + fname + "' ... ";
			System.out.println();
			System.out.printf("%s", msg);
			// creates the new file
			f.createNewFile();
			System.out.println("done");
		}
		catch (IOException err)
		{
			// complains if there is an Input/Output Error
			err.printStackTrace();
		}

		return;
    }
    
    public void writeTime(String nombre, Long[] tiempo, Integer iteraciones,Long[] comparaciones){
            //writes the amount of coordinates, the number of comparisons and time into a file.
            try
		{
                        create(nombre);
			// defines the filename
			String filename = (nombre);
			// creates new PrintWriter object for writing file
			PrintWriter out = new PrintWriter (filename);
                        for (int i = 0; i< iteraciones; i++){
                            out.printf((int) Math.pow(2, i+3)+"    "+comparaciones[i]+"    "+tiempo[i]+"\n"); 
                        }
			System.out.printf("closing file ... ");
			out.close();	// closes the output stream
			System.out.println("done");
		}
		catch (FileNotFoundException err){
			// complains if file does not exist
			err.printStackTrace();
		}
		return;
    }
}

