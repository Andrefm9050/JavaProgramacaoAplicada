package sistema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;


/**
 * Classe responsavel por abrir e ler/escrever ficheiros de forma facilitada
 * @author Andre Rios
 */
public class ManipulaFicheirosTexto {

	  // aceder ficheiros
	  File ficheiroLeitura;

	  //leitura ficheiro
	  FileReader fr;
	  BufferedReader br;

	  //escrita ficheiro
	  FileWriter fw;
	  BufferedWriter bw;
	  
	  /**
	   * Funcao para abrir ficheiro e prepara-lo para leitura
	   * @param aCaminho - caminho de ficheiro
	   * @return true se o ficheiro foi aberto para leitura
	   */
	  public boolean abrirFicheiroLeitura(String aCaminho) {

	    if(aCaminho != null && aCaminho.length() >0) {
	      ficheiroLeitura = new File(aCaminho);
	      Path path = Path.of(aCaminho);
	      //System.out.println(path);
	      if(!Files.exists(path)) {
	    	  
	    	  return false;
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

	  /**
	   * @param aCaminho - caminho do ficheiro
	   * @param aAppend - modo de ficheiro append
	   * @return true se o ficheiro foi aberto para escrita do modo selecionado
	   */
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

	  /**
	   * @return todas as linhas do ficheiro carregado anteriormente, devolve null se acontecer algum erro
	   */
	  public String[] lerFicheiro() {

	    if(br != null) {

	      String linha ="";
	      Vector <String> conteudo = new Vector<String>();
	      do {
	        try{
	          linha = br.readLine();
	          
	          if(linha != null)
	          conteudo.addElement(linha);
	          
	        } catch(IOException ioe) {
	          ioe.printStackTrace();
	          return null;
	        }

	      } while (linha != null);   
	      return conteudo.toArray(new String[0]);
	      
	    }
	    return null;
	  }

	  public boolean escreverFicheiro(String[] aConteudo) {

	    if(fw != null) {
	          try {
	        	  for(var linha : aConteudo)
	        		  fw.write( linha + "\n");
	            return true;
	          } catch(IOException ioe) {
	            ioe.printStackTrace();
	            return false;
	          }
	    }
	    return false;
	    
	  }
}
