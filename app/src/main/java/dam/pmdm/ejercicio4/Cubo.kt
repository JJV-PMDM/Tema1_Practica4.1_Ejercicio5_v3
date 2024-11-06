package dam.pmdm.ejercicio4

class Cubo(val l:Double) : FiguraGeometrica{
    override fun calcularArea(): Double {
        return 6 * Math.pow(l, 2.0)
    }

    override fun calcularVolumen(): Double {
        return Math.pow(l, 3.0)
    }

}