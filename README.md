# **Proyecto: Implementación de Grafos en Java**

Este proyecto implementa una estructura de grafo dirigida utilizando una lista de adyacencia. También incluye métodos para añadir vértices, crear arcos y encontrar el camino más corto entre dos nodos mediante búsqueda en anchura (**BFS**). Además, contiene una clase de prueba (`App`) para validar la ejecución del código.

## **Estructura del Proyecto**

El código está organizado en los siguientes paquetes:

- **`util`** → Contiene la clase `Graph`, encargada de representar el grafo y proporcionar diversas operaciones sobre él.
- **`pr2`** → Contiene la clase `App`, una aplicación sencilla que imprime `"Hello World!"`.

## **Clases**

### **Graph**
La clase `Graph<V>` representa un grafo dirigido genérico utilizando una lista de adyacencia basada en un `Map<V, Set<V>>`.

#### **Principales métodos**
- **`addVertex(V v)`** → Agrega un vértice al grafo.
- **`addEdge(V v1, V v2)`** → Crea un arco dirigido de `v1` a `v2`.
- **`obtainAdjacents(V v)`** → Devuelve el conjunto de vértices adyacentes a `v`.
- **`containsVertex(V v)`** → Verifica si el grafo contiene un vértice específico.
- **`shortestPath(V v1, V v2)`** → Encuentra el camino más corto entre `v1` y `v2` usando búsqueda en anchura (**BFS**).
- **`toString()`** → Genera una representación textual de la lista de adyacencia.

### **App**
La clase `App` se encuentra en el paquete `pr2` y contiene un método `main` que imprime `"Hello World!"`, funcionando como una prueba de ejecución básica.

#### **Método principal**
```java
public static void main(String[] args) {
    System.out.println("Hello World!");
}