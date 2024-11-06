package dam.pmdm.ejercicio4

class Cilindro(val r:Double, val h:Double):FiguraGeometrica {
    override fun calcularArea(): Double {
        return 2 * Math.PI * r * (r + h)
    }

    override fun calcularVolumen(): Double {
        return Math.PI * Math.pow(r, 2.0) * h
    }
}