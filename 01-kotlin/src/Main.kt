import java.util.*

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