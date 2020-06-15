import java.util.*
import kotlin.collections.ArrayList

fun main(args:Array<String>){
    print("Loool")
    //Int edad = 31;
    //variables mutables.. usar lo menos posible
    //usar nombres de var descriptivos
    var edad = 31   // No se especifica el tipo de dato
    //; no necesario

    //Duck Typing
    //var edaeCachorro; X->necesitams el tipo de dato
    var edadCachorro:Int;
    edadCachorro =3;
    //====================================
    //variables inmutables.. usar siempre
    val numeroCuenta = 12345 //NO SE PUEDEN REASIGNAR

    val nombreProfesor: String = "Vivente Adrian"
    val sueldo: Double = 12.20
    val apellido : Char ='a';
    val fechaNacimiento = Date()

    when (sueldo){
        12.20 -> print("Sueldo normal")
        -12.20 -> print("Sueldo negativo prro")
        else -> print("No se reconoce el sueldo")
    }

    val esSueldoMayorAlEstablecido = if(sueldo ==12.20) true else false

    //calcularSueldo(1000.00, 14.00)
    //calcularSueldo(tasa=16.00, sueldo = 800.00)//parametros nombrados
    val arregloConstante: Array<Int> = arrayOf(1,2,3)

    val arregloDinamico: ArrayList<Int> = arrayListOf(30,31,20,29)
    print(arregloDinamico)
    arregloDinamico.add(12)
    print(arregloDinamico)
    arregloDinamico.remove(30)
    print(arregloDinamico)

    arregloDinamico
            .forEach{
                println("Valoracion de la iteracion " + it)
            }

    arregloDinamico
            .forEach{valorIteracion: Int ->
                println("Valor Iteracion:" + valorIteracion)

            }

    arregloDinamico
            .forEach({valorIteracion: Int ->
                println("Valor Iteracion:" + valorIteracion)

            })

    arregloDinamico
            .forEachIndexed{ index: Int, it:Int ->
                println("Valor de la iteración"+it)

            }

    //Operadores--MAP
    val aux: List<Int> = arregloDinamico
            .map {
                val nuevoValor = it*-1
                val otroValor = nuevoValor*2
                return@map otroValor
            }
    println("Map "+aux)

    //OPERADORES--Filter
    val filtrados = arregloDinamico
            .filter { it>25 }
    println("Filter " +filtrados)

    val filtrados2 = arregloDinamico
            .filter {
                val esMayor23 = it>23
                return@filter esMayor23
            }
}



fun calcularSueldo(
        sueldo : Double,//requeridos
        tasa: Double = 12.00,//tiene valor por defecto
        calculoEspecial : Int?//OPCIONAL!!!(pueden ser nulos)
): Double {
    if (calculoEspecial!= null){
        return sueldo * tasa * calculoEspecial;
    }else{
        return sueldo * tasa
    }

}

fun imprimirMensaje():Unit{//Unit = void
    println("")
}