==========================================================================
MANUAL INSTALLATION PROYECT MODULO DE EXTRACCION DE INFORMACION 
==========================================================================

CONTENIDO:

1.- CONOCIMIENTOS PREVIOS.
2.- REQUERIMIENTOS DEL SISTEMA.
3.- INSTALACI�N MAQUINA VIRTUAL DE JAVA.
4.- INSTALACI�N DE MySQL Command Line Client.
5.- CONFIGURACI�N DE LAS VARIABLES DE ENTORNO JAVA.
6.- CREACI�N DE LA BASE DE DATOS.
7.- CONFIGURACI�N DEL ARCHIVO Config.txt.
8.- IMPORTACION DEL PROYECTO A UN IDE DE PROGRAMACION
9.- EJECUCI�N DEL PROGRAMA "FindRelations".
10.- IMPORTACION A LA GUI GATE


==========================================================================

1.-CONOCIMIENTOS PREVIOS

1.1.- Tener el conocimiento basico para la ejecucion
de un programa con la consola de comandos del sistema
operativo.

1.2.- Tener el conocimiento basico para la creacion
de una base de datos por medio de la consola de comandos
de MYSQL.

1.3.- Tener conocimientos sobre instalaci�n de software.

==========================================================================

2.- REQUERIMIENTOS DEL SISTEMA:

El equipo de trabajo deber� contener el siguiente
software para la correcta ejecuci�n del software
proporcionado.

2.1.- MAQUINA VIRTUAL DE JAVA (Java Platform) cualquier versi�n.

2.2.- MySQL Command Line Client (cualquier versi�n).

2.3.- TENER CONFIGURADAS LA VARIABLES DE ENTORNO DEL SISTEMA PARA JAVA

2.4.- TENER EL ARCHIVO "Archrecovery.sql" PARA LA CREACI�N DE 
      LA BASE DE DATOS.

2.5.- TENER INSTALADO ALGUN ID DE PROGRAMACION (NETBEANS, ECLIPSE, ETC.)

==========================================================================

3.- INSTALACI�N MAQUINA VIRTUAL DE JAVA.

3.1.- Descargar la version de JAVA del sitio oficial.
 http://www.oracle.com/technetwork/java/javase/downloads/index.html

3.2.- Seguir las instrucciones de instalacion de la maquina
 virtual de java.

==========================================================================

4.- INSTALACI�N DE MySQL Command Line Client.

4.1.- Descargar la version de MYSQL del sitio oficial.
 http://dev.mysql.com/downloads/mysql/

4.2.- Seguir las instrucciones de instalacion de MYSQL.

==========================================================================

5.- CONFIGURACI�N DE LAS VARIABLES DE ENTORNO JAVA

5.1.- Seleccionar la variable llamada PATH localidada en las 
propiedades del sistema y agregar la direcci�n del jdk de java,
esta se encuentra localizada en las carpetas donde se instalo 
JAVA, ejemplo:

 "../midirectory/jdk1.6.04/bin/"

==========================================================================

6.- CREACI�N DE LA BASE DE DATOS.

6.1.- Copiar el archivo "Archrecovery.sql" dentro del directorio donde se
      encuentra ubicado la instalacion de MySQL ".../MySQL Server/bin", la 
      cual se encuentra ubicada dentro de los archivos de instalacion del 
      sistema.
      
6.2.- Abrir la consola de comandos de MYSQL (en caso de tener
      contrase�a, introducir la contrase�a de usuario de
      MYSQL).

6.3.- Una vez copiado el archivo "Archrecovery.sql" dentro de la
      carpeta bin, escibir la siguiente instrucci�n para crear la 
      base de datos:
      
     " \. Archrecovery.sql"

      O digitar el comando "source" seguido de la direccion donde
      se encuentra copiado el archivo.
      Ejemplo
      
      source c:\$directorio$\ArchRecovery.sql;
      
