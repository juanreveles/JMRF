package Code.Run;

import java.io.File;
import java.net.URL;

import gate.Corpus;
import gate.Factory;
import gate.Gate;
//import gate.util.*;
import gate.LanguageAnalyser;
import gate.creole.SerialAnalyserController;
import gate.util.ExtensionFileFilter;
/** EL SIGUIENTE CODIGO PERMITIRA REALIZAR EL ANALISIS NECESARIO PARA ARCHIVOS CON EXTENSION .JAVA CONTENIDO EN LA VARIABLE "filter"*/
public class GateRunning {
	public URL directoryFile =null;
	public ExtensionFileFilter filter=null;
	public SerialAnalyserController controller=null;
	public Corpus corpus=null; 
	public File   aPluginDir=null;
	public File pluginsDir=null;
	/**EL SIGUIENTE CONSTRUCTOR PERMITE INICIALIZAR LA VARIABLE DIRECTORIO CON LA RUTA DIRECTORIO*/
	public GateRunning(String directoryFile) {
		// TODO Auto-generated constructor stub
		try {
			
			this.directoryFile=new URL(directoryFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("ERROR: THE URL IS NOT VALID "+e.getMessage());
		}
		
	}
	
	/**ESTE MEDOTO CONTIENE LAS LLAMADAS A LOS METODOS QUE PERMITEN PROCESO DEL ANALISIS*/
	public void executeAnalisis(String exfilter,String nameCorpus) {
		Start();
		pluginload();
		createProcers();
		createFilter(exfilter);
		createCorpus(nameCorpus);
		run();
	}
	/**EL METODO Start INICIA EL PROCESO DEL ANALISIS MANDANDO LLAMAR LA APLICACION DE LA API DE GATE.*/
	public void Start(){
		try{
			Gate.init();
			System.out.println("Starting Analiser...");
			//MainFrame.getInstance().setVisible(true);
		}
		catch(Exception e){
			System.err.println("ERROR: START GATE.INIT "+ e.getMessage());
                        e.printStackTrace();
			
		}
	}
	/**ESTE METODO ES UTILIZADO PARA CARGAR EL DIRECTORIO QUE CONTIENE LA DIRECION DEL PLUGIN A UTILZIAR*/
	public void pluginload(){
		try{
			System.out.println("Loading Plugins....");
		 pluginsDir = Gate.getPluginsHome();/**ASIGNAMOS EL DIRECTORIO HOME QUE CONTIENE LOS RECURSOS POR DEFAULT GATE*/
		 // Lets load the ANNIE plugin
		 aPluginDir = new File(pluginsDir, "ANNIE");/**CARGAMOS EL PLUGIN ANNIE ASIGNADO POR DEFAULT DE GATE*/
		  // load the plugin.
		 Gate.getCreoleRegister().registerDirectories(
		 aPluginDir.toURI().toURL());
		}catch(Exception e){
			System.err.println("ERROR: START PLUGIN "+e.getMessage());
		}
	}
		
	/**CON EL METODO Create process  UTILIZAMOS LOS RECURSOS QUE SE UTILIZARAN EN EL PROCESO DE ANALISIS (GAZETTER,
	 TOKENIZER, TRANSDUCER Y EL DOCUMENTRESERT) QUE SE ENCUENTRA EN EL PLUGIN ANTERIORMENTE INSTANCIADO*/
	public  void createProcers(){
		 // create tokenizer
		System.out.println("Starting processes...");
		try{
		 LanguageAnalyser reset = (LanguageAnalyser)Factory.createResource( "gate.creole.annotdelete.AnnotationDeletePR");
		 LanguageAnalyser tokenizer = (LanguageAnalyser)Factory.createResource( "gate.creole.tokeniser.DefaultTokeniser");
		 LanguageAnalyser gazetter = (LanguageAnalyser)Factory.createResource( "gate.creole.gazetteer.DefaultGazetteer");
		 LanguageAnalyser reglas = (LanguageAnalyser)Factory.createResource( "gate.creole.ANNIETransducer");
		 
		// create serialAnalyzerController
		 controller =(SerialAnalyserController) Factory.createResource(
		  "gate.creole.SerialAnalyserController");
		 
		 // add pr to the controller
		 /**CREAMOS UN RECURSO LLAMADO CONTROLLER QUE CONTENDRA TODOS LOS RECURSOS CREADOS ANTERIORMENTE
		PARA DESPUES COMPILARLOS DE MANERA ORDENADA*/
		 controller.add(reset); 
		 controller.add(tokenizer);
		 controller.add(gazetter);
		 controller.add(reglas);
		}catch(Exception e){
			System.err.println("ERROR: STARTING PROCERS "+e.getMessage());
		}
	}
	/**ESTE METODO PERMITE CREAR ELE FILTRO DE LOS ARCHIVOS QUE SE DESEAN ANALIZAR EJMPLO (.TXT, .JAVA...) ESTE HACE REFERENCIA ALA VARIBLE FILTER*/
	public void createFilter(String filtro){
		System.out.println("Creating filterls...");
		 filter = new ExtensionFileFilter ("javafiles",filtro);/**DEFINE LA EXTENSION DE LOS ARCHIVOS*/
		
	}
	/**ESTE METODO PERMITE CREAR UN ARCHIVO QUE CONTENDRA LA ESTRUCTURA DEL PROYECTO*/
	public void createCorpus(String name ){
		try{
			System.out.println("Creating corpus...");
			 corpus = Factory.newCorpus(name);/**CREACION DEL ARCHIVO QUE CONTIENE TODA LA ESTRUCTURA DEL PROYECTO*/
		  corpus.populate(directoryFile, filter, null, true);/**IMPLEMENTEACION DEL FILTRO DE ARCHIVOS DESEADOS*/
		}catch(Exception e){
			System.err.println("ERROR: CREATE CORPUS "+e.getMessage());
			e.printStackTrace();
		}
	}
	/**EL SIGUIENTE METODO SE ENCARGA DE CORRER LA APLICACION CON LAS REGLAS QUE ANTERIORMENTE FUERON CARGADAS*/
	public void run(){
		try{
			System.out.println("Executing Analiser....");
		  controller.setCorpus(corpus); /**ESTABLECE LAS CARACTERISTICAS AL PROYECTO CREADO*/
		  controller.execute();/**EJECUTA LA APLICACION PARA COMPILAR LOS RECURSOS*/
		}catch(Exception e){
			System.err.println("ERROR: TO EXECUTE CONTROLLER "+e.getMessage());
		};
	}
	

}
