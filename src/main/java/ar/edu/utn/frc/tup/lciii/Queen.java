package ar.edu.utn.frc.tup.lciii;

import java.util.*;
import java.util.stream.StreamSupport;

/*Se le dará un tablero de ajedrez cuadrado con una reina y varios obstáculos colocados en él. Determina cuántos cuadrados puede atacar la reina.

Una reina está de pie sobre un tablero de ajedrez n x n. Las filas del tablero de ajedrez están numeradas del 1 al n, de abajo hacia arriba. Sus columnas están numeradas del 1 al n, de izquierda a derecha. Cada cuadrado está referenciado por una tupla (r, c), que describe la fila r y la columna c, donde se encuentra el cuadrado.

La dama está de pie en la posición (rq, cq). En un solo movimiento, puede atacar cualquier casilla en cualquiera de las ocho direcciones (izquierda, derecha, arriba, abajo y las cuatro diagonales). En el siguiente diagrama, los círculos verdes indican todas las celdas desde las que la reina puede atacar (4, 4):*/
public class Queen {

    /*
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */
    public Integer queensAttack(Integer n, Integer k, Integer r_q, Integer c_q, List<List<Integer>> obstacles) {
        // Write your code here

        int[][] tablero = new int[n][n];

        tablero[r_q - 1][c_q - 1] = 1; //posiciono a la reina en el tablero con 1

        for (int i = 0; i < k; i++) {  //posiciono los obstaculos en el tablero con -1
            //ArrayList<Integer> obst = new ArrayList<Integer>();
            int positionRow = obstacles.get(i).get(0); //checked
            int positionColumn = obstacles.get(i).get(1); //checked
            tablero[positionRow - 1][positionColumn - 1] = (-1);//checked ----------------------------------------------------
        }

        int count = 0;
        count = crossAxis(tablero,c_q,r_q,count,n);
        count = mainAxis(tablero,c_q,r_q,count,n);
        count = diagonal(tablero,c_q,r_q,count,n);


        return count;
    }
    private static Integer crossAxis(int[][] tablero,Integer c_q,Integer r_q,Integer count,Integer n){
        ArrayList<Integer> obstaculosFila = new ArrayList<Integer>();
        int maxArray = tablero.length;
        for (int i = 0; i < (maxArray); i++) { //recorro cross-axis de la reina y capturo obstaculos para caluclar
            if ((tablero[i][c_q - 1]) != 1) { //
                if ((tablero[i][c_q - 1]) == -1) {
                    obstaculosFila.add(i); // 0,1,2,3
                }
            }
        }
        Collections.sort(obstaculosFila);
        if (obstaculosFila.size() < 2) {
            try{
                if(r_q>obstaculosFila.get(0)){
                    int suma = n - (obstaculosFila.get(0)+1);
                    suma -= 1; //resto la posicion q ocupa la reina
                    count += suma;

                } else if (r_q < obstaculosFila.get(0)) {
                    int result = obstaculosFila.get(0) - 1; //
                    count+=result;
                }
            }catch (Exception ex){
                count += n - 1;
            }

            //int result = 2;
            //count = maxArray - result;
        } else {

            int ultimoMenor = 1000;
            int ultimoMayor = 0;
            for (int i = 0; i < obstaculosFila.size(); i++) {

                if (obstaculosFila.get(i) > ultimoMayor) {
                    ultimoMayor = obstaculosFila.get(i);
                }
                if (obstaculosFila.get(i) < ultimoMenor) {
                    ultimoMenor = obstaculosFila.get(i);
                }
            }

            int difArriba = (ultimoMayor - 1) - (r_q - 1);
            int difAbajo = (r_q - 1) - (ultimoMenor + 1);

            count += difAbajo;
            count += difArriba;
        }
        // hasta acá están posicionados los obstaculos y se realizo la funcion del cross-axis
        return count;
    }
    private static Integer mainAxis(int[][] tablero,Integer c_q,Integer r_q,Integer count, Integer n){
        ArrayList<Integer> obstaculosCol = new ArrayList<Integer>();
        int maxArray = tablero.length;
        for (int i = 0; i < (maxArray); i++) { //recorro main-axis de la reina y capturo obstaculos para caluclar
            if ((tablero[r_q - 1][i]) != 1) { //
                if ((tablero[r_q - 1][i]) == -1) {
                    obstaculosCol.add(i); // 0,1,2,3
                }
            }
        }
        Collections.sort(obstaculosCol);
        if (obstaculosCol.size() < 2) {
            try{
                if(r_q>obstaculosCol.get(0)){
                    int suma = n - (obstaculosCol.get(0)+1);
                    suma -= 1; //resto la posicion q ocupa la reina
                    count += suma;

                } else if (r_q < obstaculosCol.get(0)) {
                    int result = c_q - 1; //r_q para la izquierda ya tiene el total que puede moverse, se resta 1 por la reina.
                    count+=result;
                }
            }catch (Exception ex){
                count += n - 1;
            }

            //int result = 2;
            //count = maxArray - result;
        } else {

            int ultimoMenor = 1000;
            int ultimoMayor = 0;
            for (int i = 0; i < obstaculosCol.size(); i++) {

                if (obstaculosCol.get(i) > ultimoMayor) {
                    ultimoMayor = obstaculosCol.get(i);
                }
                if (obstaculosCol.get(i) < ultimoMenor) {
                    ultimoMenor = obstaculosCol.get(i);
                }
            }

            int difArriba = (ultimoMayor - 1) - (c_q - 1);
            int difAbajo = (c_q - 1) - (ultimoMenor + 1);

            count += difAbajo;
            count += difArriba;
        }
        // hasta acá están posicionados los obstaculos y se realizo la funcion del main-axis
        return count;
    }

    private static Integer diagonal(int[][] tablero,Integer c_q,Integer r_q,Integer count, Integer n){
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;

        for (int i = 1; i < n;i++){
            if(!flag1 || !flag2 || !flag3 || !flag4){

                if (!flag1 ) { //from 0° to 90°
                    try{
                        if((tablero[(r_q-1)+i][(c_q-1)+i] == -1 )){ //el tablero se inicia con -1 de los datos de entrada ya que va de 0 a n
                            flag1=true;
                        }
                        else {
                            count++;
                        }
                    }catch (Exception ex){
                        flag1=true;
                    }
                }

                if(!flag2){ //from 90° to 180°
                    try{
                        if(tablero[(r_q-1)+i][(c_q-1)-i] == -1 ){
                            flag2=true;
                        }
                        else {
                            count++;
                        }
                    }catch (Exception ex){
                        flag2=true;
                    }

                }
                if(!flag3){ //from 180° to 270°
                    try {
                        if(tablero[(r_q-1)-i][(c_q-1)-i] == -1 ){
                            flag3=true;
                        }
                        else {
                            count++;
                        }
                    }catch (Exception ex){
                        flag3=true;
                    }

                }
                if(!flag4){ //from 270° to 360°
                    try {
                        if(tablero[(r_q-1)-i][(c_q-1)+i] == -1 ){
                            flag4=true;
                        }
                        else {
                            count++;
                        }
                    }catch (Exception ex){
                        flag4=true;
                    }
                }
            }
            else {
                break;
            }
        }
        return count;
    }

}




