# Pràctica 1 - App Comptador
## 1. Anàlisi de l'estructura del projecte
El que tenim és un projecte de Android, que està seguent desenvolupat en el llenguatge Kotlin, des de l'entorn Android Studio.

Conté una *Activitat* amb un *TextView*, que mostra un valor de comptador, i un *Botó*. Al pulsar-lo, el valor del comptador que es mostra s'incrementarà en una unitat cada vegada.

Els arxius més importants, que podem destacar dins l'estructura de arxius del projecte, es mostren a la següent imatge. A continuació passe a comentar els més destacables:

<img width="1243" alt="image" src="https://github.com/user-attachments/assets/3fa32c06-ffe7-48ae-b335-28f8023feacc">

1. Les **Activitats** (app -> src -> main -> java -> com): en un projecte d'Android Studio serien les pantalles o interfícies que un usuari veurà. Cada activitat representa una acció o pantalla específica dins de l'aplicació. Quan obrim la app, per exemple, ens està mostrant la pantalla inicial amb el valor del comptador i el botó d'increment, i açò ja és una activitat.

    Cada activitat té el seu propi cicle de vida, amb etapes com ara onCreate() (quan es crea), onResume() (quan es mostra) i onDestroy() (quan es tanca). També poden comunicar-se entre elles utilitzant intents, per passar informació o obrir altres pantalles.

2. Els **Layouts** (app -> src -> main -> res -> layout): Sería la definició dels elements visuals, com per exemple botons, text, imatges, etc, que apareixeran en una pantalla o activitat. Mostren la distribució d'eixos components i com se alineen entre ells per a mostrar-se a la interfície d'usuari.

   Els layouts gestionen la interacció entre l'usuari i l'aplicació i es defineixen en fitxers XML, com podem veure a la imatge anterior

3. El **AndroidManifest.xml** (app -> src -> main -> res): És on es declara la informació bàsica de l'aplicació.

    - Les activitats, serveis i components que formaran part de l'aplicació
    - Els permisos que es necessitaran
    - La activitat principal que es llançarà quan comence l'aplicació
    - Versions mínimes d'Android que es requereixen

Una *Activitat* té la següent estructura:

* **Package**: Per a especificar el paquet al que pertany la classe de l'activitat.
* **Import**: Per a incluir clases i components que no estan en eixe paquet i als que l'activitat necessita accedir.
* Implementació de la **Classe** propiament:
    - Declaració dels **Atributs**
    - Implementació dels **Mètodes**
 
Crear una *Activitat* implica:

* Crear el fitxer **Kotlin** amb la classe
* Crear el fitxer **Layout** amb els components visuals que necessitem que apareguen
* Declarar eixa Activitat dins el fitxer **AndroidMainfest.xml**

Per tant, **no** seria suficient comptar amb eixos dos fitxers que indicava l'enunciat, si la activitat no es declara també dins del Manifest.
    
## 2. Análisi del cicle de vida i el problema de la pèrdua d'estat

El cicle de vida defineix els diferents estats pels quals passa, des de que es crea fins que es tanca.

Cada estat pel que passem, desencadena un mètode que es crida automàticament quan entrem en ell. Els que podem destacar són:

- onCreate(): Que es posa en marxa quan es crea l'activitat.
- onStart(): En este cas, seria quan es fa visible l'activitat per al usuari.
- onResume(): Quan l'activitat comença a estar en primer pla i es pot interactuar amb ella.
- onPause(): Es posaria en marxa just abans de estar oculta.
- onStop(): En este cas, se executa quan ja ha passat a ser no visible per al usuari.
- onDestroy(): Este mètode es posa en marxa just abans de tancar completament l'activitat.

Per a poder-ho analitzar, el que farem és implementar cadascun d'eixos mètodes amb un missatge que s'imprimirà al LogCat. D'esta manera podrem veure per quins estats passa la nostra aplicació


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Al mètode onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Al mètode onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Al mètode onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Al mètode onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Al mètode onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Al mètode onDestroy")
    }

El que s'observa és que amb un canvi de configuració, com ara el gir de pantalla, Android destrueix l'activitat i la torna a crear per tal de adaptar-se a eixe canvi. Aleshores, els valors dels atributs, com és el valor del comptador en el nostre cas, es perden i torna a partir del valor inicial, que era zero.

És a dir, l'activitat, passa per:

* onCreate() -> onStart() -> onResume()
* En eixe moment, canviem la configuració, mitjançant el gir de pantalla, i passa per:
* onPause() -> onStop() -> onDestroy()

<img width="185" alt="image" src="https://github.com/user-attachments/assets/b2af4276-6c25-4516-a32b-a695f1f11684">

<img width="350" alt="image" src="https://github.com/user-attachments/assets/b7a8e2cf-d681-4f4a-87d7-735b59ad5258">

Això és algo que a les nostres aplicacions hem de poder gestionar per tal de que es mantinguen els valors que necessitem conservar.

