/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ph.pkg6;

/**
 *
 * @author jarro
 */
public class ComprobarCadena {
    
    
    public boolean valida(String cadena){
    boolean valida=true;
    cadena=cadena.toLowerCase();
    if((cadena.contains("?")&&cadena.length()>31)||cadena.contains("sr")||cadena.startsWith("r")||cadena.contains("a")||cadena.contains("b")||cadena.contains("c")||cadena.contains("d")||cadena.contains("g")||cadena.contains("h")||cadena.contains("i")||cadena.contains("j")||cadena.contains("l")||cadena.contains("n")||cadena.contains("ñ")||cadena.contains("o")||cadena.contains("p")||cadena.contains("q")||cadena.contains("u")||cadena.contains("v")||cadena.contains("w")||cadena.contains("x")||cadena.contains("y")||cadena.contains("z"))
    valida=false;
    
    
    return valida;
    }
}
