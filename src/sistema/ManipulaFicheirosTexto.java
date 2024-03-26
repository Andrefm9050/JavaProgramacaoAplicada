package sistema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class ManipulaFicheirosTexto {

	  // aceder ficheiros
	  File ficheiroLeitura;

	  //leitura ficheiro
	  FileReader fr;
	  BufferedReader br;

	  //escrita ficheiro
	  FileWriter fw;
	  BufferedWriter bw;
	  
	  public boolean abrirFicheiroLeitura(String aCaminho) {

	    if(aCaminho != null && aCaminho.length() >0) {
	      ficheiroLeitura = new File(aCaminho);

	      if(!ficheiroLeitura.exists()) {
	    	  try {
				ficheiroLeitura.createNewFile();
			} catch (IOException e) {
				System.out.println("Houve um erro ao criar o ficheiro " + aCaminho);
				return false;
			}
	      }
	        try {
	          fr = new FileReader(ficheiroLeitura);
	          br = new BufferedReader(fr);
	          //br = new BufferedReader(new FileReader( new File(aCaminho)) );
	          return true;
	        } catch (IOException ioe) {
	          ioe.printStackTrace();
	        }
	      
	    }
	    return false;
	  }

	  public boolean abrirFicheiroEscrita(String aCaminho, boolean aAppend) {
	    if(aCaminho != null && aCaminho.length() >0) {
	      /*
	      if(!ficheiroEscrita.exists()) {
	        ficheiroEscrita.createNewFile();
	      }
	      */
	      
	        try {
	          fw = new FileWriter(aCaminho, aAppend);
	          //bw = new BufferedWriter(fw);
	          //br = new BufferedReader(new FileReader( new File(aCaminho)) );
	          return true;
	        } catch (IOException ioe) {
	          ioe.printStackTrace();
	        }
	      
	    }
	    return false;
	  }

	  
	  public boolean fecharFicheiroLeitura() {
	    try{
	      if (br != null) {
	        br.close();
	      }
	      if (fr != null) {
	        fr.close();
	      }
	      return true;
	    }catch (IOException ioe) {
	      ioe.printStackTrace();
	    }
	    return false;
	  }

	  public boolean fecharFicheiroEscrita() {
	    try{
	      if (bw != null) {
	        bw.close();
	      }
	      if (fw != null) {
	        fw.close();
	      }
	      return true;
	    }catch (IOException ioe) {
	      ioe.printStackTrace();
	    }
	    return false;
	  }  

	  public Vector<String> lerFicheiro() {

	    if(br != null) {

	      String linha ="";
	      Vector <String> conteudo = new Vector<String>();
	      do {
	        try{
	          linha = br.readLine();
	          conteudo.addElement(linha);
	        } catch(IOException ioe) {
	          ioe.printStackTrace();
	          return null;
	        }

	      } while (linha != null);   
	      return conteudo;
	      
	    }
	    return null;
	  }

	  public boolean escreverFicheiro(String aConteudo) {

	    if(fw != null) {
	          try {
	            fw.write( aConteudo );
	            return true;
	          } catch(IOException ioe) {
	            ioe.printStackTrace();
	            return false;
	          }
	    }
	    return false;
	    
	  }
}
