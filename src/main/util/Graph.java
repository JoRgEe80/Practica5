package main.util;

import java.util.Set;
import java.util.Stack;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class Graph<V>{

    //Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade el vértice `v` al grafo.
     *
     * @param v vértice a añadir.
     * @return `true` si no estaba anteriormente y `false` en caso contrario.
     */
    public boolean addVertex(V v){
        
        if(adjacencyList.containsKey(v)){
            
            return false;
        }else{
        
            adjacencyList.put(v, new HashSet<V>());
            return true; 
        }
    }

    /**
     * Añade un arco entre los vértices `v1` y `v2` al grafo. En caso de
     * que no exista alguno de los vértices, lo añade también.
     *
     * @param v1 el origen del arco.
     * @param v2 el destino del arco.
     * @return `true` si no existía el arco y `false` en caso contrario.
     */
    public boolean addEdge(V v1, V v2){
        
        this.addVertex(v1);
        this.addVertex(v2);
        
        if(adjacencyList.get(v1).contains(v2)){

            return false;

        }else{
        
        adjacencyList.get(v1).add(v2);
        return true; 

        }
    }

    /**
     * Obtiene el conjunto de vértices adyacentes a `v`.
     *
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes.
     */
    public Set<V> obtainAdjacents(V v) throws Exception{
        
        return adjacencyList.get(v); 
    }

    /**
     * Comprueba si el grafo contiene el vértice dado. 
     *
     * @param v vértice para el que se realiza la comprobación.
     * @return `true` si `v` es un vértice del grafo.
     */
    public boolean containsVertex(V v){
        
        if(adjacencyList.containsKey(v)){
            
            return true;
        }else{
            return false;
        }
    }

    /**
     * Método `toString()` reescrito para la clase `Grafo.java`.
     * @return una cadena de caracteres con la lista de adyacencia.
     */
    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<V, Set<V>> entrada : adjacencyList.entrySet()) {
            
            sb.append(entrada.getKey()).append(" : ");
            Set<V> adyacentes = entrada.getValue();

            for( V vertice : adyacentes){

                sb.append(vertice).append(" , ");
            }
            sb.append("\n");
        }

        return sb.toString(); 
    }

    /**
     * Obtiene, en caso de que exista, el camino más corto entre
     * `v1` y `v2`. En caso contrario, devuelve `null`.
     * 
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices del camino más corto
     * entre `v1` y `v2`
     **/
    public List<V> shortestPath(V v1, V v2){
       
        ArrayList<V> visitados = new ArrayList<V>();
        ArrayDeque<V> proximo = new ArrayDeque<V>();
        HashMap<V,V> recorrido = new HashMap<V,V>();
        
        proximo.add(v1);



        while(!proximo.isEmpty()){

            V actual = proximo.pop();
            if(!visitados.contains(actual)){
                try{
                    if(this.obtainAdjacents(actual).contains(v2)){

                        Stack<V> recorridoInverso = new Stack<V>();
                        recorridoInverso.add(v2);
                        V llave = actual;

                        while(recorrido.containsKey(llave)){
                            recorridoInverso.add(llave);
                            llave = recorrido.get(llave);
                        }   
                        recorridoInverso.add(v1);

                        ArrayList<V> listaOrdenada = new ArrayList<V>();
                        while(!recorridoInverso.isEmpty()){
                        
                            listaOrdenada.add(recorridoInverso.pop());
                        }
                        return listaOrdenada;
                    } 

                    HashSet<V> lista = (HashSet<V>) this.obtainAdjacents(actual);
                
                    for(V vertice : lista){
                    
                        proximo.add(vertice);
                        recorrido.put(vertice,actual); 
                    } 
             
                    visitados.add(actual);
                } catch (ClassCastException e) {
                    System.out.println("Error realizando en cast a HashSet: " + e.getMessage());
                } catch (NullPointerException e) {
                    System.out.println("NullPointerException: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Ha ocurrido un error imprevisto: " + e.getMessage());
                    e.printStackTrace(); 
                }
            }
        }
        return null; 
    }
}