6.4.- Verificar que nuestra base de datos ha sido creada con la
      siguiente Instrucci�n.
      "show databases;"
      
Nota: Este comando mostrara las bases de datos existentes, aqui
      deber� aparecer una llamada "Archrecovery".

==========================================================================

7.- CONFIGURACI�N DEL ARCHIVO Config.txt.
     
     El archivo de configuracion "Configure.txt" que se encuentra
     en la carpeta del proyecto y sirve es para modificar el usuario, contrase�a
     que se tiene de mysql y tambien la ruta donde se encuentra la carpeta
     principal de los archivos del sistema para analizar.
     
     Ejemplo:
     
     User=root
     password=frank
     AddressFile=file:\C:\Users\Frank\Documents\tallerandroid\TwitterReader
     Databasename=ArchRecovery
      
     �ADVERTENCIA!
     NO AGREGAR ESPACIOS O COMAS.
     NO BORRAR EL NOMBRE DE LA BASE DE DATOS.
     SEGUIR EL FORMATO DE LA DIRECCION DEL ARCHIVO, INIDCANDO LAS CAPETAS CON "\" NO CON "/"
     LA DIRECCION DEBE TENER ANTERIORMENTE "file:\" PARA NO CREAR ERRORES.

==========================================================================
8.- IMPORTACION DEL PROYECTO A UN IDE

8.1.- Abrir el IDE de programaci�n deseado.

8.2.- A�adir la carpeta del proyecto al espacio de trabajo donde tenemos
      todos nuestros proyectos, es decir, nuestro "WorkSpace".

8.3.- Dentro de la barra de herramientas del editor localizar la opcion de 
      "importar proyecto".

8.4.- Aparecer� una ventana que nos preguntar� si el proyecto se encuentra
      localizado en el directorio de trabajo por default, si es asi entonces
      a�adimos la ruta del "WorkSpace", en caso de que no a�adimos la direccion
      donde tenemos el proyecto.

8.5.- Para finalizar la importaci�n seleccionamos la opcion de "Finalize" de
      la ventana que nos ha mostado el IDE.

8.6.-Si el proyecto no compila, entonces ser� necesario realizar las siguientes
     acciones:
     
     8.6.1.- Dentro del IDE de programacion seleccionar el proyecto con boton derecho
             y seleccionar la opcion  de propiedades.
     
     8.6.2.- Nos aparecer� una nueva ventana en la cual tendremos que buscar la opcion de
            "Run".
     
     8.6.3.- Aparecer� una nueva ventana en la cual deberemos verificar que la opcion de
             "Main class" no se encuentre vacia, en caso de ser asi seleccionar la clase
             principal del proyecto llamada "Main.java, en este caso se encuentra 
             ubicada dentro del directorio:
             
             "FindRelations/src/code/run/Main.java".

8.7.- Dar clic en el boton de "Run"

8.8.- Una vez compilado el proyecto deberan aparecer algunos mensajes en la consola de ejecucion
      del IDE.
      
             
  
==========================================================================
9.- EJECUCI�N DEL PROGRAMA "RelationS".

9.1.- Abrir la Consola de comandos del sistema operativo.

9.2.- Posicionarnos en la direcci�n donde se encuentra
      ubicado el archivo "RelationShip.jar"

9.3.- Escribir la siguiente instrucci�n para ejecutar un
      archivo ".jar".
      "java -jar RelationShip.jar".

9.4.- Abrir la consola de comandos de MYSQL.

9.5.- Seleccionar la base de datos que creamos con la siguiente 
      instrucci�n:
      "Use Archrecovery;"

9.6.- Mostrar los resultados obtenidos con la siguiente instrucci�n:
      "select * from class_relations;"

==========================================================================

1O.-IMPORTACION A LA GUI GATE

10.1.- Consultar el documento a�andido en la carpeta del proyecto 
	con el nombre de Importacion a la GUI.docx.


