package dam.pmdm.ejercicio4

class Esfera(val r:Double):FiguraGeometrica {
    override fun calcularArea(): Double {
        return 4 * Math.PI * Math.pow(r, 2.0)
    }

    override fun calcularVolumen(): Double {
        return (4/3) * Math.PI * Math.pow(r, 3.0)
    }
}