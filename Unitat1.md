# Pràctica 1
## Anàlisi de l'estructura del projecte
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

## Análisi del clicle de vida i el problema de la pèrdua d'estat
## Solució a la pèrdua d'estat
## Ampliant la funcionalitat amb decrements i Reset
## Canvis per implementar el View Binding