## 3. Solució a la pèrdua d'estat

La solució a la pèrdua d'estat radica en fer ús del **Bundle**.

El bundle ens permet *guardar* l'estat d'una activitat just abans de destruir-la, i també ens permet restaurar l'estat quan es torna a recrear l'activitat.

Per a poder-ho aconseguir he hagut de crear estos dos mètodes. El primer és el que ens guardarà l'estat. *Estat* serà el bundle que guardarà el valor en format de clave-valor. Com a clave li pose el nom "Comptador" i el valor que adquirirà eixa clau és el valor que en eixe moment tinga el nostre comptador.

Quan l'activitat es torne a recrear, es posarà en marxa eixe segon mètode que restablirà el atribut *comptador* al valor que teníem en el valor de la clau del Bundle:

    override fun onSaveInstanceState(estat: Bundle) {
        super.onSaveInstanceState(estat)
        estat.putInt("Comptador", comptador)
    }

    override fun onRestoreInstanceState(estat: Bundle) {
        super.onSaveInstanceState(estat)
        comptador = estat.getInt("Comptador")
        val textViewContador=findViewById<TextView>(R.id.textViewComptador)
        textViewContador.text=comptador.toString()
    }

Eixes últimes dos línees de codi, fan falta per a *refrescar* el valor en pantalla. De no posar-les, la variable comptador era restablida, però no t'adonaves fins que pulsaves algun dels botons per a canviar el valor del comptador:

        val textViewContador=findViewById<TextView>(R.id.textViewComptador)
        textViewContador.text=comptador.toString()

Amb la primera línea, asigne a eixa variable la referència a l'element *TextView* en que mostrem el valor del comptador. Amb la segona línea actualitze el valor que mostra eixte *TextView* amb el valor del comptador recuperat. 

## 4. Ampliant la funcionalitat amb decrements i Reset

Per tal d'implementar eixa funcionalitat nova, cal actualitzar el *Layout* per tal de mostrar eixos nous dos botons. Afegim per tant el següent codi al XML del Layout. Els elements que tindriem aleshores serien:

<TextView
        android:id="@+id/textViewComptador"
        android:layout_width="338dp"
        android:layout_height="276dp"
        android:fontFamily="sans-serif-black"
        android:textAlignment="center"
        android:textSize="178sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.255" />

    <Button
        android:id="@+id/btResta"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="-"
        android:textSize="34sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/btReset"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textViewComptador" />

    <Button
        android:id="@+id/btReset"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="0"
        android:textSize="34sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/btAdd"
        app:layout_constraintStart_toEndOf="@+id/btResta"
        app:layout_constraintTop_toBottomOf="@+id/textViewComptador" />

    <Button
        android:id="@+id/btAdd"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="+"
        android:textSize="34sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/btReset"
        app:layout_constraintTop_toBottomOf="@+id/textViewComptador" />

    <Button
        android:id="@+id/btOpen"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Open Activity"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/btReset" />

Els pas següent seria tindre unes variables que feren referència als nous botons del Layout:

        // Referencia al botón de restar, añadido
        val btResta=findViewById<Button>(R.id.btResta)
        // Referencia al botón Reset añadido
        val btReset=findViewById<Button>(R.id.btReset)

I ja per a acabar, caldria implementar els dos nous mètodes, el de restar una unitat al comptador i el de resetejar el valor del comptador i establir-lo a zero de nou:

        // Asociaciamos una expresión lambda como
        // respuesta (callback) al evento Clic sobre
        // el botón
        btResta.setOnClickListener {
            comptador--
            textViewContador.text=comptador.toString()
        }

        // Asociaciamos una expresión lambda como
        // respuesta (callback) al evento Clic sobre
        // el botón
        btReset.setOnClickListener {
            comptador = 0
            textViewContador.text=comptador.toString()
        }

Ara ja tenim la aplicació funcionant amb l'increment, decrement i reseteig, i tot el funcionament és correcte independentment de si canviem la configuració fent ús del gir d'orientació de vertical a horitzontal o viceversa.

## 5. Canvis per implementar el View Binding

Els passos a seguir serien els següents:

1. Abans de res, hem de Activar el ViewBinding a les buildFeatures de l'script Gradle del mòdul, afegint este bloc:

    buildFeatures {
        viewBinding = true
    }

<img width="725" alt="image" src="https://github.com/user-attachments/assets/1e1882b7-6fbf-42ab-815d-708b75fad214">

2. Sincronitzar el projecte amb aquest script, per a que genere les classes de vinculació. Seleccionem la següent opció dins de Android Studio:

<img width="270" alt="image" src="https://github.com/user-attachments/assets/673fb8be-ab0a-41ce-a4c0-95c6ceb73a17">

3. Importar la classe de vinculació en el fitxer de la classe on l'anem a utilitzar. Per a lo qual, dins de la *MainActivity* afegirem la línea per a importar-la:

        import com.pmdm.ieseljust.comptador.databinding.ActivityMainBinding






