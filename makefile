# Makefile para el proyecto de aproximación al número pi

# Definición de variables
P = Principal
MAIN_CLASS = aplicacion.$(P)
SRC_DIR = ./src
OUT_DIR = bin
LIB_DIR = ./lib
DOC_DIR = html
JAR_FILE = $(P).jar

# Compilar el proyecto
compilar: limpiar
    mkdir $(OUT_DIR)
    find $(SRC_DIR) -name *.java | xargs javac -cp $(OUT_DIR):$(LIB_DIR) -d $(OUT_DIR)

# Crear el archivo JAR
jar: compilar
    jar cvfm $(JAR_FILE) manifest.txt -C $(OUT_DIR) .

# Ejecutar el programa
ejecutar: compilar
    java -cp $(OUT_DIR) $(MAIN_CLASS) 1000000

# Limpiar los archivos generados
limpiar:
    rm -rf $(OUT_DIR)
    rm -rf $(DOC_DIR)
    rm -f $(JAR_FILE)

# Generar la documentación Javadoc
javadoc: compilar
    find . -type f -name "*.java" | xargs javadoc -d $(DOC_DIR) -encoding utf-8 -docencoding utf-8 -charset utf-8

# Compilar en modo debug
debug: compilar
    find $(SRC_DIR) -name *.java | xargs javac -g -cp $(OUT_DIR):$(LIB_DIR) -d $(OUT_DIR)
    cd $(OUT_DIR); jdb -sourcepath ../src

# Ejecutar el archivo JAR
runjar: jar
    @echo "Manifest-Version: 1.0" > manifest.txt
    @echo "Main-Class: $(MAIN_CLASS)" >> manifest.txt
    @echo "Class-Path: . " >> manifest.txt
    @echo "" >> manifest.txt
    java -jar $(JAR_FILE)
